import ru.ifmo.se.pokemon.*;
import mypackage.*;

public class Lab1 {
	public static void main(String[] args) {
		Battle bat = new Battle();
		bat.addAlly(new Druddigon("у которого неправильно работают атаки", 35));
		bat.addAlly(new Shuppet("Шапито", 13));
		bat.addAlly(new Banette("Байонетта", 37));
		bat.addFoe(new Swinub("Svin", 1));
		bat.addFoe(new Piloswine("Svinota", 37));
		bat.addFoe(new Mamoswine("SVINOZAVR", 37));
		bat.go();
	}

}
