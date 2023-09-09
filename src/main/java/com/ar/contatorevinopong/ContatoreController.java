package com.ar.contatorevinopong;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ContatoreController implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lblNum;
    private static Label lblNumStatic;

    @FXML
    private Label lblTitolo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblNumStatic = lblNum;
    }

    public static void cambiaNumero(int num){
        lblNumStatic.setText(String.valueOf(num));
    }
}
