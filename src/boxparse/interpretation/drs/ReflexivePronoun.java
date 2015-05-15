package boxparse.interpretation.drs;

public class ReflexivePronoun extends MergeDRS {

	public ReflexivePronoun(DRS drs1, DRS drs2) {
		super(drs1, drs2);
	}
	
	/**
	 * Accepts a DRS visitor.
	 * 
	 * @param visitor The visitor.
	 */
	@Override
	public void accept(DRSVisitor visitor) {
		visitor.processReflexivePronoun(this);
	}
}
