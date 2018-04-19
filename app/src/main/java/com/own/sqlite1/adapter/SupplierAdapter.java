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
import com.own.sqlite1.model.Product;
import com.own.sqlite1.model.Supplier;

import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter <SupplierAdapter.SupplierViewHolder>
        implements View.OnClickListener {

    List<Supplier> suppliers;
    View.OnClickListener listener;
    //Constructor
    public SupplierAdapter(List<Supplier> suppliers){
        this.suppliers=suppliers;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public SupplierViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se inflael cardview al reciclerview
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_supplier,parent,false);
        SupplierViewHolder holder=new SupplierViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(SupplierViewHolder holder, int position) {
        System.out.println(suppliers.get(position));
        holder.txvName.setText(suppliers.get(position).getName());
        holder.txvLastName.setText(suppliers.get(position).getLastName());
        holder.txvType.setText(suppliers.get(position).getType());
        holder.imvSupplier.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class SupplierViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView crvSupplier;
        TextView txvName;
        TextView txvLastName;
        TextView txvType;
        ImageView imvSupplier;
        ImageButton btnEditSupplier;
        ImageButton btnDeleteSupplier;
        View.OnClickListener listener;

        public SupplierViewHolder(View itemView) {
            super(itemView);
            crvSupplier=(CardView) itemView.findViewById(R.id.crv_supplier);
            imvSupplier=(ImageView)itemView.findViewById(R.id.imv_supplier);
            txvName=(TextView)itemView.findViewById(R.id.txv_name);
            txvLastName=(TextView)itemView.findViewById(R.id.txv_lastname);
            txvType=(TextView)itemView.findViewById(R.id.txv_type);
            btnEditSupplier=(ImageButton) itemView.findViewById(R.id.btn_edit_supplier);
            btnDeleteSupplier=(ImageButton) itemView.findViewById(R.id.btn_delete_supplier);
            btnEditSupplier.setOnClickListener(this);
            btnDeleteSupplier.setOnClickListener(this);
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
