///////////////////////////////////////////////////////////////////////////////
// Main Class File:    HotelTester.java
// File:               Hotel.java
// Quarter:            CSE 8B Winter 2022
//
// Author:             Rocky Hankin
// Email:              rhankin@ucsd.edu
// Instructor's Name:  Greg Miranda
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A class that maintains the attributes of a hotel room. This includes the name
 * of the hotel and a list of rooms.
 *
 * Bugs: None found.
 *
 * @author Rocky Hankin
 */
public class Hotel {
    // DO NOT CHANGE THE CODE FOR THE PRIVATE MEMBER VARIABLES.
    private String name;
    private Room[] rooms;

    /**
     * Constructor to initialize the Hotel object.
     * You should be performing a deep copy.
     *
     * DO NOT MODIFY THE CONSTRUCTOR DECLARATION.
     *
     * @param name the name to (deep) initialize with
     * @param rooms the rooms to (deep) copy from
     */
    public Hotel(String name, Room[] rooms) {
        this.name = name;
        this.rooms = new Room[rooms.length];
        for(int i = 0; i < rooms.length; i++)
        {
            this.rooms[i] = new Room(rooms[i]);
        }
    }

    /**
     * Getter method to return `name`.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method to return `rooms`.
     *
     * DO NOT MODIFY THIS METHOD.
     *
     * @return this.rooms
     */
    public Room[] getRooms() {
        return this.rooms;
    }

    /**
     * This method should book the first available room in the list of rooms,
     * returning the roomNumber of the room that was booked. If no room is available
     * for booking, then the method should return -1.
     *
     * NOTE 1: If the rooms array is empty, then this method should just return -1.
     *
     * DO NOT MODIFY THE METHOD DECLARATION.
     *
     * @return the roomNumber booked if any, else -1.
     */
    public int bookRoom(String[] guestNames) {
        
        for(int i = 0; i < rooms.length; i++)
        {
            if(rooms[i].getIsAvailable())
            {
                rooms[i].setGuestNames(guestNames);
                rooms[i].setIsAvailable(false);
                return rooms[i].getRoomNumber();
            }
        }
        return -1;
    }
    /**
     * This method books the room specified by 'roomNumber', if it's available.
     * If the booking is successful, the method should return true.
     * If the hotel room is already booked (not available for booking), the method should return false.
     *
     * DO NOT MODIFY THE METHOD DECLARATION.
     *
     * @return the status of booking as described above
     */
    public boolean bookRoom(int roomNumber, String[] guestNames) {
        for(int i = 0; i < rooms.length; i++)
        {
            if(rooms[i].getRoomNumber() == roomNumber)
            {
                if(rooms[i].getIsAvailable()) {
                rooms[i].setGuestNames(guestNames);
                rooms[i].setIsAvailable(false);
                return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
}
