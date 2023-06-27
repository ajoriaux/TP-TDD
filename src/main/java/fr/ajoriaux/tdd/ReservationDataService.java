package fr.ajoriaux.tdd;

import java.util.ArrayList;

public interface ReservationDataService {
    void createReservation(Reservation reservation);

    Reservation getReservation(String id);
    
    ArrayList<Reservation> getReservationsByMember(String id);

    void removeReservation(String id);
}