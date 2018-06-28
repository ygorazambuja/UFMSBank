package br.ufms.controller.model;

import br.ufms.controller.views.JanelaAgenciaController;
import br.ufms.controller.views.JanelaContaBancaria;
import br.ufms.controller.views.JanelaCriarAgenciaController;
import br.ufms.controller.views.JanelaCriarContaController;
import br.ufms.model.bean.Banco;
import br.ufms.model.bean.ContaBancaria;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StageController {

    public void chamarStage(String url, Event event) {
        try {
            Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getClassLoader().getResource(url))));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chamarStageContaBancaria(ContaBancaria contaBancaria, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getResource("/view/fxml/JanelaContaBancaria.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            JanelaContaBancaria janelaContaBancaria = loader.getController();
            janelaContaBancaria.setContaBancaria(contaBancaria);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chamarStageAgencia(Banco banco, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getResource("/view/fxml/JanelaAgencia.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            JanelaAgenciaController janelaAgenciaController = loader.getController();
            janelaAgenciaController.setBanco(banco);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void chamarStageCriarAgencia(Integer id, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getResource("/view/fxml/JanelaCriarAgencias.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            JanelaCriarAgenciaController jca = loader.getController();
            jca.setParameters(id);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void criarStageCriarConta(Integer bancoId, Integer agenciaId, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getResource("/view/fxml/JanelaCriarConta.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            JanelaCriarContaController jcc = loader.getController();
            jcc.setParameter(bancoId, agenciaId);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
