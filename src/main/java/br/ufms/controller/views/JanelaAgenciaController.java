package br.ufms.controller.views;

import br.ufms.controller.model.StageController;
import br.ufms.model.bean.Banco;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaAgenciaController implements Initializable {

    private Banco banco;

    @FXML
    JFXTextField nomeBancoText;
    @FXML
    JFXTextField codigoTextField;

    @FXML
    JFXButton btnVoltar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnVoltar.addEventHandler(ActionEvent.ACTION, event -> {
            System.out.println("chupa pitoca");
            new StageController().chamarStage("/view/fxml/JanelaBanco.fxml", event);
        });
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
        codigoTextField.setText(String.valueOf(banco.getId()));
    }

    public Banco getBanco() {
        return banco;
    }
}
