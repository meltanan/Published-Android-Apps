package com.education.mohamed.hw2;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExpenseTable {
    static final String TABLENAME="Expenses";
    static final String COLUMN_ID="_id";
    static final String EXPENSENAME="name";
    static final String EXPENSECATEGORY="category";
    static final String EXPENSEDATE="date";
    static final String EXPENSEAMOUNT="amount";
    static final String EXPENSERECEPIT="receipt";

    static public void onCreate(SQLiteDatabase db)
    {
        StringBuilder sb=new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+" (");
        sb.append(COLUMN_ID+" integer primary key autoincrement, ");
        sb.append(EXPENSENAME+" text not null, ");
        sb.append(EXPENSECATEGORY+" text not null, ");
        sb.append(EXPENSEDATE+" text not null, ");
        sb.append(EXPENSEAMOUNT+" text not null, ");
        sb.append(EXPENSERECEPIT+" text ); ");

        try {
            db.execSQL(sb.toString());
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
        ExpenseTable.onCreate(db);
    }





}
