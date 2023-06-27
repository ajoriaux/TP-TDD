package fr.ajoriaux.tdd;

public class ReservationManager {
    private ReservationDataService dbReservationDataService;

    public ReservationDataService getDatabaseReservationDataService() {
        return dbReservationDataService;
    }

    public void setDatabaseReservationDataService(ReservationDataService dbReservationDataService) {
        this.dbReservationDataService = dbReservationDataService;
    }
    
    public boolean addReservation(Reservation reservation) {
    	dbReservationDataService.createReservation(reservation);
    	return true;
    }
    
    public Reservation searchReservation(String id) {
    	Reservation reservation = dbReservationDataService.getReservation(id);
    	return reservation;
    }
}
