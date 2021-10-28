package lab3.characters;

import lab3.place.Room;
import lab3.items.Item;

import java.util.Objects;


public abstract class Character implements Moveable{
    private String name;
    private Room location;
    private Item takenItem;
    private Mood mood;
    protected Character(String name, Room location){
        this.name = name;
        this.location = location;
        mood = Mood.NEUTRAL;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", takenItem=" + takenItem +
                ", mood=" + mood +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return name.equals(character.name) &&
                location.equals(character.location) &&
                takenItem.equals(character.takenItem) &&
                mood == character.mood;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, takenItem, mood);
    }

    public Room currentLocation() { return location; }

    public void changeLocation(Room room) {
        this.location = room;
        print("moved to " + room.getPlace());
    }

    public void takeItem(Item item) {
        takenItem = item;
        print("have taken the " + item.getName());
    }

    public Item getItem() { return takenItem; }

    public void changeMood(Mood mood) { this.mood = mood; }
    public Mood getMood() { return mood; }
    public void print(String str) {
        System.out.println("Character " + this.getName() + " " + str);
    }

    public String getName() { return name; }
}
