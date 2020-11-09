package lab3.characters;

import lab3.place.Place;
import lab3.items.Item;

import java.util.Objects;


public abstract class Character implements Take{
    private String name;
    private Place location;
    private Item takenItem;
    private Mood mood;
    protected Character(String name, Place location){
        this.name = name;
        this.location = location;
        mood = Mood.NEUTRAL;
        takenItem = new Item("EmptyHand",true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;
        Character character = (Character) o;
        return name.equals(character.name) &&
                location == character.location &&
                takenItem.equals(character.takenItem) &&
                mood == character.mood;
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
    public int hashCode() {
        return Objects.hash(name, location, takenItem, mood);
    }
    public String getName() { return name; }
    public void changeLocation(Place location){
        this.location = location;
        print(" moved to " + location.toString());
    }
    public Place currentLocation() { return location; }
    @Override
    public void takeItem(Item item) {
        takenItem = item;
        print(" have taken the " + item.getName());
    }
    public Item getItem() { return takenItem; }
    public void changeMood(Mood mood) { this.mood = mood; }
    public void print(String str) {
        System.out.println("Character " + this.getName() + str);
    }
}
