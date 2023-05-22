package sg.edu.rp.c346.id21028514.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView appEnd;
    TextView appEnd2;
    Button buttonEndSplit;
    Button buttonEndReset;
    ToggleButton toggleTaxGST;
    ToggleButton toggleTaxSVS;
    EditText editMoneyTextAmount;
    EditText editPeopleTextPax;
    EditText editTextTaxDiscount;
    RadioGroup radioMethodGRPPayment;
    RadioButton radioPaynow;
    RadioButton radioPayLah;
    EditText EndHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appEnd = findViewById(R.id.textOutcome);
        appEnd2 = findViewById(R.id.textOutcome2);
        buttonEndSplit = findViewById(R.id.buttonSplit);
        toggleTaxGST = findViewById(R.id.toggleGST);
        toggleTaxSVS = findViewById(R.id.toggleSVS);
        buttonEndReset = findViewById(R.id.buttonReset);
        editMoneyTextAmount = findViewById(R.id.editTextAmount);
        editPeopleTextPax = findViewById(R.id.editTextPax);
        editTextTaxDiscount = findViewById(R.id.editTextDiscount);
        radioMethodGRPPayment = findViewById(R.id.radioGRPPayment);
        radioPaynow = findViewById(R.id.radioPaynow);
        radioPayLah = findViewById(R.id.radioPayLah);
        EndHP = findViewById(R.id.editTextHP);

        buttonEndSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editMoneyTextAmount.getText().toString().trim().length()!=0 && editPeopleTextPax.getText().toString().trim().length()!=0) {
                    double newAmt = 0.0;
                    if (!toggleTaxSVS.isChecked() && !toggleTaxGST.isChecked()) {
                        newAmt = Double.parseDouble(editMoneyTextAmount.getText().toString());
                    } else if (toggleTaxSVS.isChecked() && !toggleTaxGST.isChecked()) {
                        newAmt = Double.parseDouble(editMoneyTextAmount.getText().toString()) * 1.1;
                    } else if (!toggleTaxSVS.isChecked() && toggleTaxGST.isChecked()) {
                        newAmt = Double.parseDouble(editMoneyTextAmount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(editMoneyTextAmount.getText().toString()) * 1.17;
                    }
//Pay mode

//Discount
                    if (editTextTaxDiscount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(editTextTaxDiscount.getText().toString()) / 100;
                    }
                    appEnd.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(editPeopleTextPax.getText().toString());
                    int checkedRadioId = radioMethodGRPPayment.getCheckedRadioButtonId();
                    if (numPerson != 1)

                        if(checkedRadioId == R.id.radioPaynow)

                                appEnd2.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + " via " + radioPaynow.getText().toString() + " to " + EndHP.getText().toString());

                        else if(checkedRadioId == R.id.radioPayLah)

                                appEnd2.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + " via " + radioPayLah.getText().toString() + " to " + EndHP.getText().toString());                          else{

                            }
                        else if(checkedRadioId == R.id.radioCash)
                            appEnd2.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + " cash " );
                    else
                        appEnd2.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                }else {
                    Toast.makeText(getApplicationContext(), "Amount or Number of people is empty", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonEndReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMoneyTextAmount.setText("");
                editPeopleTextPax.setText("");
                toggleTaxSVS.setChecked(false);
                toggleTaxGST.setChecked(false);
                editTextTaxDiscount.setText("");
            }
        });


    }
}