package com.own.sqlite1.adapter;


import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.own.sqlite1.R;
import com.own.sqlite1.model.Customer;
import com.own.sqlite1.model.MethodPayment;
import com.own.sqlite1.model.OrderHeader;
import com.own.sqlite1.model.PurchaseHeader;
import com.own.sqlite1.model.Supplier;
import com.own.sqlite1.sqlite.DBOperations;

import java.util.List;


public class PurchaseAdapter extends RecyclerView.Adapter <PurchaseAdapter.PurchaseViewHolder>
        implements View.OnClickListener {

    List<PurchaseHeader> purchases;
    Supplier supplier;
    MethodPayment method;
    DBOperations operations;
    View.OnClickListener listener;
    //Constructor
    public PurchaseAdapter(List<PurchaseHeader> purchases, DBOperations operations){
        this.purchases = purchases;
        this.operations = operations;

    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public PurchaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se infla el cardview al reciclerview
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_cell_layout,parent,false);
        PurchaseViewHolder holder=new PurchaseViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(PurchaseViewHolder holder, int position) {
        Cursor c = operations.getSupplierById(purchases.get(position).getIdSupplier());
        if(c.moveToNext()){
            supplier = new Supplier(c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4));
        }
        c=operations.getMethodPaymentById(purchases.get(position).getIdMethodPayment());
        if(c.moveToNext()){
            method = new MethodPayment(c.getString(1), c.getString(2));
        }
        holder.txvSupplier.setText(supplier.getName()+" "+supplier.getLastName());
        holder.txvMethod.setText(method.getName());
        holder.txvDate.setText(purchases.get(position).getDate());
        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class PurchaseViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView crvPurchase;
        TextView txvSupplier;
        TextView txvMethod;
        TextView txvDate;
        ImageButton btnEdit;
        ImageButton btnDelete;
        ImageButton btnDetail;
        View.OnClickListener listener;




        public PurchaseViewHolder(View itemView) {
            super(itemView);
            crvPurchase=(CardView) itemView.findViewById(R.id.crv_purchase);
            txvSupplier=(TextView)itemView.findViewById(R.id.txv_supplier);
            txvMethod=(TextView)itemView.findViewById(R.id.txv_method);
            txvDate=(TextView)itemView.findViewById(R.id.txv_date);
            btnEdit =(ImageButton) itemView.findViewById(R.id.btn_edit);
            btnDelete =(ImageButton) itemView.findViewById(R.id.btn_delete);
            btnDetail =(ImageButton) itemView.findViewById(R.id.btn_detail);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
            btnDetail.setOnClickListener(this);
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
