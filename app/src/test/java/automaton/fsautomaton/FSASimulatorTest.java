package automaton.fsautomaton;

import org.junit.Test;

import automaton.automaton.AutomatonCreator;

import static org.junit.Assert.*;

public class FSASimulatorTest {
    @Test
    public void easyFiniteStateAutomatonNotApproved() {
        FSASimulator simulator = new FSASimulator(AutomatonCreator.getCreator().getEasyFSA());
        assertFalse(simulator.simulateInput("b"));
    }

    @Test
    public void easyFiniteStateAutomatonIsApproved() {
        FSASimulator simulator = new FSASimulator(AutomatonCreator.getCreator().getEasyFSA());
        assertTrue(simulator.simulateInput("a"));
    }

    @Test
    public void normalFiniteStateAutomatonNotApproved() {
        FSASimulator simulator = new FSASimulator(AutomatonCreator.getCreator().getNormalFSA());
        assertFalse(simulator.simulateInput("01"));
    }

    @Test
    public void normalFiniteStateAutomatonIsApproved() {
        FSASimulator simulator = new FSASimulator(AutomatonCreator.getCreator().getNormalFSA());
        assertTrue(simulator.simulateInput("000000000101"));
    }

    @Test
    public void nfaToDfaTestAutomatonIsApproved() {
        FSASimulator simulator = new FSASimulator(AutomatonCreator.getCreator().getNfaToDFATestFSA());
        assertTrue(simulator.simulateInput("aaab"));
    }
}