package automaton.fsautomaton;

import org.junit.Test;

import automaton.automaton.Automaton;
import automaton.automaton.AutomatonCreator;

import static org.junit.Assert.*;

public class FSAAlphabetRetrieverTest {
    @Test
    public void alphabetRetrieverTest() {
        Automaton easyFSA = AutomatonCreator.getCreator().getEasyFSA();
        FSAAlphabetRetriever alphabetRetriever = new FSAAlphabetRetriever();
        String[] alphabet = alphabetRetriever.getAlphabet(easyFSA);
        assertArrayEquals(alphabet, new String[]{"a"});
    }
}