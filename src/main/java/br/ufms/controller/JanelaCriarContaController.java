package br.ufms.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaCriarContaController implements Initializable {

    @FXML
    private JFXSlider limiteSlider;
    @FXML
    private JFXTextField correntistaTextField;
    @FXML
    private JFXTextField nomeUsuarioTextField;
    @FXML
    private JFXPasswordField senhaTextField;
    @FXML
    private JFXButton btnCriar;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label label;
    @FXML
    private ImageView errorHum, errorDois, errorTres;
    @FXML
    private JFXCheckBox correnteCheck;
    @FXML
    private JFXCheckBox poupancaCheck;
    @FXML
    private AnchorPane anchorContaCorrente, anchorContaPoupanca;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nomeUsuarioTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            nomeUsuarioTextField.setText(newValue.replaceAll("[^A-Za-z0-9]", "").toLowerCase());
            if (senhaTextField.getText().isEmpty()) {
                errorTres.setVisible(true);
            }
        });
        senhaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                errorTres.setVisible(false);
                btnCriar.setDisable(false);
            } else {
                errorTres.setVisible(true);
                btnCriar.setDisable(true);
            }
        });

        correnteCheck.focusedProperty().addListener(observable -> {
            poupancaCheck.setSelected(false);
            anchorContaCorrente.setDisable(false);
            anchorContaPoupanca.setDisable(true);
        });
        poupancaCheck.focusedProperty().addListener(observable -> {
            correnteCheck.setSelected(false);
            anchorContaPoupanca.setDisable(false);
            anchorContaCorrente.setDisable(true);
        });
    }
}
