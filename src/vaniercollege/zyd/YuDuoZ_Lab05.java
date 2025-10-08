package vaniercollege.zyd;

import javafx.application.Application;
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
        
        Label style = new Label("Select Bag Style: ");
        Label quantity = new Label("Select Bag Style");
        ListView bagStyles = new ListView();
        ComboBox bagQuantity = new ComboBox();
        ToggleGroup bagSizes = new ToggleGroup();
        RadioButton small = new RadioButton("Small");
        small.setToggleGroup(bagSizes);
        RadioButton medium = new RadioButton("Medium");
        medium.setToggleGroup(bagSizes);
        RadioButton large = new RadioButton("Large");
        large.setToggleGroup(bagSizes);
        VBox sizeControl = new VBox();
        sizeControl.getChildren().addAll(small, medium, large);
        
        bagChoice.getChildren().addAll(style, bagStyles, quantity, bagQuantity, sizeControl);
        bagChoice.setSpacing(15);
        root.setCenter(bagChoice);
        root.setPadding(new Insets(15,15,15,15));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
