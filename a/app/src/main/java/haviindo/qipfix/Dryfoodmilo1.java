package haviindo.qipfix;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Dryfoodmilo1 extends AppCompatActivity {

    private EditText Date;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    DatePicker simpleDatePicker;
    Button BtDatePicker;
    boolean valid = false;
    Button Submit;
    Button Back;
    Button Next;

    public void cs1col2RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs1col2:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs1col2:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs1col3RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs1col3:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs1col3:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs1col4RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs1col4:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs1col4:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs1col5RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs1col5:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs1col5:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs1col6RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs1col6:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs1col6:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs1col7RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs1col7:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs1col7:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs1col8RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs1col8:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs1col8:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs1col9RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs1col9:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs1col9:
                if (checked)
                    valid = true;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryfoodmilo1);

        Submit = (Button) findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StatusPenerimaanActivity.class);
                startActivity(intent);
            }
        });

        Back = (Button) findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Next = (Button) findViewById(R.id.Next);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Dryfoodmilo2.class);
                startActivity(intent);
            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        Date = (EditText) findViewById(R.id.date);
        BtDatePicker = (Button) findViewById(R.id.bt_datepicker);
        BtDatePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                Date.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        fromDatePickerDialog.show();
    }
}
