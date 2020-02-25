package automaton.automaton;

public abstract class AlphabetRetriver {
    public AlphabetRetriver() {

    }

    public abstract String[] getAlphabet(Automaton automaton);
}
