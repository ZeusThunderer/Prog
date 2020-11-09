package mypackage;
import ru.ifmo.se.pokemon.*;

public class Druddigon extends Pokemon {

	public Druddigon() {
		super();
		this.setType(Type.DRAGON);
		setStats(77, 120, 90, 60, 90, 48);
		setMove(new ShadowClaw(), new RockClimb(), new DracoMeteor(), new HoneClaws());
	}

	public Druddigon(String name, int level) {
		super(name, level);
		this.setType(Type.DRAGON);
		this.setStats(77, 120, 90, 60, 90, 48);
		this.setMove(new ShadowClaw(), new RockClimb(), new DracoMeteor(), new HoneClaws());
	}

}
