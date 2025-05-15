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

            int choice;
            while (true) {
                String input = scanner.nextLine();
                try {
                    choice = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Nepareiza izvēle. Ievadiet skaitli!");
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("Ievadiet viesa vārdu: ");
                    String name = scanner.nextLine();

                    int roomNumber;
                    while (true) {
                        System.out.println("Ievadiet room numuru: ");
                        String input = scanner.nextLine();
                        try {
                            roomNumber = Integer.parseInt(input);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Nepareizi! Ievadiet room numuru vēlreiz!");
                        }
                    }

                    LocalDate checkIn;
                    while (true) {
                        System.out.println("Ievadiet ierašanās datumu (YYYY-MM-DD): ");
                        String input = scanner.nextLine();
                        try {
                            checkIn = LocalDate.parse(input);
                            break;
                        } catch (Exception e) {
                            System.out.println("Nepareizs datuma formāts! Ievadiet vēlreiz!");
                        }
                    }

                    LocalDate checkOut;
                    while (true) {
                        System.out.println("Ievadiet izbraukšanas datumu (YYYY-MM-DD): ");
                        String input = scanner.nextLine();
                        try {
                            checkOut = LocalDate.parse(input);
                            break;
                        } catch (Exception e) {
                            System.out.println("Nepareizs datuma formāts! Ievadiet vēlreiz!");
                        }
                    }

                    manager.addReservation(name, roomNumber, checkIn, checkOut);
                    System.out.println("Reservācija pievienota!");
                    break;

                case 2:
                    int deleteRoom;
                    while (true) {
                        System.out.println("Ievadiet dzēšamās rezervācijas numura numuru: ");
                        String input = scanner.nextLine();
                        try {
                            deleteRoom = Integer.parseInt(input);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Nepareizi! Ievadiet room numuru vēlreiz!");
                        }
                    }
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
                    System.out.println("Ievadiet viesa vārdu meklēšanai: ");
                    String searchName = scanner.nextLine();
                    var found = manager.searchByGuestName(searchName);
                    if (found.isEmpty()) {
                        System.out.println("Nav rezervāciju ar tādu vārdu!");
                    } else {
                        found.forEach(System.out::println);
                    }
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

