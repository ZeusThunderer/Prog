package lab3.characters;

import lab3.place.Room;

public interface Movable {
    Room currentLocation();
    void changeLocation(Room room);
}
