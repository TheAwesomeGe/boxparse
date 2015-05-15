package boxparse.interpretation.drs;

public class ProperName extends MergeDRS {

	public ProperName(DRS drs1, DRS drs2) {
		super(drs1, drs2);
	}
	
	/**
	 * Accepts a DRS visitor.
	 * 
	 * @param visitor The visitor.
	 */
	@Override
	public void accept(DRSVisitor visitor) {
		visitor.processProperName(this);
	}
}
