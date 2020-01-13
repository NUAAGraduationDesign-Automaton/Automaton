package automaton.fsautomaton;

import automaton.automaton.Automaton;
import automaton.automaton.State;

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


}
