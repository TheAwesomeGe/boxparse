package boxparse.interpretation.drs;

import java.util.List;

import boxparse.interpretation.drs.condition.Condition;

/**
 * A basic DRS.
 * 
 * @author Eugenio Ribeiro
 */
public class BasicDRS implements DRS {
	/**
	 * The conditions present in the DRS.
	 */
	private List<Condition> conditions;
	
	/**
	 * Creates a new basic DRS.
	 * 
	 * @param conditions The conditions.
	 */
	public BasicDRS(List<Condition> conditions) {
		this.conditions = conditions;
	}
	
	/**
	 * Gets the conditions of the DRS.
	 * 
	 * @return The conditions.
	 */
	public List<Condition> getConditions() {
		return conditions;
	}
	
}
