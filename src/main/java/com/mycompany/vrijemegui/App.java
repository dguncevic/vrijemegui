package com.mycompany.vrijemegui;

import com.google.gson.Gson;
import com.mycompany.beans.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    ObservableList cities;

    @Override
    public void start(Stage stage) {

        AnchorPane anchor = new AnchorPane();
        Scene scene = new Scene(anchor, 400, 500);

        AnchorPane pickCity = new AnchorPane();
        AnchorPane cityInfo = new AnchorPane();
        cityInfo.setVisible(false);

        pickCity.setStyle("-fx-background-color: azure");
        cityInfo.setStyle("-fx-background-color: azure");
        anchor.setStyle("-fx-background-color: azure");

        anchor.getChildren().addAll(pickCity, cityInfo);

        ImageView imgView = new ImageView();
        imgView.setLayoutX(150);
        imgView.setLayoutY(50);

        Image clearSky = new Image("C:\\Users\\19par\\Documents\\NetBeansProjects\\VrijemeGUI\\clearsky.png");
        Image brokenClouds = new Image("C:\\Users\\19par\\Documents\\NetBeansProjects\\VrijemeGUI\\brokenclouds.png");
        Image overcastClouds = new Image("C:\\Users\\19par\\Documents\\NetBeansProjects\\VrijemeGUI\\brokenclouds.png");
        Image mist = new Image("C:\\Users\\19par\\Documents\\NetBeansProjects\\VrijemeGUI\\mist.png");
        ComboBox<Cities> cbCities = new ComboBox();
        cbCities.setLayoutX(160);
        cbCities.setLayoutY(200);

        pickCity.getChildren().add(cbCities);

        Cities osijek = new Cities("Osijek", 45.56, 18.67);
        Cities zagreb = new Cities("Zagreb", 45.81, 15.98);
        Cities dallas = new Cities("Dallas", 32.78, -96.81);
        Cities berlin = new Cities("Berlin", 52.50, 13.40);

        Label cityName = getNewLabel("CityName", 120, 200, 18);
        cityName.setFont(Font.font(STYLESHEET_MODENA, FontWeight.BOLD, 18));
        Label temp = getNewLabel("Temperatura", 120, 250, 16);
        Label feelsLike = getNewLabel("", 120, 300, 16);
        Label descrp = getNewLabel("Opis...", 120, 350, 16);

        cityInfo.getChildren().addAll(cityName, temp, feelsLike, descrp, imgView);

        cities = FXCollections.observableArrayList();
        cities.addAll(osijek, zagreb, dallas, berlin);
        cbCities.setItems(cities);

        cbCities.setOnAction((t) -> {

            cityInfo.setVisible(true);
            pickCity.setVisible(false);

            Updater.updaterX = cbCities.getSelectionModel().getSelectedItem().getX();
            Updater.updaterY = cbCities.getSelectionModel().getSelectedItem().getY();

            try {
                String jsonResult = Updater.getJson();
                Gson gson = new Gson();

                MyWeather mw = gson.fromJson(jsonResult, MyWeather.class);

                System.out.println(mw.getName());

                cityName.setText("" + mw.getName());
                temp.setText("" + mw.getMain());
                descrp.setText("" + mw.getWeather());

                System.out.println(mw.getWeather());

                if (descrp.getText().equals("[clear sky]")) {
                    Platform.runLater(() -> imgView.setImage(clearSky));
                } else if (descrp.getText().equals("[broken clouds]")) {
                    Platform.runLater(() -> imgView.setImage(brokenClouds));
                    imgView.setLayoutX(100);
                    imgView.setLayoutY(30);
                    descrp.setText("Broken clouds");
                } else if (descrp.getText().equals("[overcast clouds]")) {
                    Platform.runLater(() -> imgView.setImage(overcastClouds));
                     descrp.setText("Overcast clouds");
                } else if (descrp.getText().contains("[light intensity drizzle, mist, light rain]")) {
                    Platform.runLater(() -> imgView.setImage(mist));
                     descrp.setText("Light intensity drizzle, mist, light rain");
                }
                
//                Button goback = new Button("Return");
//                goback.setLayoutX(350);
//                goback.setLayoutY(350);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Label getNewLabel(String str, double x, double y, int z) {

        Label label = new Label(str);

        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(Font.font(z));

        return label;
    }

}
