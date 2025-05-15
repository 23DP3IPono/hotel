package lv.rvt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lv.rvt.tools.Helper;

public class ReservationManager {
    private List<Reservation> reservations;
    private static final String CSV_FILE = "reservations.csv";

    public ReservationManager() {
        this.reservations = new ArrayList<>();
        loadReservationsFromCSV();
    }

    public void addReservation(String guestName, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        reservations.add(new Reservation(guestName, roomNumber, checkIn, checkOut));
        saveReservationsToCSV();
    }

    public boolean removeReservation(int roomNumber) {
        boolean removed = reservations.removeIf(reservation -> reservation.getRoomNumber() == roomNumber);
        if (removed) {
            saveReservationsToCSV();
        }
        return removed;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Reservation> searchByGuestName(String name) {
        return reservations.stream()
                .filter(reservation -> reservation.getGuestName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void displayReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Nav nevienas rezervācijas.");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    private void saveReservationsToCSV() {
        try (BufferedWriter writer = Helper.getWriter(CSV_FILE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Reservation reservation : reservations) {
                writer.write(reservation.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadReservationsFromCSV() {
        try (BufferedReader reader = Helper.getReader(CSV_FILE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] fields = line.split(",");
                if (fields.length < 4) {
                    System.err.println("Invalid line: " + line);
                    continue;
                }
                String guestName = fields[0];
                int roomNumber = Integer.parseInt(fields[1]);
                LocalDate checkInDate = LocalDate.parse(fields[2]);
                LocalDate checkOutDate = LocalDate.parse(fields[3]);
                reservations.add(new Reservation(guestName, roomNumber, checkInDate, checkOutDate));
            }
        } catch (IOException e) {
            
        }
    }
}