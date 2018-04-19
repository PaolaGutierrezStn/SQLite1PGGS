package com.own.sqlite1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.own.sqlite1.adapter.DetailAdapter;
import com.own.sqlite1.adapter.PurchaseDetailAdapter;
import com.own.sqlite1.model.OrderDetail;
import com.own.sqlite1.model.OrderHeader;
import com.own.sqlite1.model.Product;
import com.own.sqlite1.model.PurchaseDetail;
import com.own.sqlite1.model.PurchaseHeader;
import com.own.sqlite1.sqlite.Contract;
import com.own.sqlite1.sqlite.DBOperations;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDetailActivity extends AppCompatActivity{
    private Spinner spiProduct;
    private EditText edtQuantity;
    private EditText edtPrice;
    private Button btnSave;
    private DBOperations operations;
    private PurchaseHeader purchase;
    private PurchaseDetail detail;
    private RecyclerView rcvDetail;
    private List<PurchaseDetail> details =new ArrayList<PurchaseDetail>();
    private List<Integer> sequences =new ArrayList<Integer>();
    private List<Product> products =new ArrayList<Product>();
    private PurchaseDetailAdapter purchaseDetailAdapter;
    private SimpleCursorAdapter productAdapter;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);
        Bundle bundle = getIntent().getExtras();
        purchase = (PurchaseHeader) bundle
                .getSerializable("purchase");
        operations=DBOperations.getDBOperations(getApplicationContext());
        initComponents();
    }
    private void initComponents(){
        rcvDetail = findViewById(R.id.rcv_detail);
        rcvDetail.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rcvDetail.setLayoutManager(layoutManager);

        getDetails(purchase.getIdPurchaseHeader());
        getProducts();
        purchaseDetailAdapter =new PurchaseDetailAdapter(details, operations);

        purchaseDetailAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_delete:
                        detail = details.get(rcvDetail.getChildPosition((View)v.getParent().getParent()));
                        operations.deletePurchaseDetail(detail.getIdPurchaseHeader(), String.valueOf(
                                detail.getSequence()));
                        getDetails(purchase.getIdPurchaseHeader());
                        purchaseDetailAdapter.notifyDataSetChanged();
                        clearData();
                        break;
                    case R.id.btn_edit:
                        detail = details.get(
                                rcvDetail.getChildPosition((View)v.getParent().getParent()));
                        int sequence = detail.getSequence();
                        System.out.println("SEQUENCE: "+sequence);
                        Cursor c = operations.getProductById(detail.getIdProduct());
                        if(c.moveToFirst()) {
                            for (Product product : products) {
                                System.out.println("Cursor: "+c.getString(1));
                                if (c.getString(1).equals(product.getIdProduct())) {
                                    PurchaseDetailActivity.this.product = product;
                                    break;
                                }
                            }
                        }
                        spiProduct.setSelection(products.indexOf(product));
                        edtQuantity.setText(String.valueOf(detail.getQuantity()));
                        edtPrice.setText(String.valueOf(detail.getPrice()));

                }

            }
        });
        rcvDetail.setAdapter(purchaseDetailAdapter);

        spiProduct =(Spinner) findViewById(R.id.spi_product);

        productAdapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                operations.getProducts(),
                new String[]{Contract.Products.NAME, Contract.Products.PRICE},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spiProduct.setAdapter(productAdapter);

        edtQuantity = findViewById(R.id.edt_quantity);
        edtQuantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                return false;
            }
        });
        edtPrice= findViewById(R.id.edt_price);


        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product = products.get(spiProduct.getSelectedItemPosition());
                if (detail!=null && !detail.getIdPurchaseHeader().equals("")){
                    detail.setIdProduct(product.getIdProduct());
                    try {
                        detail.setQuantity(Integer.parseInt(
                                edtQuantity.getText().toString()));
                        setAmount();
                        detail.setPrice(Float.parseFloat(
                                edtPrice.getText().toString()));
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    operations.updatePurchaseDetail(detail);

                }else {
                    try {
                        detail = new PurchaseDetail(purchase.getIdPurchaseHeader(),
                                0,
                                product.getIdProduct(),
                                Integer.parseInt(
                                        edtQuantity.getText().toString()),
                                Float.parseFloat(
                                        edtPrice.getText().toString()));
                        getSequence();

                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    operations.insertPurchaseDetail(detail);
                }
                getDetails(purchase.getIdPurchaseHeader());
                clearData();
                purchaseDetailAdapter.notifyDataSetChanged();
            }
        });

    }
    private void getDetails(String id){
        Cursor c =operations.getPurchaseDetailById(id);
        details.clear();
        sequences.clear();
        if(c!=null){
            while (c.moveToNext()){
                details.add(new PurchaseDetail(c.getString(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getInt(4),
                        c.getFloat(5)));
                sequences.add(c.getInt(2));
            }
            System.out.println(details);
        }
    }


    private void getProducts(){
        Cursor c =operations.getProducts();
        products.clear();
        if(c!=null){
            while (c.moveToNext()){
                products.add(new Product(
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3),
                        c.getFloat(4)));
            }
        }
    }

    private void clearData(){
        spiProduct.setSelection(0);
        edtQuantity.setText("");
        edtPrice.setText("");
        detail =null;
        detail = new PurchaseDetail();
        detail.setSequence(0);
    }

    private void setAmount(){
        if(detail!=null){
            try {
                detail.setPrice(Float.parseFloat(
                        edtQuantity.getText().toString())
                        * product.getPrice());
            }catch (Exception e) {
                detail.setPrice(0);
            }
            edtPrice.setText(String.valueOf(detail.getPrice()));
        }

    }

    private void getSequence(){

        if(detail.getSequence()==0) {
            int newId = 1;
            detail.setSequence(newId);
            if (details.size() > 0) {
                sequences.clear();
                for (PurchaseDetail detail : details) {
                    sequences.add(detail.getSequence());
                }
                for (int i = 0; i <= sequences.size(); i++) {
                    newId = i + 1;
                    if (!sequences.contains(newId)) {
                        detail.setSequence(newId);
                        break;
                    }
                }
            }
        }

    }


}
