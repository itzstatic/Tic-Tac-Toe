package org.teamresistance.util.state;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Finite State Machine (FSM). The FSM can be in one single state at a time. 
 * Users should instantiate the machine, provide it with states and (case-sensitive) names,
 * set it to an initial state, and then initialize the machine.
 * 
 * @author Mathis
 */
public class StateMachine {
	/**
	 * This FSM's current state. Can never be null after initialization
	 */
	private State currentState;
	
	private Map<String, State> states;
	
	public StateMachine() {
		currentState = null;
		states = new HashMap<>();
	}
	
	/**
	 * Updates this State Machine's current state.
	 */
	public void update() {
		if(currentState != null) {
			currentState.update();
		}
	}

	/**
	 * Registers the specified state.
	 * @param state the state
	 * @throws NullPointerException if state was null.
	 */
	public void addState(State state) {
		if (state == null) {
			throw new NullPointerException("Cannot add null state.");
		}
		addState(state, state.getClass().getSimpleName());
	}
	
	/**
	 * Registers the specified state instance, and associates it with the specified name.
	 * If the name is a null pointer, then the state's runtime class name is used
	 * @param state the state
	 * @param stateName the name
	 * @throws NullPointerException if state was null.
	 * @throws RuntimeException if this machine already contained a state of the specified name.
	 */
	public void addState(State state, String stateName) {
		if (state == null) {
			throw new NullPointerException("Cannot add null state.");
		}
		if (stateName == null) {
			stateName = state.getClass().getSimpleName();
		} 
		if (containsState(stateName)) {
			throw new RuntimeException("Machine already contains state of name " + stateName + ".");
		}
		
		state.setStateMachine(this);
		state.setName(stateName);

		states.put(stateName, state);
	}
	
	/**
	 * Exits the current state, and enters the state of the specified name.
	 * If the state is the current state, Then this method returns without altering this StateMachine.
	 * @param stateName the name
	 * @throws NullPointerException if stateName was null.
	 * @throws RuntimeException if this machine did not contain a state of the specified name.
	 */
	public void setState(String stateName) {
		if (stateName == null) {
			throw new NullPointerException("Cannot set to state of null name.");
		}
		setState(getState(stateName));
	}
	/**
	 * Returns the state object registered with the specified name.
	 * @param stateName the name of the state
	 * @return the state
	 * @throws NullPointerException if stateName was null.
	 * @throws RuntimeException if this machine did not contain a state of the specified name.
	 */
	public State getState(String stateName) {
		if(stateName == null) {
			throw new NullPointerException("Cannot get state of null name.");
		}
		if(!containsState(stateName)) {
			throw new RuntimeException("Machine does not contain state of name " + stateName + ".");
		}
		return states.get(stateName);
	}

	public int getNumStates() {
		return states.size();
	}
	public boolean containsState(String stateName) {
		return states.containsKey(stateName);
	}
	
	/**
	 * Attempts to transition into the specified state.
	 * @param state the state
	 */
	public void setState(State state) {
		if (state == null) {
			throw new NullPointerException("Cannot set to null state.");
		}
		if (!states.containsValue(state)) {
			throw new RuntimeException("Machine does not contain state " + state.getClass().getSimpleName() + ".");
		}
		if (state == currentState) {
			return;
		}
		StateTransition transition = new StateTransition(currentState, state);
		if (currentState != null) {
			currentState.onExit(transition);
		}
		currentState = state;
		
		state.onEntry(transition);
	}
	
	public String getCurrentState() {
		return currentState.getName();
	}

}