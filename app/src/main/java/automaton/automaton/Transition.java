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

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public State getFrom() {
        return from;
    }

    public State getTo() {
        return to;
    }

    public int specialHash() {
        int hash = from == to ? from.specialHash() : from.specialHash() ^ to.specialHash();
        return hash;
    }
}
