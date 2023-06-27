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
    
    public void setNewBook(Book book) {
    	if ("" == book.getTitle() || "" == book.getAuthor() ||
    		"" == book.getAuthor() || "" == book.getFormat()) {
    		book = webBookDataService.getBookData(book.getIsbn());
    	}
    	dbBookDataService.addBook(book);
    }
    
    public boolean updateBook(Book book) {
    	
    	return true;
    }
}
