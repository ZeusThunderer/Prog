
package lab3.characters;


import lab3.items.Item;
import lab3.place.Place;

public class FrokenBock extends Character{
    public FrokenBock() {
        super("Froken Bock", Place.KITCHEN);
    }
    public void Sing(){
        this.changeMood(Mood.GOOD);
        print(" is singing:\"Ah, Frida, it's better for you!..\"");
        System.out.println();
    }
    public void doTheDishes() {
        this.getItem().MakeClean();
        print("is washing the dishes...");
        takeTowel();
    }
    public void takeTowel(){
        if (this.currentLocation() == Place.KITCHEN) {
            changeMood(Mood.ANGRY);
            print(" couldn't find towels.");
            print(" is angry now");
        }
        else
            takeItem(new Item("Towel", true));
    }
}
