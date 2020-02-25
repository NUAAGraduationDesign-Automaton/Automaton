package automaton.automaton;

import java.util.ArrayList;

import automaton.fsautomaton.FiniteStateAutomaton;

public class AutomatonCreator {
    private static AutomatonCreator creator = new AutomatonCreator();

    private FiniteStateAutomaton easyFSA;

    private FiniteStateAutomaton normalFSA;

    private FiniteStateAutomaton nfaToDFATestFSA;

    public FiniteStateAutomaton getEasyFSA() {
        if (easyFSA == null) {
            easyFSA = new FiniteStateAutomaton();
            State state1 = new State(easyFSA);
            State state2 = new State(easyFSA);
            Transition transition1 = new Transition(state1, state2);
            transition1.setLabel("");
            easyFSA.addStates(new State[]{state1, state2});
            easyFSA.addTransition(transition1);
            easyFSA.setInitialState(state1);
            easyFSA.addFinalState(state2);
        }
        return easyFSA;
    }

    public FiniteStateAutomaton getNormalFSA() {
        if (normalFSA == null) {
            normalFSA = new FiniteStateAutomaton();
            State state1 = new State(normalFSA);
            State state2 = new State(normalFSA);
            State state3 = new State(normalFSA);
            Transition transition1 = new Transition(state1, state1);
            transition1.setLabel("0");
            Transition transition2 = new Transition(state1, state2);
            transition2.setLabel("1");
            Transition transition3 = new Transition(state2, state1);
            transition3.setLabel("1");
            Transition transition4 = new Transition(state2, state2);
            transition4.setLabel("0");
            Transition transition5 = new Transition(state3, state3);
            transition5.setLabel("0");
            Transition transition6 = new Transition(state3, state1);
            transition6.setLabel("1");
            normalFSA.addStates(new State[]{state1, state2, state3});
            normalFSA.addTransitions(new Transition[]{transition1, transition2, transition3, transition4, transition5, transition6});
            normalFSA.setInitialState(state1);
            normalFSA.addFinalState(state1);
        }
        return normalFSA;
    }

    public FiniteStateAutomaton getNfaToDFATestFSA() {
        if (nfaToDFATestFSA == null) {
            nfaToDFATestFSA = new FiniteStateAutomaton();
            ArrayList<State> statesList = new ArrayList();
            for (int i = 0; i < 10; i++) {
                State state = new State(nfaToDFATestFSA);
                statesList.add(state);
                state.setName("s" + i);
                nfaToDFATestFSA.addState(state);
            }
            Transition transition1 = new Transition(statesList.get(0), statesList.get(1));
            transition1.setLabel("");
            Transition transition2 = new Transition(statesList.get(0), statesList.get(7));
            transition2.setLabel("");
            Transition transition3 = new Transition(statesList.get(1), statesList.get(2));
            transition3.setLabel("");
            Transition transition4 = new Transition(statesList.get(1), statesList.get(4));
            transition4.setLabel("");
            Transition transition5 = new Transition(statesList.get(2), statesList.get(3));
            transition5.setLabel("a");
            Transition transition6 = new Transition(statesList.get(3), statesList.get(6));
            transition6.setLabel("");
            Transition transition7 = new Transition(statesList.get(4), statesList.get(5));
            transition7.setLabel("b");
            Transition transition8 = new Transition(statesList.get(5), statesList.get(6));
            transition8.setLabel("");
            Transition transition9 = new Transition(statesList.get(6), statesList.get(7));
            transition9.setLabel("");
            Transition transition10 = new Transition(statesList.get(6), statesList.get(1));
            transition10.setLabel("");
            Transition transition11 = new Transition(statesList.get(7), statesList.get(8));
            transition11.setLabel("a");
            Transition transition12 = new Transition(statesList.get(8), statesList.get(9));
            transition12.setLabel("b");
            nfaToDFATestFSA.addTransitions(new Transition[]{
                    transition1, transition2, transition3, transition4, transition5,
                    transition6, transition7, transition8, transition9, transition10,
                    transition11, transition12
            });
            nfaToDFATestFSA.setInitialState(statesList.get(0));
            nfaToDFATestFSA.addFinalState(statesList.get(9));
        }
        return nfaToDFATestFSA;
    }

    private AutomatonCreator() {
    }

    public static AutomatonCreator getCreator() {
        return creator;
    }
}
