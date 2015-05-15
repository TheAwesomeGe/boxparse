package boxparse.interpretation.drs.condition;

import boxparse.interpretation.drs.DRSVisitor;

public class WhQuestion implements Condition {
	
	/**
	 * Accepts a DRS visitor.
	 * 
	 * @param visitor The visitor.
	 */
	@Override
	public void accept(DRSVisitor visitor) {
		visitor.processWhQuestion(this);
	}

}
