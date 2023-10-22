///////////////////////////////////////////////////////////////////////////////
// Title:              HotelTester
// Files:              HotelTester.java, Hotel.java, Room.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@uscd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A class to test the Hotel and Room class.
 *
 * Bugs: None known
 *
 * @author Rocky Hankin */
public class HotelTester {
    /**
     * Execution point of testing code for the Hotel and Room class.
     *
     * DO NOT MODIFY METHOD DECLARATION.
     */
    public static void main(String[] args) {
        Room room1 = new Room(1, new String[] {"John", "Mary"}, false);
        Room room2 = new Room(2); //creates a free room
        Room room3 = new Room(room1);

        Room[] roomList = new Room[] {room1, room2, room3};

        Room r1 = new Room(101, new String[] {"Benson", "Jack"}, false);
        Room r2 = new Room(121, new String[] {"Prajwala", "Sasya"}, false);
        Room r3 = new Room(90);
        Room[] roomList1 = new Room[] {r1, r2, r3};
        Hotel h1 = new Hotel("Marriott", roomList1);

        for(int i = 0; i < h1.getRooms().length; i++)
        {
            for(int j = 0; j < h1.getRooms()[i].getGuestNames().length; j++)
            {
                System.out.print(h1.getRooms()[i].getGuestNames()[j] + " ");
            }
            System.out.println();
        }

        String[] guestList = new String[] {"Bao", "James"};
        h1.bookRoom(guestList);

        for(int i = 0; i < h1.getRooms().length; i++)
        {
            for(int j = 0; j < h1.getRooms()[i].getGuestNames().length; j++)
            {
                System.out.print(h1.getRooms()[i].getGuestNames()[j] + " ");
            }
            System.out.println();
        }
        Hotel hotel1 = new Hotel("Hotel California", roomList);

        // Testing for simple assignment within constructor.
        // We do NOT want a shallow copy - we want a deep copy.
        // As such, if both arrays have the same reference, then this test fails.
        if (hotel1.getRooms() == roomList) {
            System.out.println("Test Failed! Shallow copy detected.");
        }

        // Testing `bookRoom()` for `hotel1`.
        int output = hotel1.bookRoom(new String[] {"Paul", "Mark"});
        int expected = 2;
        if (output != expected) {
            System.out.println("Test Failed! Booking status does not match.");
        }
        if(!hotel1.getRooms()[1].getGuestNames()[0].equals("Paul"))
        {
            System.out.println("Test Failed! Booking status does not match.");
        }
        // You should write more tests to ensure deep copy (and copy constructor) work properly,
        // and to test `bookRoom()` and `bookRoom(int roomNumber)`.
    }
}
