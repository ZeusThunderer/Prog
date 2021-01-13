
package lab3.characters;

import lab3.items.Cigar;
import lab3.items.Towel;
import lab3.place.Room;

public class Karlson extends Character{

    public Karlson(Room room){
        super("Karlson", room);
    }
    public void killTheMood(Character character){
        print("tries to kill " + character.getName() + "'s mood");
        if (character.getMood() == Mood.ANGRY)
            character.changeMood(Mood.VERY_ANGRY);
        else
            print("couldn't do anything because " + character.getName() + " is to happy");
    }

    public void takeTowels() {
        this.takeItem(currentLocation().withdrawItem(new Towel("Towel", false)));
    }
    public void getOffended(){
        this.changeMood(Mood.OFFENDED);
        print("is offended because Lillie Bror's parents did't mentioned him");
    }
}
