package stdgroup.enums;

public enum HairColor {
    GREEN,
    RED,
    BLUE,
    ORANGE,
    WHITE;

    public static String stringValues(){
        String values =  "";
        for (HairColor hairColor: values()){
            values += hairColor.name() + ", ";
        }
        return values;
    }
}
