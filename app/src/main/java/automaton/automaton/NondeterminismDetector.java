package automaton.automaton;

import java.util.ArrayList;

public abstract class NondeterminismDetector {

    public abstract boolean areNondeterministic(Transition t1, Transition t2);

    State[] getNondeterministicStates(Automaton automaton) {
        State[] states = automaton.getStates();
        ArrayList<State> list = new ArrayList<>();
        for (State state : states) {
            Transition[] transitions = automaton.getTransitionsFromState(state);
            for (int i = 0; i < transitions.length; i++) {
                Transition transition1 = transitions[i];
                if (transition1.getLabel().equals("")) {
                    if (!list.contains(state)) {
                        list.add(state);
                    }
                } else {
                    for (int j = i + 1; j < transitions.length; j++) {
                        Transition transition2 = transitions[j];
                        if (areNondeterministic(transition1, transition2)) {
                            if (!list.contains(state)) {
                                list.add(state);
                            }
                        }
                    }
                }
            }
        }
        return list.toArray(new State[1]);
    }

}
