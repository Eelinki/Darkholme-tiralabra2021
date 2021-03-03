package fi.eelij.Darkholme.UI;

import fi.eelij.Darkholme.Domain.Generator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {
    private ImageView imageView;
    private int pointAmount;

    @Override
    public void start(Stage primaryStage) {
        GridPane gp = new GridPane();
        GridPane controls = new GridPane();
        pointAmount = 50;

        Generator g = new Generator(200, 200, 6969420);
        g.generate(pointAmount);

        this.imageView = new ImageView(g.generateImage(4));
        gp.add(this.imageView, 1, 0);

        Button generateButton = new Button("Generate");

        generateButton.setOnAction(event -> {
            g.generate(pointAmount);
            this.imageView.setImage(g.generateImage(4));
        });

        TextField pointAmountField = new TextField(Integer.toString(pointAmount));
        pointAmountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                pointAmountField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            pointAmount = Integer.parseInt(pointAmountField.getText());
        });

        controls.add(generateButton, 0, 0);
        controls.add(pointAmountField, 0, 1);

        gp.add(controls, 0, 0);

        Scene scene = new Scene(gp);

        primaryStage.setTitle("Darkholme");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
