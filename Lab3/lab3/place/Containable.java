package lab3.place;

import lab3.items.Item;

public interface Containable {
    boolean contains(Item item);
    Item withdrawItem(Item item);
    Item[] content(Room room);
}
