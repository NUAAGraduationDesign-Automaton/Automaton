package automaton.automaton;

import org.junit.Test;

import automaton.fsautomaton.FiniteStateAutomaton;

import static org.junit.Assert.*;

public class ClosureTakerTest {
    private FiniteStateAutomaton easyFSA;

    private FiniteStateAutomaton buildEasyFSA() {
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

    @Test
    public void takeClosureTest() {
        buildEasyFSA();
        assertArrayEquals(ClosureTaker.getClosure(easyFSA.getInitialState(), easyFSA), easyFSA.getStates());
    }
}