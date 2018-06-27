package br.ufms.controller.views;

import br.ufms.controller.model.ContaBancariaController;
import br.ufms.controller.model.StageController;
import br.ufms.model.bean.ContaCorrente;
import br.ufms.model.bean.ContaPoupanca;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private JFXButton btnCriarCorrente, btnCriarPoupanca, btnVoltar, btnBancoAgencia;
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
    @FXML
    private JFXComboBox<Character> comboBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popularComboBox();
        nomeUsuarioTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            nomeUsuarioTextField.setText(newValue.replaceAll("[^A-Za-z0-9]", "").toLowerCase());
            if (senhaTextField.getText().isEmpty()) {
                errorTres.setVisible(true);
            }
        });
        senhaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                errorTres.setVisible(false);
            } else {
                errorTres.setVisible(true);
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
        btnCriarCorrente.addEventHandler(ActionEvent.ACTION, event -> {
            ContaCorrente contaCorrente = new ContaCorrente();
            contaCorrente.setLimite(limiteSlider.getValue());
            contaCorrente.setNomeUsuario(nomeUsuarioTextField.getText());
            contaCorrente.setCorrentista(correntistaTextField.getText());
            contaCorrente.setSenha(senhaTextField.getText());
            new ContaBancariaController().criarContaCorrente(contaCorrente);
            label.setVisible(true);
        });
        btnCriarPoupanca.addEventHandler(ActionEvent.ACTION, event -> {
            ContaPoupanca contaPoupanca = new ContaPoupanca();
            contaPoupanca.setCorrentista(correntistaTextField.getText());
            contaPoupanca.setNomeUsuario(nomeUsuarioTextField.getText());
            contaPoupanca.setSenha(senhaTextField.getText());
            new ContaBancariaController().criarContaPoupanca(contaPoupanca);
            label.setVisible(true);
        });
        btnVoltar.addEventHandler(ActionEvent.ACTION, event -> {
            new StageController().chamarStage("view/fxml/TelaLogin.fxml", event);
        });
        btnBancoAgencia.addEventHandler(ActionEvent.ACTION, event -> {
            new StageController().chamarStage("view/fxml/JanelaBancoAgencia.fxml", event);
        });
    }

    public void popularComboBox() {
        ObservableList<Character> data = FXCollections.observableArrayList('A', 'B', 'C');
        comboBox.setItems(data);
    }

}
