package automaton.fsautomaton;

import automaton.automaton.Automaton;
import automaton.automaton.Transition;

public class FSALabelHandler {

    public FSALabelHandler() {

    }

    public static boolean hasMultipleCharacter(Automaton automaton) {
        Transition[] transitions = automaton.getTransitions();
        for (Transition transition : transitions) {
            if (transition.getLabel().length() > 1) {
                return true;
            }
        }
        return false;
    }

}
