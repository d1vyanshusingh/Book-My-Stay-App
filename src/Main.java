import java.util.*;

class Room {
    int roomNumber;
    String type;
    boolean available;

    Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = true;
    }
}

class Booking {
    int bookingId;
    String customerName;
    int roomNumber;

    Booking(int bookingId, String customerName, int roomNumber) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}

public class Main {

    static ArrayList<Room> rooms = new ArrayList<>();
    static HashMap<Integer, Booking> bookings = new HashMap<>();
    static Queue<String> waitingList = new LinkedList<>();

    static int bookingCounter = 1;

    public static void main(String[] args) {

        rooms.add(new Room(101, "Deluxe"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(103, "Suite"));

        viewRooms();

        bookRoom("Rahul", 101);

        bookRoom("Amit", 101);

        cancelBooking(1);

        viewRooms();
    }

    static void viewRooms() {

        System.out.println("\nAvailable Rooms:");

        for (Room r : rooms) {
            if (r.available) {
                System.out.println("Room " + r.roomNumber + " - " + r.type);
            }
        }
    }

    static void bookRoom(String customerName, int roomNumber) {

        for (Room r : rooms) {

            if (r.roomNumber == roomNumber) {

                if (r.available) {

                    r.available = false;

                    Booking b = new Booking(bookingCounter, customerName, roomNumber);
                    bookings.put(bookingCounter, b);

                    System.out.println("\nBooking Successful!");
                    System.out.println("Booking ID: " + bookingCounter);
                    System.out.println("Customer: " + customerName);

                    bookingCounter++;

                } else {

                    System.out.println("\nRoom not available. Added to waiting list.");
                    waitingList.add(customerName);
                }

                return;
            }
        }

        System.out.println("Room not found.");
    }

    static void cancelBooking(int bookingId) {

        if (bookings.containsKey(bookingId)) {

            Booking b = bookings.get(bookingId);

            for (Room r : rooms) {
                if (r.roomNumber == b.roomNumber) {
                    r.available = true;
                }
            }

            bookings.remove(bookingId);

            System.out.println("\nBooking Cancelled for room " + b.roomNumber);

            if (!waitingList.isEmpty()) {

                String nextCustomer = waitingList.poll();

                System.out.println("Next customer from waiting list: " + nextCustomer);
            }

        } else {

            System.out.println("Invalid Booking ID");
        }
    }
}