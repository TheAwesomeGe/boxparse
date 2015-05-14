package boxparse.interpretation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import boxparse.interpretation.drs.condition.Condition;

/**
 * A referent is something referred to in the DRS. It is usually an entity or a verb.
 * 
 * @author Eugenio Ribeiro
 */
public class Referent {
	/**
	 * The referent's ID.
	 */
	private final String id;
	
	/**
	 * The ID of the token associated with the referent.
	 */
	private final String tokenID;
	
	/**
	 * The list of references to the referent.
	 */
	private final List<Condition> references;
	
	/**
	 * Creates a new referent.
	 * 
	 * @param id The referent's ID.
	 * @param tokenID The ID of the token associated with the referent.
	 */
	public Referent(String id, String tokenID) {
		this.id = id;
		this.tokenID = tokenID;
		this.references = new ArrayList<Condition>();
	}
	
	/**
	 * Creates a new referent without an associated token.
	 * 
	 * @param id The referent's ID.
	 */
	public Referent(String id) {
		this(id, "");
	}
	
	/**
	 * Gets the referent's ID.
	 * 
	 * @return The referent's ID.
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Gets the associated token ID.
	 * 
	 * @return The token's ID.
	 */
	public String getTokenID() {
		return tokenID;
	}
	
	/**
	 * Gets the list of references. 
	 * 
	 * @return An unmodifiable list of references.
	 */
	public List<Condition> getReferences() {
		return Collections.unmodifiableList(references);
	}
	
	/**
	 * Adds a new reference.
	 * 
	 * @param reference The reference.
	 */
	public void addReference(Condition reference) {
		references.add(reference);
	}
}

