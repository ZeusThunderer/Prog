package lab3.place;

import lab3.errors.WithdrawException;

import lab3.items.Item;

import java.util.Arrays;
import java.util.Objects;

public class Room implements Containable{
    private String place;
    private Item items[];
    public Room(String place)
    {
        this.place = place;
    }
    public Room(String place, Item items[])
    {
        this.place = place;
        this.items = items.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(place, room.place) &&
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
                "place='" + place + '\'' +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    @Override
    public boolean contains(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item) == true)
                return true;
        }
        return false;
    }

    @Override
    public Item withdrawItem(Item item) throws WithdrawException{
        if (this.contains(item) == false)
            throw new WithdrawException();
        Item itemsTemp[] = new Item[items.length-1];
        for (int i = 0,y= 0; i<items.length-1; i++,y++)
        {
            if (items[y] == item) {y++; continue;}
            itemsTemp[i] = items[y];
        }
        return item;

    }

    @Override
    public Item[] content(Room room) {
        for (Item item : items)
            item.toString();
        return new Item[0];
    }
    public Item getItem(int index) { return items[index]; }
    public String getPlace() {return place;}

}
