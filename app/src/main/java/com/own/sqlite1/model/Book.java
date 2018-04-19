package com.own.sqlite1.model;

/**
 * Created by Paola on 13/04/2018.
 */

public class Book {
    private String idBook;
    private String name;
    private String author;
    private int isbn;
    private String genre;
    private int pagesNum;
    private int year;

    public Book(String idBook, String name, String author, int isbn, String genre, int pagesNum, int year) {
        this.idBook = idBook;
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.pagesNum = pagesNum;
        this.year = year;
    }

    public Book(){
        this("", "", "", 0, "", 0, 0);
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(int pagesNum) {
        this.pagesNum = pagesNum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook='" + idBook + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", isbn=" + isbn +
                ", genre='" + genre + '\'' +
                ", pagesNum=" + pagesNum +
                ", year=" + year +
                '}';
    }
}
