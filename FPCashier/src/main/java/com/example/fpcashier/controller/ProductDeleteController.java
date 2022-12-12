package com.example.fpcashier.controller;

import com.example.fpcashier.ItemsModel;
import com.example.fpcashier.product.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductDeleteController implements Initializable {
    @FXML
    private ToggleGroup ProductType;

    @FXML
    private TableView<ItemsModel> TableItem;

    @FXML
    private Button buttonDelete;

    @FXML
    private ComboBox<String> cboProduct;

    @FXML
    private TableColumn<ItemsModel, String> itemName;

    @FXML
    private TableColumn<ItemsModel, Double> itemPrice;

    @FXML
    private TableColumn<ItemsModel, Integer> itemQty;

    @FXML
    private MenuBar menuBar;

    @FXML
    private RadioButton rdoAll;

    @FXML
    private RadioButton rdoDessert;

    @FXML
    private RadioButton rdoDrink;

    @FXML
    private RadioButton rdoFood;

    @FXML
    private RadioButton rdoOther;

    @FXML
    private TableColumn<ItemsModel, Double> tableTotalPrice;

    ArrayList<Item> itemList = new ArrayList<Item>();

    @FXML
    void buttonDeleteClick(ActionEvent event) {
        Item remove = null;
        for (Item i : itemList)
        {
            String name = i.getName();
            String picked = cboProduct.getValue();
            if (name.compareTo(picked) == 0)
            {
                remove = i;
            }
        }
        itemList.remove(remove);
        tableUpdate();
    }

    @FXML
    void changeView(ActionEvent event) {
        cboProduct.getItems().removeAll(cboProduct.getItems());
        ObservableList<ItemsModel> data = FXCollections.observableArrayList();
        if (rdoAll.isSelected())
        {
            for (Item i : itemList)
            {
                String name = i.getName();
                int qty = i.getQty();
                double price = i.getPrice();
                data.add(new ItemsModel(name, qty, price, qty * price));
                cboProduct.getItems().add(name);
            }
        }
        else
        {
            if (rdoFood.isSelected())
            {
                for (Item i : itemList)
                {
                    if (i instanceof Food) {
                        String name = i.getName();
                        int qty = i.getQty();
                        double price = i.getPrice();
                        data.add(new ItemsModel(name, qty, price, qty * price));
                        cboProduct.getItems().add(name);
                    }
                }
            }
            else
            {
                if (rdoDrink.isSelected())
                {
                    for (Item i : itemList)
                    {
                        if (i instanceof Drink) {
                            String name = i.getName();
                            int qty = i.getQty();
                            double price = i.getPrice();
                            data.add(new ItemsModel(name, qty, price, qty * price));
                            cboProduct.getItems().add(name);
                        }
                    }
                }
                else
                {
                    if (rdoDessert.isSelected())
                    {
                        for (Item i : itemList)
                        {
                            if (i instanceof Dessert) {
                                String name = i.getName();
                                int qty = i.getQty();
                                double price = i.getPrice();
                                data.add(new ItemsModel(name, qty, price, qty * price));
                                cboProduct.getItems().add(name);
                            }
                        }
                    }
                    else
                    {
                        for (Item i : itemList)
                        {
                            if (i instanceof Other) {
                                String name = i.getName();
                                int qty = i.getQty();
                                double price = i.getPrice();
                                data.add(new ItemsModel(name, qty, price, qty * price));
                                cboProduct.getItems().add(name);
                            }
                        }
                    }
                }
            }
        }
        cboProduct.getSelectionModel().selectFirst();
        TableItem.setItems(data);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void switchCashier(ActionEvent event) throws Exception {
        File f = new File("src\\main\\java\\com\\example\\fpcashier\\fxml\\CashierUI.fxml");
        URL url = new File(f.getAbsolutePath().toString()).toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();
        CashierController controller = loader.getController();
        controller.setData(itemList);
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cashier");
        stage.show();
    }

    @FXML
    void switchProductAdd(ActionEvent event) throws Exception {
        File f = new File("src\\main\\java\\com\\example\\fpcashier\\fxml\\ProductAdd.fxml");
        URL url = new File(f.getAbsolutePath().toString()).toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();
        ProductAddController controller = loader.getController();
        controller.setData(itemList);
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Product");
        stage.show();
    }

    @FXML
    void switchProductDelete(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        tableTotalPrice.setCellValueFactory(new PropertyValueFactory<>("tableTotalPrice"));

        rdoAll.fire();
    }

    public void setData(ArrayList<Item> listOfProduct)
    {
        itemList.clear();
        cboProduct.getItems().removeAll(cboProduct.getItems());
        for (Item i : listOfProduct)
        {
            String name = i.getName();
            double price = i.getPrice();
            if (i instanceof Food)
            {
                itemList.add(new Food(name,price));
            }
            if (i instanceof Drink)
            {
                itemList.add(new Drink(name,price));
            }
            if (i instanceof Dessert)
            {
                itemList.add(new Dessert(name,price));
            }
            if (i instanceof Other)
            {
                itemList.add(new Other(name,price));
            }
            cboProduct.getItems().add(name);
        }
        tableUpdate();
        cboProduct.getSelectionModel().selectFirst();
    }

    public void tableUpdate()
    {
        ObservableList<ItemsModel> data = FXCollections.observableArrayList();
        for (Item i : itemList)
        {
            String name = i.getName();
            int qty = i.getQty();
            double price = i.getPrice();
            data.add(new ItemsModel(name, qty, price, qty * price));
        }
        TableItem.setItems(data);
    }
}
