package automaton.fsautomaton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

import automaton.automaton.Automaton;
import automaton.automaton.AutomatonChecker;
import automaton.automaton.ClosureTaker;
import automaton.automaton.State;
import automaton.automaton.Transition;

public class NFAToDFA {

    public NFAToDFA() {

    }

    public boolean hasFinalState(State[] states, Automaton automaton) {
        for (State state : states) {
            if (automaton.isFinalState(state)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsState(State state, State[] states) {
        for (State s : states) {
            if (state == s) return true;
        }
        return false;
    }

    public String getStringForStates(State[] states) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < states.length - 1; i++) {
            buffer.append(states[i].getName());
            buffer.append(",");
        }
        buffer.append(states[states.length - 1].getName());
        return buffer.toString();
    }

    public State[] getStatesForState(State state, Automaton automaton) {
        if (state.getName() == null) {
            return new State[0];
        }
        StringTokenizer tokenizer = new StringTokenizer(state.getName(), " \t\n\r\f,q");
        ArrayList<State> states = new ArrayList();
        while (tokenizer.hasMoreTokens()) {
            states.add(automaton.getStateWithName(tokenizer.nextToken()));
        }
        return states.toArray(new State[0]);
    }

    private boolean containSameStates(State[] states1, State[] states2) {
        if (states1.length != states2.length) return false;
        Arrays.sort(states1, new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return o1.hashCode() - o2.hashCode();
            }
        });
        Arrays.sort(states2, new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return o1.hashCode() - o2.hashCode();
            }
        });

        for (int i = 0; i < states1.length; i++) {
            if (states1[i] != states2[i]) return false;
        }
        return true;
    }

    public State getStateForStates(State[] states, Automaton dfa, Automaton nfa) {
        State[] dfaStates = dfa.getStates();
        for (State state : dfaStates) {
            State[] nfaStates = getStatesForState(state, nfa);
            if (containSameStates(nfaStates, states)) {
                return state;
            }
        }
        return null;
    }

    public State createStateWithStates(Automaton dfa, State[] states, Automaton nfa) {
        State newState = new State(dfa);
        newState.setName(getStringForStates(states));
        dfa.addState(newState);
        return newState;
    }

    public State createInitialState(Automaton nfa, Automaton dfa) {
        State initialState = nfa.getInitialState();
        State[] initialStateClosure = ClosureTaker.getClosure(initialState, nfa);
        State state = createStateWithStates(dfa, initialStateClosure, nfa);
        dfa.setInitialState(state);
        return state;
    }

    public State[] getStatesOnTerminal(String terminal, State[] states, Automaton automaton) {
        ArrayList<State> list = new ArrayList();
        for (State state : states) {
            Transition[] transitions = automaton.getTransitionsFromState(state);
            for (Transition transition : transitions) {
                if (transition.getLabel().equals(terminal)) {
                    State toState = transition.getTo();
                    State[] closure = ClosureTaker.getClosure(toState, automaton);
                    for (State stateInClosure : closure) {
                        if (!list.contains(stateInClosure)) {
                            list.add(stateInClosure);
                        }
                    }
                }
            }
        }
        return list.toArray(new State[0]);
    }

    public ArrayList expandState(State state, Automaton nfa, Automaton dfa) {
        ArrayList list = new ArrayList();
        FSAAlphabetRetriever fsaAlphabetRetriever = new FSAAlphabetRetriever();
        String[] alphabet = fsaAlphabetRetriever.getAlphabet(nfa);
        for (String s : alphabet) {
            State[] states = getStatesOnTerminal(s, getStatesForState(state, nfa), nfa);
            if (states.length > 0) {
                State toState = getStateForStates(states, dfa, nfa);
                if (toState == null) {
                    toState = createStateWithStates(dfa, states, nfa);
                    list.add(toState);
                }
                Transition transition = new Transition(state, toState);
                transition.setLabel(s);
                dfa.addTransition(transition);
            }
        }
        return list;
    }

    public FiniteStateAutomaton convertToDFA(Automaton automaton) {
        AutomatonChecker ac = new AutomatonChecker();
        if (!ac.isNFA(automaton)) {
            return (FiniteStateAutomaton) automaton.clone();
        }

        FiniteStateAutomaton dfa = new FiniteStateAutomaton();
        State initialState = createInitialState(automaton, dfa);

        ArrayList list = new ArrayList<>();
        list.add(initialState);
        while (!list.isEmpty()) {
            ArrayList statesToExpand = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                State state = (State) it.next();
                statesToExpand.addAll(expandState(state, automaton, dfa));
                it.remove();
            }
            list.addAll(statesToExpand);
        }
        return dfa;
    }
}
