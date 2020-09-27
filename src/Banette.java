
public class Banette extends Shuppet {

	public Banette() {
		super();
		this.setStats(64, 115, 65, 83, 63, 65);
		this.addMove(new ShadowClaw());
	}

	public Banette(String name, int level) {
		super(name, level);
		this.setStats(64, 115, 65, 83, 63, 65);
		this.addMove(new ShadowClaw());
	}
}
