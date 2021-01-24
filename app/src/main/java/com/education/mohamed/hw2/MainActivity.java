package com.education.mohamed.hw2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class            MainActivity extends AppCompatActivity {

    DatabaseDateManager databaseDateManager;
    ArrayList<Expenses>expensesFromDB;

    private int ADD_ID=100;
    private int EDDIT_ID=200;
    private static int DELETE_ID=300;
    private static int SHOWEXPENSE=400;
    static String RESULT="result";
    static String DELETEDEXPENSERESULT="expenseDeletedResult";
    static String EDITEDEXPENSES="editedExpenses";
    static String EditRESULT="editResult";
    private ArrayList<Expenses> expensesArrayList=new ArrayList();
    public static String EXPENSESLIST="expenseList";
    public static String image="ima";
    public static String ExpenseIndex="expenseIndex";
    Button buttonShowExpense;
    Button buttonEdit;
    Button buttonAdd;
    Button buttonDelete;
    Button buttonFinish;
    ImageView imageViewBack;

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdd=findViewById(R.id.buttonAddExpense);
        buttonEdit=findViewById(R.id.buttonEditExpesne);
        buttonShowExpense=findViewById(R.id.buttonShowExpense);
        buttonDelete=findViewById(R.id.buttonDeleteExpense);
        buttonFinish=findViewById(R.id.buttonFinish);
        Button buttonAbout=findViewById(R.id.buttonAout);
        databaseDateManager=new DatabaseDateManager(this);

        setTitle("Expenses Manager");

        expensesFromDB= (ArrayList<Expenses>) databaseDateManager.getAllExpenses();
        if (expensesFromDB.size()==0)
        {
            buttonEdit.setEnabled(false);
            buttonShowExpense.setEnabled(false);
            buttonDelete.setEnabled(false);
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddExpense.class);
                startActivityForResult(intent,ADD_ID);
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EdditActivity.class);
                intent.putParcelableArrayListExtra(EXPENSESLIST,expensesFromDB);
                startActivityForResult(intent,EDDIT_ID);

            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DeleteActivity.class);
                intent.putParcelableArrayListExtra(EXPENSESLIST,expensesFromDB);
                startActivityForResult(intent,DELETE_ID);

            }
        });
        buttonShowExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ShowExpense.class);
                expensesFromDB= (ArrayList<Expenses>) databaseDateManager.getAllExpenses();
                intent.putParcelableArrayListExtra(EXPENSESLIST,expensesFromDB);
                startActivityForResult(intent,SHOWEXPENSE);
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("About").setMessage("This Application Was Built by Mohamed ELtanani. " +"\n"+
                                "Moeltanani@hotmail.com " +"\n"+
                                "Thank You!").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit").setMessage("You are about to exit the app. Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setCancelable(false).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==ADD_ID)
        {
            if (resultCode==RESULT_OK)
            {
                expensesFromDB= (ArrayList<Expenses>) databaseDateManager.getAllExpenses();
                if (expensesFromDB.size()>0) {
                    buttonDelete.setEnabled(true);
                    buttonEdit.setEnabled(true);
                    buttonShowExpense.setEnabled(true);
                    Log.d("test",expensesFromDB.toString());
                }

            }
        }

        else if (requestCode==EDDIT_ID)
        {
            if (resultCode==RESULT_OK)
            {

            }
        }

        else if (requestCode==DELETE_ID)
        {
            if (resultCode==RESULT_OK)
            {
                expensesFromDB= (ArrayList<Expenses>) databaseDateManager.getAllExpenses();
                if (expensesFromDB.size()==0)
                {
                    buttonEdit.setEnabled(false);
                    buttonShowExpense.setEnabled(false);
                    buttonDelete.setEnabled(false);
                }

            }
        }
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseDateManager.close();
    }
}
