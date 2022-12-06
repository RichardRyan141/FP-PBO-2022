package com.example.fpcashier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.DefaultTableModel;

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
    private Button buttonPay;

    @FXML
    private Button buttonReset;

    @FXML
    private Button item1;

    @FXML
    private Button item2;

    @FXML
    private Button item3;

    @FXML
    private Button item4;

    @FXML
    private Button item5;

    @FXML
    private Button item6;

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
    private Label labelSubTotal;

    @FXML
    private Label labelTax;

    @FXML
    private Label labelTotal;

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
        tableUpdate();
        textTotalPayment.setText("");
        getTotal();
        labelHutang.setVisible(false);
        labelChange.setText("0.00");
    }

    int selectedItem=-1;

    @FXML
    void itemClick(ActionEvent event) {
        if (event.getSource() == item1)
        {
            selectedItem=0;
        }
        if (event.getSource() == item2)
        {
            selectedItem=1;
        }
        if (event.getSource() == item3)
        {
            selectedItem=2;
        }
        if (event.getSource() == item4)
        {
            selectedItem=3;
        }
        if (event.getSource() == item5)
        {
            selectedItem=4;
        }
        if (event.getSource() == item6)
        {
            selectedItem=5;
        }
        if (selectedItem != -1)
        {
            itemList.get(selectedItem).addQty();
        }
        tableUpdate();
        getTotal();
    }

    @FXML
    void numberClick(ActionEvent event) {
        String num = textTotalPayment.getText();
        if (event.getSource() == NumberDot)
        {
            String str = textTotalPayment.getText();
            if (!str.contains("."))
            {
                if (str == "")
                {
                    textTotalPayment.setText("0.");
                }
                else {
                    textTotalPayment.setText(str + ".");
                }
            }
        }
        else {
            if (event.getSource() == NumberDelete) {
                textTotalPayment.setText("");
            }
            else {
                if (num == "") {
                    textTotalPayment.setText(((Button) event.getSource()).getText());
                } else {
                    num = textTotalPayment.getText() + ((Button) event.getSource()).getText();
                    textTotalPayment.setText(num);
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemList.add(new Food(ItemNames.Hotdog,2));
        itemList.add(new Drink(ItemNames.IcedTea,4));
        itemList.add(new Drink(ItemNames.Coffee,6));
        itemList.add(new Food(ItemNames.Cupcake,7.50));
        itemList.add(new Drink(ItemNames.MilkShake,8.50));
        itemList.add(new Drink(ItemNames.Soup,9));


        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        tableTotalPrice.setCellValueFactory(new PropertyValueFactory<>("tableTotalPrice"));

        tableUpdate();
        getTotal();

        labelHutang.setVisible(false);

        labelChange.setText("0.00");

        item1.setText("Hotdog\n $2");
        item2.setText("Iced Tea\n $4");
        item3.setText("Coffee\n $6");
        item4.setText("Cupcake\n $7.50");
        item5.setText("Milkshake\n $8.50");;
        item6.setText("Soup\n $9");
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
            String name = i.getName().toString();
            int qty = i.getQty();
            double price = i.getPrice();
            if (qty != 0) {
                data.add(new ItemsModel(name, qty, price, qty * price));
            }
        }
        TableItem.setItems(data);
    }
}
