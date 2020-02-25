package automaton.fsautomaton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import automaton.automaton.Automaton;
import automaton.automaton.AutomatonSimulator;
import automaton.automaton.Configuration;
import automaton.automaton.State;
import automaton.automaton.Transition;

public class FSASimulator extends AutomatonSimulator {
    
    FSASimulator(Automaton automaton) {
        super(automaton);
    }

    @Override
    public boolean simulateInput(String input) {
        this.configurations.clear();
        Configuration[] initialConfigurations = getInitialConfigurations(input);
        Collections.addAll(this.configurations, initialConfigurations);
        while (!this.configurations.isEmpty()) {
            if (isAccepted()) {
                return true;
            }
            ArrayList<FSAConfiguration> configurationsToAdd = new ArrayList<>();
            Iterator it = this.configurations.iterator();
            while (it.hasNext()) {
                FSAConfiguration configuration = (FSAConfiguration) it.next();
                ArrayList<FSAConfiguration> configsToAdd = stepConfiguraion(configuration);
                configurationsToAdd.addAll(configsToAdd);
                it.remove();
            }
            this.configurations.addAll(configurationsToAdd);
        }
        return false;
    }

    @Override
    public boolean isAccepted() {
        for (Configuration configuration : getConfigurations()) {
            FSAConfiguration fsaConfiguration = (FSAConfiguration) configuration;
            State currentState = configuration.getCurrentState();
            if (fsaConfiguration.getUnprocessed().equals("") && getAutomaton().isFinalState(currentState)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<FSAConfiguration> stepConfiguraion(Configuration configuration) {
        ArrayList<FSAConfiguration> list = new ArrayList<>();
        FSAConfiguration fsaConfiguration = (FSAConfiguration) configuration;
        String unprocessedInput = fsaConfiguration.getUnprocessed();
        String totalInput = fsaConfiguration.getInput();
        State currentState = fsaConfiguration.getCurrentState();
        Transition[] transitions = getAutomaton().getTransitionsFromState(currentState);
        for (Transition transition : transitions) {
            String label = transition.getLabel();
            String input = "";
            if (unprocessedInput.startsWith(label)) {
                if (label.length() < unprocessedInput.length()) {
                    input = unprocessedInput.substring(label.length());
                }
                State toState = transition.getTo();
                FSAConfiguration configurationToAdd = new FSAConfiguration(toState, fsaConfiguration, totalInput, input);
                list.add(configurationToAdd);
            }
        }
        return list;
    }

    private Configuration[] getInitialConfigurations(String input) {
        Configuration[] configurations = new Configuration[1];
        configurations[0] = new FSAConfiguration(getAutomaton().getInitialState(), null, input, input);
        return configurations;
    }
}
