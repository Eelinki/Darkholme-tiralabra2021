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
    private int width;
    private int height;
    private int scale;

    @Override
    public void start(Stage primaryStage) {
        GridPane gp = new GridPane();
        VBox controls = new VBox();
        pointAmount = 50;
        seed = 0;
        corridorPercentage = 0.15;
        width = 200;
        height = 200;
        scale = 4;

        Generator g = new Generator();
        g.generate(pointAmount, seed, corridorPercentage, width, height);

        this.imageView = new ImageView(g.generateImage(scale));
        gp.add(this.imageView, 1, 0);

        Button generateButton = new Button("Generate");

        generateButton.setOnAction(event -> {
            g.generate(pointAmount, seed, corridorPercentage, width, height);
            this.imageView.setImage(g.generateImage(scale));
        });

        Label widthFieldLabel = new Label("Width");
        TextField widthField = new TextField(Integer.toString(width));
        widthField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                widthField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            width = Integer.parseInt(widthField.getText());
        });

        Label heightFieldLabel = new Label("Height");
        TextField heightField = new TextField(Integer.toString(height));
        heightField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                heightField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            height = Integer.parseInt(heightField.getText());
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

        Label scaleLabel = new Label("Scale");
        Label scaleValueLabel = new Label();
        Slider scaleSlider = new Slider(1, 4, 4);
        scaleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            scale = (int) scaleSlider.valueProperty().get();
        });

        scaleValueLabel.textProperty().bind(Bindings.format("%.0f", scaleSlider.valueProperty()));

        controls.getChildren().add(generateButton);

        controls.getChildren().add(widthFieldLabel);
        controls.getChildren().add(widthField);

        controls.getChildren().add(heightFieldLabel);
        controls.getChildren().add(heightField);

        controls.getChildren().add(pointAmountFieldLabel);
        controls.getChildren().add(pointAmountField);

        controls.getChildren().add(seedLabel);
        controls.getChildren().add(seedField);

        controls.getChildren().add(corridorPercentageLabel);
        controls.getChildren().add(corridorPercentageSlider);
        controls.getChildren().add(corridorPercentageValueLabel);

        controls.getChildren().add(scaleLabel);
        controls.getChildren().add(scaleSlider);
        controls.getChildren().add(scaleValueLabel);

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
