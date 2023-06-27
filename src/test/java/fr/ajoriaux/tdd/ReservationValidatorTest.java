package fr.ajoriaux.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationValidatorTest {
	ReservationDataService dbService;
	ReservationManager manager;
	
	@BeforeEach
	public void initMocks() {
        dbService = mock(ReservationDataService.class);
        
        manager = new ReservationManager();
        manager.setDatabaseReservationDataService(dbService);
	}
	
	/// Ajout d'une réservation 
	@Test
	public void addReservationToDatabase() {
		Book book = new Book("2714493238", "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true);
		Member member = new Member("MEM1", "Henry", "Thierry", new Date(1984, 4, 8), "M");
		Reservation reservation = new Reservation("1", member, book, new Date(2023, 5, 26), new Date(2023, 9, 26));
		manager.addReservation(reservation);
		verify(dbService).createReservation(reservation);
		assertTrue(manager.addReservation(reservation));
	}
	
	/// Recherche adhérent par code
	@Test
	public void searchReservation() {
		String id = "1";
		Book book = new Book("2714493238", "Et c'est ainsi que nous vivrons", "Douglas Kennedy", "Belfond", "Broché", true);
		Member member = new Member("MEM1", "Henry", "Thierry", new Date(1984, 4, 8), "M");
		Reservation reservation = new Reservation(id, member, book, new Date(2023, 5, 26), new Date(2023, 9, 26));
		when(dbService.getReservation(id)).thenReturn(reservation);
		manager.searchReservation(id);
		assertEquals(reservation, dbService.getReservation(id));
	}
	
	/// Suppression d'une réservation 
	@Test
	public void removeReservationFromDatabase() {
		String id = "1";
		manager.removeReservation(id);
		verify(dbService).removeReservation(id);
		assertTrue(manager.removeReservation(id));
	}
}
