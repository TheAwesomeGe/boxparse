package boxparse.interpretation.drs;

public class MergeDRS implements DRS {
	private final DRS drs1;
	private final DRS drs2;
	
	public MergeDRS(DRS drs1, DRS drs2) {
		this.drs1 = drs1;
		this.drs2 = drs2;
	}
	
	public DRS getFirstDRS() {
		return drs1;
	}
	
	public DRS getSecondDRS() {
		return drs2;
	}
	
	/**
	 * Accepts a DRS visitor.
	 * 
	 * @param visitor The visitor.
	 */
	@Override
	public void accept(DRSVisitor visitor) {
		visitor.processMergeDRS(this);
	}
}
