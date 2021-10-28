
package lab3.items;

import java.util.Objects;

public class Item {
    protected String name;
    public Item(String name){
        this.name = name;
    }
    public String getName() {return name;}
    public void Clean() {};
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Item: " + "\nname= " + name +  '}';
    }
}
