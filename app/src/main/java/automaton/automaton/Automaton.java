package automaton.automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Automaton {

    // 唯一标识
    private UUID uuid;

    // 自动机下所有状态
    private HashSet<State> states;

    // 自动机下所有转移
    private HashSet<Transition> transitions;

    // 自动机的终结状态
    private HashSet<State> finalStates;

    // 自动机的初始状态
    private State initialState;

    private HashMap transitionFromStateMap = new HashMap();

    private HashMap transitionToStateMap = new HashMap();

    private State[] cachedStates = null;

    private Transition[] cachedTransitions = null;

    private State[] cachedFinalStates = null;

    public Automaton() {
        states = new HashSet<State>();
        transitions = new HashSet<Transition>();
        finalStates = new HashSet<State>();
        initialState = null;
        uuid = UUID.randomUUID();
    }

    protected final void addState(State state) {
        states.add(state);
        transitionFromStateMap.put(state, new LinkedList<Transition>());
        transitionToStateMap.put(state, new LinkedList<Transition>());
        cachedStates = null;
    }

    public void removeState(State state) {
        for (Transition transition : getTransitionsFromState(state)) {
            removeTransition(transition);
        }
        for (Transition transition : getTransitionsToState(state)) {
            removeTransition(transition);
        }
        transitionFromStateMap.remove(state);
        transitionToStateMap.remove(state);
        if (isInitialState(state)) {
            initialState = null;
        }
        finalStates.remove(state);
        states.remove(state);
        cachedStates = null;
    }

    protected final void addTranstion(Transition transition) {
        if (transitions.contains(transition)) {
            return;
        }
        transitions.add(transition);
        List transitionsFromState = (List) transitionFromStateMap.get(transition.getFrom());
        List transitionsToState = (List) transitionToStateMap.get(transition.getTo());
        transitionsFromState.add(transition);
        transitionsToState.add(transition);
        cachedTransitions = null;
    }

    public void removeTransition(Transition transition) {
        transitions.remove(transition);
        List transitionsFromState = (List) transitionFromStateMap.get(transition.getFrom());
        transitionsFromState.remove(transition);
        List transitionsToState = (List) transitionToStateMap.get(transition.getTo());
        transitionsToState.remove(transition);
        cachedTransitions = null;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public State getInitialState() {
        return initialState;
    }

    public boolean isInitialState(State state) {
        return initialState.equals(state);
    }

    public void addFinalState(State state) {
        finalStates.add(state);
    }

    public void removeFinalState(State state) {
        finalStates.remove(state);
    }

    public State[] getFinalStates() {
        if (cachedFinalStates == null) {
            cachedFinalStates = (State[]) finalStates.toArray();
        }
        return cachedFinalStates;
    }

    public boolean isFinalState(State state) {
        return finalStates.contains(state);
    }

    public State[] getStates() {
        if (cachedStates == null) {
            cachedStates = (State[]) states.toArray();
        }
        return cachedStates;
    }

    public Transition[] getTransitionsFromState(State from) {
        List transitionsFromState = (List) transitionFromStateMap.get(from);
        return (Transition[]) transitionsFromState.toArray();
    }

    public Transition[] getTransitionsToState(State to) {
        List transitionsToState = (List) transitionToStateMap.get(to);
        return (Transition[]) transitionsToState.toArray();
    }

    public Transition[] getTransitionsFromStateToState(State from, State to) {
        Transition[] transitionsFromState = getTransitionsFromState(from);
        ArrayList list = new ArrayList();
        for (Transition transition : transitionsFromState) {
            if (transition.getTo().equals(to)) {
                list.add(transition);
            }
        }
        return (Transition[]) list.toArray();
    }

    public Transition[] getTransitions() {
        if (cachedTransitions == null) {
            cachedTransitions = (Transition[]) transitions.toArray();
        }
        return cachedTransitions;
    }
}
