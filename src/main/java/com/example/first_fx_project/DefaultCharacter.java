package com.example.first_fx_project;

public class DefaultCharacter extends Player{
    static private int maxMeter;

    public DefaultCharacter(int length, int width, int speed, boolean isInverted, boolean meterIsFull) {
        super(length, width, speed, isInverted, meterIsFull);
    }
}
