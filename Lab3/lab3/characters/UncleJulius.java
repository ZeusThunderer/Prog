
package lab3.characters;

import lab3.items.Cigar;
import lab3.place.Room;

public class UncleJulius extends Character {
    public UncleJulius(Room room) {
        super("Uncle Julius", room);
    }

    public void Smoke() {
        takeItem(currentLocation().withdrawItem(new Cigar("Cigar")));
    }
    public void grumbling(){
        changeMood(Mood.ANGRY);
        print(" is grumbling: \"What a bad police in town!\"");
    }
}
