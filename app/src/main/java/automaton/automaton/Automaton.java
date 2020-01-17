package automaton.automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Automaton implements Cloneable {

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

    public Automaton clone() {
        Automaton a;
        try {
            a = getClass().newInstance();
        } catch (Throwable e) {
            System.err.println("clone of automaton failed!");
            return null;
        }
        HashMap map = new HashMap();
        Iterator it = states.iterator();
        while (it.hasNext()) {
            State state = (State) it.next();
            State stateCopy = new State(state.getAutomaton());
            stateCopy.setName(state.getName());
            map.put(state, stateCopy);
            a.addState(stateCopy);
        }

        it = finalStates.iterator();
        while (it.hasNext()) {
            State state = (State) it.next();
            a.addFinalState((State) map.get(state));
        }
        a.setInitialState((State) map.get(initialState));

        it = states.iterator();
        while (it.hasNext()) {
            State state = (State) it.next();
            Transition[] transitions = getTransitionsFromState(state);
            State from = (State) map.get(state);
            for (Transition transition : transitions) {
                State to = (State) map.get(transition.getTo());
                Transition transitionCopy = new Transition(from, to);
                a.addTransition(transitionCopy);
            }
        }
        return a;
    }

    public void addState(State state) {
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

    public State getStateWithName(String name) {
        for (State state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
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

    @Override
    public int hashCode() {
        int ret = 0;
        for (State state : states) {
            ret += state.specialHash();
        }
        for (Transition transition : transitions) {
            ret += transition.specialHash();
        }
        ret += finalStates.hashCode();
        ret += initialState == null ? 0 : (int)(initialState.specialHash() * Math.PI);
        return ret;
    }
}
