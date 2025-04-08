package lv.rvt;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReservationManager manager = new ReservationManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nViecnīcu rezervācija sistēma");
            System.out.println("1. Pievienot rezervāciju");
            System.out.println("2. Dzēst rezervāciju");
            System.out.println("3. Attēlot visas rezervācijas");
            System.out.println("4. Meklēt rezervācijas pēc viesa vārda");
            System.out.println("5. Iziet");
            System.out.println("Izvēlieties darbību: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Ievadiet viesa vārdu: ");
                    String name = scanner.nextLine();
                    System.out.println("Ievadiet room numuru: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ievadiet ierašanās datumu (YYYY-MM-DD): ");
                    LocalDate checkIn = LocalDate.parse(scanner.nextLine());
                    System.out.println("Ievadiet izbraukšanas datumu (YYYY-MM-DD): ");
                    LocalDate checkOut = LocalDate.parse(scanner.nextLine());

                    manager.addReservation(name, roomNumber, checkIn, checkOut);
                    System.out.println("Reservācija pievienota!");
                    break;
                
                case 2:
                    System.out.println("Ievadiet dzēšamās rezervācijas numura numuru: ");
                    int deleteRoom = scanner.nextInt();
                    if (manager.removeReservation(deleteRoom)) {
                        System.out.println("Reservācija dzēsta.");
                    } else {
                        System.out.println("Rezervācija netika atrasta.");
                    }
                    break;
                
                case 3:
                    System.out.println("Visas rezervācijas:");
                    manager.displayReservations();
                    break;
                
                case 4:
                    System.out.println("Ievadiet viesa vārdu mēklēšanai: ");
                    String searchName = scanner.nextLine();
                    manager.searchByGuestName(searchName).forEach(System.out::println);
                    break;
                
                case 5:
                    System.out.println("Iziet no programmas.");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("Nepareiza izvēle. Mēģiniet vēlreiz.");
            }
        }
    }
}

