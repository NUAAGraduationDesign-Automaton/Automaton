package automaton.fsautomaton;

import automaton.automaton.Automaton;
import automaton.automaton.Configuration;
import automaton.automaton.State;

public class FSAConfiguration extends Configuration {

    private String input;

    private String unprocessed;

    public FSAConfiguration(State state, FSAConfiguration parent, String input, String unprocessed) {
        super(state, parent);
        this.input = input;
        this.unprocessed = unprocessed;
    }

    public String getInput() {
        return input;
    }

    public String getUnprocessed() {
        return unprocessed;
    }

    @Override
    public boolean isAccepted() {
        if (getUnprocessed().length() != 0) {
            return false;
        }
        State state = getCurrentState();
        Automaton automaton = state.getAutomaton();
        return automaton.isFinalState(state);
    }
}
