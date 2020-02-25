package automaton.fsautomaton;

import automaton.automaton.NondeterminismDetector;
import automaton.automaton.Transition;

public class FSANondeterminismDetector extends NondeterminismDetector {
    @Override
    public boolean areNondeterministic(Transition t1, Transition t2) {
        if (t1.getLabel().equals(t2.getLabel())) {
            return true;
        } else if (t1.getLabel().startsWith(t2.getLabel())) {
            return true;
        } else return t2.getLabel().startsWith(t1.getLabel());
    }
}
