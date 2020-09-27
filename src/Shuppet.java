import ru.ifmo.se.pokemon.*;

public class Shuppet extends Pokemon {

	public Shuppet() {
		super();
		this.addType(Type.GHOST);
		this.setStats(44, 75, 35, 63, 33, 45);
		this.setMove(new ShadowSneak(), new DarkPulse(), new Swagger());
	}

	public Shuppet(String name, int level) {
		super(name, level);
		this.addType(Type.GHOST);
		this.setStats(44, 75, 35, 63, 33, 45);
		this.setMove(new ShadowSneak(), new DarkPulse(), new Swagger());
	}
}
