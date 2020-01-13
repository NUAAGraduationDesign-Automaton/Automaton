package automaton.automaton;

import org.junit.Test;

import automaton.fsautomaton.FSASimulator;
import automaton.fsautomaton.FiniteStateAutomaton;

import static org.junit.Assert.*;

public class AutomatonCheckerTest {
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

    private FiniteStateAutomaton buildNormalFSA() {
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

    @Test
    public void easyFiniteStateAutomatonIsNotNFA() {
        AutomatonChecker checker = new AutomatonChecker();
        assertFalse(checker.isNFA(buildEasyFSA()));
    }

    @Test
    public void normalFiniteStateAutomatonIsNotNFA() {
        AutomatonChecker checker = new AutomatonChecker();
        assertFalse(checker.isNFA(buildNormalFSA()));
    }
}