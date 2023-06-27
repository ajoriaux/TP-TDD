package fr.ajoriaux.tdd;

public interface ReservationDataService {
    void createReservation(Reservation reservation);

    Reservation getReservation(String id);
}