package boxparse.interpretation.drs.condition;

public class NamedEntity implements Condition {
	private final String referent;
	private final String symbol;
	private final String type;
	
	public NamedEntity(String referent, String symbol, String type) {
		this.referent = referent;
		this.symbol = symbol;
		this.type = type;
	}

	public String getReferent() {
		return referent;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getType() {
		return type;
	}
}
