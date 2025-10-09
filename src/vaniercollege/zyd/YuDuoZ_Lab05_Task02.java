package vaniercollege.zyd;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * @author Yu Duo Zhang (2480549)
 * https://github.com/zyd-074/Vanier_F25_420-SF3-RE_Lab05.git
 */
public class YuDuoZ_Lab05_Task02 extends Application {
    double subtotalAmount;
    double tipsAmount;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        
        HBox selectControl = new HBox();
        Label categorySelect = new Label("Category: ");
        ComboBox category = new ComboBox();
        category.getItems().addAll("Beverage", "Appetizer", "Main Course", "Dessert");
        category.getSelectionModel().select(0);
        selectControl.getChildren().addAll(categorySelect, category);
        
        ArrayList<String> beverageList = new ArrayList<>();
        ArrayList<String> appetizerList = new ArrayList<>();
        ArrayList<String> mainList = new ArrayList<>();
        ArrayList<String> dessertList = new ArrayList<>();
        // Add Menu
        {beverageList.add("Coffee | 2.50");
        beverageList.add("Tea | 2.00");
        beverageList.add("Soft Drink | 1.75");
        beverageList.add("Water | 2.95");
        beverageList.add("Milk | 1.50");
        beverageList.add("Juice | 2.50");
        appetizerList.add("Soup | 4.50");
        appetizerList.add("Salad | 3.75");
        appetizerList.add("Spring Rolls | 5.25");
        appetizerList.add("Garlic Bread | 3.00");
        appetizerList.add("Chips and Salsa | 6.95");
        mainList.add("Steak | 15.00");
        mainList.add("Grilled Chicken | 13.50");
        mainList.add("Chicken Alfredo | 13.95");
        mainList.add("Turkey Club | 11.90");
        mainList.add("Shrimp Scampi | 18.99");
        mainList.add("Pasta | 11.75");
        mainList.add("Fish and Chips | 12.25");
        dessertList.add("Apple Pie | 5.95");
        dessertList.add("Carrot Cake | 4.50");
        dessertList.add("Mud Pie | 4.75");
        dessertList.add("Pudding | 3.25");
        dessertList.add("Apple Crisp | 5.98");} 
        double[] beveragePrice = {2.50, 2.00, 1.75, 2.95, 1.50, 2.50};
        double[] appetizerPrice = {4.50, 3.75, 5.25, 3.00, 6.95};
        double[] mainPrice = {15.00, 13.50, 13.95, 11.90, 18.99, 11.75, 12.25};
        double[] dessertPrice = {5.95, 4.50, 4.75, 3.25, 5.98};
        ObservableList<String> beverage = FXCollections.observableArrayList(beverageList);
        ObservableList<String> appetizer = FXCollections.observableArrayList(appetizerList);
        ObservableList<String> mainCourse = FXCollections.observableArrayList(mainList);
        ObservableList<String> dessert = FXCollections.observableArrayList(dessertList);
        ListView<String> beverageMenu = new ListView<>(beverage);
        beverageMenu.setPrefSize(100, 100);
        ListView<String> appetizerMenu = new ListView<>(appetizer);
        appetizerMenu.setPrefSize(100, 100);
        ListView<String> mainMenu = new ListView<>(mainCourse);
        mainMenu.setPrefSize(100, 100);
        ListView<String> dessertMenu = new ListView<>(dessert);
        dessertMenu.setPrefSize(100, 100);

        root.setLeft(selectControl);
        category.setOnAction(e -> {
            switch (category.getValue().toString()) {
                case "Beverage" -> {
                    appetizerMenu.getSelectionModel().select(-1);
                    mainMenu.getSelectionModel().select(-1);
                    dessertMenu.getSelectionModel().select(-1);
                    root.setCenter(beverageMenu);
                }
                case "Appetizer" -> {
                    beverageMenu.getSelectionModel().select(-1);
                    mainMenu.getSelectionModel().select(-1);
                    dessertMenu.getSelectionModel().select(-1);
                    root.setCenter(appetizerMenu);
                }
                case "Main Course" -> {
                    beverageMenu.getSelectionModel().select(-1);
                    appetizerMenu.getSelectionModel().select(-1);
                    dessertMenu.getSelectionModel().select(-1);
                    root.setCenter(mainMenu);
                }
                case "Dessert" -> {
                    beverageMenu.getSelectionModel().select(-1);
                    mainMenu.getSelectionModel().select(-1);
                    appetizerMenu.getSelectionModel().select(-1);
                    root.setCenter(dessertMenu);
                }
            }
        });
        
