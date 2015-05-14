package boxparse.interpretation.drs.condition;

/**
 * A relation between two referents.
 * 
 * @author Eugenio Ribeiro
 */
public class Relation implements Condition {
	/**
	 * The first referent.
	 */
	private final String ref1;
	/**
	 * The second referent.
	 */
	private final String ref2;
	/**
	 * The symbol that originated the relation.
	 */
	private final String symbol;
	/**
	 * The sense of the relation (What is this? :x)
	 */
	private final int sense;
	
	/**
	 * Creates a new relation.
	 * 
	 * @param ref1 The first referent.
	 * @param ref2 The second referent.
	 * @param symbol The symbol.
	 * @param sense The sense.
	 */
	public Relation(String ref1, String ref2, String symbol, int sense) {
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.symbol = symbol;
		this.sense = sense;
	}

	/**
	 * Gets the first referent.
	 * 
	 * @return The first referent.
	 */
	public String getFirstReferent() {
		return ref1;
	}

	/**
	 * Gets the second referent.
	 * 
	 * @return The second referent.
	 */
	public String getSecondReferent() {
		return ref2;
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
	 * Gets the sense.
	 * 
	 * @return The sense.
	 */
	public int getSense() {
		return sense;
	}
	
}
