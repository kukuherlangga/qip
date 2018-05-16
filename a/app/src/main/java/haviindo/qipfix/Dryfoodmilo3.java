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

public class Dryfoodmilo3 extends AppCompatActivity {

    private EditText Date;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    DatePicker simpleDatePicker;
    Button BtDatePicker;
    boolean valid = false;
    Button Submit;
    Button Back;
    Button Next;

    public void cs3col2RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs3col2:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs3col2:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs3col3RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs3col3:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs3col3:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs3col4RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs3col4:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs3col4:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs3col5RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs3col5:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs3col5:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs3col6RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs3col6:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs3col6:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs3col7RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs3col7:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs3col7:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs3col8RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs3col8:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs3col8:
                if (checked)
                    valid = true;
                break;
        }
    }

    public void cs3col9RadioBtn(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.Ycs3col9:
                if (checked)
                    valid = true;
                break;
            case R.id.Ncs3col9:
                if (checked)
                    valid = true;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryfoodmilo3);

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
                Intent intent = new Intent(getApplicationContext(), Dryfoodmilo2.class);
                startActivity(intent);
            }
        });

        Next = (Button) findViewById(R.id.Next);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Dryfoodmilo4.class);
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
