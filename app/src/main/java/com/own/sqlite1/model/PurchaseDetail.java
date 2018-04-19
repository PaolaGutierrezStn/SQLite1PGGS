package com.own.sqlite1.model;

/**
 * Created by Paola on 12/04/2018.
 */

public class PurchaseDetail {
    private String idPurchaseHeader;
    private int sequence;
    private String idProduct;
    private int quantity;
    private float price;

    public PurchaseDetail(String idPurchaseHeader, int sequence, String idProduct, int quantity, float price) {
        this.idPurchaseHeader = idPurchaseHeader;
        this.sequence = sequence;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public PurchaseDetail(){
        this("", 0, "", 0, 0.0F);
    }

    public String getIdPurchaseHeader() {
        return idPurchaseHeader;
    }

    public void setIdPurchaseHeader(String idPurchaseHeader) {
        this.idPurchaseHeader = idPurchaseHeader;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PurchaseDetail{" +
                "idPurchaseHeader='" + idPurchaseHeader + '\'' +
                ", sequence=" + sequence +
                ", idProduct='" + idProduct + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
