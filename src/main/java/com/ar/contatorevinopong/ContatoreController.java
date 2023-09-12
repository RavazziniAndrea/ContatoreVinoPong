package com.ar.contatorevinopong;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

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
        anchorPane.setStyle("-fx-background-color: #000000");
        avviaCambiaColore();
    }

    private void avviaCambiaColore() {
        Thread t = new Thread(()->{
            Colore colore = new Colore();
            while(true) {
                Platform.runLater(() -> {
                    colore.cambiaColore();
                    lblNum.setTextFill(Color.rgb(colore.getR(), colore.getG(), colore.getB()));
                    lblTitolo.setTextFill(Color.rgb(colore.getR(), colore.getG(), colore.getB()));
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"Cambia colore");
        t.setDaemon(true);
        t.setPriority(Thread.NORM_PRIORITY);
        t.start();
    }

    public static void cambiaNumero(int num){
        lblNumStatic.setText(String.valueOf(num));
    }
}
