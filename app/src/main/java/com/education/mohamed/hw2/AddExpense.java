package com.education.mohamed.hw2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;


public class AddExpense extends AppCompatActivity {
    String date,name;
    Double amount;
    int category;
   public static Uri imageURL=null;
    ImageView imageViewReceipte;
    private DatePickerDialog.OnDateSetListener mDateSitListner;
    private static final int PICK_IMAGE=0;

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        final EditText editTextName=findViewById(R.id.editDeleteTextName);
        Spinner spinner=findViewById(R.id.spinner);
        final EditText editTextAmount=findViewById(R.id.editDeleteTextAmount);
        final TextView textViewDate=findViewById(R.id.textViewDeleteDate);
        imageViewReceipte=findViewById(R.id.imageViewReceipte);
        Button buttonAddExpense=findViewById(R.id.buttonAddExpense);
        final ImageView imageViewBack=findViewById(R.id.imageViewAddBack);

        setTitle("Add");
        imageURL=Uri.EMPTY;
        //spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                category=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        AddExpense.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSitListner, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSitListner=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                Log.d("demo",i+"/ "+i1+"/ "+i2);
                date=String.valueOf(i1+"/"+i2+"/"+i);
                textViewDate.setText(date);
                textViewDate.setHint(date);
            }
        };

        imageViewReceipte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {

                String name;
                Expenses expenses;
                if (editTextName.getText().toString().isEmpty())
                {
                    Toast.makeText(AddExpense.this,"Name must be entered",500).show();
                }
                else if (editTextAmount.getText().toString().isEmpty())
                    Toast.makeText(AddExpense.this,"Amount must be entered",500).show();
                else if (textViewDate.getHint().toString().equals("Select Date"))
                    Toast.makeText(AddExpense.this,"Date must be entered",500).show();
                else {
                    name=editTextName.getText().toString();
                    amount= Double.valueOf(editTextAmount.getText().toString());
                    amount = Double.valueOf(String.format("%.2f", amount));
                    expenses=new Expenses(name,category,date,amount,imageURL);
                    editTextName.setText(null);
                    editTextName.setHint("Name");
                    editTextAmount.setText(null);
                    editTextAmount.setHint("Amount");
                    textViewDate.setText(null);
                    textViewDate.setHint("Date");
                    imageViewReceipte.setImageDrawable(getResources().getDrawable(R.drawable.bluepic));
                    Toast.makeText(AddExpense.this,"Expense Added Successfully",500).show();
                    DatabaseDateManager databaseDateManager=new DatabaseDateManager(AddExpense.this);
                    databaseDateManager.saveExpense(expenses);
                }
            }
        });
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    private void openGallery() {
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==PICK_IMAGE&&resultCode==RESULT_OK)
        {
            imageURL=data.getData();
            imageViewReceipte.setImageURI(imageURL);
            Log.d("result3",String.valueOf(imageURL));
        }
        else
        {
            imageURL=null;
        }
    }

    public void hideKeyboard(View view)
    {
        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}

