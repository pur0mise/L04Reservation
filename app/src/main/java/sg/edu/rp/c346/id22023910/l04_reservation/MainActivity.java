package sg.edu.rp.c346.id22023910.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declare field variables
    EditText nameInput;
    EditText mobileInput;
    EditText groupInput;
    DatePicker dp;
    TimePicker tp;
    RadioButton smoke;
    RadioButton noSmoke;
    Button confirmBtn;
    Button clearBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link the field variables to UI components in layout
        nameInput = findViewById(R.id.nameInput);
        mobileInput = findViewById(R.id.mobileInput);
        groupInput = findViewById(R.id.groupInput);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        smoke = findViewById(R.id.smokingArea);
        noSmoke = findViewById(R.id.nonSmokingArea);
        confirmBtn = findViewById(R.id.confirmBtn);
        clearBtn = findViewById(R.id.clearBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int date = dp.getDayOfMonth();
                int month = dp.getMonth();
                int year = dp.getYear();
                String dateDisplay = String.format("%02d/%02d/%02d", date, month + 1, year);

                int hour = tp.getCurrentHour();
                int minute = tp.getCurrentMinute();
                String timeDisplay = String.format("%02d:%02d", hour, minute);

                String resInfo = nameInput.getText().toString() + " | " + mobileInput.getText().toString() + " | " + groupInput.getText().toString();

                String tableType = "";
                if (smoke.isChecked() == true && noSmoke.isChecked() == false) {
                    tableType = "Smoking Area";
                }

                else if (smoke.isChecked() == false && noSmoke.isChecked() == true) {
                    tableType = "Non-smoking Area";
                }


                if (nameInput.getText().toString().trim().length() == 0 && mobileInput.getText().toString().trim().length() == 0
                        && groupInput.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Name, Mobile Number and Group Size is required! ",Toast.LENGTH_SHORT).show();
                    nameInput.setError("Name is required");
                    groupInput.setError("Group size is required");
                    mobileInput.setError("Mobile number is required");
                }
                else if (nameInput.getText().toString().trim().length() == 0 && mobileInput.getText().toString().trim().length() == 0
                        && groupInput.getText().toString().trim().length() != 0) {
                    Toast.makeText(MainActivity.this, "Name and Mobile Number is required! ",Toast.LENGTH_SHORT).show();
                    nameInput.setError("Name is required");
                    mobileInput.setError("Mobile number is required");
                }
                else if (nameInput.getText().toString().trim().length() == 0 && mobileInput.getText().toString().trim().length() != 0
                        && groupInput.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Name and Group Size is required! ",Toast.LENGTH_SHORT).show();
                    nameInput.setError("Name is required");
                    groupInput.setError("Group Size is required");
                }
                else if (nameInput.getText().toString().trim().length() != 0 && mobileInput.getText().toString().trim().length() == 0
                        && groupInput.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Mobile number and Group Size is required! ",Toast.LENGTH_SHORT).show();
                    mobileInput.setError("Mobile number is required");
                    groupInput.setError("Group Size is required");
                }
                else if (nameInput.getText().toString().trim().length() == 0 && mobileInput.getText().toString().trim().length() != 0
                        && groupInput.getText().toString().trim().length() != 0) {
                    Toast.makeText(MainActivity.this, "Name is required! ",Toast.LENGTH_SHORT).show();
                    nameInput.setError("Name is required");
                }
                else if (nameInput.getText().toString().trim().length() != 0 && mobileInput.getText().toString().trim().length() == 0
                        && groupInput.getText().toString().trim().length() != 0) {
                    Toast.makeText(MainActivity.this, "Mobile number is required! ",Toast.LENGTH_SHORT).show();
                    nameInput.setError("Name is required");
                    groupInput.setError("Group Size is required");
                }
                else if (nameInput.getText().toString().trim().length() != 0 && mobileInput.getText().toString().trim().length() != 0
                        && groupInput.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Group Size is required! ",Toast.LENGTH_SHORT).show();
                    groupInput.setError("Group Size is required");
                }
                else {
                    Toast.makeText(MainActivity.this, resInfo + " | " + dateDisplay + " | " + timeDisplay + " | "
                            + tableType ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dp.updateDate(2023, 5, 1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                nameInput.setText("");
                mobileInput.setText("");
                groupInput.setText("");

            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker v, int hour, int minute) {
                if (tp.getCurrentHour() < 8) {
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                    Toast.makeText(MainActivity.this, "Store only open after 8am", Toast.LENGTH_SHORT).show();
                }
                else if (tp.getCurrentHour() >= 21) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(59);
                    Toast.makeText(MainActivity.this, "Store closed after 9pm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}