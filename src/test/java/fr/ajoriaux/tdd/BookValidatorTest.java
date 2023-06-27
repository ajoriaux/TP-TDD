package fr.ajoriaux.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	
	@Test
	public void bookInDatabase() throws NotFoundException {
		String isbn = "2714493238";
		when(dbService.getBookData(isbn)).thenReturn(new Book(isbn, "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true));
		manager.getLocator(isbn);
		verify(dbService).getBookData(isbn);
	}
	
	@Test
	public void searchWebServiceIfDataMissingDuringCreation() {
		String isbn = "2714493238";
		when(webService.getBookData(isbn)).thenReturn(new Book(isbn, "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true));
		manager.setNewBook(new Book(isbn, "", "", "", "", true));
		verify(webService).getBookData(isbn);
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
}
