/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.main;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author chloe
 */
public class GUI extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("xAPI Translator V1.0");
        
        ArrayList<Status> statuslist = new ArrayList();
        statuslist.add(new Status("start", null, "moodle", "Start"));
        statuslist.add(new Status("moodle", "start", "xapi", "Moodle Log"));
        statuslist.add(new Status("xapi", "moodle", "extract", "xAPI Log"));
        statuslist.add(new Status("extract", "xapi", "options", "Extraction"));
        statuslist.add(new Status("options", "extract", "create", "Options"));
        statuslist.add(new Status("create", "options", null, "Create"));
        int status = 0;
        
        BorderPane mainBorderPane = new BorderPane();
        
        //Top (Logo + App Name)
        HBox mainTopBox = new HBox();
        mainTopBox.getStyleClass().add("mainTopBox");
            Label topLabel = new Label("xAPI-Translator");
            topLabel.setId("appname");
        mainTopBox.getChildren().add(topLabel);
        mainBorderPane.setTop(mainTopBox);
        
        
        //Side-Menu / left menu
        VBox menuVBox = new VBox(0);
        menuVBox.getStyleClass().add("menuVBox");
            Label menuStart = new Label("Start");
            Label menuMoodle = new Label("Moodle Log");
            Label menuXAPI = new Label("xAPI Log");
            Label menuExtract = new Label("Extraction");
            Label menuOptions = new Label("Options");
            Label menuCreate = new Label("Create");
            menuVBox.getChildren().addAll(menuStart, menuMoodle, menuXAPI,
                    menuExtract, menuOptions, menuCreate);
        mainBorderPane.setLeft(menuVBox);
        
        //foot (buttons)
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button nextButton = new Button("Next");
            Button backButton = new Button("Back");
            Button createButton = new Button("Create");
            Button cancelButton = new Button("Cancel");
            mainButtonBox.getChildren().addAll(nextButton, backButton,
                    createButton, cancelButton);
        mainBorderPane.setBottom(mainButtonBox);
        
        //start
        getStartPage(mainBorderPane);
        
        //init
        Scene scene = new Scene(mainBorderPane);
        stage.setScene(scene);
        scene.getStylesheets().add(GUI.class.getResource("GUI.css").toExternalForm());
        stage.show();
        
    }
    
    private class Status {
        private String actuel;
        private String before;
        private String next;
        private String name;
        
        public Status(String actuel, String before, String next, String name) {
            this.actuel = actuel;
            this.before = before;
            this.next = next;
            this.name = name;
        }

        public String getActuel() {
            return actuel;
        }

        public void setActuel(String actuel) {
            this.actuel = actuel;
        }

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    
    private void getStartPage(BorderPane mainPane) {
        VBox vbox = new VBox(20);
        vbox.getStyleClass().add("mainCenterBox");
            vbox.getChildren().add(new Label("Welcome!"));
            vbox.getChildren().add(new Label("click on Next to start the process."));
        mainPane.setCenter(vbox);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button nextButton = new Button("Next");
            nextButton.setOnAction(e -> {
                getMoodlePage(mainPane);
            });
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> {
                Platform.exit();
            });
            mainButtonBox.getChildren().addAll(nextButton, cancelButton);
        mainPane.setBottom(mainButtonBox);
    }
    
    private void getMoodlePage(BorderPane mainPane) {
        VBox vbox = new VBox(20);
        vbox.getStyleClass().add("mainCenterBox");
            vbox.getChildren().add(new Label("Welcome!"));
            vbox.getChildren().add(new Label("click on Next to start the process."));
        mainPane.setCenter(vbox);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                getStartPage(mainPane);
            });
        
            Button nextButton = new Button("Next");
            nextButton.setOnAction(e -> {
                
            });
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> {
                Platform.exit();
            });
            mainButtonBox.getChildren().addAll(backButton, nextButton, cancelButton);
        mainPane.setBottom(mainButtonBox);
    }
    //Button backButton = new Button("Back");
    //Button createButton = new Button("Create");
}
