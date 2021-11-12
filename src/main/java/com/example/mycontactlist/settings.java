package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Map;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initSettings();
        initSettingsButton();
        initListButton();
        initMapButton();
        initRadioGroupSortBy();
        initRadioGroupSortOrder();
    }
    private void initMapButton() {
        Button mapButton = findViewById(R.id.buttonMap);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, map.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initListButton() {
        Button listButton = findViewById(R.id.buttonList);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, ContactListActivity.class);
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
                Intent intent = new Intent(settings.this, settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initSettings() {
        SharedPreferences pref = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE);
        String sortBy = pref.getString("sortfield", "Name");
        String sortOrder = pref.getString("sortorder", "Ascending");
        RadioButton radioButton = findViewById(R.id.radioButton);
        RadioButton radioButton2 = findViewById(R.id.radioButton2);
        RadioButton radioButton3 = findViewById(R.id.radioButton3);
        if (sortBy.equals("Name")) {
            radioButton3.setChecked(true);
        } else if (sortBy.equals("Birthday")) {
            radioButton.setChecked(true);
        } else {
            radioButton2.setChecked(true);
        }
        RadioButton radioButton5 = findViewById(R.id.radioButton5);
        RadioButton radiobutton4 = findViewById(R.id.radioButton4);
        if (sortOrder.equals("Ascending")) {
            radioButton5.setChecked(true);
        } else {
            radiobutton4.setChecked(true);
        }
    }
        private void initRadioGroupSortBy()
        {
            RadioGroup rg = findViewById(R.id.radioGroup);
            RadioButton radioButtonName = findViewById(R.id.radioButton3);
            RadioButton radioButtonBirthday = findViewById(R.id.radioButton);
            RadioButton radioButtonCity = findViewById(R.id.radioButton2);
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (radioButtonBirthday.isChecked())
                    {
                        SharedPreferences sp = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("sortfield","Birthday");
                        editor.apply();
                    }
                    else if (radioButtonCity.isChecked())
                    {
                        SharedPreferences sp = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("sortfield","City");
                        editor.apply();
                    }
                    else if (radioButtonName.isChecked())
                    {
                        SharedPreferences sp = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("sortfield","Name");
                        editor.apply();
                    }
                }
            });
        }
        private void initRadioGroupSortOrder()
        {
            RadioGroup rg = findViewById(R.id.radioGroup2);
            RadioButton radioButtonAscending = findViewById(R.id.radioButton5);
            RadioButton radioButtonDescending = findViewById(R.id.radioButton4);
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (radioButtonAscending.isChecked())
                    {
                        SharedPreferences sp = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("sortorder","Ascending");
                        editor.apply();
                    }
                    else if (radioButtonDescending.isChecked())
                    {
                        SharedPreferences sp = getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("sortorder","Descending");
                        editor.apply();
                    }
                }
            });
        }


}