package automaton.automaton;

import automaton.fsautomaton.FSANondeterminismDetector;
import automaton.fsautomaton.FiniteStateAutomaton;

public class AutomatonChecker {
    public boolean isNFA(Automaton automaton) {
        if (!(automaton instanceof FiniteStateAutomaton)) {
            return false;
        }
        NondeterminismDetector nd = new FSANondeterminismDetector();
        State[] nondeterministicStates = nd.getNondeterministicStates(automaton);
        return nondeterministicStates.length > 0;
    }
}
