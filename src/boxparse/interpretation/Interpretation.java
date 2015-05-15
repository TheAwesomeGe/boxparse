package boxparse.interpretation;

import java.util.HashMap;

import boxparse.interpretation.drs.DRS;

/**
 * An interpretation in DRS form.
 * 
 * @author Eugenio Ribeiro
 */
public class Interpretation {
	private HashMap<String, Token> tokens;
	private HashMap<String, Referent> referents;
	private DRS drs;
	
	public Interpretation(HashMap<String, Token> tokens, HashMap<String, Referent> referents, DRS drs) {
		this.tokens = tokens;
		this.referents = referents;
		this.drs = drs;
	}
	
	public HashMap<String, Token> getTokens() {
		return tokens;
	}
	
	public HashMap<String, Referent> getReferents() {
		return referents;
	}
	
	public DRS getDRS() {
		return drs;
	}
}
