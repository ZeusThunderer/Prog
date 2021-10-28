package lab3.place;

import lab3.items.Item;

public interface Containable {
    int contains(Item item);
    void content(Room room);
}
