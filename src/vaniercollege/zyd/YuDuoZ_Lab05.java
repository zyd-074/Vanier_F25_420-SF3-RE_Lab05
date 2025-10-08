package vaniercollege.zyd;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * @author Yu Duo Zhang (24800549)
 * https://github.com/zyd-074/Vanier_F25_420-SF3-RE_Lab05.git
 */
public class YuDuoZ_Lab05 extends Application {

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
        
        // Bag Quantity
        ComboBox bagQuantity = new ComboBox();
        bagQuantity.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        
        // Bag Sizes
        ToggleGroup bagSizes = new ToggleGroup();
        RadioButton small = new RadioButton("Small");
        small.setToggleGroup(bagSizes);
        RadioButton medium = new RadioButton("Medium");
        medium.setToggleGroup(bagSizes);
        RadioButton large = new RadioButton("Large");
        large.setToggleGroup(bagSizes);
        VBox sizeControl = new VBox();
        sizeControl.getChildren().addAll(small, medium, large);
        
        // Layout Setup
        bagChoice.getChildren().addAll(style, bagStyles, quantity, bagQuantity, size, sizeControl);
        bagChoice.setSpacing(15);
        root.setCenter(bagChoice);
        root.setPadding(new Insets(15,15,15,15));
        
        // Scene Setup
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
