package automaton.automaton;

import java.util.HashSet;
import java.util.UUID;

public class Automaton {

    // 唯一标识
    private UUID uuid;

    // 自动机下所有状态
    private HashSet<State> states;

    // 自动机下所有转移
    private HashSet<Transition> transitions;

    // 自动机的终结状态
    private HashSet<State> finalStates;

    // 自动机的初始状态
    private State initialState;

    public Automaton() {
        states = new HashSet<State>();
        transitions = new HashSet<Transition>();
        finalStates = new HashSet<State>();
        initialState = null;
        uuid = UUID.randomUUID();
    }


}
