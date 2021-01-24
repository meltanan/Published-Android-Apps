package com.education.mohamed.hw2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    private SQLiteDatabase db;

    public ExpenseDAO(SQLiteDatabase db)
    {
        this.db=db;
    }
    public long save(Expenses expenses)
    {
        ContentValues values=new ContentValues();
        values.put(ExpenseTable.EXPENSENAME,expenses.getName());
        values.put(ExpenseTable.EXPENSECATEGORY,expenses.getCategory());
        values.put(ExpenseTable.EXPENSEDATE,expenses.getDate());
        values.put(ExpenseTable.EXPENSEAMOUNT,expenses.getPrice());
        values.put(ExpenseTable.EXPENSERECEPIT, String.valueOf(expenses.getImage()));
        return db.insert(ExpenseTable.TABLENAME,null,values);
    }
    public boolean update(Expenses expenses)
    {
        ContentValues values=new ContentValues();
        values.put(ExpenseTable.EXPENSENAME,expenses.getName());
        values.put(ExpenseTable.EXPENSECATEGORY,expenses.getCategory());
        values.put(ExpenseTable.EXPENSEDATE,expenses.getDate());
        values.put(ExpenseTable.EXPENSEAMOUNT,expenses.getPrice());
        values.put(ExpenseTable.EXPENSERECEPIT, String.valueOf(expenses.getImage()));
        return db.update(ExpenseTable.TABLENAME,values,ExpenseTable.COLUMN_ID+"=?",new String []{expenses.get_id()+""})>0;
    }

    public boolean delete(Expenses expenses)
    {
        return db.delete(ExpenseTable.TABLENAME,ExpenseTable.COLUMN_ID+"=?",new String[]{expenses.get_id()+""})>0;

    }

    public Expenses get(long id)
    {
        Expenses expenses=null;
        Cursor cursor= db.query(true,ExpenseTable.TABLENAME,new String[]
                {ExpenseTable.COLUMN_ID,ExpenseTable.EXPENSENAME,ExpenseTable.EXPENSECATEGORY,
                        ExpenseTable.EXPENSEAMOUNT,ExpenseTable.EXPENSEDATE,
                ExpenseTable.EXPENSERECEPIT},ExpenseTable.COLUMN_ID+"=?",
                new String[]{id+""},null,null,null,null,null);

        if (cursor!=null&&cursor.moveToFirst())
        {
            expenses=buildExpensesFromCursor(cursor);
            if (!cursor.isClosed())
            {
                cursor.close();
            }
        }
        return expenses;
    }

    public List<Expenses> getAll()
    {
        List<Expenses> expenses=new ArrayList<Expenses>();
        Cursor cursor=db.query(ExpenseTable.TABLENAME,new String[]
                {ExpenseTable.COLUMN_ID,ExpenseTable.EXPENSENAME,ExpenseTable.EXPENSECATEGORY
                        ,ExpenseTable.EXPENSEDATE,ExpenseTable.EXPENSEAMOUNT,
                        ExpenseTable.EXPENSERECEPIT},null,null,null,null,null);
        if (cursor!=null&&cursor.moveToFirst())
        {
            do{
                Expenses expenses1=buildExpensesFromCursor(cursor);
                if (expenses1!=null)
                {
                    expenses.add(expenses1);
                }
            }while (cursor.moveToNext());
            if (!cursor.isClosed())
            {
                cursor.close();
            }
        }
        return expenses;
    }

    private Expenses buildExpensesFromCursor(Cursor cursor) {
        Expenses expenses=null;
        if (cursor!=null)
        {
            expenses=new Expenses();
            expenses.set_id(cursor.getLong(0));
            expenses.setName(cursor.getString(1));
            expenses.setCategory(cursor.getInt(2));
            expenses.setDate(cursor.getString(3));
            expenses.setPrice(cursor.getDouble(4));
            expenses.setImage(Uri.parse(cursor.getString(5)));
        }
        return expenses;
    }


}
