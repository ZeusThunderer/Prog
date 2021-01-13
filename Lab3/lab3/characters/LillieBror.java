package lab3.characters;

import lab3.items.Item;
import lab3.items.Postcard;
import lab3.place.Room;

public class LillieBror extends Character{
    public LillieBror(Room room) {
        super("LillieBror", room);
    }
    public LillieBror(Room room, Item item) {
        super("LillieBror", room, item);
    }
    public void receivePostcard(Character[] characters, Postcard postcard){
        String str = " " + characters[0].getName();
        for (int i = 1; i < characters.length; i++){
            str += " and " + characters[i].getName() ;
        }
        print("received postcard from" + str + " and it says:\n\n" + postcard.getText() + "\n" + postcard.getSign() + "\n");
        this.takeItem(postcard);
    }
}
