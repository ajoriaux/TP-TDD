package fr.ajoriaux.tdd;

public class BookManager {
    private BookDataService dbBookDataService;
    
    private BookDataService webBookDataService;

    public BookDataService getDatabaseBookDataService() {
        return dbBookDataService;
    }

    public void setDatabaseBookDataService(BookDataService dbBookDataService) {
        this.dbBookDataService = dbBookDataService;
    }
    
    public void setWebBookDataService(BookDataService webService) {
    	this.webBookDataService = webService;
    }
    
    public Book searchBookByIsbn(String isbn) {
        Book book = dbBookDataService.getBookData(isbn);
		return book;
    }
    
    public Book searchBookByTitle(String title) {
        Book book = dbBookDataService.getBookDataByTitle(title);
		return book;
    }
    
    public Book searchBookByAuthor(String author) {
        Book book = dbBookDataService.getBookDataByAuthor(author);
		return book;
    }
    
    public boolean setNewBook(Book book) {
    	if (book.getFormat() != "Poche" && book.getFormat() != "Broché" && book.getFormat() != "Grand Format") {
    		return false;
    	}
    	
    	if ("" == book.getTitle() || "" == book.getAuthor() || "" == book.getAuthor()) {
    		book = webBookDataService.getBookData(book.getIsbn());
    	}
    	
    	dbBookDataService.addBook(book);
    	return true;
    }
    
    public boolean updateBook(Book newBook) {
    	if (newBook.getFormat() != "Poche" && newBook.getFormat() != "Broché" && newBook.getFormat() != "Grand Format") {
    		return false;
    	}
    	
    	Book book = dbBookDataService.getBookData(newBook.getIsbn());
    	if (book == newBook) {
    		return false;
    	}
    	
    	if (!(newBook.getTitle() == book.getTitle())) {
    		book.setTitle(newBook.getTitle());
    	}
    	if (!(newBook.getAuthor() == book.getAuthor())) {
    		book.setAuthor(newBook.getAuthor());
    	}
    	if (!(newBook.getEditor() == book.getEditor())) {
    		book.setTitle(newBook.getTitle());
    	}
    	if (!(newBook.getEditor() == book.getTitle())) {
    		book.setEditor(newBook.getEditor());
    	}
    	if (!(newBook.getFormat() == book.getFormat())) {
    		book.setFormat(newBook.getFormat());
    	}
    	if (!(newBook.isAvailable() == book.isAvailable())) {
    		book.setAvailable(newBook.isAvailable());
    	}
    	
    	dbBookDataService.updateBook(book);
    	return true;
    }
    
    public boolean removeBook(String isbn) {
    	if ("" == isbn) {
    		return false;
    	}
    	dbBookDataService.removeBook(isbn);
    	return true;
    }
}
