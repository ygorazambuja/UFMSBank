package br.ufms.controller.views;

import br.ufms.controller.model.StageController;
import br.ufms.model.bean.Agencia;
import br.ufms.model.bean.Banco;
import br.ufms.model.dao.AgenciaDAO;
import br.ufms.model.dao.BancoDAO;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class JanelaBancoAgenciaController implements Initializable {

    @FXML
    private TableView<Agencia> agenciaTable;

    @FXML
    private TableView<Banco> bancoTable;

    @FXML
    private TableColumn<Object, Object> bancoIdColumn, bancoNomeColumn;

    @FXML
    private TableColumn<?, ?> agenciaIdColumn;

    @FXML
    private Label bancoIdLabel;

    @FXML
    private Label bancoNomeLabel;

    @FXML
    private Label agenciaIdLabel;

    @FXML
    private JFXButton btnCriar, btnVoltar;

    private ObservableList<Banco> bancos;
    private ObservableList<Agencia> agencias;

    private Banco banco;
    private Agencia agencia;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bancoIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bancoNomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        agenciaIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        popularTableBanco();

        bancoTable.setOnMouseClicked(event -> {
            this.banco = bancoTable.getItems().get(bancoTable.getSelectionModel().getSelectedIndex());
            agenciaIdLabel.setText(null);
            popularTableAgencia(banco);
            bancoIdLabel.setText(String.valueOf(banco.getId()));
            bancoNomeLabel.setText(banco.getNome());
        });
        agenciaTable.setOnMouseClicked(event -> {
            this.agencia = agenciaTable.getItems().get(agenciaTable.getSelectionModel().getSelectedIndex());
            agenciaIdLabel.setText(String.valueOf(agencia.getId()));
            btnCriar.setDisable(false);
        });


        btnCriar.addEventHandler(ActionEvent.ACTION, event -> {
            new StageController().criarStageCriarConta(Integer.parseInt(bancoIdLabel.getText()),
                    Integer.parseInt(agenciaIdLabel.getText()), event);
        });

        btnVoltar.addEventHandler(ActionEvent.ACTION, event -> new StageController().chamarStage("view/fxml/JanelaCriarConta.fxml", event));
    }

    private void popularTableBanco() {
        List<Banco> bancoz = new BancoDAO().getAll();
        bancos = FXCollections.observableArrayList(bancoz);
        bancoTable.setItems(bancos);
    }

    private void popularTableAgencia(Banco banco) {
        List<Agencia> agenciaz = new AgenciaDAO().getAll(banco);
        agencias = FXCollections.observableArrayList(agenciaz);
        agenciaTable.setItems(agencias);
    }

}
