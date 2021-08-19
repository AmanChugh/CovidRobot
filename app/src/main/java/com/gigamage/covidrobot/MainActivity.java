package com.gigamage.covidrobot;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText coordinates = findViewById(R.id.et_coordinates);
        TextInputEditText positions = findViewById(R.id.et_positions);
        TextInputEditText movement = findViewById(R.id.et_movement);


        findViewById(R.id.btn_execute).setOnClickListener(v -> {

            String[] coordinatesArr = coordinates.getText().toString().split("");

            String positionsString = positions.getText().toString();

            String movementCommand = movement.getText().toString();

            if (!checkCoodinates(coordinatesArr)) {
                coordinates.setError("Invalid Coordinates");
            } else if (!checkPositions(positionsString)) {
                positions.setError("Invalid Positions");
            } else if (!checkMovement(movementCommand)) {
                movement.setError("Invalid movement Command");
            } else {
                InputCommand inputCommand = createInputObject(coordinatesArr, positionsString.split(""), movementCommand.split(""));
                if (validatePositions(inputCommand)) {

                    CovidHelperRobot covidHelperRobot = new CovidHelperRobot(inputCommand);
                    covidHelperRobot.executeCommand();

                    updateUI(String.format("Output is:- %d %d %s",
                            inputCommand.getxCurrent(),
                            inputCommand.getyCurrent(),
                            inputCommand.getDirection()));
                } else {
                    updateUI("Invalid Robot position");
                }
            }

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        });
    }

    private void updateUI(String resultString) {
        TextView resultTextView = findViewById(R.id.tv_result);
        resultTextView.setVisibility(View.VISIBLE);
        resultTextView.setText(resultString);
    }

    private boolean validatePositions(InputCommand inputCommand) {
        return inputCommand.getxCurrent() <= inputCommand.getxMax() && inputCommand.getyCurrent() <= inputCommand.getyMax();
    }

    private InputCommand createInputObject(String[] coordinatesArr, String[] positionsArr, String[] movementCommand) {
        InputCommand inputCommand = new InputCommand();
        inputCommand.setxCurrent(Integer.parseInt(positionsArr[0]));
        inputCommand.setyCurrent(Integer.parseInt(positionsArr[1]));

        inputCommand.setxMax(Integer.parseInt(coordinatesArr[0]));
        inputCommand.setyMax(Integer.parseInt(coordinatesArr[1]));

        inputCommand.setDirection(positionsArr[2]);
        inputCommand.setMovement(movementCommand);

        return inputCommand;
    }


    private boolean checkMovement(String movementCommand) {
        return movementCommand.matches("^[LRM]+$");
    }

    private boolean checkPositions(String positions) {
        return positions.matches("^([0-9]{2}[NESW]{1})+$");
    }

    private boolean checkCoodinates(String[] coordinatesArr) {
        return coordinatesArr.length == 2;
    }
}