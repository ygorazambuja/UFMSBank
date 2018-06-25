package br.ufms.controller.model;

import br.ufms.controller.views.JanelaContaBancaria;
import br.ufms.model.bean.ContaBancaria;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.security.krb5.internal.PAData;

import java.io.IOException;
import java.util.Objects;

public class StageController {

    public void chamarStage(String url, Event event) {
        try {
            Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource(url))));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chamarStageContaBancaria(ContaBancaria contaBancaria, Event event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/fxml/JanelaContaBancaria.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            JanelaContaBancaria janelaContaBancaria = loader.getController();
            janelaContaBancaria.setContaBancaria(contaBancaria);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
