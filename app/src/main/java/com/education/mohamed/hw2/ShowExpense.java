package com.education.mohamed.hw2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

    public class ShowExpense extends AppCompatActivity {

        ArrayList <Expenses>expenseList;
        String [] categories;
        int index=0;

        @Override
        public void setTitle(int titleId) {
            super.setTitle(titleId);
        }

        @Override
        public void setTitle(CharSequence title) {
            super.setTitle(title);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(com.education.mohamed.hw2.R.layout.activity_show_expense);
            final ImageView imageView=findViewById(com.education.mohamed.hw2.R.id.imageViewShow);
            setTitle("Show Expense");
            final TextView textViewName=findViewById(com.education.mohamed.hw2.R.id.textViewShowName);
            final TextView textViewAmount=findViewById(com.education.mohamed.hw2.R.id.textViewAmount);
            final TextView textViewCategory=findViewById(com.education.mohamed.hw2.R.id.textViewShowCategory);
            final TextView textViewDate=findViewById(com.education.mohamed.hw2.R.id.textViewShowDate);
            final ImageView imageViewNext=findViewById(com.education.mohamed.hw2.R.id.imageViewShowNext);
            final ImageView imageView1NextLast=findViewById(com.education.mohamed.hw2.R.id.imageViewNextLast);
            final ImageView imageViewPrevious=findViewById(com.education.mohamed.hw2.R.id.imageViewPrevious);
            final ImageView imageViewPreviousFirst=findViewById(com.education.mohamed.hw2.R.id.imageViewPreviousFist);
            final ImageView imageViewBck=findViewById(com.education.mohamed.hw2.R.id.imageViewShowBack);

            expenseList=getIntent().getParcelableArrayListExtra(MainActivity.EXPENSESLIST);
            categories=new String[9];
            categories=getResources().getStringArray(com.education.mohamed.hw2.R.array.items);
            setTitle("Display");

            if (expenseList.size()<=1)
            {
                imageViewNext.setEnabled(false);
                imageViewNext.setImageAlpha(0x3F);
                imageView1NextLast.setEnabled(false);
                imageView1NextLast.setImageAlpha(0X3F);

            }

            imageViewPrevious.setEnabled(false);
            imageViewPrevious.setImageAlpha(0X3F);
            imageViewPreviousFirst.setEnabled(false);
            imageViewPreviousFirst.setImageAlpha(0X3F);
            textViewName.setText(expenseList.get(index).name);
            textViewAmount.setText(String.valueOf(expenseList.get(index).price));
            textViewDate.setText(expenseList.get(index).date);
            textViewCategory.setText(categories[expenseList.get(index).category]);
            if (Uri.EMPTY.equals(expenseList.get(index).image))
                imageView.setImageDrawable(getResources().getDrawable(com.education.mohamed.hw2.R.drawable.bluepic));
            else
            imageView.setImageURI(expenseList.get(index).image);
            imageViewNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index++;
                    if (index==expenseList.size()-1)
                    {
                        imageViewNext.setEnabled(false);
                        imageViewNext.setImageAlpha(0x3F);

                        imageView1NextLast.setEnabled(false);
                        imageView1NextLast.setImageAlpha(0x3F);
                    }
                    imageViewPrevious.setEnabled(true);
                    imageViewPrevious.setImageAlpha(0XFF);
                    imageViewPreviousFirst.setEnabled(true);
                    imageViewPreviousFirst.setImageAlpha(0XFF);
                    textViewName.setText(expenseList.get(index).name);
                    textViewAmount.setText(String.valueOf(expenseList.get(index).price));
                    textViewDate.setText(expenseList.get(index).date);
                    textViewCategory.setText(categories[expenseList.get(index).category]);
                    if (Uri.EMPTY.equals(expenseList.get(index).image))
                        imageView.setImageDrawable(getResources().getDrawable(com.education.mohamed.hw2.R.drawable.bluepic));
                    else
                    imageView.setImageURI(expenseList.get(index).image);
                }
            });

            imageView1NextLast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index=expenseList.size()-1;
                    imageViewNext.setEnabled(false);
                    imageViewNext.setImageAlpha(0X3F);
                    imageView1NextLast.setEnabled(false);
                    imageView1NextLast.setImageAlpha(0x3F);
                    imageViewPrevious.setEnabled(true);
                    imageViewPrevious.setImageAlpha(0xFF);
                    imageViewPreviousFirst.setEnabled(true);
                    imageViewPreviousFirst.setImageAlpha(0xFF);
                    textViewName.setText(expenseList.get(index).name);
                    textViewAmount.setText(String.valueOf(expenseList.get(index).price));
                    textViewDate.setText(expenseList.get(index).date);
                    textViewCategory.setText(categories[expenseList.get(index).category]);
                    if (Uri.EMPTY.equals(expenseList.get(index).image))
                        imageView.setImageDrawable(getResources().getDrawable(com.education.mohamed.hw2.R.drawable.bluepic));
                    else
                        imageView.setImageURI(expenseList.get(index).image);
                }
            });

            imageViewPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    index--;
                    if (index==0)
                    {
                        imageViewPrevious.setEnabled(false);
                        imageViewPrevious.setImageAlpha(0x3F);
                        imageViewPreviousFirst.setEnabled(false);
                        imageViewPreviousFirst.setImageAlpha(0x3F);
                    }
                    imageViewNext.setEnabled(true);
                    imageViewNext.setImageAlpha(0xFF);
                    imageView1NextLast.setEnabled(true);
                    imageView1NextLast.setImageAlpha(0xFF);
                    textViewName.setText(expenseList.get(index).name);
                    textViewAmount.setText(String.valueOf(expenseList.get(index).price));
                    textViewDate.setText(expenseList.get(index).date);
                    textViewCategory.setText(categories[expenseList.get(index).category]);
                    if (Uri.EMPTY.equals(expenseList.get(index).image))
                        imageView.setImageDrawable(getResources().getDrawable(com.education.mohamed.hw2.R.drawable.bluepic));
                    else
                        imageView.setImageURI(expenseList.get(index).image);
                }
            });

            imageViewPreviousFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    index=0;
                    imageViewPrevious.setEnabled(false);
                    imageViewPrevious.setImageAlpha(0x3F);
                    imageViewPreviousFirst.setEnabled(false);
                    imageViewPreviousFirst.setImageAlpha(0x3F);
                    imageViewNext.setEnabled(true);
                    imageViewNext.setImageAlpha(0xFF);
                    imageView1NextLast.setEnabled(true);
                    imageView1NextLast.setImageAlpha(0xFF);
                    textViewName.setText(expenseList.get(index).name);
                    textViewAmount.setText(String.valueOf(expenseList.get(index).price));
                    textViewDate.setText(expenseList.get(index).date);
                    textViewCategory.setText(categories[expenseList.get(index).category]);
                    if (Uri.EMPTY.equals(expenseList.get(index).image))
                        imageView.setImageDrawable(getResources().getDrawable(com.education.mohamed.hw2.R.drawable.bluepic));
                    else
                        imageView.setImageURI(expenseList.get(index).image);

                }
            });

            imageViewBck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setResult(RESULT_CANCELED);
                    finish();

                }
            });

        }
    }
