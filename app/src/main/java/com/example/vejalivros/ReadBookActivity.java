package com.example.vejalivros;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.vejalivros.model.Book;

public class ReadBookActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book);

        ReadBookTask task = new ReadBookTask();
        task.execute();
    }

    class ReadBookTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            Book book = (Book) getIntent().getSerializableExtra("book");
            return null;
        }
    }
}
