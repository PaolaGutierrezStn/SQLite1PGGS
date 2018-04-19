package com.own.sqlite1.sqlite;

import java.util.UUID;

/**
 * Created by devtacho on 9/03/18.
 */

public class Contract {
    interface OrderHeaderColumns{
        String ID = "id";
        String ID_CUSTOMER = "id_customer";
        String ID_METHOD_PAYMENT = "id_method_payment";
        String DATE = "record_date";
    }

    interface OrderDetailColumns{
        String ID = "id";
        String SEQUENCE = "sequence";
        String ID_PRODUCT = "id_product";
        String QUANTITY = "quantity";
        String PRICE = "price";
    }

    interface ProductColumns{
        String ID = "id";
        String NAME = "name";
        String PRICE = "price";
        String STOCK = "stock";
    }

    interface  CustomerColumns{
        String ID = "id";
        String FIRSTNAME = "firstname";
        String LASTNAME = "lastname";
        String PHONE = "phone";
    }

    interface MethodPaymentColumns{
        String ID = "id";
        String NAME = "name";
    }

    //
    interface  SupplierColumns{
        String ID = "id";
        String NAME = "name";
        String LASTNAME = "lastName";
        String TYPE = "type";
    }

    interface PurchaseHeaderColumns{
        String ID = "id";
        String ID_SUPPLIER = "id_supplier";
        String ID_METHOD_PAYMENT = "id_method_payment";
        String DATE = "record_date";
    }

    interface PurchaseDetailColumns{
        String ID = "id";
        String SEQUENCE = "sequence";
        String ID_PRODUCT = "id_product";
        String QUANTITY = "quantity";
        String PRICE = "price";
    }

    interface BookColumns{
        String ID = "id";
        String NAME = "name";
        String AUTHOR = "author";
        String ISBN = "isbn";
        String GENRE = "genre";
        String PAGESNUM = "pagesNum";
        String YEAR = "year";
    }

    public static class OrderHeaders implements OrderHeaderColumns{
        public static String generateIdOrderHeader(){
            return  "OH-"+ UUID.randomUUID().toString();
        }
    }

    public static class OrderDetails implements OrderDetailColumns{

    }

    public static class Products implements ProductColumns{
        public static String generateIdProduct(){
            return  "PR-"+ UUID.randomUUID().toString();
        }
    }

    public static class Customers implements CustomerColumns{
        public static String generateIdCustomer(){
            return  "CU-"+ UUID.randomUUID().toString();
        }
    }

    public static class MethodsPayment implements MethodPaymentColumns{
        public static String generateIdMethodPayment(){
            return  "MP-"+ UUID.randomUUID().toString();
        }
    }

    //
    public static class Suppliers implements SupplierColumns{
        public static String generateIdSupplier(){
            return  "SU-"+ UUID.randomUUID().toString();
        }
    }

    public static class PurchaseHeaders implements PurchaseHeaderColumns{
        public static String generateIdPurchaseHeader(){
            return  "PH-"+ UUID.randomUUID().toString();
        }
    }

    public static class PurchaseDetails implements PurchaseDetailColumns{

    }

    public static class Books implements BookColumns{
        public static String generateIdBook(){
            return  "BO-"+ UUID.randomUUID().toString();
        }
    }


    private Contract() {

    }


}
