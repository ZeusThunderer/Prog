package lab3.characters;

import lab3.errors.CantTalkException;
import lab3.place.Room;
import lab3.items.Item;

import java.util.Objects;


public abstract class Character implements Movable{
    private String name;
    private Room location;
    private Item takenItem;
    private Mood mood;
    protected Character(String name){
        this.name = name;
        mood = Mood.NEUTRAL;
    }
    protected Character(String name, Room location){
        this.name = name;
        this.location = location;
        mood = Mood.NEUTRAL;
    }
    protected Character(String name, Room location, Item item){
        this.name = name;
        this.location = location;
        takenItem = item;
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
    public void talkTo(Character listener, String phrase) throws CantTalkException {
        if (this.currentLocation().equals(listener.currentLocation()))
        this.print("talked to " + listener.getName() + " and said:\"" + phrase + "\"");
        else
            throw new CantTalkException();
    }
    public void call(Character answerer, String phrase){
        this.print("called " + answerer.getName() + " and said:\"" + phrase + "\"");
    }
    public void answer(Character listener, String phrase) {
        this.print("answered " + " to " + listener.getName() + "by" + "\""+phrase+"\"");
    }
    public void look(Character target){
        print("looked at " + target.getName());
    }
    public static enum Mood {
        NEUTRAL,
        GOOD,
        ANGRY,
        VERY_ANGRY,
        OFFENDED,
        FRIGHTENED
    }
}
