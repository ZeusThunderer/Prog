import lab3.characters.*;
import lab3.characters.Character;
import lab3.errors.CantTalkException;
import lab3.items.*;
import lab3.place.Room;
import lab3.steal.Steal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Room    kitchen = new Room("KITCHEN", new Item[]{new Dishes("Dishes", false)}),
                livingRoom = new Room("LIVINGROOM", new Item[]{new Cigar("Cigar")}),
                smallRoom = new Room("LILLIEBRORS_ROOM", new Item[]{new Towel("Towel", false)});
        Phille phille = new Phille();
        Rulle rulle = new Rulle();
        Police police = new Police();
        Karlson Karl = new Karlson(kitchen);
        FrokenBock Bock = new FrokenBock(kitchen);
        UncleJulius Julius = new UncleJulius(kitchen);
        LillieBror Malysh = new LillieBror(kitchen, new Pistol());
        Character[] parents =  {new Mother(), new Father()};
        Postcard postcard = new Postcard("The trip was great and we hope you are having fun too and that he gets along well with Uncle Julius and Miss Bock.", "From your parents");
         new Father();
        //Last night
        rulle.burglary(new Steal() {
            @Override
            public void steal() {
                rulle.print("commit crime");
            }
        });
        phille.burglary(new Steal() {
            @Override
            public void steal() {
                phille.takeItem(new Item("somebodies belongs"));
                phille.print("haven't taken it, he stole it!\n\n");
            }
        });
        // Actions after Lunch

        Malysh.look(Julius);
        Julius.grumbling();
        Julius.call(police, "I want to report the incident");
        police.answer(Julius, "Nah, We don't interested. We got 313 another burglaries last night");
        Julius.look(Karl);
        Karl.changeMood(Character.Mood.GOOD);
        Karl.look(Bock);
        Julius.changeMood(Character.Mood.FRIGHTENED);
        try {
            Malysh.talkTo(Julius, "This pistol is just a toy");
        }
        catch (CantTalkException exp){
            Malysh.print("can't talk to " + Julius.getName() + "because they are in the different locations");
        }
        Julius.changeLocation(livingRoom);
        Julius.Smoke();
        Bock.doTheHousework();
        Bock.Sing();
        Karl.killTheMood(Bock);
        Bock.takeTowel();
        Karl.changeLocation(smallRoom);
        Karl.takeTowels();
        Karl.changeLocation(livingRoom);
        Bock.checkTowels(Karl);
        System.out.println("Time passed");
        Malysh.receivePostcard(parents,postcard);
        if (postcard.getText().contains("Karlson") == false){
            Karl.getOffended();
        }
    }
}
