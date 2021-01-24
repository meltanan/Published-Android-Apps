package com.example.mohamed.inclass2areacalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Double length1=0.0;
    Double length2=0.0;
    Double area=0.0;

    @Override
    public void setTitle(int titleId) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Area Calculator");


        final EditText editTextLength1=findViewById(R.id.editTextLength1);
        final EditText editTextLenght2=findViewById(R.id.editTextLength2);

        final TextView textViewArea=findViewById(R.id.textViewArea);
        Button buttonTriangle=findViewById(R.id.buttonTriangle);
        Button buttonSquare=findViewById(R.id.buttonSquare);
        Button buttonCircle=findViewById(R.id.buttonCircle);
        Button buttonRectangle=findViewById(R.id.buttonRectangle);
        Button buttonClearAll=findViewById(R.id.buttonClearAll);
        Button buttonAbout=findViewById(R.id.buttonAbout);

        buttonTriangle.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (editTextLength1.getText().toString().isEmpty()||editTextLenght2.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Both lenghts are required",500).show();
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
                        Toast.makeText(MainActivity.this,"Input must be digits only",500).show();


                }




            }
        });

        buttonSquare.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (editTextLength1.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Length 1 is required",500).show();
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
                        Toast.makeText(MainActivity.this,"Input must be digits only",500).show();

                }

            }
        });

        buttonCircle.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (editTextLength1.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Length 1 is required",500).show();
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
                        Toast.makeText(MainActivity.this,"Input must be digits only",500).show();


                }

            }
        });

        buttonRectangle.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {

                if (editTextLength1.getText().toString().isEmpty()||editTextLenght2.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Both lenghts are required",500).show();
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
                        Toast.makeText(MainActivity.this,"Input must be digits only",500).show();
                }


            }
        });

        buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextLength1.setText(null);
                editTextLenght2.setText(null);
                textViewArea.setText(null);
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ActivityABout.class);
                startActivity(intent);
            }
        });
    }


}
