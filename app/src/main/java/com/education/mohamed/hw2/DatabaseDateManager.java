package com.education.mohamed.hw2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class DatabaseDateManager {
    private Context mContext;
    private DataBaseHelper mdbOpenHelper;
    private SQLiteDatabase db;
    private ExpenseDAO expenseDAO;

    public DatabaseDateManager (Context mContext)
    {
        this.mContext=mContext;
        mdbOpenHelper=new DataBaseHelper(this.mContext);
        db=mdbOpenHelper.getWritableDatabase();
        expenseDAO=new ExpenseDAO(db);
    }

    public void close()
    {
        if (db!=null)
        {
            db.close();
        }
    }
    public ExpenseDAO getExpenseDAO()
    {
        return this.expenseDAO;

    }

    public long saveExpense(Expenses expenses)
    {
        return this.expenseDAO.save(expenses);
    }

    public boolean updateExpense(Expenses expenses)
    {
        return this.expenseDAO.update(expenses);
    }

    public boolean deleteExpese(Expenses expenses)
    {
        return this.expenseDAO.delete(expenses);
    }
    public  Expenses getExpense(long id)
    {
        return this.expenseDAO.get(id);
    }

    public List<Expenses> getAllExpenses()
    {
        return this.expenseDAO.getAll();

    }
}
