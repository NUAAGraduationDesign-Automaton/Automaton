package automaton.automaton;

import org.junit.Test;

import static org.junit.Assert.*;

public class AutomatonCheckerTest {
    @Test
    public void easyFiniteStateAutomatonIsNotNFA() {
        AutomatonChecker checker = new AutomatonChecker();
        assertFalse(checker.isNFA(AutomatonCreator.getCreator().getEasyFSA()));
    }

    @Test
    public void normalFiniteStateAutomatonIsNotNFA() {
        AutomatonChecker checker = new AutomatonChecker();
        assertFalse(checker.isNFA(AutomatonCreator.getCreator().getNormalFSA()));
    }

    @Test
    public void nfaToDfaTestAutomatonIsNFA() {
        AutomatonChecker checker = new AutomatonChecker();
        assertTrue(checker.isNFA(AutomatonCreator.getCreator().getNfaToDFATestFSA()));
    }
}