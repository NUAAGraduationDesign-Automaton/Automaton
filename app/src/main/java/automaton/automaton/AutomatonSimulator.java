package automaton.automaton;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class AutomatonSimulator {

    private Automaton automaton;

    protected HashSet<Configuration> configurations;

    public AutomatonSimulator(Automaton automaton) {
        this.automaton = automaton;
        this.configurations = new HashSet<>();
    }

    public abstract boolean simulateInput(String input);

    public abstract boolean isAccepted();

    public abstract ArrayList stepConfiguraion(Configuration configuration);

    public Automaton getAutomaton() {
        return automaton;
    }

    protected HashSet<Configuration> getConfigurations() {
        return configurations;
    }
}
