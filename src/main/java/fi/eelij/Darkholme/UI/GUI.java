package fi.eelij.Darkholme.UI;

import fi.eelij.Darkholme.Domain.Generator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application{
    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) {
        GridPane gp = new GridPane();

        Generator g = new Generator(200, 200);
        g.generate(25);

        this.imageView = new ImageView(g.generateImage(5));
        gp.add(this.imageView, 0, 0);

        Button generate = new Button("Generate");

        gp.add(generate, 0, 1);

        Scene scene = new Scene(gp);

        primaryStage.setTitle("Darkholme");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
