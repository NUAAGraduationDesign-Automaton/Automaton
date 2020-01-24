package automaton.fsautomaton;

import org.junit.Test;

import automaton.automaton.AutomatonCreator;

import static org.junit.Assert.*;

public class NFAToDFATest {

    @Test
    public void hasFinalState() {
        FiniteStateAutomaton automaton = AutomatonCreator.getCreator().getNfaToDFATestFSA();
        NFAToDFA ntd = new NFAToDFA();
        assertTrue(ntd.hasFinalState(automaton.getStates(), automaton));
    }

    @Test
    public void convertToDFA() {
        FiniteStateAutomaton automaton = AutomatonCreator.getCreator().getNfaToDFATestFSA();
        NFAToDFA ntd = new NFAToDFA();
        FiniteStateAutomaton dfa = ntd.convertToDFA(automaton);
    }
}