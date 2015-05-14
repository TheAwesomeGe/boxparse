package boxparse.interpretation.drs.condition;

/**
 * A predicate represents a property of a given referent.
 * 
 * @author Eugenio Ribeiro
 */
public class Predicate implements Condition {
	/**
	 * The referent.
	 */
	private final String referent;
	/**
	 * The symbol that originated the predicate.
	 */
	private final String symbol;
	/**
	 * The POS kind of predicate.
	 */
	private final String pos;
	/**
	 * The sense of the predicate (What is this? :x)
	 */
	private final int sense;
	
	/**
	 * Creates a new predicate.
	 * 
	 * @param referent The referent.
	 * @param symbol The symbol.
	 * @param pos The POS type.
	 * @param sense The sense.
	 */
	public Predicate(String referent, String symbol, String pos, int sense) {
		this.referent = referent;
		this.symbol = symbol;
		this.pos = pos;
		this.sense = sense;
	}

	/**
	 * Gets the referent.
	 * 
	 * @return The referent.
	 */
	public String getReferent() {
		return referent;
	}

	/**
	 * Gets the symbol.
	 * 
	 * @return The symbol.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Gets the POS type.
	 * 
	 * @return The POS type.
	 */
	public String getPOS() {
		return pos;
	}

	/**
	 * Gets the sense.
	 * 
	 * @return The sense.
	 */
	public int getSense() {
		return sense;
	}
}
