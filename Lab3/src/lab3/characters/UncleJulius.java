
package lab3.characters;

import lab3.items.Cigar;
import lab3.place.Room;

public class UncleJulius extends Character {
    public UncleJulius(Room room) {
        super("Uncle Julius", room);
    }

    public void Smoke() {
        takeItem(currentLocation().getItem(currentLocation().contains(new Cigar("Cigar"))));
    }
}
