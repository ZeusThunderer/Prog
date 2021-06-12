package common.stdgroup;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    private Integer x; //Максимальное значение поля: 806, Поле не может быть null
    private Integer y; //Поле не может быть null

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x.equals(that.x) &&
                y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "("+ x +";"+y+")";
    }
}

