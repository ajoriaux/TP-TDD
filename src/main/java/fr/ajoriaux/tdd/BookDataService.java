package fr.ajoriaux.tdd;

import java.util.ArrayList;

public interface BookDataService {
    Book getBookData(String isbn);

    ArrayList<Book> getBookDataByTitle(String title);

    ArrayList<Book> getBookDataByAuthor(String author);
    
    void addBook(Book book);
    
    void updateBook(Book book);
    
    void removeBook(String isbn);
}
