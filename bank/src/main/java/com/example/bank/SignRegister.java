package com.example.bank;

import com.example.bank.model.Register;
import com.example.bank.model.RegisterList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class SignRegister implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private TextField amountTxt;

    @FXML
    private DatePicker dateDp;

    @FXML
    private TextArea descTa;

    @FXML
    private ChoiceBox<String> typeCb;

    private String[] type = {"Ingreso","Gasto"};

    @FXML
    void addRegister(ActionEvent event){
        try{
            if(amountTxt.getText().isBlank()||dateDp.getValue()==null||typeCb.getValue().isBlank()||descTa.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Se deben llenar todos los campos");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent()&&result.get()==ButtonType.OK){
                    return;
                }
            }
            if(Double.parseDouble(amountTxt.getText())<=0||!amountTxt.getText().matches("\\d+")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("El monto debe ser un número mayor a 0.");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent()&&result.get()==ButtonType.OK){
                    amountTxt.clear();
                }
            }else{
                Register record = new Register(
                        Double.parseDouble(amountTxt.getText()),
                        getTypeCb(),
                        descTa.getText()
                );

                record.convertStringToDate(getDate());
                RegisterList.getInstance().getData().add(record);
                Stage stage = (Stage) addBtn.getScene().getWindow();
                stage.close();
            }
        }catch (ParseException e){e.printStackTrace();}

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeCb.getItems().addAll(type);
    }

    public String getTypeCb(){
        return typeCb.getValue();
    }

    public String getDate(){
        LocalDate myDate = dateDp.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return  myFormattedDate;
    }
}
