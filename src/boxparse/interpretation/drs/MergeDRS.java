package boxparse.interpretation.drs;

public class MergeDRS implements DRS {
	private DRS drs1;
	private DRS drs2;
	
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
}
