package boxparse.interpretation.drs.condition;

import boxparse.interpretation.drs.DRS;

public class Negation implements Condition {
	private final DRS drs;
	
	public Negation(DRS drs) {
		this.drs = drs;
	}
	
	public DRS getDRS() {
		return drs;
	}
}
