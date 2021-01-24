package com.education.mohamed.hw2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


import java.util.ArrayList;
import java.util.Calendar;

public class EdditActivity extends AppCompatActivity {
    ArrayList <Expenses> list;
    ArrayList <Expenses>editedExpenses;
    int expenseIndex=-1;
    Uri uri;
    int category=0;
    String date;
    static int PICK_IMAGE=1;
    ImageView imageViewReceipte;
    private DatePickerDialog.OnDateSetListener mDateSitListner;

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eddit);
        setTitle("Edit");

        final EditText editTextName=findViewById(R.id.editDeleteTextName);
        final Spinner spinner=findViewById(R.id.Deletespinner);
        final EditText editTextAmount=findViewById(R.id.editDeleteTextAmount);
        final TextView textViewDate=findViewById(R.id.textViewDeleteDate);
        imageViewReceipte=findViewById(R.id.imageViewEditReceipte);
        Button buttonSaveExpense=findViewById(R.id.buttonAddExpense);
        Button buttonSelectExpese=findViewById(R.id.buttonDeleteSelect);

        final ImageView imageViewBack=findViewById(R.id.imageViewBack);
        uri=Uri.EMPTY;



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

        list=new ArrayList<>();
        list=getIntent().getParcelableArrayListExtra(MainActivity.EXPENSESLIST);

        getResources().getString(R.string.addCategory);
        String now= getResources().getStringArray(R.array.items)[3];
        final String [] expensesList=new String [list.size()];
        for (int x=0;x<list.size();x++)
        {
            expensesList[x]=list.get(x).name;
        }

        //select expense
        buttonSelectExpese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(EdditActivity.this);
                builder.setItems(expensesList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        expenseIndex=i;
                        editTextName.setText(list.get(i).name);
                        editTextAmount.setText(String.valueOf(list.get(i).price));
                        spinner.setSelection(list.get(i).category);
                        textViewDate.setText(list.get(i).date);
                        uri=list.get(i).image;

                        if (Uri.EMPTY.equals(uri))
                        {
                            imageViewReceipte.setImageDrawable(getResources().getDrawable(R.drawable.bluepic));
                        }

                       else
                        {
                            imageViewReceipte.setImageURI(uri);
                        }
                    }
                }).setTitle("Select Expense").show();

            }
        });

        buttonSaveExpense.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (editTextName.getText().toString().isEmpty())
                {
                    Toast.makeText(EdditActivity.this,"Name must be entered",500).show();
                }
                else if (editTextAmount.getText().toString().isEmpty())
                    Toast.makeText(EdditActivity.this,"Amount must be entered",500).show();
                else if (expenseIndex==-1)
                    Toast.makeText(EdditActivity.this,"An Expense Must be Selected First",500).show();

                else {

                    String name=editTextName.getText().toString();
                    Double amount= Double.valueOf(editTextAmount.getText().toString());
                    amount = Double.valueOf(String.format("%.2f", amount));
                    date=textViewDate.getText().toString();
                    Expenses expenses=list.get(expenseIndex);
                    expenses.setName(name);
                    expenses.setCategory(category);
                    expenses.setDate(date);
                    expenses.setPrice(amount);
                    expenses.setImage(uri);
                    DatabaseDateManager databaseDateManager=new DatabaseDateManager(EdditActivity.this);
                    databaseDateManager.updateExpense(expenses);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EdditActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSitListner, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        mDateSitListner=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date=String.valueOf(i1+"/"+i2+"/"+i);
                textViewDate.setText(date);
            }
        };

        imageViewReceipte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==PICK_IMAGE&&resultCode==RESULT_OK)
        {
            uri=data.getData();
            imageViewReceipte.setImageURI(uri);
        }
    }

    public void hideKeyboard(View view)
    {
        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent();
        intent.putExtra(MainActivity.EDITEDEXPENSES,editedExpenses);
        setResult(RESULT_OK,intent);
        finish();
    }
}
