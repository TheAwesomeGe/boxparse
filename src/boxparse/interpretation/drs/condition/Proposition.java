package boxparse.interpretation.drs.condition;

import boxparse.interpretation.drs.DRS;
import boxparse.interpretation.drs.DRSVisitor;

public class Proposition implements Condition {
	
	private final String referent;
	private final DRS drs;
	
	public Proposition(String referent, DRS drs) {
		this.referent = referent;
		this.drs = drs;
	}
	
	public String getReferent() {
		return referent;
	}

	public DRS getDRS() {
		return drs;
	}

	/**
	 * Accepts a DRS visitor.
	 * 
	 * @param visitor The visitor.
	 */
	@Override
	public void accept(DRSVisitor visitor) {
		visitor.processProposition(this);
	}

}
