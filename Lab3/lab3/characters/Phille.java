package lab3.characters;

import lab3.place.Room;
import lab3.steal.Steal;

public class Phille extends Character{
    public Phille() {
        super("Phille", new Room("Somewhere in the city"));
    }
    public void burglary(Steal steal){
        steal.steal();
    }
}
