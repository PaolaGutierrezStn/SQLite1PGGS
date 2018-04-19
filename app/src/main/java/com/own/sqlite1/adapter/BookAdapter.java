package com.own.sqlite1.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.support.v7.widget.Toolbar;

import com.own.sqlite1.R;
import com.own.sqlite1.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter <BookAdapter.BookViewHolder>
        implements View.OnClickListener {

    List<Book> books;
    View.OnClickListener listener;
    //Constructor
    public BookAdapter(List<Book> books){
        this.books=books;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se inflael cardview al reciclerview
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_book,parent,false);
        BookViewHolder holder=new BookViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        System.out.println(books.get(position));
        holder.txvName.setText(books.get(position).getName());
        holder.txvAuthor.setText(books.get(position).getAuthor());
        holder.txvIsbn.setText(String.valueOf(books.get(position).getIsbn()));
        holder.txvGenre.setText(books.get(position).getGenre());
        holder.txvPagesNum.setText(String.valueOf(books.get(position).getPagesNum()));
        holder.txvYear.setText(String.valueOf(books.get(position).getYear()));
        holder.imvBook.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class BookViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cvBook;
        TextView txvName;
        TextView txvAuthor;
        TextView txvIsbn;
        TextView txvGenre;
        TextView txvPagesNum;
        TextView txvYear;
        ImageView imvBook;
        ImageButton btnEditBook;
        ImageButton btnDeleteBook;
        View.OnClickListener listener;

        public BookViewHolder(View itemView) {
            super(itemView);
            cvBook=(CardView) itemView.findViewById(R.id.cv_book);
            imvBook=(ImageView)itemView.findViewById(R.id.imv_book);
            txvName=(TextView)itemView.findViewById(R.id.txv_name); //
            txvAuthor=(TextView)itemView.findViewById(R.id.txv_author);
            txvIsbn=(TextView)itemView.findViewById(R.id.txv_isbn);
            txvGenre=(TextView)itemView.findViewById(R.id.txv_genre);
            txvPagesNum=(TextView)itemView.findViewById(R.id.txv_pages_num);
            txvYear=(TextView)itemView.findViewById(R.id.txv_year);
            btnEditBook=(ImageButton) itemView.findViewById(R.id.btn_edit_book);
            btnDeleteBook=(ImageButton) itemView.findViewById(R.id.btn_delete_book);
            btnEditBook.setOnClickListener(this);
            btnDeleteBook.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener){
            this.listener=listener;

        }
    }
}
