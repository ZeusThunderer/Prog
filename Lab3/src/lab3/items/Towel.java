package lab3.items;

public class Towel extends Item{
    private boolean clean;
    public Towel(String name, boolean clean) {
        super(name);
        this.clean = clean;
    }

    @Override
    public void Clean() {
        clean = true;
    }

    @Override
    public String toString() {
        return "Cigar: " + "\nname= " + name +  '}';
    }
}
