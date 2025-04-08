package lv.rvt;
import java.time.LocalDate;

public class Reservation {
    private String guestName;
    private int roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(String guestName, int roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String toCSV() {
        return guestName + "," + roomNumber + "," + checkInDate + "," + checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation: " +
                "Viesa vārds='" + guestName + '\'' +
                ", Numura numurs=" + roomNumber +
                ", Ierašanās datums=" + checkInDate +
                ", Izrakstīšanās datums=" + checkOutDate +
                '.';
    }
}

