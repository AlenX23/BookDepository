package ru.rksi.koleukhov.bookdepository;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class DatePickerFragment extends DialogFragment
{
    public static final String EXTRA_DATE = "ru.rksi.koleukhov.bookdepository.date";
    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;
    private Button mButtonOk;

    public static DatePickerFragment newInstance(Date date)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void sendResult(int resultCode, Date date)
    {
        if(getTargetFragment() == null)
        {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_DATE, date);
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //Date date = (Date) getArguments().getSerializable(ARG_DATE);
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(date);
        //int year = calendar.get(Calendar.YEAR);
        //int month = calendar.get(Calendar.MONTH);
        //int day = calendar.get(Calendar.DAY_OF_MONTH);

        //View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        //mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
        //mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity()).create();
        //        .setView(v)
        //        .setTitle(R.string.date_picker_title)
        //        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
         //       {
         //           @Override
         //           public void onClick(DialogInterface dialog, int which)
         //           {
        //                int year = mDatePicker.getYear();
         //               int month = mDatePicker.getMonth();
         //               int day = mDatePicker.getDayOfMonth();
          //              Date date = new GregorianCalendar(year, month, day).getTime();
           //             sendResult(Activity.RESULT_OK, date);
           //         }
           //     })
           //     .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View v = inflater.inflate(R.layout.dialog_date, container, false);
        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year, month, day, null);

        mButtonOk = (Button) v.findViewById(R.id.dialog_date_button_ok);
        mButtonOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                sendResult(Activity.RESULT_OK, date);
            }
        });

        return v;
    }
}
