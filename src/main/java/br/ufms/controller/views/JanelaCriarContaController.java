package br.ufms.controller.views;

import br.ufms.controller.model.StageController;
import br.ufms.model.bean.Agencia;
import br.ufms.model.bean.Banco;
import br.ufms.model.bean.ContaCorrente;
import br.ufms.model.bean.ContaPoupanca;
import br.ufms.model.dao.AgenciaDAO;
import br.ufms.model.dao.BancoDAO;
import br.ufms.model.dao.ContaBancariaDAO;
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
    private JFXTextField bancoTextField, agenciaTextField;
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

    private Banco banco;
    private Agencia agencia;



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
            contaCorrente.setAgencia(this.agencia);
            new ContaBancariaDAO().salvar(contaCorrente, this.agencia.getId());
            label.setVisible(true);
        });
        btnCriarPoupanca.addEventHandler(ActionEvent.ACTION, event -> {
            ContaPoupanca contaPoupanca = new ContaPoupanca();
            contaPoupanca.setCorrentista(correntistaTextField.getText());
            contaPoupanca.setNomeUsuario(nomeUsuarioTextField.getText());
            contaPoupanca.setSenha(senhaTextField.getText());
            contaPoupanca.setAgencia(this.agencia);
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


    public void setParameter(Integer bancoId, Integer agenciaId) {
        this.banco = new BancoDAO().get(bancoId);
        this.agencia = new AgenciaDAO().getPorId(Agencia.class, agenciaId);
        bancoTextField.setText(this.banco.getNome());
        agenciaTextField.setText(String.valueOf(this.agencia.getId()));
    }
}
