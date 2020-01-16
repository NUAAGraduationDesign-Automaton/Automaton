package automaton.fsautomaton;

import org.junit.Test;

import automaton.automaton.State;
import automaton.automaton.Transition;

import static org.junit.Assert.*;

public class FSAAlphabetRetrieverTest {
    private FiniteStateAutomaton easyFSA;

    private FiniteStateAutomaton normalFSA;

    private FiniteStateAutomaton buildEasyFSA() {
        if (easyFSA == null) {
            easyFSA = new FiniteStateAutomaton();
            State state1 = new State(easyFSA);
            State state2 = new State(easyFSA);
            Transition transition1 = new Transition(state1, state2);
            transition1.setLabel("a");
            easyFSA.addStates(new State[]{state1, state2});
            easyFSA.addTransition(transition1);
            easyFSA.setInitialState(state1);
            easyFSA.addFinalState(state2);
        }
        return easyFSA;
    }

    @Test
    public void alphabetRetrieverTest() {
        buildEasyFSA();
        FSAAlphabetRetriever alphabetRetriever = new FSAAlphabetRetriever();
        String[] alphabet = alphabetRetriever.getAlphabet(easyFSA);
        assertArrayEquals(alphabet, new String[]{"a"});
    }
}