package lab3.characters;

import lab3.place.Room;
import lab3.steal.Steal;

public class Rulle extends Character{
    public Rulle() {
        super("Rulle", new Room("Somewhere in the city"));
    }
    public void burglary(Steal steal){
        steal.steal();
    }
}
