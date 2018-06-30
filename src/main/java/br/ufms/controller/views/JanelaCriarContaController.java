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
import javafx.scene.control.Alert;
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
    private AnchorPane anchorContaCorrente, anchorContaPoupanca, anchorConta;
    @FXML
    private JFXComboBox<Character> comboBox;

    private Banco banco;
    private Agencia agencia;

    private Alert alert;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        anchorConta.setDisable(true);

        popularComboBox();


        nomeUsuarioTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            nomeUsuarioTextField.setText(newValue.replaceAll("[^A-Za-z0-9]", "").toLowerCase());
            errorHum.setVisible(false);
            if (newValue.isEmpty()) {
                errorHum.setVisible(true);
            }
        });
        correntistaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            errorDois.setVisible(false);
            if (newValue.isEmpty()) {
                errorDois.setVisible(true);
            }
        });
        senhaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            errorTres.setVisible(false);
            if (newValue.isEmpty()) {
                errorTres.setVisible(true);
            }
        });

        correnteCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                poupancaCheck.setSelected(false);
                anchorContaPoupanca.setDisable(true);
                anchorContaCorrente.setDisable(false);
            } else {
                anchorContaCorrente.setDisable(true);
            }

        });

        poupancaCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {
                correnteCheck.setSelected(false);
                anchorContaPoupanca.setDisable(false);
                anchorContaCorrente.setDisable(true);
            } else {
                anchorContaPoupanca.setDisable(true);
            }


        });
        btnCriarCorrente.addEventHandler(ActionEvent.ACTION, event -> {

            if (verificaCondicao()) {
                ContaCorrente contaCorrente = new ContaCorrente();
                contaCorrente.setLimite(limiteSlider.getValue());
                contaCorrente.setNomeUsuario(nomeUsuarioTextField.getText());
                contaCorrente.setCorrentista(correntistaTextField.getText());
                contaCorrente.setSenha(senhaTextField.getText());
                contaCorrente.setAgencia(this.agencia);
                new ContaBancariaDAO().salvar(contaCorrente, this.agencia.getId());
                label.setVisible(true);
            } else {
                alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Não foi Possivel");
                alert.setHeaderText("Ocorreu um erro!");
                alert.setContentText("Verifique se não deixou algum campo vazio ... ");
                alert.showAndWait();

            }

        });
        btnCriarPoupanca.addEventHandler(ActionEvent.ACTION, event -> {
            ContaPoupanca contaPoupanca = new ContaPoupanca();
            contaPoupanca.setCorrentista(correntistaTextField.getText());
            contaPoupanca.setNomeUsuario(nomeUsuarioTextField.getText());
            contaPoupanca.setSenha(senhaTextField.getText());
            contaPoupanca.setAgencia(this.agencia);
            new ContaBancariaDAO().salvar(contaPoupanca, this.agencia.getId());
            label.setVisible(true);
        });
        btnVoltar.addEventHandler(ActionEvent.ACTION, event -> {
            new StageController().chamarStage("view/fxml/TelaLogin.fxml", event);
        });
        btnBancoAgencia.addEventHandler(ActionEvent.ACTION, event -> {
            new StageController().chamarStage("view/fxml/JanelaBancoAgencia.fxml", event);
        });
    }

    private boolean verificaCondicao() {
        return !correntistaTextField.getText().isEmpty() && !senhaTextField.getText().isEmpty() && !nomeUsuarioTextField.getText().isEmpty();
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
        anchorConta.setDisable(false);
        errorHum.setVisible(true);
        errorDois.setVisible(true);
        errorTres.setVisible(true);
    }
}
