package automaton.fsautomaton;

import java.util.ArrayList;

import automaton.automaton.Automaton;
import automaton.automaton.AutomatonSimulator;
import automaton.automaton.Configuration;
import automaton.automaton.State;

public class FSASimulator extends AutomatonSimulator {
    
    public FSASimulator(Automaton automaton) {
        super(automaton);
    }

    @Override
    public boolean simulateInput() {
        return false;
    }

    @Override
    public boolean isAccepted() {
        for (Configuration configuration : getConfigurations()) {
            FSAConfiguration fsaConfiguration = (FSAConfiguration) configuration;
            State currentState = configuration.getCurrentState();
            if (fsaConfiguration.getUnprocessed() == "" && getAutomaton().isFinalState(currentState)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList stepConfiguraion() {
        return null;
    }
}
