package fr.ajoriaux.tdd;

public interface BookDataService {
    Book getBookData(String isbn);

    Book getBookDataByTitle(String title);

    Book getBookDataByAuthor(String author);
    
    void addBook(Book book);
    
    void updateBook(Book book);
    
    void removeBook(String isbn);
}
