package automaton.automaton;

import java.util.UUID;

public class Transition {

    // 转移的唯一标识
    private UUID uuid;

    // 转移的头
    private State from;

    // 转移的尾
    private State to;

    // 转移的标签
    private String label;


    public Transition(State from, State to) {
        uuid = UUID.randomUUID();
        this.from = from;
        this.to = to;
    }
}
