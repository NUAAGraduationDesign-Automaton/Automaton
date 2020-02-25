package automaton.automaton;

import org.junit.Test;

public class AutomatonTest {
    @Test
    public void clone1() {
        Automaton easyFSACopy = AutomatonCreator.getCreator().getEasyFSA().clone();
    }
}