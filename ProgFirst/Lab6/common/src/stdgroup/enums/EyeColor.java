package stdgroup.enums;

public enum EyeColor {
        RED,
        BLUE,
        YELLOW,
        WHITE;

        public static String stringValues(){
                String values =  "";
                for (EyeColor eyeColor: values()){
                        values += eyeColor.name() + ", ";
                }
                return values;
        }
}
