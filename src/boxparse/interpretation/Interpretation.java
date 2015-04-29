package boxparse.interpretation;

import java.util.HashMap;

import boxparse.interpretation.drs.DRS;

public class Interpretation {
	private HashMap<String, Token> tokens;
	private DRS drs;
	
	public HashMap<String, Token> getTokens() {
		return tokens;
	}
	
	public DRS getDRS() {
		return drs;
	}
}
