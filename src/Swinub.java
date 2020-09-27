import ru.ifmo.se.pokemon.*;

public class Swinub extends Pokemon {
	public Swinub() {
		super();
		this.setType(Type.ICE, Type.GROUND);
		this.setStats(50, 50, 40, 30, 30, 50);
		this.setMove(new Rest(), new DoubleTeam());
	}

	public Swinub(String name, int level) {
		super(name, level);
		this.setType(Type.ICE, Type.GROUND);
		this.setStats(50, 50, 40, 30, 30, 50);
		this.setMove(new Rest(), new DoubleTeam());
	}
}
