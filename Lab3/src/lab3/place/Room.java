package lab3.place;

import lab3.items.Item;

import java.util.Arrays;
import java.util.Objects;

public class Room implements Containable{
    private Place place;
    private Item items[];
    public Room(Place place, Item items[])
    {
        this.place = place;
        this.items = items.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return place == room.place &&
                Arrays.equals(items, room.items);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(place);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "place=" + place +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    @Override
    public int contains(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item) == true)
                return i;
        }
        return -1;
    }
    @Override
    public void content(Room room) {
        for (Item item : items)
            item.toString();
    }
    public Item getItem(int index) { return items[index]; }
    public Place getPlace() {return place;}

}
