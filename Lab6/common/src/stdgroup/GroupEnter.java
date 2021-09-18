package stdgroup;

import exception.EmptyStringException;
import exception.WrongLimitsException;
import stdgroup.enums.Country;
import stdgroup.enums.EyeColor;
import stdgroup.enums.HairColor;
import stdgroup.enums.Semester;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;


public class GroupEnter {
    private final int MAX_X = 806;
    private final int MIN_STUDENTSCOUNT = 0;
    private final int MIN_EXPELLEDSTUDENTS = 0;
    private final float MIN_AVERAGEMARK = 0.0F;
    private final char InputSign = '>';

    private BufferedInputStream stream;

    private boolean fileMode;
    public GroupEnter(BufferedInputStream stream){
        this.stream = stream;
    }


    public BufferedInputStream getStream(){
        return stream;
    }

    public void setStream(BufferedInputStream stream){

        this.stream = stream;
    }
    /**
     * Sets mode to 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Sets mode to 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
    }

    public String read(){
        String str = "";
        while (true) {
            try {
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c + "";
                }
                if (str.equals("")) throw new EmptyStringException();
                break;
            } catch (EmptyStringException exception) {
                System.err.println("Поле не может быть пустым!");
            } catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
        }
        return str;
    }

    /**
     * @return Name
     */
    public String getName() {
        System.out.println("Введите имя:");
        return read();
    }

    /**
     * @return X coordinate
     */
    public Integer getX()  {
        Integer x;
        while (true) {
            try {
                System.out.println("Введите координату X:");
                x = Integer.valueOf(read());
                if (x > MAX_X) throw new WrongLimitsException();
                return x;
            }  catch (WrongLimitsException exception) {
                System.err.println("Значение должно быть меньше " + MAX_X);

            }  catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");

            }
        }
    }

    /**
     * @return Y coordinate
     */
    public Integer getY()  {
        Integer y;
        while (true) {
            try {
                System.out.println("Введите координату Y:");
                return Integer.valueOf(read());
            }  catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");
            }
        }
    }

    /**
     * @return Coordinates
     */
    public Coordinates getCoordinates() {
        System.out.println("Введите координаты:");
        Integer x = getX(),
                y = getY();
        return new Coordinates(x,y);
    }

    /**
     * @return Students Count
     */
    public Long getStudentsCount() {
        long studentsCount;
        while (true) {
            try {
                System.out.println("Введите количество студентов: ");
                studentsCount = Long.parseLong( read() );
                if (studentsCount <= MIN_STUDENTSCOUNT) throw new WrongLimitsException();
                return studentsCount;
            } catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");

            } catch (WrongLimitsException e) {
                System.err.println("Кол-во студентов должно быть больше нуля");

            }
        }
    }

    /**
     * @return Expelled Students
     */
    public int getExpelledStudents(){
        int expelledStudents;
        while (true) {
            try {
                System.out.println("Введите число отчисленных студентов: ");
                expelledStudents = Integer.parseInt(read());
                if (expelledStudents <= MIN_EXPELLEDSTUDENTS) throw new WrongLimitsException();
                return expelledStudents;
            } catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");

            } catch (WrongLimitsException e) {
                System.err.println("Кол-во отчисленных студентов должно быть больше нуля");

            }
        }
    }

    /**
     * @return Avegage Mark
     */
    public float getAverageMark() {
        float averageMark;
        while (true) {
            try {
                System.out.println("Введите средний балл: ");
                averageMark = Float.parseFloat(read());
                if (averageMark <= MIN_AVERAGEMARK) throw new WrongLimitsException();
                return averageMark;
            } catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");

            } catch (WrongLimitsException e) {
                System.err.println("Средний балл должен быть больше нуля");

            }
        }
    }

    /**
     * @return Semester
     */
    public Semester getSemester() {
        while (true) {
            try {
                System.out.println("Введите номер семестра: ");
                System.out.println("Список значений: " + Semester.stringValues());
                return Semester.valueOf(read().toUpperCase());
            }  catch (IllegalArgumentException e){
                System.err.println("Неверное значение");
            }
        }
    }

    /**
     * @return Birthday
     */
    public Date getBirthday() {
        while (true) {
            try {
                System.out.println("Введите дату рождения админа группы: ");
                System.out.println("Формат даты - дд/мм/гггг");
                return new SimpleDateFormat("dd/MM/yyyy").parse(read());
            }
            catch (ParseException e) {
                System.err.println("Неправильный формат даты");
            }
        }
    }

    /**
     * @return Eye Color
     */
    public EyeColor getEyeColor() {
        while (true) {
            try {
                System.out.println("Введите цвет глаз: ");
                System.out.println("Список значений: " + EyeColor.stringValues());
                return EyeColor.valueOf(read().toUpperCase());
            }   catch (IllegalArgumentException e){
                System.err.println("Неверное значение");

            }
        }
    }

    /**
     * @return Hair Color
     */
    public HairColor getHairColor() {
        while (true) {
            try {
                System.out.println("Введите цвет волос: ");
                System.out.println("Список значений: " + HairColor.stringValues());
                return HairColor.valueOf(read().toUpperCase());
            }   catch (IllegalArgumentException e){
                System.err.println("Неверное значение");
            }
        }
    }

    /**
     * @return Nationality
     */
    public Country getNationality() {
        while (true) {
            try {
                System.out.println("Введите национальность: ");
                System.out.println("Список значений: " + Country.stringValues());
                return Country.valueOf(read().toUpperCase());
            }   catch (IllegalArgumentException e){
                System.err.println("Неверное значение");

            }
        }
    }

    /**
     * @return Group Admin
     */
    public Person getGroupAdmin()  {
        System.out.println("Введите админа группы");
        String name = getName();
        java.util.Date birthday = getBirthday();
        EyeColor eyeColor = getEyeColor();
        HairColor hairColor = getHairColor();
        Country nationality = getNationality();
        return new Person(name,birthday,eyeColor,hairColor,nationality);
    }
    public RawGroup getStudyGroup() {
        System.out.println("Введите учебную группу");
        return new RawGroup(getName(),getCoordinates(), getStudentsCount(),getExpelledStudents(),getAverageMark(),getSemester(),getGroupAdmin());
    }

    public boolean change(String s)  {
        String finalQuestion = s + "? (да , нет)";
        String answer;
        while (true) {
            try {
                System.out.println(finalQuestion);
                answer = read().toLowerCase();
                if (!answer.equals("да") && !answer.equals("нет")) throw new WrongLimitsException();
                return answer.equals( "да" );
            } catch (NoSuchElementException exception) {
                System.err.println("Ответ не распознан!");
            } catch (WrongLimitsException exception) {
                System.err.println("Ответ должен быть либо да либо нет!");

            }
        }
    }


}
