package br.ufms.controller;

import br.ufms.model.bean.Banco;
import br.ufms.model.dao.BancoDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class JanelaBancoController implements Initializable {

    @FXML
    private TableView<Banco> bancoTableView;

    @FXML
    private TableColumn<Object, Object> idColumn, nomeColumn;

    @FXML
    private JFXTextField nomeTextField;

    @FXML
    private JFXTextField codTextField;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton agenciaBtn;

    @FXML
    private JFXButton contasBtn;

    private ObservableList<Banco> data;

    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        popularTableView();


        addBtn.addEventHandler(ActionEvent.ACTION, event -> {
            new Thread(() -> {
                new BancoController().adicionar(nomeTextField.getText());
            }).start();
        });
        deleteBtn.addEventHandler(ActionEvent.ACTION, event -> {
            new Thread(() -> {
                new BancoController().remover(nomeTextField.getText(), codTextField.getText());
            }).start();
        });

        agenciaBtn.addEventHandler(ActionEvent.ACTION, event -> {
            chamarStage("view/fxml/JanelaAgencia.fxml", event);
        });

        contasBtn.addEventHandler(ActionEvent.ACTION, event -> {
            chamarStage("view/fxml/JanelaContaBancaria.fxml", event);
        });

        bancoTableView.setOnMouseClicked(event -> {
            Banco banco = bancoTableView.getItems().get(bancoTableView.getSelectionModel().getSelectedIndex());
            codTextField.setText(String.valueOf(banco.getId()));
            nomeTextField.setText(banco.getNome());
        });
    }

    private void popularTableView() {
        List<Banco> bancos = new BancoDAO().getAll();
        data = FXCollections.observableArrayList(bancos);
        bancoTableView.setItems(data);
    }


    private void chamarStage(String url, Event event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(url)));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
