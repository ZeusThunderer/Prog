package common.stdgroup.enums;

public enum Country {
    RUSSIA,
    UNITED_KINGDOM,
    SPAIN,
    THAILAND,
    JAPAN;

    public static String stringValues(){
        String values =  "";
        for (Country country : values()){
            values += country.name() + ", ";
        }
        return values;
    }
}

