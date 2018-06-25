package br.ufms.controller.views;

import br.ufms.model.bean.ContaBancaria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaContaBancaria implements Initializable {

    private ContaBancaria contaBancaria;

    @FXML
    private Label teste;

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
        teste.setText(contaBancaria.getCorrentista());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
