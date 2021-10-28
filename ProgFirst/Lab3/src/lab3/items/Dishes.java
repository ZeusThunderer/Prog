package lab3.items;

public class Dishes extends Item{
    private boolean clean;
    public Dishes(String name,boolean clean) {
        super(name);
        this.clean = clean;
    }

    public void Clean() { clean = true; }
    @Override
    public String toString() {
        return "Cigar: " + "\nname= " + name +  '}';
    }
}
