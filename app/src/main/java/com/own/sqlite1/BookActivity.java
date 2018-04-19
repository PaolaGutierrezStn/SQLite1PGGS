package com.own.sqlite1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.own.sqlite1.adapter.BookAdapter;
import com.own.sqlite1.model.Book;
import com.own.sqlite1.sqlite.DBOperations;

public class BookActivity extends AppCompatActivity {
    private TextView edtName;
    private TextView edtAuthor;
    private TextView edtIsbn;
    private TextView edtGenre;
    private TextView edtPagesNum;
    private TextView edtYear;
    private Button btnSaveBook;
    private DBOperations operations;
    private Book book = new Book();
    private RecyclerView rcvBooks;
    private List<Book> books=new ArrayList<Book>();
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //iniciacion de la isntancia
        operations=DBOperations.getDBOperations(getApplicationContext());
        book.setIdBook("");

        initComponents();
    }

    private void initComponents(){
        rcvBooks=(RecyclerView)findViewById(R.id.rcv_books_list);
        rcvBooks.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rcvBooks.setLayoutManager(layoutManeger);
        //
        getBooks();
        adapter=new BookAdapter(books);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_delete_book:
                        operations.deleteBook(books.get(rcvBooks.getChildPosition((View)v.getParent().getParent())).getIdBook());
                        getBooks();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_edit_book:

                        Toast.makeText(getApplicationContext(),"Editar",Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getBookById(books.get(
                                rcvBooks.getChildPosition(
                                        (View)v.getParent().getParent())).getIdBook());
                        if (c!=null){
                            if (c.moveToFirst()){
                                book = new Book(c.getString(1),c.getString(2),c.getString(3),
                                        c.getInt(4), c.getString(5), c.getInt(6), c.getInt(6));
                            }
                            edtName.setText(book.getName());
                            edtAuthor.setText(book.getAuthor());
                            edtIsbn.setText(String.valueOf(book.getIsbn()));
                            edtGenre.setText(book.getGenre());
                            edtPagesNum.setText(String.valueOf(book.getPagesNum()));
                            edtYear.setText(String.valueOf(book.getYear()));
                        }else{
                            Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });

        rcvBooks.setAdapter(adapter);

        edtName=(EditText)findViewById(R.id.edt_name);
        edtAuthor=(EditText)findViewById(R.id.edt_author);
        edtIsbn=(EditText)findViewById(R.id.edt_isbn);
        edtGenre=(EditText)findViewById(R.id.edt_genre);
        edtPagesNum=(EditText)findViewById(R.id.edt_pages_num);
        edtYear=(EditText)findViewById(R.id.edt_year);

        btnSaveBook=(Button)findViewById(R.id.btn_save_book);

        btnSaveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!book.getIdBook().equals("")){
                    book.setName(edtName.getText().toString());
                    book.setAuthor(edtAuthor.getText().toString());
                    book.setIsbn(Integer.parseInt(edtIsbn.getText().toString()));
                    book.setGenre(edtGenre.getText().toString());
                    book.setPagesNum(Integer.parseInt(edtPagesNum.getText().toString()));
                    book.setYear(Integer.parseInt(edtYear.getText().toString()));
                    operations.updateBook(book);
                }else {
                    book = new Book("", edtName.getText().toString(),
                            edtAuthor.getText().toString(),
                            Integer.parseInt(edtIsbn.getText().toString()),
                            edtGenre.getText().toString(),
                            Integer.parseInt(edtPagesNum.getText().toString()),
                            Integer.parseInt(edtYear.getText().toString()));
                    operations.insertBook(book);
                }
                getBooks();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void getBooks(){
        Cursor c = operations.getBooks();
        books.clear();
        if(c!=null){
            while (c.moveToNext()){
                books.add(new Book(c.getString(1),c.getString(2),c.getString(3),c.getInt(4),
                        c.getString(5), c.getInt(6), c.getInt(7)));
            }
        }
    }

    private void clearData(){
        edtName.setText("");
        edtAuthor.setText("");
        edtIsbn.setText("");
        edtGenre.setText("");
        edtPagesNum.setText("");
        edtYear.setText("");
        book=null;
        book= new Book();
        book.setIdBook("");
    }
}
