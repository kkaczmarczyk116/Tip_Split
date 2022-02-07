package com.example.tipsplit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  String TAG = getClass().getSimpleName();


    private RadioGroup rgPercent;
    private EditText billTotal;
    private TextView tipOut;
    private TextView withTip;

    private TextView numberInput;
    private Button btNumber;
    private TextView totalPerPersonOutcome;
    private TextView overage;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            System.out.println("savedInstanceState is null");
        }else{
            System.out.println("savedInstanceState is not null");
        }

        billTotal = findViewById(R.id.billInput);
        tipOut = findViewById(R.id.tipOut);
        withTip = findViewById(R.id.withTip);
        rgPercent = findViewById(R.id.rgPercent);

        numberInput = findViewById(R.id.numberInput);
        btNumber = findViewById(R.id.btNumber);
        totalPerPersonOutcome = findViewById(R.id.totalPerPersonOutcome);
        overage = findViewById(R.id.overage);
        clear = findViewById(R.id.btnClear);





        rgPercent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("DefaultLocale")
            @Override

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(billTotal.getText().toString().isEmpty()){
                    billTotal.setText("0");
                }

                switch (i){

                    case R.id.rb12:
                        Toast.makeText(MainActivity.this, "12% Selected", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"12");
                        tipOut.setText(String.format("%.2f", Double.parseDouble(billTotal.getText().toString()) * .12));
                        withTip.setText(String.format("%.2f", Double.parseDouble(tipOut.getText().toString()) + Double.parseDouble(billTotal.getText().toString())));
                        rgPercent.check(i);
                        break;
                    case R.id.rb15:
                        Toast.makeText(MainActivity.this, "15% Selected", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"15");
                        tipOut.setText(String.format("%.2f", Double.parseDouble(billTotal.getText().toString()) * .15));
                        withTip.setText(String.format("%.2f", Double.parseDouble(tipOut.getText().toString()) + Double.parseDouble(billTotal.getText().toString())));
                        rgPercent.check(i);
                        break;
                    case R.id.rb18:
                        Toast.makeText(MainActivity.this, "18% Selected", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"18");
                        tipOut.setText(String.format("%.2f", Double.parseDouble(billTotal.getText().toString()) * .18));
                        withTip.setText(String.format("%.2f", Double.parseDouble(tipOut.getText().toString()) + Double.parseDouble(billTotal.getText().toString())));
                        rgPercent.check(i);
                        break;
                    case R.id.rb20:
                        Toast.makeText(MainActivity.this, "20% Selected", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"20");
                        tipOut.setText(String.format("%.2f", Double.parseDouble(billTotal.getText().toString()) * .20));
                        withTip.setText(String.format("%.2f", Double.parseDouble(tipOut.getText().toString()) + Double.parseDouble(billTotal.getText().toString())));
                        rgPercent.check(i);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Select Percentage", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

       billTotal.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if(!billTotal.getText().toString().isEmpty() && rgPercent.getCheckedRadioButtonId() != -1){
                   int sel = rgPercent.getCheckedRadioButtonId();
                   RadioButton rb = findViewById(sel);
                   double percentage = Double.parseDouble(rb.getText().toString());
                   tipOut.setText(String.format("%.2f",Double.parseDouble(billTotal.getText().toString()) * (percentage /100)));
                   double extra = Double.parseDouble(tipOut.getText().toString());
                   withTip.setText(String.format("%.2f",Double.parseDouble(billTotal.getText().toString()) + (extra)));

               }else{
                   tipOut.setText("$");
                   withTip.setText("$");
               }
               //clearing the radio group would crash the prog if textEdit was empty


               /*if(billTotal.getText().toString().matches("")){
                   //int sel = rgPercent.getCheckedRadioButtonId();
                   //RadioButton rb = findViewById(sel);
                   //forClear.clearCheck();
               }*/
               /*if(charSequence.length() == 1){
                   rgPercent.clearCheck();

               }*/

           }
           @Override
           public void afterTextChanged(Editable editable) {

           }
       });

    btNumber.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!withTip.getText().toString().isEmpty() && !numberInput.getText().toString().isEmpty()){
                double amount = Double.parseDouble(withTip.getText().toString());
                double num = Double.parseDouble(numberInput.getText().toString());
                Double perPerson = amount/num;
                totalPerPersonOutcome.setText("$ " +String.format("%.2f",perPerson));
                double checkOver = (Math.round(perPerson *100.0)/100.0 * num) - (Math.round(amount * 100.0)/100.0);
                String logTest = String.format("%.2f",checkOver);
                Log.d(TAG, String.valueOf(checkOver));
                if(String.format("%.2f",checkOver) != "0.00"){
                    overage.setText("$ " + String.format("%.2f",Math.abs(checkOver)));
                    Log.d(TAG,overage.getText().toString());
                }
            }else{
                Toast.makeText(MainActivity.this, "Missing Information", Toast.LENGTH_SHORT).show();
            }



        }
    });


    /*clear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            billTotal.setText("");
            tipOut.setText("");
            withTip.setText("");
            numberInput.setText("");
            totalPerPersonOutcome.setText("");
            overage.setText("");
            Log.d(TAG,billTotal.getText().toString());
            rgPercent.clearCheck();

        }
    });*/

    }


    public void clickClear(View view) {
        billTotal.setText("");
        tipOut.setText("");
        withTip.setText("");
        numberInput.setText("");
        totalPerPersonOutcome.setText("");
        overage.setText("");
        Log.d(TAG,billTotal.getText().toString());
        rgPercent.clearCheck();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("totalPerson", totalPerPersonOutcome.getText().toString());
        outState.putString("overage", overage.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        totalPerPersonOutcome.setText(savedInstanceState.getString("totalPerson"));
        overage.setText(savedInstanceState.getString("overage"));
    }
}