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
    
    public String getLocator(String isbn) throws NotFoundException {
        Book book = dbBookDataService.getBookData(isbn);
        if (book == null) {
        	book = webBookDataService.getBookData(isbn);
        }
        if (book == null) throw new NotFoundException();
        String locator = isbn.substring(isbn.length() - 4)
                + book.getAuthor().charAt(0)
                + book.getTitle().split(" ").length;
        return locator;
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
