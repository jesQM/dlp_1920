package symbolTable;

import java.util.*;
import ast.definitions.Definition;

public class SymbolTable {
	
	private int scope=0;
	private List<Map<String,Definition>> table;
	public SymbolTable()  {
		table = new ArrayList<>();
		table.add( new HashMap<>() ); // Create the global scope
	}

	/**
	 * Adds a scope
	 */
	public void set() {
		table.add( new HashMap<>() );
		this.scope++;
	}

	/**
	 * Removes a scope
	 */
	public void reset() {
		table.remove( this.scope);
		this.scope--;
	}
	
	public boolean insert(Definition definition) {
		Map<String,Definition> tableOfScope = table.get( this.scope );

		// Already defined in scope => return false
		if ( tableOfScope.get(definition.getName()) != null )
			return false;

		// Not defined in scope => add & return true
		tableOfScope.put( definition.getName(), definition );
		definition.setScope(this.scope);
		return true;
	}

	/**
	 * Search on all scopes for a definition of an id
	 * @param id
	 * @return the definition or null if it was not defined
	 */
	public Definition find(String id) {
		int startScope = this.scope; // save scope state
		Definition definition = null;

		while( this.scope >= 0 && definition == null ){
			definition = findInCurrentScope( id );
			this.scope--;
		}

		this.scope = startScope; // restore scope state
		return definition;
	}

	public Definition findInCurrentScope(String id) {
		return table.get( this.scope ).get( id );
	}
}
