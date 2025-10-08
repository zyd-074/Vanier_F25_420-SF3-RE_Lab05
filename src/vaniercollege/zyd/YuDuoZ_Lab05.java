package vaniercollege.zyd;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.value.*;

/**
 * @author Yu Duo Zhang (24800549)
 * https://github.com/zyd-074/Vanier_F25_420-SF3-RE_Lab05.git
 */
public class YuDuoZ_Lab05 extends Application {
    static String orderStyle = "";
    static String orderQuantity = "";
    static String orderSize = "";        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        HBox bagChoice = new HBox();
        
        // Labels
        Label style = new Label("Select Bag Style: ");
        Label quantity = new Label("Select Quantity: ");
        Label size = new Label("Select Size: ");
        
        // Bag List 
        ArrayList<String> strBagList = new ArrayList<>();
        strBagList.add("Full Decorative");
        strBagList.add("Beaded");
        strBagList.add("Pirate Design");
        strBagList.add("Fringed");
        strBagList.add("Fringed");
        strBagList.add("Plain");
        ObservableList<String> bagList = FXCollections.observableArrayList(strBagList);
        ListView<String> bagStyles = new ListView(bagList);
        bagStyles.setPrefSize(200, 100);
        
        bagStyles.getSelectionModel().selectedItemProperty().addListener(e -> {
            orderStyle = bagStyles.getSelectionModel().getSelectedItem();
        });
        
        
        // Bag Quantity
        ComboBox bagQuantity = new ComboBox();
        bagQuantity.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        
        bagQuantity.setOnAction(e -> {
            if (bagQuantity.getValue() != null) {
                orderQuantity = bagQuantity.getValue().toString();
            }
        });
        
        // Bag Sizes
        ToggleGroup bagSizes = new ToggleGroup();
        RadioButton small = new RadioButton("Small");
        small.setUserData("Small");
        small.setToggleGroup(bagSizes);
        RadioButton medium = new RadioButton("Medium");
        medium.setUserData("Medium");
        medium.setToggleGroup(bagSizes);
        RadioButton large = new RadioButton("Large");
        large.setUserData("Large");
        large.setToggleGroup(bagSizes);
        VBox sizeControl = new VBox();
        sizeControl.getChildren().addAll(small, medium, large);
        
        bagSizes.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) -> {
            if (bagSizes.getSelectedToggle() != null) {
                orderSize = bagSizes.getSelectedToggle().getUserData().toString();
            }
        });
        
        // Place Order & Clear
        VBox orderControls = new VBox();
        
        HBox orderButtons = new HBox();
        Button order = new Button("Place Order");
        Button clear = new Button("Clear Selections");
        orderButtons.getChildren().addAll(order, clear);
        orderButtons.setSpacing(15);
        Label orderConfirm = new Label("");
        orderButtons.setAlignment(Pos.CENTER_RIGHT);
        
        orderControls.getChildren().addAll(orderButtons, orderConfirm);
        orderControls.setAlignment(Pos.CENTER_RIGHT);
        orderControls.setSpacing(10);
        orderControls.setPadding(new Insets(10,10,10,10));
        
        order.setOnAction(e -> {
            if (!orderStyle.isEmpty() && !orderQuantity.isEmpty() && !orderSize.isEmpty()) {
                String orderText = String.format("You ordered %s %s %s Bags.", orderQuantity, orderSize, orderStyle);
                orderConfirm.setText(orderText);
            } else {
                orderConfirm.setText("Please select all informations.");
            }
        });
        
        clear.setOnAction(e -> {
            orderSize = null;
            orderQuantity = null;
            orderStyle = null;
            
            bagSizes.selectToggle(null);
            bagQuantity.setValue(null);
            bagStyles.getSelectionModel().select(-1);
            
            orderConfirm.setText("");
        });
        
        // Layout Setup
        bagChoice.getChildren().addAll(style, bagStyles, quantity, bagQuantity, size, sizeControl);
        bagChoice.setSpacing(15);
        bagChoice.setPadding(new Insets(10,10,10,10));
        root.setCenter(bagChoice);
        root.setBottom(orderControls);
        root.setPadding(new Insets(15,15,15,15));
        
        // Scene Setup
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bag Order Form");
        stage.show();
    }
}
