package utils;

import exception.EmptyStringException;
import exception.IncorrectScriptException;
import exception.WrongLimitsException;
import stdgroup.Coordinates;
import stdgroup.Person;
import stdgroup.StudyGroup;
import stdgroup.enums.Country;
import stdgroup.enums.EyeColor;
import stdgroup.enums.HairColor;
import stdgroup.enums.Semester;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

    /**
     * @return Name
     * @throws IncorrectScriptException
     */
    public String getName() throws IncorrectScriptException {
        String name;
        while (true) {
            try {
                name = "";
                System.out.println("Введите имя:");
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    name += c + "";
                }
                if (fileMode == true) {
                    name.replace('\r',' ');
                    name = name.trim();
                    System.out.println(name);
                }
                if (name.equals("")) throw new EmptyStringException();
                break;
            } catch (EmptyStringException exception) {
                System.err.println("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
        }
        return name;
    }

    /**
     * @return X coordinate
     * @throws IncorrectScriptException
     */
    public Integer getX() throws IncorrectScriptException {
        String str;
        Integer x;
        while (true) {
            try {
                str = "";
                System.out.println("Введите X: ");
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                x = Integer.valueOf(str);
                if (x > MAX_X) throw new WrongLimitsException();
                break;
            }  catch (WrongLimitsException exception) {
                System.err.println("Значение должно быть меньше " + MAX_X);
                if (fileMode) throw new IncorrectScriptException();
            } catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
            catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return x;
    }

    /**
     * @return Y coordinate
     * @throws IncorrectScriptException
     */
    public Integer getY() throws IncorrectScriptException {
        String str;
        Integer y;
        while (true) {
            try {
                str = "";
                System.out.println("Введите Y: ");
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                y = Integer.valueOf(str);
                break;
            }   catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
            catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return y;
    }

    /**
     * @return Coordinates
     * @throws IncorrectScriptException
     */
    public Coordinates getCoordinates() throws IncorrectScriptException{
        System.out.println("Введите координаты");
        Integer x = getX(),
                y = getY();
        return new Coordinates(x,y);
    }

    /**
     * @return Students Count
     * @throws IncorrectScriptException
     */
    public Long getStudentsCount() throws  IncorrectScriptException{
        String str;
        Long studentsCount;
        while (true) {
            try {
                str = "";
                System.out.println("Введите количество студентов: ");
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                if
                    (str == "") studentsCount = null;
                else
                    studentsCount = Long.valueOf(str);
                if (studentsCount <= MIN_STUDENTSCOUNT) throw new WrongLimitsException();
                break;
            }   catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
            catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");
                if (fileMode) throw new IncorrectScriptException();
            } catch (WrongLimitsException e) {
                System.err.println("Кол-во студентов должно быть больше нуля");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return studentsCount;
    }

    /**
     * @return Expelled Students
     * @throws IncorrectScriptException
     */
    public int getExpelledStudents() throws  IncorrectScriptException{
        String str;
        int expelledStudents;
        while (true) {
            try {
                str = "";
                System.out.println("Введите число отчисленных студентов: ");
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                expelledStudents = Integer.parseInt(str);
                if (expelledStudents <= MIN_EXPELLEDSTUDENTS) throw new WrongLimitsException();
                break;
            }   catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
            catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");
                if (fileMode) throw new IncorrectScriptException();
            } catch (WrongLimitsException e) {
                System.err.println("Кол-во отчисленных студентов должно быть больше нуля");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return expelledStudents;
    }

    /**
     * @return Avegage Mark
     * @throws IncorrectScriptException
     */
    public float getAverageMark() throws  IncorrectScriptException{
        String str;
        float averageMark;
        while (true) {
            try {
                str = "";
                System.out.println("Введите средний балл: ");
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                averageMark = Float.parseFloat(str);
                if (averageMark <= MIN_AVERAGEMARK) throw new WrongLimitsException();
                break;
            }   catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
            catch (NumberFormatException e){
                System.err.println("Значение должно быть числом");
                if (fileMode) throw new IncorrectScriptException();
            } catch (WrongLimitsException e) {
                System.err.println("Средний балл должен быть больше нуля");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return averageMark;
    }

    /**
     * @return Semester
     * @throws IncorrectScriptException
     */
    public Semester getSemester() throws  IncorrectScriptException{
        String str;
        Semester semester;
        while (true) {
            try {
                str = "";
                System.out.println("Введите номер семестра: ");
                System.out.println("Список значений: " + Semester.stringValues());
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                semester=Semester.valueOf(str.toUpperCase());
                break;
            } catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            } catch (IllegalArgumentException e){
                System.err.println("Неверное значение");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return semester;
    }

    /**
     * @return Birthday
     * @throws IncorrectScriptException
     */
    public Date getBirthday() throws  IncorrectScriptException{
        String str;
        Date birthday;
        while (true) {
            try {
                str = "";
                System.out.println("Введите дату рождения админа группы: ");
                System.out.println("Формат даты - дд/мм/гггг");
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                birthday = new SimpleDateFormat("dd/MM/yyyy").parse(str);
                break;
            }   catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
            catch (ParseException e) {
                System.err.println("Неправильный формат даты");
            }
        }
        return birthday;
    }

    /**
     * @return Eye Color
     * @throws IncorrectScriptException
     */
    public EyeColor getEyeColor() throws  IncorrectScriptException{
        String str;
        EyeColor eyeColor;
        while (true) {
            try {
                str = "";
                System.out.println("Введите цвет глаз: ");
                System.out.println("Список значений: " + EyeColor.stringValues());
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                eyeColor= EyeColor.valueOf(str.toUpperCase());
                break;
            }   catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }   catch (IllegalArgumentException e){
                System.err.println("Неверное значение");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return eyeColor;
    }

    /**
     * @return Hair Color
     * @throws IncorrectScriptException
     */
    public HairColor getHairColor() throws  IncorrectScriptException{
        String str;
        HairColor hairColor;
        while (true) {
            try {
                str = "";
                System.out.println("Введите цвет волос: ");
                System.out.println("Список значений: " + HairColor.stringValues());
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                hairColor=HairColor.valueOf(str.toUpperCase());
                break;
            }   catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }   catch (IllegalArgumentException e){
                System.err.println("Неверное значение");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return hairColor;
    }

    /**
     * @return Nationality
     * @throws IncorrectScriptException
     */
    public Country getNationality() throws  IncorrectScriptException{
        String str;
        Country country;
        while (true) {
            try {
                str = "";
                System.out.println("Введите национальность: ");
                System.out.println("Список значений: " + Country.stringValues());
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    str += c;
                }
                if (fileMode == true) {
                    str.replace('\r',' ');
                    str = str.trim();
                    System.out.println(str);
                }
                country=Country.valueOf(str.toUpperCase());
                break;
            }   catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }   catch (IllegalArgumentException e){
                System.err.println("Неверное значение");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return country;
    }

    /**
     * @return Group Admin
     * @throws IncorrectScriptException
     */
    public Person getGroupAdmin() throws IncorrectScriptException {
        System.out.println("Введите админа группы");
        String name = getName();
        java.util.Date birthday = getBirthday();
        EyeColor eyeColor = getEyeColor();
        HairColor hairColor = getHairColor();
        Country nationality = getNationality();
        return new Person(name,birthday,eyeColor,hairColor,nationality);
    }
    public StudyGroup getStudyGroup(Long id) throws IncorrectScriptException {
        System.out.println("Введите учебную группу");
        LocalDateTime lcDate = LocalDateTime.now();
        return new StudyGroup(id,getName(),getCoordinates(), lcDate, getStudentsCount(),getExpelledStudents(),getAverageMark(),getSemester(),getGroupAdmin());
    }

    public boolean change(String s) throws IncorrectScriptException {
        String finalQuestion = s + "? (yes, no)";
        String answer = "";
        while (true) {
            try {
                System.out.println(finalQuestion);
                System.out.print(InputSign);
                char c;
                while (true) {
                    c = (char) stream.read();
                    if (c == '\n')
                        break;
                    answer += c;
                }
                if (fileMode == true) {
                    answer.replace('\r',' ');
                    answer = answer.trim();
                    System.out.println(answer);
                }
                if (fileMode) System.out.println(answer);
                if (!answer.equals("yes") && !answer.equals("no")) throw new WrongLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                System.err.println("Ответ не распознан!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (WrongLimitsException exception) {
                System.err.println("Ответ должен быть либо 'yes' либо 'no'!");
                if (fileMode) throw new IncorrectScriptException();
            }  catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
        }
        return (answer.equals("yes")) ? true : false;
    }


}