        VBox billControl = new VBox();
        ListView<String> bill = new ListView<>();
        bill.setPrefSize(150, 100);
        Button addToMenu = new Button("Add to bill");
        Button clear = new Button("Clear bill");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(addToMenu, clear);
        Slider tips = new Slider(0,20,15);
        Label tipsMessage = new Label("Please leave a tip: ");
        Label subtotal = new Label(String.format(String.format("Subtotal: %.2f", subtotalAmount)));
        Label tax = new Label(String.format("Tax: %.2f", 0.00));
        Label tip = new Label(String.format("Tip: %.2f (Currently at %.2f percents)", 0.00, tips.getValue()));
        Label total = new Label(String.format("Total: %.2f", 0.00));
        tips.valueProperty().addListener((obs, oldValue, newValue) -> {
            tip.setText(String.format("Tip: %.2f (Currently at %.2f percents)", calTip(tips), tips.getValue()));
            total.setText(String.format("Total: %.2f", calTot()));  
        });
        tips.showTickLabelsProperty().set(true);
        tips.showTickMarksProperty().set(true);
        tips.showTickMarksProperty();
        billControl.getChildren().addAll(bill, buttons, tipsMessage, tips, subtotal, tax, tip, total);
        root.setRight(billControl);
        root.setCenter(beverageMenu);
        
        // Add Button Handler
        addToMenu.setOnAction(e -> {
            switch(category.getValue().toString()) {
                case "Beverage" -> {
                    if (beverageMenu.getSelectionModel().getSelectedIndex() != -1) {
                    bill.getItems().add(beverageMenu.getSelectionModel().getSelectedItem());
                    subtotalAmount += beveragePrice[beverageMenu.getSelectionModel().getSelectedIndex()];
                    subtotal.setText(String.format("Subtotal: %.2f", subtotalAmount));
                    tax.setText(String.format("Tax: %.2f", calTax()));
                    tip.setText(String.format("Tip: %.2f (Currently at %.2f percents)", calTip(tips), tips.getValue()));
                    total.setText(String.format("Total: %.2f", calTot()));  
                    }
                }
                case "Appetizer" -> {
                    if (appetizerMenu.getSelectionModel().getSelectedIndex() != -1) {
                    bill.getItems().add(appetizerMenu.getSelectionModel().getSelectedItem());
                    subtotalAmount += appetizerPrice[appetizerMenu.getSelectionModel().getSelectedIndex()];
                    subtotal.setText(String.format("Subtotal: %.2f", subtotalAmount));
                    tax.setText(String.format("Tax: %.2f", calTax()));
                    tip.setText(String.format("Tip: %.2f (Currently at %.2f percents)", calTip(tips), tips.getValue()));
                    total.setText(String.format("Total: %.2f", calTot()));                        
                    }
                }
                case "Main Course" -> {
                    if (mainMenu.getSelectionModel().getSelectedIndex() != -1) {
                    bill.getItems().add(mainMenu.getSelectionModel().getSelectedItem());
                    subtotalAmount += mainPrice[mainMenu.getSelectionModel().getSelectedIndex()];
                    subtotal.setText(String.format("Subtotal: %.2f", subtotalAmount));
                    tax.setText(String.format("Tax: %.2f", calTax()));
                    tip.setText(String.format("Tip: %.2f (Currently at %.2f percents)", calTip(tips), tips.getValue()));
                    total.setText(String.format("Total: %.2f", calTot()));                        
                    }
                }
                case "Dessert" -> {
                    if (dessertMenu.getSelectionModel().getSelectedIndex() != -1) {
                    bill.getItems().add(dessertMenu.getSelectionModel().getSelectedItem());
                    subtotalAmount += dessertPrice[dessertMenu.getSelectionModel().getSelectedIndex()];
                    subtotal.setText(String.format("Subtotal: %.2f", subtotalAmount));     
                    tax.setText(String.format("Tax: %.2f", calTax()));
                    tip.setText(String.format("Tip: %.2f (Currently at %.2f percents)", calTip(tips), tips.getValue()));
                    total.setText(String.format("Total: %.2f", calTot()));                        
                    }
                }
            }
        });
        
        Scene scene = new Scene(root, 600, 300);
        stage.setScene(scene);
        stage.setTitle("Table Bill Calculator");
        stage.show();
    }

    public double calTax() {
        return subtotalAmount * 0.15;
    }

    public double calTip(Slider tips) {
        tipsAmount = subtotalAmount * (tips.getValue()/100);
        return tipsAmount;
    }

    public double calTot() {
        return subtotalAmount + tipsAmount + calTax();
    }
}
