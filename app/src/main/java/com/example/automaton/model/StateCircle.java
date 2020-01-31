package com.example.automaton.model;

import automaton.automaton.State;

public class StateCircle {
    public int xPosition;
    public int yPosition;
    public int radius;
    public State state;

    public StateCircle(int xPosition, int yPosition, int radius, State state) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.radius = radius;
        this.state = state;
    }
}
