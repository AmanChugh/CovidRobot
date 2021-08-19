package com.gigamage.covidrobot;

import java.util.HashMap;
import java.util.Map;

public class CovidHelperRobot {


    private Map<String, String> leftDirection, rightDirection;

    private InputCommand mInputCommand;

    public CovidHelperRobot(InputCommand inputCommand) {
        this.mInputCommand = inputCommand;
        initDirections();
    }

    private void initDirections() {
        rightDirection = new HashMap<>();
        rightDirection.put("N", "E");
        rightDirection.put("E", "S");
        rightDirection.put("S", "W");
        rightDirection.put("W", "N");

        leftDirection = new HashMap<>();
        leftDirection.put("N", "W");
        leftDirection.put("W", "S");
        leftDirection.put("S", "E");
        leftDirection.put("E", "N");
    }


    public void executeCommand() {

        String[] movement = mInputCommand.getMovement();
        String direction = mInputCommand.getDirection();
        for (int i = 0; i < movement.length; i++) {
            if (movement[i].equalsIgnoreCase("R")) {
                mInputCommand.setDirection(rightDirection.get(mInputCommand.getDirection()));
            } else if (movement[i].equalsIgnoreCase("L")) {
                mInputCommand.setDirection(leftDirection.get(mInputCommand.getDirection()));
            } else if (movement[i].equalsIgnoreCase("M")) {
                changeLocation();
            }
        }
    }

    private void changeLocation() {
        if (mInputCommand.getDirection().equalsIgnoreCase("N") && mInputCommand.getyMax() > mInputCommand.getyCurrent()) {
            mInputCommand.setyCurrent(mInputCommand.getyCurrent() + 1);
        } else if (mInputCommand.getDirection().equalsIgnoreCase("E") && mInputCommand.getxMax() > mInputCommand.getxCurrent()) {
            mInputCommand.setxCurrent(mInputCommand.getxCurrent() + 1);
        } else if (mInputCommand.getDirection().equalsIgnoreCase("S") && mInputCommand.getyCurrent() > 0) {
            mInputCommand.setyCurrent(mInputCommand.getyCurrent() - 1);
        } else if (mInputCommand.getDirection().equalsIgnoreCase("W") && mInputCommand.getxCurrent() > 0) {
            mInputCommand.setxCurrent(mInputCommand.getxCurrent() - 1);
        }
    }

}
