package boxparse.interpretation.drs.condition;

import boxparse.interpretation.drs.DRSVisitor;

public class CardinalExpression implements Condition {
	
	/**
	 * Accepts a DRS visitor.
	 * 
	 * @param visitor The visitor.
	 */
	@Override
	public void accept(DRSVisitor visitor) {
		visitor.processCardinal(this);
	}

}
