package com.example.mycontactlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerDialog extends DialogFragment {

    Calendar selectedDate;

    public interface SaveDateListener {
        void didFinishDatePickerDialog(Calendar selectedDate);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_date, container);
        getDialog().setTitle("Select Date");
        selectedDate = Calendar.getInstance();

        CalendarView cv = view.findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                            int year, int month, int day) {
                selectedDate.set(year, month, day);
            }
        });

        Button okButton = view.findViewById(R.id.buttonOK);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDateListener activity = (SaveDateListener) getActivity();
                activity.didFinishDatePickerDialog(selectedDate);
                getDialog().dismiss();
            }
        });

        Button cancelButton = view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }

}
