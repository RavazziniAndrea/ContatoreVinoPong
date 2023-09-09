package com.ar.contatorevinopong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Contatore extends Application {

    static int num = 0;
    static final String FILE_ULTIMO_NUMERO = "ultimoNumero.txt";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Contatore.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        scene.setOnKeyPressed(event -> {
            keyPressed(event.getCode());
        });

        num = leggiNumDafile();
        ContatoreController.cambiaNumero(num);

        lanciaThreadSalvaFile();

        stage.setTitle("Numero Vinopong");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    private int leggiNumDafile() {
        int numLetto = 0;
        try{
            List<String> lines = Files.readAllLines(Paths.get(FILE_ULTIMO_NUMERO));
            if(!lines.isEmpty()){
                String linea = lines.get(lines.size()-1);
                numLetto = Integer.parseInt(linea);
                System.out.println("Letto da file: "+numLetto);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return numLetto;
    }

    public static void keyPressed(KeyCode keyCode){
        int code = keyCode.getCode();
        if(code == 38) { //UP
            num++;
            ContatoreController.cambiaNumero(num);
        } else if(code == 40) { //DOWN
            num--;
            ContatoreController.cambiaNumero(num);
        }
    }


    private void lanciaThreadSalvaFile() {
        Thread t = new Thread(()->{
            int numInThread = num ;

            File file = new File(FILE_ULTIMO_NUMERO);

            while(true) {
                try {
                    Thread.sleep(2000);

                    if (numInThread != num)
                    {
                        numInThread = num;

                        FileWriter fr = new FileWriter(file, false);
                        fr.write(String.valueOf(num));
                        fr.close();
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        },"ThreadSalvaFile");
        t.setDaemon(true);
        t.setPriority(Thread.NORM_PRIORITY);
        t.start();
    }



    public static void main(String[] args) {
        launch();
    }
}