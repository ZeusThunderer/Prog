import lab3.characters.FrokenBock;
import lab3.characters.Karlson;
import lab3.characters.UncleJulius;
import lab3.items.Item;
import lab3.place.Place;

public class Main {
    public static void main(String[] args) {
        Karlson Karl = new Karlson();
        FrokenBock Bock = new FrokenBock();
        UncleJulius Julius = new UncleJulius();
        // Actions after Lunch
        Julius.changeLocation(Place.LIVINGROOM);
        Julius.takeItem(new Item("Cigar"));
        Bock.Sing();
        Bock.doTheDishes();
        Karl.changeLocation(Place.LILLEBRORS_ROOM);
        Karl.takeItem(new Item("Towels", false));
        Karl.changeLocation(Place.KITCHEN);
        Karl.killTheMood(Bock);
    }
}
