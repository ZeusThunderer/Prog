import ru.ifmo.se.pokemon.*;

public class Lab1 {
	public static void main(String[] args) {
		Battle bat = new Battle();
		bat.addAlly(new Druddigon("� �������� ����������� �������� �����", 35));
		bat.addAlly(new Shuppet("������", 13));
		bat.addAlly(new Banette("���������", 37));
		bat.addFoe(new Swinub("Svin", 1));
		bat.addFoe(new Piloswine("Svinota", 37));
		bat.addFoe(new Mamoswine("SVINOZAVR", 37));
		bat.go();
	}

}