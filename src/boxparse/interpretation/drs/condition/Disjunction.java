package boxparse.interpretation.drs.condition;

import boxparse.interpretation.drs.DRS;

public class Disjunction implements Condition {
	private final DRS drs1;
	private final DRS drs2;
	
	public Disjunction(DRS drs1, DRS drs2) {
		this.drs1 = drs1;
		this.drs2 = drs2;
	}
	
	public DRS getFirstDRS() {
		return drs1;
	}
	
	public DRS getSecondDRS() {
		return drs2;
	}
}
