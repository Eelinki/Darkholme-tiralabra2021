package fi.eelij.Darkholme.UI;

import fi.eelij.Darkholme.Domain.Generator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application{
    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gp = new GridPane();

        Generator g = new Generator(200, 200);
        g.generate(10);

        this.imageView = new ImageView(g.generateImage(5));
        gp.add(this.imageView, 0, 0);

        Scene scene = new Scene(gp);

        primaryStage.setTitle("Darkholme");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
