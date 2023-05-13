package com.example.bank;

import com.example.bank.model.Register;
import com.example.bank.model.RegisterList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class HelloController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<Register, Double> amountTc;

    @FXML
    private TextField balanceTxt;

    @FXML
    private TableColumn<Register, Date> dateTc;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Register, String> descTc;

    @FXML
    private TextField earnTxt;

    @FXML
    private TableView<Register> moneyT;

    @FXML
    private AnchorPane root;

    @FXML
    private ChoiceBox<String> filterCb;

    @FXML
    private TextField spentTxt;

    @FXML
    private TableColumn<Register, String> typeTc;

    @FXML
    void addRegister(ActionEvent event) {

    }

    @FXML
    void deleteRegister(ActionEvent event) {
        Register element = moneyT.getSelectionModel().getSelectedItem();
        if(element!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar un registro");
            alert.setContentText("¿Estás seguro de eliminar el registro?.");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()&&result.get()==ButtonType.OK){
                RegisterList.getInstance().getData().remove(element);
                moneyT.setItems(
                        RegisterList.getInstance().getData()
                );
            }
        }
    }


    private  String[] filters = {"Ingreso", "Gasto", "Ambas"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateTc.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountTc.setCellValueFactory(new PropertyValueFactory<>("amount"));
        typeTc.setCellValueFactory(new PropertyValueFactory<>("type"));
        descTc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        earnTxt.getText();
        spentTxt.getText();
        balanceTxt.getText();
        calculate();
        filterCb.getItems().addAll(filters);
        filterCb.setValue(filters[2]);
        moneyT.setItems(
                RegisterList.getInstance().getData()
        );
        filterCb.setOnAction(e -> getFilter());

        addBtn.setOnAction(actionEvent -> {
            HelloApplication.openWindow("sign-register.fxml");
        });

    }

    private void calculate(){
        double resultEarn = 0.0;
        double resultSpent=0.0;
        double resultBalance=0.0;
        for (Register r : RegisterList.getInstance().getData()) {
            if(r.getType().equals("Ingreso")){
                resultEarn += r.getAmount();
            }
            if(r.getType().equals("Gasto")){
                resultSpent += r.getAmount();
            }
        }
        resultBalance=resultEarn-resultSpent;
        earnTxt.setText(Double.toString(resultEarn));
        spentTxt.setText(Double.toString(resultSpent));
        balanceTxt.setText(Double.toString(resultBalance));
    }

    public void getFilter(){

        switch (filterCb.getValue()){
            case "Ingreso":
                Predicate<Register> filtro = r -> r.getType().equals("Ingreso");
                moneyT.setItems(
                        RegisterList.getInstance().getData().filtered(filtro)
                );
                break;
            case "Gasto":
                Predicate<Register> filtro2 = r -> r.getType().equals("Gasto");
                moneyT.setItems(
                        RegisterList.getInstance().getData().filtered(filtro2)
                );
                break;
            case "Ambas":
                moneyT.setItems(
                        RegisterList.getInstance().getData()
                );
                break;
        }
        calculate();
    }
}
