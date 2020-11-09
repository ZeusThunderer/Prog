
package lab3.items;

import java.util.Objects;

public class Item implements Clean{
    private String name;
    private boolean isClean;
    public Item(String name){
        this.name = name;
        isClean = true;
    }
    public Item(String name, boolean isClean){
        this.name = name;
        this.isClean = isClean;
    }
    public boolean getCleanliness() {return isClean;}
    public void MakeClean() {isClean = true;}
    public String getName() {return name;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return isClean == item.isClean &&
                name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isClean);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", isClean=" + isClean +
                '}';
    }
}
