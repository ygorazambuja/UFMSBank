package br.ufms.controller.views;

import br.ufms.controller.model.StageController;
import br.ufms.model.bean.ContaBancaria;
import br.ufms.model.dao.ContaBancariaDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaLoginController implements Initializable {

    @FXML
    private JFXTextField loginTextField;

    @FXML
    private JFXPasswordField senhaTextField;

    @FXML
    private JFXButton btnConfirma;

    @FXML
    private JFXButton btnCriarConta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginTextField.setText(newValue.replaceAll("[^A-Za-z0-9]", "").toLowerCase());

        });
        senhaTextField.textProperty().addListener(observable -> {
            btnConfirma.setVisible(true);
            btnConfirma.setDisable(false);
        });
        btnConfirma.addEventHandler(ActionEvent.ACTION, event -> {
            try {
                ContaBancaria contaBancaria = new ContaBancariaDAO().getPorNome(loginTextField.getText());
                if (contaBancaria != null) {
                    if (senhaTextField.getText().equals(contaBancaria.getSenha())) {
                        new StageController().chamarStageContaBancaria(contaBancaria, event);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.showAndWait();
                }
            } catch (NullPointerException np) {
                System.out.println("deeeeeeeeeeeeeeu pau");
                np.printStackTrace();
            }
        });
        btnCriarConta.addEventHandler(ActionEvent.ACTION, event -> {
            new StageController().chamarStage("view/fxml/JanelaCriarConta.fxml", event);
        });
    }
}
