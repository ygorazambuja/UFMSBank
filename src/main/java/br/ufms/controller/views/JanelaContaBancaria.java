package br.ufms.controller.views;

import br.ufms.controller.model.ContaBancariaController;
import br.ufms.model.bean.ContaBancaria;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaContaBancaria implements Initializable {

    private ContaBancaria contaBancaria;

    @FXML
    private Label limiteLabel, saldoLabel;

    @FXML
    private JFXButton saqueBtn, depositoBtn, transfereBtn;

    @FXML
    private JFXTextField correntistaText, nomeUsuarioText;

    @FXML
    private JFXTextField saqueTextField, depositoTextField;

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
        correntistaText.setText(contaBancaria.getCorrentista());
        saldoLabel.setText(String.valueOf(contaBancaria.getSaldo()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saqueBtn.addEventHandler(ActionEvent.ACTION, event -> {
            new ContaBancariaController().saque(contaBancaria, Double.valueOf(saqueTextField.getText()));
            saldoLabel.setText(String.valueOf(contaBancaria.getSaldo()));
        });
        depositoBtn.addEventHandler(ActionEvent.ACTION, event -> {
            new ContaBancariaController().deposita(contaBancaria, Double.valueOf(depositoTextField.getText()));
            saldoLabel.setText(String.valueOf(contaBancaria.getSaldo()));
        });
        transfereBtn.addEventHandler(ActionEvent.ACTION, event -> {

        });
    }
}
