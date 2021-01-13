
package lab3.characters;


import lab3.items.Dishes;
import lab3.items.Item;
import lab3.items.Towel;
import lab3.place.Room;

public class FrokenBock extends Character{
    public FrokenBock(Room room) {
        super("Froken Bock", room);
    }
    public void Sing(){
        this.changeMood(Mood.GOOD);
        print("is singing:\"Ah, Frida, it's better for you!..\"");
    }
    public void doTheHousework() {
        print("is clearing the table");
        currentLocation().getItem(currentLocation().contains(new Dishes("Dishes", false))).Clean();
        print("is washing the dishes...");
    }
    public void takeTowel() {
        print("tries to find towels");
        if (currentLocation().contains(new Towel("Towel", true)) >= 0) {
            takeItem(currentLocation().getItem(currentLocation().contains(new Towel("Towel", true))));
            print("has taken towels");
        }
        else {
            changeMood(Mood.ANGRY);
            print("is angry because she couldn't find towels");
        }

    }

    public void checkTowels(Character character) {
        if (character.getItem().equals(new Towel("Towel", false))) {
            changeMood(Mood.VERY_ANGRY);
            print("is very angry now because " + character.getName() + " has brought dirty towels");
        }
    }
}
