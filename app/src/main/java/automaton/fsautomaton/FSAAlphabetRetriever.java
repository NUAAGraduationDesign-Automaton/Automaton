package automaton.fsautomaton;

import java.util.ArrayList;

import automaton.automaton.AlphabetRetriver;
import automaton.automaton.Automaton;
import automaton.automaton.Transition;

public class FSAAlphabetRetriever extends AlphabetRetriver {
    @Override
    public String[] getAlphabet(Automaton automaton) {
        ArrayList<String> list = new ArrayList<>();
        Transition[] transitions = automaton.getTransitions();
        for (Transition transition : transitions) {
            String label = transition.getLabel();
            if (!label.equals("") && !list.contains(label)) {
                list.add(label);
            }
        }
        return list.toArray(new String[0]);
    }
}
