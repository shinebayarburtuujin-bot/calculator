package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField txt_result;

    private double number1 = 0;
    private String operator = "";
    private boolean isStartNumber = true;

    @FXML
    public void onClearClick(ActionEvent event) {
        txt_result.setText("0");
        number1 = 0;
        operator = "";
        isStartNumber = true;
    }

    @FXML
    public void onDeleteClick(ActionEvent event) {
        String currentText = txt_result.getText();

        if (currentText.isEmpty() || currentText.startsWith("Error")) {
            txt_result.setText("0");
            isStartNumber = true;
            return;
        }

        if (currentText.length() == 1) {
            txt_result.setText("0");
            isStartNumber = true;
        } else {
            txt_result.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    @FXML
    public void onEqualClick(ActionEvent event) {
        String currentText = txt_result.getText();

        if (operator.isEmpty() || currentText.isEmpty() || currentText.startsWith("Error")) {
            return;
        }

        double number2 = Double.parseDouble(currentText);
        double result = 0;

        switch (operator) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
            case "×":
                result = number1 * number2;
                break;
            case "/":
            case "÷":
                if (number2 == 0) {
                    txt_result.setText("Error: Division by zero");
                    operator = "";
                    isStartNumber = true;
                    return;
                }
                result = number1 / number2;
                break;
            default:
                return;
        }

        txt_result.setText(formatNumber(result));
        operator = "";
        isStartNumber = true;
    }

    @FXML
    public void onNumberClick(ActionEvent event) {
        String number = ((Button) event.getSource()).getText();

        if (isStartNumber || txt_result.getText().equals("0") || txt_result.getText().startsWith("Error")) {
            txt_result.setText(number);
            isStartNumber = false;
        } else {
            txt_result.appendText(number);
        }
    }

    @FXML
    public void onOperatorClick(ActionEvent event) {
        String currentText = txt_result.getText();

        if (currentText.isEmpty() || currentText.startsWith("Error")) {
            return;
        }

        String buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equals("x²") || buttonText.equals("x^2") || buttonText.equals("x2") || buttonText.equals("kv") || buttonText.equals("Кв") || buttonText.equals("кв")) {
            onSquareClick(event);
            return;
        }

        if (buttonText.equals("√") || buttonText.equals("√x") || buttonText.equals("sqrt") || buttonText.equals("yzguur") || buttonText.equals("Язгуур") || buttonText.equals("язгуур")) {
            onSquareRootClick(event);
            return;
        }

        number1 = Double.parseDouble(currentText);
        operator = buttonText;
        isStartNumber = true;
    }

    @FXML
    public void onPlusMinusClick(ActionEvent event) {
        String currentText = txt_result.getText();

        if (currentText.isEmpty() || currentText.equals("0") || currentText.startsWith("Error")) {
            return;
        }

        if (currentText.startsWith("-")) {
            txt_result.setText(currentText.substring(1));
        } else {
            txt_result.setText("-" + currentText);
        }
    }

    @FXML
    public void onPointClick(ActionEvent event) {
        if (isStartNumber || txt_result.getText().startsWith("Error")) {
            txt_result.setText("0.");
            isStartNumber = false;
            return;
        }

        if (!txt_result.getText().contains(".")) {
            txt_result.appendText(".");
        }
    }

    @FXML
    public void onSquareClick(ActionEvent event) {
        String currentText = txt_result.getText();

        if (currentText.isEmpty() || currentText.startsWith("Error")) {
            return;
        }

        double number = Double.parseDouble(currentText);
        double result = number * number;
        txt_result.setText(formatNumber(result));
        isStartNumber = true;
    }

    @FXML
    public void onSquareRootClick(ActionEvent event) {
        String currentText = txt_result.getText();

        if (currentText.isEmpty() || currentText.startsWith("Error")) {
            return;
        }

        double number = Double.parseDouble(currentText);

        if (number < 0) {
            txt_result.setText("Error: Negative root");
            isStartNumber = true;
            return;
        }

        double result = Math.sqrt(number);
        txt_result.setText(formatNumber(result));
        isStartNumber = true;
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }
}
