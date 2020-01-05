package automaton.automaton;

import java.util.UUID;

public class State {

    // 状态的唯一标识
    private UUID uuid;

    // 该状态所在的自动机
    private Automaton automaton;

    // 该状态的状态名
    private String name;

    public State(Automaton automaton) {
        uuid = UUID.randomUUID();
        this.automaton = automaton;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
