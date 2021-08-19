package com.gigamage.covidrobot;

public class InputCommand {
    private String direction;
    int xCurrent, yCurrent, xMax, yMax;
    private String[] movement;


    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getxCurrent() {
        return xCurrent;
    }

    public void setxCurrent(int xCurrent) {
        this.xCurrent = xCurrent;
    }

    public int getyCurrent() {
        return yCurrent;
    }

    public void setyCurrent(int yCurrent) {
        this.yCurrent = yCurrent;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    public String[] getMovement() {
        return movement;
    }

    public void setMovement(String[] movement) {
        this.movement = movement;
    }
}
