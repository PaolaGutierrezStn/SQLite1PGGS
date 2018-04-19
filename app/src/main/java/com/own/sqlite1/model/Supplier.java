package com.own.sqlite1.model;

/**
 * Created by Paola on 12/04/2018.
 */

public class Supplier {
    private String idSupplier;
    private String name;
    private String lastName;
    private String type;

    public Supplier(String idSupplier, String name, String lastName, String type) {
        this.idSupplier = idSupplier;
        this.name = name;
        this.lastName = lastName;
        this.type = type;
    }

    public Supplier(){
        this("", "", "", "");
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "idSupplier='" + idSupplier + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
