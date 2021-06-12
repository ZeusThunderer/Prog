package common.stdgroup.enums;

public enum EyeColor {
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
