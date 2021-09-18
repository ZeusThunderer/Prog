package stdgroup;

import stdgroup.enums.EyeColor;
import stdgroup.enums.HairColor;
import stdgroup.enums.Country;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле может быть null
    private EyeColor eyeColor; //Поле может быть null
    private HairColor hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null

    public Person(String name, Date birthday, EyeColor eyeColor, HairColor hairColor, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) &&
                birthday.equals(person.birthday) &&
                eyeColor == person.eyeColor &&
                hairColor == person.hairColor &&
                nationality == person.nationality;
    }

    /**
     * @param person
     * @returnb true if this person born later than param person
     */
    public boolean compare(Person person){
        if (person.birthday.before(this.birthday))
            return true;
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, eyeColor, hairColor, nationality);
    }

    @Override
    public String toString() {
        return  "Имя: " + name +
                ",\nДата рождения " + new SimpleDateFormat("dd/MM/yyyy").format(birthday) +
                ",\nЦвет глаз " + eyeColor +
                ",\nЦвет волос " + hairColor +
                ",\nСтрана рождения " + nationality;
    }
}