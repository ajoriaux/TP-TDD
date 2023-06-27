package fr.ajoriaux.tdd;

import java.util.ArrayList;

public class ReservationManager {
    private ReservationDataService dbReservationDataService;

    public ReservationDataService getDatabaseReservationDataService() {
        return dbReservationDataService;
    }

    public void setDatabaseReservationDataService(ReservationDataService dbReservationDataService) {
        this.dbReservationDataService = dbReservationDataService;
    }
    
    public boolean addReservation(Reservation reservation, String memberId) {
    	ArrayList<Reservation> reservations = dbReservationDataService.getReservationsByMember(memberId);
    	if (2 < reservations.size()) {
    		return false;
    	}
    	
    	dbReservationDataService.createReservation(reservation);
    	return true;
    }
    
    public Reservation searchReservation(String id) {
    	Reservation reservation = dbReservationDataService.getReservation(id);
    	return reservation;
    }
    
    public boolean removeReservation(String id) {
    	dbReservationDataService.removeReservation(id);
    	return true;
    }
}
