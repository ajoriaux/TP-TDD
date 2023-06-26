package fr.ajoriaux.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
	public void bookInDatabase() {
		String isbn = "2714493238";
        dbService = new BookDataService() {
           @Override
           public Book getBookData(String isbn) {
               return new Book(isbn, "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true);
           }
       };
       verify(dbService).getBookData(isbn);
	}
}
