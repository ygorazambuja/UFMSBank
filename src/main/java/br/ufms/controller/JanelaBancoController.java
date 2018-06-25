package br.ufms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class JanelaBancoController implements Initializable {

    @FXML
    private JFXTextField nomeTextField;

    @FXML
    private JFXTextField codTextField;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    JFXButton agenciaBtn;

    @FXML
    JFXButton contasBtn;

    @FXML
    private JFXListView<?> bancosListView;

    public void initialize(URL location, ResourceBundle resources) {
        addBtn.addEventHandler(ActionEvent.ACTION, event -> {
            new Thread(() -> {
                new BancoController().adicionar(nomeTextField.getText());
            }).start();
        });
        deleteBtn.addEventHandler(ActionEvent.ACTION, event -> {
            new Thread(() -> {
                new BancoController().remover(nomeTextField.getText(), codTextField.getText());
            }).start();
        });

        agenciaBtn.addEventHandler(ActionEvent.ACTION, event -> {
            chamarStage("fxml/JanelaAgencia.fxml", event);
        });

        contasBtn.addEventHandler(ActionEvent.ACTION, event -> {
            chamarStage("fxml/JanelaContaBancaria.fxml", event);
        });
    }

    private void chamarStage(String url, Event event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(url)));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
