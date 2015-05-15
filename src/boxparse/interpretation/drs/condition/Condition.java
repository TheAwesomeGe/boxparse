package boxparse.interpretation.drs.condition;

import boxparse.interpretation.drs.DRSVisitor;

public interface Condition {
	
	/**
	 * Accepts a DRS visitor.
	 * 
	 * @param visitor The visitor.
	 */
	public void accept(DRSVisitor visitor);
	
}
