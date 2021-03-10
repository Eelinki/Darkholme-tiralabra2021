package fi.eelij.Darkholme.UI;

import fi.eelij.Darkholme.Domain.Generator;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    private ImageView imageView;
    private int pointAmount;
    private long seed;
    private double corridorPercentage;

    @Override
    public void start(Stage primaryStage) {
        GridPane gp = new GridPane();
        VBox controls = new VBox();
        pointAmount = 50;
        seed = 0;
        corridorPercentage = 0.15;

        Generator g = new Generator(200, 200);
        g.generate(pointAmount, seed, corridorPercentage);

        this.imageView = new ImageView(g.generateImage(4));
        gp.add(this.imageView, 1, 0);

        Button generateButton = new Button("Generate");

        generateButton.setOnAction(event -> {
            g.generate(pointAmount, seed, corridorPercentage);
            this.imageView.setImage(g.generateImage(4));
        });

        Label pointAmountFieldLabel = new Label("Points");
        TextField pointAmountField = new TextField(Integer.toString(pointAmount));
        pointAmountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                pointAmountField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            pointAmount = Integer.parseInt(pointAmountField.getText());
        });

        Label seedLabel = new Label("Seed (set to 0 for random)");
        TextField seedField = new TextField(Long.toString(seed));
        seedField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("-?\\d*")) {
                seedField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            seed = Integer.parseInt(seedField.getText());
        });

        Label corridorPercentageLabel = new Label("Corridors %");
        Label corridorPercentageValueLabel = new Label();
        Slider corridorPercentageSlider = new Slider(0, 1, 0.15);
        corridorPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            corridorPercentage = corridorPercentageSlider.valueProperty().get();
        });

        corridorPercentageValueLabel.textProperty().bind(Bindings.format("%.2f", corridorPercentageSlider.valueProperty()));

        controls.getChildren().add(generateButton);

        controls.getChildren().add(pointAmountFieldLabel);
        controls.getChildren().add(pointAmountField);

        controls.getChildren().add(seedLabel);
        controls.getChildren().add(seedField);

        controls.getChildren().add(corridorPercentageLabel);
        controls.getChildren().add(corridorPercentageSlider);
        controls.getChildren().add(corridorPercentageValueLabel);

        controls.setPadding(new Insets(10, 10, 10, 10));
        controls.setSpacing(5);

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
