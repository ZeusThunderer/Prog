
package lab3.characters;

import lab3.place.Place;

public class Karlson extends Character{

    public Karlson(){
        super("Karlson",Place.KITCHEN);
    }
    public void killTheMood(Character character){
        System.out.println("But towels are DIRTY!!!");
        character.changeMood(Mood.VERY_ANGRY);
        character.print(" is very angry now" );
    }

}
