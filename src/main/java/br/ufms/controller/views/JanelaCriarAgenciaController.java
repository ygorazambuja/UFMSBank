package br.ufms.controller.views;

import br.ufms.controller.model.StageController;
import br.ufms.model.bean.Agencia;
import br.ufms.model.bean.Banco;
import br.ufms.model.dao.AgenciaDAO;
import br.ufms.model.dao.BancoDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

public class JanelaCriarAgenciaController implements Initializable {


    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXTextField bancoNome;

    @FXML
    private JFXTextField bancoCodigo;

    @FXML
    private JFXButton btnCriar;

    @FXML
    private TableView<Agencia> tableAgencia;

    @FXML
    private TableColumn<Object, Object> idColumn;

    @FXML
    private Label sucessoLabel;


    private Banco banco;
    private ObservableList<Agencia> agencias;
    private Agencia agencia;

    public void setParameters(Integer id) {
        this.banco = new BancoDAO().get(id);
        bancoNome.setText(banco.getNome());
        bancoCodigo.setText(String.valueOf(banco.getId()));
        popularTableAgencia(banco);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        btnCriar.addEventHandler(ActionEvent.ACTION, event -> {
            new AgenciaDAO().salvar(new Agencia(), Integer.valueOf(bancoCodigo.getText()));
            popularTableAgencia(banco);
        });

        btnVoltar.addEventHandler(ActionEvent.ACTION, event -> {
            new StageController().chamarStage("view/fxml/JanelaBanco.fxml", event);
        });
    }

    private void popularTableAgencia(Banco banco) {
        List<Agencia> agenciaz = new AgenciaDAO().getAll(banco);
        agencias = FXCollections.observableArrayList(agenciaz);
        tableAgencia.setItems(agencias);
    }
}
