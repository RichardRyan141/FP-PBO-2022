package com.example.fpcashier.controller;

import com.example.fpcashier.ItemTypes;
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
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ProductAddController implements Initializable {

    @FXML
    private TableView<ItemsModel> TableItem;

    @FXML
    private Button buttonAdd;

    @FXML
    private ComboBox<String> cboType;

    @FXML
    private TableColumn<ItemsModel, String> itemName;

    @FXML
    private TableColumn<ItemsModel, Double> itemPrice;

    @FXML
    private TableColumn<ItemsModel, Integer> itemQty;

    @FXML
    private Label labelExist;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableColumn<ItemsModel, Double> tableTotalPrice;

    @FXML
    private TextField textName;

    @FXML
    private TextField textPrice;

    ArrayList<Item> itemList = new ArrayList<Item>();
    Set<String> itemNameSet = new HashSet<String>();

    @FXML
    void buttonAddClick(ActionEvent event) {
        try {
            String name = textName.getText();
            double price = Double.parseDouble(textPrice.getText());
            String shortenedName = name.replaceAll("\\s", "");
            shortenedName = shortenedName.toLowerCase();
            if (! itemNameSet.contains(shortenedName))
            {
                Item i;
                String type = cboType.getValue();
                if (type.compareTo("Food") == 0)
                {
                    i = new Food(name,price);
                    itemList.add(i);
                }
                if (type.compareTo("Drink") == 0)
                {
                    i = new Drink(name,price);
                    itemList.add(i);
                }
                if (type.compareTo("Dessert") == 0)
                {
                    i = new Dessert(name,price);
                    itemList.add(i);
                }
                if (type.compareTo("Other") == 0)
                {
                    i = new Other(name,price);
                    itemList.add(i);
                }
                itemNameSet.add(shortenedName);
            }
            else
            {
                labelExist.setText(name + " already exists");
                labelExist.setVisible(true);
            }
            cboType.getSelectionModel().selectFirst();
            textName.setText("");
            textPrice.setText("");
            tableUpdate();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    void selectType(ActionEvent event) {
        String type = cboType.getValue();
        ObservableList<ItemsModel> data = FXCollections.observableArrayList();
        if (type.compareTo("Food") == 0)
        {
            for (Item i : itemList)
            {
                if (i instanceof Food) {
                    String name = i.getName();
                    int qty = i.getQty();
                    double price = i.getPrice();
                    data.add(new ItemsModel(name, qty, price, qty * price));
                }
            }
        }
        else
        {
            if (type.compareTo("Drink") == 0)
            {
                for (Item i : itemList)
                {
                    if (i instanceof Drink) {
                        String name = i.getName();
                        int qty = i.getQty();
                        double price = i.getPrice();
                        data.add(new ItemsModel(name, qty, price, qty * price));
                    }
                }
            }
            else
            {
                if (type.compareTo("Dessert") == 0)
                {
                    for (Item i : itemList)
                    {
                        if (i instanceof Dessert) {
                            String name = i.getName();
                            int qty = i.getQty();
                            double price = i.getPrice();
                            data.add(new ItemsModel(name, qty, price, qty * price));
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
                        }
                    }
                }
            }
        }
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
    void switchProductAdd(ActionEvent event) {

    }

    @FXML
    void switchProductDelete(ActionEvent event) throws Exception {
        File f = new File("src\\main\\java\\com\\example\\fpcashier\\fxml\\ProductDelete.fxml");
        URL url = new File(f.getAbsolutePath()).toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();
        ProductDeleteController controller = loader.getController();
        controller.setData(itemList);
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Delete Product");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        tableTotalPrice.setCellValueFactory(new PropertyValueFactory<>("tableTotalPrice"));
        for(ItemTypes ty: ItemTypes.values())
        {
            cboType.getItems().add(ty.toString());
        }
        cboType.getSelectionModel().selectFirst();
        labelExist.setVisible(false);
    }

    public void setData(ArrayList<Item> listOfProduct)
    {
        itemList.clear();
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
            String str = name.replaceAll("\\s", "");
            str = str.toLowerCase();
            itemNameSet.add(str);
        }
        tableUpdate();
    }

    public void tableUpdate()
    {
        ObservableList<ItemsModel> data = FXCollections.observableArrayList();
        for (Item i : itemList)
        {
            String name = i.getName().toString();
            int qty = i.getQty();
            double price = i.getPrice();
            data.add(new ItemsModel(name, qty, price, qty * price));
        }
        TableItem.setItems(data);
    }
}
