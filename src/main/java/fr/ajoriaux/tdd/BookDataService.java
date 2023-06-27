package fr.ajoriaux.tdd;

public interface BookDataService {
    Book getBookData(String isbn);

    void addBook(Book book);
    
    void updateBook(Book book);
}
