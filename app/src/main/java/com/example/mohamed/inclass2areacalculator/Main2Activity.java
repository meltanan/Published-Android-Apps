package com.example.mohamed.inclass2areacalculator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Double length1=0.0;
    Double length2=0.0;
    Double area=0.0;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText editTextLength1=findViewById(R.id.editTextLength1);
        final EditText editTextLenght2=findViewById(R.id.editTextLength2);
        final TextView textViewArea=findViewById(R.id.textViewArea);
        Button buttonCalcualte=findViewById(R.id.buttonCalculate);
        final RadioButton radioButtonTriangle=findViewById(R.id.radioButtonTrinalge);
        final RadioButton radioButtonSquare=findViewById(R.id.radioButtonSquare);
        final RadioButton radioButtonCircle=findViewById(R.id.radioButtonCircle);
        final RadioButton radioButtonRectangle=findViewById(R.id.radioButtonRectangle);
        final RadioButton radioButtonClear=findViewById(R.id.radioButtonClear);

        RadioGroup radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

           radioButton=findViewById(i);
            }
        });

        buttonCalcualte.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (radioButton==radioButtonTriangle)
                {
                    if (editTextLength1.getText().toString().isEmpty()||editTextLenght2.getText().toString().isEmpty())
                    {
                        Toast.makeText(Main2Activity.this,"Both lenghts are required",500).show();
                        textViewArea.setText(null);
                    }
                    else
                    {

                        if ( TextUtils.isDigitsOnly(editTextLength1.getText())==true&&TextUtils.isDigitsOnly(editTextLenght2.getText())==true) {
                            length1 = Double.valueOf(editTextLength1.getText().toString());
                            length2 = Double.valueOf(editTextLenght2.getText().toString());
                            area = 0.5 * length1 * length2;
                            area = Double.valueOf(String.format("%.2f", area));
                            textViewArea.setText(area.toString());
                        }
                        else
                            Toast.makeText(Main2Activity.this,"Input must be digits only",500).show();


                    }
                }
                else if(radioButton==radioButtonSquare)
                {
                    if (editTextLength1.getText().toString().isEmpty())
                    {
                        Toast.makeText(Main2Activity.this,"Length 1 is required",500).show();
                    }
                    else
                    {
                        if ( TextUtils.isDigitsOnly(editTextLength1.getText())==true) {


                            length1 = Double.valueOf(editTextLength1.getText().toString());
                            editTextLenght2.setText(null);
                            area = length1 * length1;
                            area = Double.valueOf(String.format("%.2f", area));
                            textViewArea.setText(area.toString());
                        }
                        else
                            Toast.makeText(Main2Activity.this,"Input must be digits only",500).show();

                    }
                }

                else if (radioButton==radioButtonCircle)
                {
                    if (editTextLength1.getText().toString().isEmpty())
                    {
                        Toast.makeText(Main2Activity.this,"Length 1 is required",500).show();
                        textViewArea.setText(null);
                    }
                    else
                    {

                        if ( TextUtils.isDigitsOnly(editTextLength1.getText())==true)
                        {
                            length1= Double.valueOf(editTextLength1.getText().toString());
                            editTextLenght2.setText(null);
                            area=3.14159*length1*length1;
                            area= Double.valueOf(String.format("%.2f",area));
                            textViewArea.setText(area.toString());
                        }
                        else
                            Toast.makeText(Main2Activity.this,"Input must be digits only",500).show();


                    }
                }
                else if(radioButton==radioButtonRectangle)
                {
                    if (editTextLength1.getText().toString().isEmpty()||editTextLenght2.getText().toString().isEmpty())
                    {
                        Toast.makeText(Main2Activity.this,"Both lenghts are required",500).show();
                        textViewArea.setText(null);
                    }
                    else
                    {
                        if ( TextUtils.isDigitsOnly(editTextLength1.getText())==true&&TextUtils.isDigitsOnly(editTextLenght2.getText())==true)
                        {
                            length1= Double.valueOf(editTextLength1.getText().toString());
                            length2=Double.valueOf(editTextLenght2.getText().toString());
                            area=length1*length2;
                            area= Double.valueOf(String.format("%.2f",area));
                            textViewArea.setText(area.toString());

                        }
                        else
                            Toast.makeText(Main2Activity.this,"Input must be digits only",500).show();
                    }

                }
                else if(radioButton==radioButtonClear)
                {
                    editTextLength1.setText(null);
                    editTextLenght2.setText(null);
                    textViewArea.setText(null);
                }
                else
                    Toast.makeText(Main2Activity.this,"You must select an option",500).show();

            }
        });








    }
}
