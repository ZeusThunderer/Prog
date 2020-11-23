import lab3.characters.FrokenBock;
import lab3.characters.Karlson;
import lab3.characters.UncleJulius;
import lab3.items.Cigar;
import lab3.items.Dishes;
import lab3.items.Item;
import lab3.items.Towel;
import lab3.place.Place;
import lab3.place.Room;

public class Main {
    public static void main(String[] args) {
        Room rooms[] = {new Room(Place.KITCHEN, new Item[]{new Dishes("Dishes", false)}),new Room(Place.LIVINGROOM, new Item[]{new Cigar("Cigar")}),new Room(Place.LITTLEBRORS_ROOM, new Item[]{new Towel("Towel", false)})};
        Karlson Karl = new Karlson(rooms[0]);
        FrokenBock Bock = new FrokenBock(rooms[0]);
        UncleJulius Julius = new UncleJulius(rooms[0]);
        // Actions after Lunch
        Julius.changeLocation(rooms[1]);
        Julius.Smoke();
        Bock.doTheHousework();
        Bock.Sing();
        Karl.killTheMood(Bock);
        Bock.takeTowel();
        Karl.changeLocation(rooms[2]);
        Karl.takeTowels();
        Karl.changeLocation(rooms[1]);
        Bock.checkTowels(Karl);
    }
}
