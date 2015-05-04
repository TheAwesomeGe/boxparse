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
	private DRS drs;
	
	public Interpretation(HashMap<String, Token> tokens, DRS drs) {
		this.tokens = tokens;
		this.drs = drs;
	}
	
	public HashMap<String, Token> getTokens() {
		return tokens;
	}
	
	public DRS getDRS() {
		return drs;
	}
}
