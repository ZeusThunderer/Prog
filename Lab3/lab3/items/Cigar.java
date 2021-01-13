package lab3.items;

public class Cigar extends Item{
    public Cigar(String name) {
        super(name);
    }

    public void Clean() {}
    @Override
    public String toString() {
        return "Cigar: " + "\nname= " + name +  '}';
    }
}
