package automaton.automaton;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class AutomatonSimulator {

    private Automaton automaton;

    private HashSet<Configuration> configurations;

    public AutomatonSimulator(Automaton automaton) {
        this.automaton = automaton;
        this.configurations = new HashSet<Configuration>();
    }

    public abstract boolean simulateInput();

    public abstract boolean isAccepted();

    public abstract ArrayList stepConfiguraion();

    public Automaton getAutomaton() {
        return automaton;
    }

    public HashSet<Configuration> getConfigurations() {
        return configurations;
    }
}
