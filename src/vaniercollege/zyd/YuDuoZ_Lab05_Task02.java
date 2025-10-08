package vaniercollege.zyd;

import javafx.application.Application;
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
        
        root.setLeft(selectControl);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
