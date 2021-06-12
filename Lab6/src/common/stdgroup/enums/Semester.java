package common.stdgroup.enums;

public enum Semester {
    FIRST,
    SECOND,
    FIFTH,
    SEVENTH,
    EIGHTH;

    public static String stringValues(){
        String values =  "";
        for (Semester semester : values()){
            values += semester.name() + ", ";
        }
        return values;
    }
}

