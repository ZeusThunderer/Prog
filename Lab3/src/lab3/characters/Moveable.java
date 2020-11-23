package lab3.characters;

import lab3.place.Room;

public interface Moveable {
    Room currentLocation();
    void changeLocation(Room room);
}
