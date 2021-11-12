package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener {

    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentContact = new Contact();

        initToggleButton();
        initListButton();
        initSettingsButton();
        initMapButton();
        initChangeDateButton();
        initSaveButton();
        initTextChangeEvents();
        setForEditing(false);
    }

    public void onResume()
    {
        super.onResume();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if(name!=null)
        {
            EditText nameEdit = findViewById(R.id.editContact);
            nameEdit.setText(name);
        }
    }

    private void initListButton() {
        Button listButton = findViewById(R.id.buttonList);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initMapButton() {
        Button mapButton = findViewById(R.id.buttonMap);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, map.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initSettingsButton ()
    {
        Button settingsButton = findViewById(R.id.buttonSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initChangeDateButton()
    {
        Button changeDateButton = findViewById(R.id.buttonChange);
        changeDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =getSupportFragmentManager();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(fm,"DatePick");
            }
        });
    }
    private void initToggleButton() {
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setForEditing(toggleButton.isChecked());
            }
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editContact = findViewById(R.id.editContact);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipcode = findViewById(R.id.editZipcode);
        EditText editHome = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEmail);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonChange = findViewById(R.id.buttonChange);

        editContact.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipcode.setEnabled(enabled);
        editHome.setEnabled(enabled);
        editCell.setEnabled(enabled);
        editEmail.setEnabled(enabled);
        buttonSave.setEnabled(enabled);
        buttonChange.setEnabled(enabled);
    }

    @Override
    public void didFinishDatePickerDialog(Calendar selectedDate)
    {
        TextView textBirthday = findViewById(R.id.textBirthday);
        textBirthday.setText(DateFormat.format("MM/DD/yyyy", selectedDate));
        currentContact.setBirthday(selectedDate);
    }
    private void initSaveButton()
    {
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                ContactDataSource ds = new ContactDataSource(MainActivity.this);
                try
                {
                    ds.open();
                    if(currentContact.getContactID()==-1)
                    {
                        wasSuccessful = ds.insertContact(currentContact);
                        if (wasSuccessful)
                        {
                            int newId = ds.getLastContactID();
                            currentContact.setContactID(newId);
                        }
                    }
                    else
                        {
                            wasSuccessful = ds.updateContact(currentContact);
                        }
                    ds.close();
                }
                catch (Exception e)
                {
                    wasSuccessful=false;
                }
                if (wasSuccessful)
                {
                    ToggleButton editToggle = findViewById(R.id.toggleButton);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }
    private void initTextChangeEvents()
    {
        EditText nameEdit = findViewById((R.id.editContact));
        nameEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                currentContact.setName(nameEdit.getText().toString());
                currentContact.setContactID(-1);
            }
        });
            EditText cityEdit = findViewById(R.id.editCity);
            cityEdit.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
                {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
                {

                }

                @Override
                public void afterTextChanged(Editable editable)
                {
                    currentContact.setName(cityEdit.getText().toString());
                    currentContact.setContactID(-1);
                }
            });
        EditText addressEdit = findViewById(R.id.editAddress);
        addressEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                currentContact.setName(addressEdit.getText().toString());
                currentContact.setContactID(-1);
            }
        });
        EditText stateEdit = findViewById(R.id.editState);
        stateEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                currentContact.setName(stateEdit.getText().toString());
                currentContact.setContactID(-1);
            }
        });
        EditText zipEdit = findViewById(R.id.editZipcode);
        zipEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                currentContact.setName(zipEdit.getText().toString());
                currentContact.setContactID(-1);
            }
        });
        EditText homeEdit = findViewById(R.id.editHome);
        homeEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                currentContact.setName(homeEdit.getText().toString());
                currentContact.setContactID(-1);
            }
        });
        EditText cellEdit = findViewById(R.id.editCell);
        cellEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                currentContact.setName(cellEdit.getText().toString());
                currentContact.setContactID(-1);
            }
        });
        EditText emailEdit = findViewById(R.id.editEmail);
        emailEdit.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                currentContact.setName(emailEdit.getText().toString());
                currentContact.setContactID(-1);
            }
        });
        homeEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        cellEdit.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }
}