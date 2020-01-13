package automaton.automaton;

import java.util.ArrayList;
import java.util.List;

public class ClosureTaker {
    private ClosureTaker() {

    }

    public static State[] getClosure(State state, Automaton automaton) {
        ArrayList<State> list = new ArrayList();
        list.add(state);
        for (int i = 0; i < list.size(); i++) {
            State s = list.get(i);
            Transition[] transitions = automaton.getTransitionsFromState(s);
            for (Transition transition : transitions) {
                if (transition.getLabel().equals("")) {
                    State toState = transition.getTo();
                    if (!list.contains(toState)) {
                        list.add(toState);
                    }
                }
            }
        }
        return list.toArray(new State[0]);
    }
}
