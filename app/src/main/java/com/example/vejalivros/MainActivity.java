package com.example.vejalivros;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vejalivros.model.Book;
import com.example.vejalivros.rest.Config;
import com.example.vejalivros.rest.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetBookTask rest = new GetBookTask();
        rest.execute();
    }

    public void onResponseBooks(String json) {
        try {
            JSONObject jsonRes = new JSONObject(json);
            final ArrayList<Book> books = new ArrayList<Book>();
            JSONArray data = jsonRes.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);
                int id = (int)item.get("id");
                String author = (String)(item.getJSONObject("author").get("name"));
                String title = (String)item.get("title");
                String text = (String)item.get("text");

                Book book = new Book(id, author, title, text);
                books.add(book);
            }
            ListView listView = (ListView)findViewById(R.id.listView);

            BooksListViewAdapter customAdapter = new BooksListViewAdapter(books);
            listView.setAdapter(customAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    Book actualBook = books.get(i);
                    onClickBook(actualBook);
                }
            });

        } catch (JSONException e) {
            new AlertDialog.Builder(this).setTitle("Um erro ocorreu")
                    .setMessage("Um erro ocorreu ao captar os livros em nosso acervo, por favor, reinicie a aplicação")
                    .show();
        }
    }

    public void onClickBook(Book book) {
        Intent intent = new Intent(this, ReadBookActivity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }

    class GetBookTask extends AsyncTask<String, Integer, String> {
        ProgressDialog loading;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            loading = ProgressDialog.show(MainActivity.this, "Carregando Livros ...","Espere por favor...",false,false);
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            loading.dismiss();
            onResponseBooks(s);
        }

        @Override
        protected String doInBackground(String ...urls) {
            RequestHandler rh = new RequestHandler();
            return rh.get(Config.getBooks);
        }
    }

    class BooksListViewAdapter extends BaseAdapter {
        private ArrayList<Book> books;

        BooksListViewAdapter(ArrayList<Book> books) {
            this.books = books;
        }

        @Override
        public int getCount() {
            return this.books.size();
        }

        @Override
        public  Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView_book = (TextView) view.findViewById(R.id.textView);
            TextView textView_author = (TextView) view.findViewById(R.id.textView2);

            textView_author.setText(this.books.get(i).getAuthor());
            textView_book.setText(this.books.get(i).getName());

            return view;
        }
    }
}
