package boxparse.interpretation;

/**
 * A token represents a segment of an utterance.
 * 
 * @author Eugenio Ribeiro
 */
public class Token {
	
	/**
	 * The token's id.
	 */
	private final String id;
	
	/**
	 * The word.
	 */
	private final String word;
	
	/**
	 * The POS tag associated with the token.
	 */
	private String pos;
	
	/**
	 * The Named Entity tag associated with the token if it refers to a named entity.
	 */
	private String ne;
	
	/**
	 * Instantiates a new token.
	 *
	 * @param id the id
	 * @param word the word
	 * @param pos The POS tag
	 * @param ne The 
	 */
	public Token(String id, String word, String pos, String ne) {
		this.id = id;
		this.word = word;
		this.pos = pos;
		this.ne = ne;
	}
	
	/**
	 * Instantiates a new token.
	 *
	 * @param id the id
	 * @param word the word
	 * @param pos the pos
	 */
	public Token(String id, String word, String pos) {
		this(id, word, pos, "");
	}
	
	/**
	 * Instantiates a new token.
	 *
	 * @param id the id
	 * @param word the word
	 */
	public Token(String id, String word) {
		this(id, word, "", "");
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Gets the word.
	 *
	 * @return the word
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Gets the POS tag.
	 *
	 * @return the POS tag
	 */
	public String getPOSTag() {
		return pos;
	}
	
	/**
	 * Sets the POS tag.
	 *
	 * @param pos the new POS tag
	 */
	public void setPOSTag(String pos) {
		this.pos = pos;
	}
	
	/**
	 * Gets the NE tag.
	 *
	 * @return the NE tag
	 */
	public String getNETag() {
		return ne;
	}
	
	/**
	 * Sets the NE tag.
	 *
	 * @param ne the new NE tag
	 */
	public void setNETag(String ne) {
		this.ne = ne;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		final String NEW_LINE = System.getProperty("line.separator");
		
		builder.append("Token " + id + ":" + NEW_LINE);
		builder.append("Word: " + word);
		
		if(!pos.isEmpty()) {
			builder.append(" - POS: " + pos);
		}
		
		if(!ne.isEmpty()) {
			builder.append(" - NE: " + ne);
		}
		
		builder.append(NEW_LINE);
		
		return builder.toString();
	}
}
