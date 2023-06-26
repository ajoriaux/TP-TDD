package fr.ajoriaux.tdd;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookValidatorTest {
	BookDataService webService;
	
	@Test
	public void webServiceExist() {
		webService = mock(BookDataService.class);
		verify(webService).getBookData("2714493238");
	}
}
