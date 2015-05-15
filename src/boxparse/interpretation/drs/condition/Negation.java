package boxparse.interpretation.drs.condition;

import boxparse.interpretation.drs.DRS;
import boxparse.interpretation.drs.DRSVisitor;

public class Negation implements Condition {
	private final DRS drs;
	
	public Negation(DRS drs) {
		this.drs = drs;
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
		visitor.processNegation(this);
	}
}
