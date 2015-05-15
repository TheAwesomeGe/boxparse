package boxparse.interpretation.drs;

public interface DRS {

	/**
	 * Accepts a DRS visitor.
	 * 
	 * @param visitor The visitor.
	 */
	public void accept(DRSVisitor visitor);
}
