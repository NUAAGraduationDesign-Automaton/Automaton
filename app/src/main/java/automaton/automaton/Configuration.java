package automaton.automaton;

public abstract class Configuration {

    private State currentState;

    private Configuration parent;

    public Configuration(State state, Configuration parent) {
        this.currentState = state;
        this.parent = parent;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public Configuration getParent() {
        return parent;
    }

    public abstract boolean isAccepted();
}
