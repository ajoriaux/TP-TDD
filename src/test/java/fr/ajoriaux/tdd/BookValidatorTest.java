package fr.ajoriaux.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import java.util.ArrayList;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookValidatorTest {
	BookDataService dbService;
	BookDataService webService;
	BookManager manager;
	
	@BeforeEach
	public void initMocks() {
        dbService = mock(BookDataService.class);
        webService = mock(BookDataService.class);
        
        manager = new BookManager();
        manager.setDatabaseBookDataService(dbService);
        manager.setWebBookDataService(webService);
	}
	
	/// Recherche de livre(s) dans la base de donnée (par isbn, titre, ou auteur)  
	@Test
	public void searchBookInDatabaseByIsbn() {
		String isbn = "2714493238";
		Book book = new Book(isbn, "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true);
		when(dbService.getBookData(isbn)).thenReturn(book);
		manager.searchBookByIsbn(isbn);
		assertEquals(book, dbService.getBookData(isbn));
	}
	
	@Test
	public void searchBookInDatabaseByTitle() {
		String title = "Et c'est ainsi que nous vivrons";
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(new Book("2714493238", "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true));
		when(dbService.getBookDataByTitle(title)).thenReturn(books);
		manager.searchBookByTitle(title);
		assertEquals(books, dbService.getBookDataByTitle(title));
	}
	
	@Test
	public void searchBookInDatabaseByAuthor() {
		String author = "Douglas Kennedy";
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(new Book("2714493238", "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true));
		when(dbService.getBookDataByAuthor(author)).thenReturn(books);
		manager.searchBookByAuthor(author);
		assertEquals(books, dbService.getBookDataByAuthor(author));
	}
	
	/// Création d'un livre 
	@Test
	public void formatMustBeValidForCreation() {
		Book book = new Book("2714493238", "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "format", true);
		manager.setNewBook(book);
		assertFalse(manager.setNewBook(book));
	}
	
	@Test
	public void searchWebServiceIfDataMissingDuringCreation() {
		String isbn = "2714493238";
		Book book = new Book(isbn, "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true);
		when(webService.getBookData(isbn)).thenReturn(book);
		manager.setNewBook(new Book(isbn, "", "", "", "Broché", true));
		verify(webService).getBookData(isbn);
		verify(dbService).addBook(book);
		assertTrue(manager.setNewBook(book));
	}
	
	/// Modification d'un livre
	@Test
	public void formatMustBeValidForUpdate() {
		Book book = new Book("2714493238", "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "format", false);
		manager.updateBook(book);
		assertFalse(manager.updateBook(book));
	}
	
	@Test
	public void updateDataBookFromDatabase() {
		String isbn = "2714493238";
		Book book = new Book(isbn, "Nouveau titre", "Auteur", "Belfond", "Broché", false);
		when(dbService.getBookData(isbn)).thenReturn(new Book(isbn, "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true));
		manager.updateBook(book);
		verify(dbService).getBookData(isbn);
		assertTrue(manager.updateBook(book));
	}
	
	@Test
	public void oldDataMustBeDifferentFromNewData() {
		String isbn = "2714493238";
		Book book = new Book(isbn, "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true);
		when(dbService.getBookData(isbn)).thenReturn(book);
		manager.updateBook(book);
		verify(dbService).getBookData(isbn);
		assertFalse(manager.updateBook(book));
	}
	
	/// Suppression d'un livre
	@Test
	public void removeBookFromDatabase() {
		String isbn = "2714493238";
		manager.removeBook(isbn);
		verify(dbService).removeBook(isbn);
		assertTrue(manager.removeBook(isbn));
	}
	
	@Test
	public void isbnCannotBeEmptyForDeletion() {
		String isbn = "";
		manager.removeBook(isbn);
		verifyNoInteractions(dbService);
		assertFalse(manager.removeBook(isbn));
	}
}
