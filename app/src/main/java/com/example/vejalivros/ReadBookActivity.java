package com.example.vejalivros;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import com.example.vejalivros.model.Book;

import java.util.Locale;

public class ReadBookActivity extends AppCompatActivity {
    Book book;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_book);

        // Set Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.book = (Book) getIntent().getSerializableExtra("book");
        TextView bookText = (TextView) findViewById(R.id.book_text);
        bookText.setText(this.book.getText());
        bookText.setMovementMethod(new ScrollingMovementMethod());

        getSupportActionBar().setTitle(book.getName());
        ReadBookTask task = new ReadBookTask();
        task.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                tts.stop();
                tts.shutdown();
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ReadBookTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        tts.setLanguage(Locale.getDefault());
                        tts.speak(book.getText(), TextToSpeech.QUEUE_ADD, null, null);
                    }
                }
            });
            return null;
        }
    }
}
