import ru.ifmo.se.pokemon.*;

//  PHYSICAL MOVES
class ShadowClaw extends PhysicalMove {
	protected ShadowClaw() {
		super(Type.GHOST, 70, 100);
	}

	@Override
	protected double calcCriticalHit(Pokemon att, Pokemon def) {
		if (Math.random() <= 1 / 8) {
			System.out.println("����������� ����!");
			return 2.0;
		} else
			return 1.0;
	}

	@Override
	protected String describe() {
		return "���������� Shadow Claw";
	}
}

class RockClimb extends PhysicalMove {
	protected RockClimb() {
		super(Type.NORMAL, 90, 85);
	}

	@Override
	protected void applyOppEffects(Pokemon def) {
		if (Math.random() <= 0.2)
			def.confuse();
	}

	@Override
	protected String describe() {
		return "���������� Rock Climb";
	}
}

class ShadowSneak extends PhysicalMove {
	protected ShadowSneak() {
		super(Type.GHOST, 40, 100, 1, 1);
	}

	@Override
	protected String describe() {
		return "���������� Shadow Sneak";
	}
}

// SPECIAL MOVES
class DracoMeteor extends SpecialMove {
	protected DracoMeteor() {
		super(Type.DRAGON, 130, 90, 0, 1);
	}

	@Override
	protected void applySelfEffects(Pokemon att) {
		att.setMod(Stat.SPECIAL_ATTACK, -2);
	}

	@Override
	protected String describe() {
		return "���������� Draco Meteor";
	}
}

class DarkPulse extends SpecialMove {
	public DarkPulse() {
		super(Type.DARK, 80, 100);
	}

	@Override
	protected void applyOppEffects(Pokemon def) {
		if (Math.random() < 0.2)
			Effect.flinch(def);
	}

	@Override
	protected String describe() {
		return "���������� Dark Pulse";
	}
}

class IceBeam extends SpecialMove {
	public IceBeam() {
		super(Type.ICE, 90, 100);
	}

	@Override
	protected void applyOppEffects(Pokemon def) {
		if (Math.random() <= 0.1)
			Effect.freeze(def);
	}
}
// STATUS MOVES

class HoneClaws extends StatusMove {
	protected HoneClaws() {
		super(Type.DARK, 0, 100);
	}

	@Override
	protected void applySelfEffects(Pokemon att) {
		att.setMod(Stat.ACCURACY, 1);
		att.setMod(Stat.ATTACK, 1);
	}

	@Override
	protected String describe() {
		return "���������� Hone Claws";
	}
}

class Swagger extends StatusMove {
	public Swagger() {
		super(Type.NORMAL, 0, 85);
	}

	@Override
	protected void applyOppEffects(Pokemon def) {
		def.setMod(Stat.ATTACK, 2);
		def.confuse();
	}

	@Override
	protected String describe() {
		return "���������� Swagger";
	}
}

class Rest extends StatusMove {
	protected Rest() {
		super(Type.PSYCHIC, 0, 100);
	}

	@Override
	protected void applySelfEffects(Pokemon att) {
		att.setMod(Stat.HP, (int) (att.getHP() - att.getStat(Stat.HP)));
		att.setCondition(new Effect().turns(2).condition(Status.SLEEP));
	}

	@Override
	protected String describe() {
		return "���������� Rest\n";

	}
}

class DoubleTeam extends StatusMove {
	public DoubleTeam() {
		super(Type.NORMAL, 0, 100);
	}

	@Override
	protected void applySelfEffects(Pokemon att) {
		att.setMod(Stat.EVASION, 1);
	}

	@Override
	protected String describe() {
		return "���������� Double Team";
	}
}

class Amnesia extends StatusMove {
	public Amnesia() {
		super(Type.PSYCHIC, 0, 100);
	}

	@Override
	protected void applySelfEffects(Pokemon att) {
		att.setMod(Stat.SPECIAL_ATTACK, 2);
	}

	@Override
	protected String describe() {
		return "���������� Amnesia";
	}
}
