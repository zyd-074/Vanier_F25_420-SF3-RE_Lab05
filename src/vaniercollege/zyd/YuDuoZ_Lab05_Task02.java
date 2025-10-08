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
        selectControl.getChildren().addAll(categorySelect, category);
        
        ArrayList<String> beverageList = new ArrayList<>();
        ArrayList<String> appetizerList = new ArrayList<>();
        ArrayList<String> mainList = new ArrayList<>();
        ArrayList<String> dessertList = new ArrayList<>();
        {beverageList.add("Coffee | 2.50");
        beverageList.add("Tea | 2.00");
        beverageList.add("Soft Drink | 1.75");
        beverageList.add("Water | 2.95");
        beverageList.add("Milk | 1.5");
        beverageList.add("Juice | 2.5");
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
        dessertList.add("Apple Crisp | 5.98");} // Add Menu
        ObservableList<String> beverage = FXCollections.observableArrayList(beverageList);
        ObservableList<String> appetizer = FXCollections.observableArrayList(appetizerList);
        ObservableList<String> mainCourse = FXCollections.observableArrayList(mainList);
        ObservableList<String> dessert = FXCollections.observableArrayList(dessertList);
        ListView<String> beverageMenu = new ListView<>(beverage);
        beverageMenu.setPrefSize(200, 100);
        ListView<String> appetizerMenu = new ListView<>(appetizer);
        appetizerMenu.setPrefSize(200, 100);
        ListView<String> mainMenu = new ListView<>(mainCourse);
        mainMenu.setPrefSize(200, 100);
        ListView<String> dessertMenu = new ListView<>(dessert);
        dessertMenu.setPrefSize(200, 100);

        root.setLeft(selectControl);
        category.setOnAction(e -> {
            switch (category.getValue().toString()) {
                case "Beverage" -> root.setCenter(beverageMenu);
                case "Appetizer" -> root.setCenter(appetizerMenu);
                case "Main Course" -> root.setCenter(mainMenu);
                case "Dessert" -> root.setCenter(dessertMenu);
            }
        });
        
        VBox billControl = new VBox();
        ListView<String> bill = new ListView<>();
        bill.setPrefSize(150, 100);
        Button addToMenu = new Button("Add to bill");
        Button clear = new Button("Clear bill");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(addToMenu, clear);
        Label tipsMessage = new Label("Please leave a tip: ");
        Slider tips = new Slider(0,20,15);
        tips.showTickLabelsProperty().set(true);
        tips.showTickMarksProperty().set(true);
        tips.showTickMarksProperty();
        Label subtotal = new Label();
        Label tax = new Label();
        Label tip = new Label();
        Label total = new Label();
        billControl.getChildren().addAll(bill, buttons, tipsMessage, tips, subtotal, tax, tip, total);
        root.setRight(billControl);
        
        Scene scene = new Scene(root, 600, 300);
        stage.setScene(scene);
        stage.show();
    }
}
