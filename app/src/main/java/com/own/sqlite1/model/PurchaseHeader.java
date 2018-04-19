package com.own.sqlite1.model;

import java.io.Serializable;

/**
 * Created by Paola on 12/04/2018.
 */

public class PurchaseHeader implements Serializable {
    private String idPurchaseHeader;
    private String idSupplier;
    private String idMethodPayment;
    private String date;

    public PurchaseHeader(String idPurchaseHeader, String idSupplier, String idMethodPayment, String date) {
        this.idPurchaseHeader = idPurchaseHeader;
        this.idSupplier = idSupplier;
        this.idMethodPayment = idMethodPayment;
        this.date = date;
    }

    public PurchaseHeader(){
        this("", "", "", "");
    }

    public String getIdPurchaseHeader() {
        return idPurchaseHeader;
    }

    public void setIdPurchaseHeader(String idPurchaseHeader) {
        this.idPurchaseHeader = idPurchaseHeader;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getIdMethodPayment() {
        return idMethodPayment;
    }

    public void setIdMethodPayment(String idMethodPayment) {
        this.idMethodPayment = idMethodPayment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PurchaseHeader{" +
                "idPurchaseHeader='" + idPurchaseHeader + '\'' +
                ", idSupplier='" + idSupplier + '\'' +
                ", idMethodPayment='" + idMethodPayment + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
