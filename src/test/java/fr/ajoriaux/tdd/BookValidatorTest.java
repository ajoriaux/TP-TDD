package fr.ajoriaux.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
