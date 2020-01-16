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

    private HashMap<State, LinkedList<Transition>> transitionFromStateMap = new HashMap<>();

    private HashMap<State, LinkedList<Transition>> transitionToStateMap = new HashMap<>();

    private State[] cachedStates = null;

    private Transition[] cachedTransitions = null;

    private State[] cachedFinalStates = null;

    public Automaton() {
        states = new HashSet<>();
        transitions = new HashSet<>();
        finalStates = new HashSet<>();
        initialState = null;
        uuid = UUID.randomUUID();
    }

    private void addState(State state) {
        states.add(state);
        transitionFromStateMap.put(state, new LinkedList<Transition>());
        transitionToStateMap.put(state, new LinkedList<Transition>());
        cachedStates = null;
    }

    public final void addStates(State[] states) {
        for (State state : states) {
            addState(state);
        }
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

    public final void addTransition(Transition transition) {
        if (transitions.contains(transition)) {
            return;
        }
        transitions.add(transition);
        List<Transition> transitionsFromState = transitionFromStateMap.get(transition.getFrom());
        List<Transition> transitionsToState = transitionToStateMap.get(transition.getTo());
        if (transitionsFromState != null) {
            transitionsFromState.add(transition);
        }
        if (transitionsToState != null) {
            transitionsToState.add(transition);
        }
        cachedTransitions = null;
    }

    public final void addTransitions(Transition[] transitions) {
        for (Transition transition : transitions) {
            addTransition(transition);
        }
    }

    private void removeTransition(Transition transition) {
        transitions.remove(transition);
        List<Transition> transitionsFromState = transitionFromStateMap.get(transition.getFrom());
        if (transitionsFromState != null) {
            transitionsFromState.remove(transition);
        }
        List<Transition> transitionsToState = transitionToStateMap.get(transition.getTo());
        if (transitionsToState != null) {
            transitionsToState.remove(transition);
        }
        cachedTransitions = null;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public State getInitialState() {
        return initialState;
    }

    private boolean isInitialState(State state) {
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
            cachedFinalStates = finalStates.toArray(new State[0]);
        }
        return cachedFinalStates;
    }

    public boolean isFinalState(State state) {
        return finalStates.contains(state);
    }

    public State[] getStates() {
        if (cachedStates == null) {
            cachedStates = states.toArray(new State[0]);
        }
        return cachedStates;
    }

    public Transition[] getTransitionsFromState(State from) {
        List<Transition> list = transitionFromStateMap.get(from);
        if (list != null) {
            return list.toArray(new Transition[0]);
        }
        return new Transition[1];
    }

    private Transition[] getTransitionsToState(State to) {
        List<Transition> list = transitionToStateMap.get(to);
        if (list != null) {
            return list.toArray(new Transition[0]);
        }
        return new Transition[1];
    }

    public Transition[] getTransitionsFromStateToState(State from, State to) {
        Transition[] transitionsFromState = getTransitionsFromState(from);
        ArrayList<Transition> list = new ArrayList<>();
        for (Transition transition : transitionsFromState) {
            if (transition.getTo().equals(to)) {
                list.add(transition);
            }
        }
        return (Transition[]) list.toArray();
    }

    public Transition[] getTransitions() {
        if (cachedTransitions == null) {
            cachedTransitions = transitions.toArray(new Transition[0]);
        }
        return cachedTransitions;
    }
}
