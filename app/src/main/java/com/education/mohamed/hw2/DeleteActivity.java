package com.education.mohamed.hw2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    DatabaseDateManager databaseDateManager;
    ArrayList<Expenses> list;
    ArrayList<Expenses>deletedExpeses;
    int expenseIndex;
    String [] categories;
    String [] expensesList;

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.education.mohamed.hw2.R.layout.activity_delete);

        setTitle("Delete");
        expenseIndex=-1;
        final Button buttonDelete=findViewById(com.education.mohamed.hw2.R.id.buttonAddExpense);
        Button buttonSelectExpense=findViewById(com.education.mohamed.hw2.R.id.buttonDeleteSelect);
        final TextView textViewName=findViewById(com.education.mohamed.hw2.R.id.textViewDeleteName);

        final  TextView textViewAmount=findViewById(com.education.mohamed.hw2.R.id.textViewDeleteAmount);
        final ImageView imageViewReceipte=findViewById(com.education.mohamed.hw2.R.id.imageViewDeleteReceipte);
        final TextView textViewDate=findViewById(com.education.mohamed.hw2.R.id.textViewDeleteDate);
        final TextView textViewCategory=findViewById(com.education.mohamed.hw2.R.id.textViewCategory);
        final ImageView imageViewBack=findViewById(com.education.mohamed.hw2.R.id.imageViewDeleteBack);
       // buttonDelete.setEnabled(false);
        deletedExpeses=new ArrayList<>();

        list=getIntent().getParcelableArrayListExtra(MainActivity.EXPENSESLIST);
        if (list.size()==0)
            buttonDelete.setEnabled(false);
         expensesList=new String [list.size()];
        for (int x=0;x<list.size();x++)
        {
            expensesList[x]=list.get(x).name;
        }
        categories=new String[9];
        categories=getResources().getStringArray(com.education.mohamed.hw2.R.array.items);

        buttonSelectExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DeleteActivity.this);
                builder.setItems(expensesList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        expenseIndex=i;
                        textViewName.setText(list.get(i).name);
                        textViewAmount.setText(String.valueOf(list.get(i).price));
                        textViewCategory.setText(categories[list.get(i).category]);
                        if (Uri.EMPTY.equals(list.get(i).image))
                            imageViewReceipte.setImageDrawable(getResources().getDrawable(com.education.mohamed.hw2.R.drawable.bluepic));
                        else
                            imageViewReceipte.setImageURI(list.get(i).image);

                        textViewDate.setText(list.get(i).date);
                        buttonDelete.setEnabled(true);
                    }
                }).setTitle("Select Expense").show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (expenseIndex==-1)
                {
                    Toast.makeText(DeleteActivity.this,"Expense Must be Selected First",500).show();

                }

                else {

                    //deletedExpeses.add(list.get(expenseIndex));
                    databaseDateManager=new DatabaseDateManager(DeleteActivity.this);
                    databaseDateManager.deleteExpese(list.get(expenseIndex));
                    list.remove(expenseIndex);
                    textViewName.setHint("Name");
                    textViewAmount.setHint("Amount");
                    imageViewReceipte.setImageURI(null);
                    textViewCategory.setText(null);
                    textViewName.setText(null);
                    textViewAmount.setText(null);
                    textViewDate.setText(null);
                    textViewDate.setHint("Date");
                    expensesList = new String[list.size()];
                    for (int x = 0; x < list.size(); x++) {
                        expensesList[x] = list.get(x).name;

                    }
                    buttonDelete.setEnabled(false);
                    Toast.makeText(DeleteActivity.this, "Expense Deleted Successfully", 500).show();
                }
            }
        });
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                intent.putParcelableArrayListExtra(MainActivity.RESULT,list);
                intent.putExtra(MainActivity.DELETEDEXPENSERESULT,deletedExpeses);
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

}
