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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CashierController implements Initializable {
    @FXML
    private Button Number0;

    @FXML
    private Button Number1;

    @FXML
    private Button Number2;

    @FXML
    private Button Number3;

    @FXML
    private Button Number4;

    @FXML
    private Button Number5;

    @FXML
    private Button Number6;

    @FXML
    private Button Number7;

    @FXML
    private Button Number8;

    @FXML
    private Button Number9;

    @FXML
    private Button NumberDelete;

    @FXML
    private Button NumberDot;

    @FXML
    private TableView<ItemsModel> TableItem;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonPay;

    @FXML
    private Button buttonReset;

    @FXML
    private ComboBox<String> cboProduct;

    @FXML
    private TableColumn<ItemsModel, String> itemName;

    @FXML
    private TableColumn<ItemsModel, Double> itemPrice;

    @FXML
    private TableColumn<ItemsModel, Integer> itemQty;

    @FXML
    private Label labelChange;

    @FXML
    private Label labelHutang;

    @FXML
    private Label labelPrice;

    @FXML
    private Label labelSubTotal;

    @FXML
    private Label labelTax;

    @FXML
    private Label labelTotal;

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
    private Spinner<Integer> spQty;

    @FXML
    private TableColumn<ItemsModel, Double> tableTotalPrice;

    @FXML
    private TextField textTotalPayment;

    ArrayList<Item> itemList = new ArrayList<Item>();

    @FXML
    void btnPayClick(ActionEvent event) {
        try
        {
            double payment;
            getTotal();
            textTotalPayment.setText(textTotalPayment.getText().replaceAll("\\s", ""));
            double total = Double.parseDouble(labelTotal.getText());
            if (textTotalPayment.getText() == "")
            {
                payment = 0.0;
            }
            else {
                payment = Double.parseDouble(textTotalPayment.getText());
            }
            double change = payment-total;
            labelChange.setText(new DecimalFormat("0.00").format(change));
            if (change < 0)
            {
                labelHutang.setVisible(true);
            }
            else
            {
                labelHutang.setVisible(false);
            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Total Payment contains non numeric character");
            System.exit(-1);
        }
    }

    @FXML
    void btnResetClick(ActionEvent event) {
        for(Item i  : itemList)
        {
            i.updateData(0);
        }
        rdoAll.fire();
        tableUpdate();
        textTotalPayment.setText("");
        getTotal();
        labelHutang.setVisible(false);
        labelChange.setText("0.00");
    }

    @FXML
    void btnAddClick(ActionEvent event) {
        for (Item i : itemList)
        {
            String name = i.getName();
            String picked = cboProduct.getValue();
            int qty = spQty.getValue();
            if (name.compareTo(picked) == 0)
            {
                i.addQty(qty);
            }
        }
        tableUpdate();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
        valueFactory.setValue(1);
        spQty.setValueFactory(valueFactory);
        getTotal();
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
                if (qty != 0) {
                    data.add(new ItemsModel(name, qty, price, qty * price));
                }
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
                        if (qty != 0) {
                            data.add(new ItemsModel(name, qty, price, qty * price));
                        }
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
                            if (qty != 0) {
                                data.add(new ItemsModel(name, qty, price, qty * price));
                            }
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
                                if (qty != 0) {
                                    data.add(new ItemsModel(name, qty, price, qty * price));
                                }
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
                                if (qty != 0) {
                                    data.add(new ItemsModel(name, qty, price, qty * price));
                                }
                                cboProduct.getItems().add(name);
                            }
                        }
                    }
                }
            }
        }
        TableItem.setItems(data);
        if (cboProduct.getItems().size() != 0) {
            cboProduct.getSelectionModel().selectFirst();
            String first = cboProduct.getValue();
            for (Item i : itemList) {
                if (i.getName().compareTo(first) == 0) {
                    labelPrice.setText("Price : $ " + i.getPrice());
                }
            }
        }
    }

    @FXML
    void numberClick(ActionEvent event) {
        try {
            textTotalPayment.setText(textTotalPayment.getText().replaceAll("\\s", ""));
            String num = textTotalPayment.getText();
            if (event.getSource() == NumberDot) {
                if (!num.contains(".")) {
                    if (num == "") {
                        textTotalPayment.setText("0.");
                    } else {
                        textTotalPayment.setText(num + ".");
                    }
                }
            } else {
                if (event.getSource() == NumberDelete) {
                    textTotalPayment.setText("");
                } else {
                    if (num == "") {
                        textTotalPayment.setText(((Button) event.getSource()).getText());
                    } else {
                        num = textTotalPayment.getText() + ((Button) event.getSource()).getText();
                        textTotalPayment.setText(num);
                    }
                }
            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Total Payment contains non numeric character");
            System.exit(-1);
        }
    }

    @FXML
    void selectProduct(ActionEvent event) {
        String picked = cboProduct.getValue();
        if (picked != null) {
            for (Item i : itemList) {
                if (i.getName().compareTo(picked) == 0) {
                    labelPrice.setText("Price : $ " + i.getPrice());
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemList.add(new Food("Hotdog",2));
        itemList.add(new Drink("Iced Tea",4));
        itemList.add(new Drink("Coffee",6));
        itemList.add(new Food("Cupcake",7.50));
        itemList.add(new Dessert("Milkshake",8.50));
        itemList.add(new Dessert("Ice cream", 2.50));
        itemList.add(new Other("Ice cube", 0.25));

        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        tableTotalPrice.setCellValueFactory(new PropertyValueFactory<>("tableTotalPrice"));

        getTotal();

        labelHutang.setVisible(false);
        labelChange.setText("0.00");

        rdoAll.fire();

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
        valueFactory.setValue(1);
        spQty.setValueFactory(valueFactory);
    }

    public double getSubTotal()
    {
        double subTotal=0;
        for (Item i : itemList)
        {
            subTotal = subTotal+ i.getPrice() * (double)i.getQty();
        }
        return subTotal;
    }

    public double getTax()
    {
        double subTotal = getSubTotal();
        double tax = subTotal*0.15;
        String str = Double.toString(tax);
        return tax;
    }

    public void getTotal()
    {
        double subTotal = getSubTotal();
        double tax = getTax();
        double total = subTotal+tax;
        String str = Double.toString(total);
        labelSubTotal.setText(new DecimalFormat("0.00").format(subTotal));
        labelTax.setText(new DecimalFormat("0.00").format(tax));
        labelTotal.setText(new DecimalFormat("0.00").format(total));
    }

    public void tableUpdate()
    {
        ObservableList<ItemsModel> data = FXCollections.observableArrayList();
        for (Item i : itemList)
        {
            String name = i.getName();
            int qty = i.getQty();
            double price = i.getPrice();
            if (qty != 0) {
                data.add(new ItemsModel(name, qty, price, qty * price));
            }
        }
        TableItem.setItems(data);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void switchCashier(ActionEvent event) {

    }

    @FXML
    private void switchProductAdd(ActionEvent event) throws Exception {
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
    void switchProductDelete(ActionEvent event) throws Exception {
        File f = new File("src\\main\\java\\com\\example\\fpcashier\\fxml\\ProductDelete.fxml");
        URL url = new File(f.getAbsolutePath().toString()).toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        root = loader.load();
        ProductDeleteController controller = loader.getController();
        controller.setData(itemList);
        stage = (Stage) menuBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Product");
        stage.show();
    }

    public void setData(ArrayList<Item> listOfProduct) {
        itemList.clear();
        cboProduct.getItems().removeAll(cboProduct.getItems());
        for (Item i : listOfProduct) {
            String name = i.getName();
            double price = i.getPrice();
            if (i instanceof Food) {
                itemList.add(new Food(name, price));
            }
            if (i instanceof Drink) {
                itemList.add(new Drink(name, price));
            }
            if (i instanceof Dessert) {
                itemList.add(new Dessert(name, price));
            }
            if (i instanceof Other) {
                itemList.add(new Other(name, price));
            }
            cboProduct.getItems().add(name);
        }
        tableUpdate();
        cboProduct.getSelectionModel().selectFirst();
        String first = cboProduct.getValue();
        for (Item i : itemList)
        {
            if (i.getName().compareTo(first) == 0)
            {
                labelPrice.setText("Price : $ " + i.getPrice());
            }
        }
    }
}
