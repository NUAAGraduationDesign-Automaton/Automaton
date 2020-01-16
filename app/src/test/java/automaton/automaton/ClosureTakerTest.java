package automaton.automaton;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClosureTakerTest {
    @Test
    public void takeClosureTest() {
        Automaton easyFSA = AutomatonCreator.getCreator().getEasyFSA();
        assertArrayEquals(ClosureTaker.getClosure(easyFSA.getInitialState(), easyFSA), easyFSA.getStates());
    }
}