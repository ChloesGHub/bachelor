package xapi.translator.main;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import xapi.translator.fromCSV.StGenerator;
import xapi.translator.fromXAPI.XAPIReader;
import xapi.translator.maps.XAPIActorList;
import xapi.translator.maps.XAPIStatementList;
import xapi.translator.maps.XAPIVerbList;

/**
 * main class containing javafx gui
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class Main extends Application {
    private XAPIStatementList statementList = new XAPIStatementList();
    private XAPIActorList actorList = new XAPIActorList();
    private XAPIVerbList verbList;
    private StGenerator stGenerator;
    private XAPIReader xapiReader;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void reset() {
        this.statementList = new XAPIStatementList();
        this.actorList = new XAPIActorList();
        this.verbList = new XAPIVerbList();
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("xAPI Translator V1.0");
        
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
            menuStart.setId("menuActive");
            Label menuMoodle = new Label("Moodle Log");
            Label menuXAPI = new Label("xAPI Log");
            Label menuExtract = new Label("Extraction");
            Label menuOptions = new Label("Options");
            Label menuCreate = new Label("Create");
            menuVBox.getChildren().addAll(menuStart, menuMoodle, menuXAPI,
                    menuExtract, menuOptions, menuCreate);
        mainBorderPane.setLeft(menuVBox);
        
        //start
        getStartPage(mainBorderPane);
        
        //init
        Scene scene = new Scene(mainBorderPane);
        stage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("GUI.css").toExternalForm());
        stage.show();
        
    }
    
    private VBox getVMenu(String active) {
        LinkedHashMap<String,Label> menuMap = new LinkedHashMap<>();
            menuMap.put("Start", new Label("Start"));
            menuMap.put("Moodle Log", new Label("Moodle Log"));
            menuMap.put("xAPI Log", new Label("xAPI Log"));
            menuMap.put("Extraction", new Label("Extraction"));
            menuMap.put("Options", new Label("Options"));
            menuMap.put("Create", new Label("Create"));
        Label activeLabel = menuMap.get(active);
        activeLabel.setId("menuActive");
        menuMap.replace(active, activeLabel);
        
        VBox menuVBox = new VBox(0);
            menuVBox.getStyleClass().add("menuVBox");
            menuMap.forEach((k,v) -> menuVBox.getChildren().add(v));
        return menuVBox;
    }
    
    private void getStartPage(BorderPane mainPane) {
        VBox vbox = new VBox(20);
        vbox.getStyleClass().add("mainCenterBox");
            Label entry = new Label("Welcome");
            entry.setId("entry");
            vbox.getChildren().add(entry);
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
        mainPane.setLeft(getVMenu("Start"));
    }
    
    private void getMoodlePage(BorderPane mainPane) {            
        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("mainCenterBox");
            gpane.setVgap(20);
            gpane.setHgap(20);
            
            Label entry = new Label("Configure moodle sourece(s)");
            entry.setId("entry");
            gpane.add(entry, 0, 0);

            //moodle logs
            Label logInst = new Label("Following Moodle logs \nwill be extracted:");            
            ArrayList<File> logFileList = new ArrayList<>();
            ObservableList<String> logNameList = FXCollections.observableArrayList();
            ListView<String> logListViewer = new ListView<>(logNameList);
            logListViewer.getStyleClass().add("listviewer");
            
            Button addLogButton = new Button("Add file");
            addLogButton.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("D:\\Bachelorarbeit\\"));
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    logFileList.add(file);
                    logNameList.add(file.getName());
                }
            });
            Button remLogButton = new Button("Remove file");
            remLogButton.disableProperty().bind(Bindings.isEmpty(logListViewer.getSelectionModel().getSelectedIndices()));
            remLogButton.setOnAction(e -> {
                int selected = logListViewer.getSelectionModel().getSelectedIndex();
                logFileList.remove(selected);
                logNameList.remove(selected);
            });
            
            gpane.add(logInst, 0, 1);
            GridPane.setValignment(logInst, VPos.TOP);
            gpane.add(addLogButton, 1, 2);
            gpane.add(remLogButton, 2, 2);
            gpane.add(logListViewer, 1, 1, 2, 1);
            
            //userids
            Label uIdInst = new Label("Following userid logs \nwill be extracted:");            
            ArrayList<File> uIdFileList = new ArrayList<>();
            ObservableList<String> uIdNameList = FXCollections.observableArrayList();
            ListView<String> uIdListViewer = new ListView<>(uIdNameList);
            uIdListViewer.getStyleClass().add("listviewer");
            
            Button addUIdButton = new Button("Add file");
            addUIdButton.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("D:\\Bachelorarbeit\\"));
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    uIdFileList.add(file);
                    uIdNameList.add(file.getName());
                }
            });
            Button remUIdButton = new Button("Remove file");
            remUIdButton.disableProperty().bind(Bindings.isEmpty(uIdListViewer.getSelectionModel().getSelectedIndices()));
            remUIdButton.setOnAction(e -> {
                int selected = uIdListViewer.getSelectionModel().getSelectedIndex();
                uIdFileList.remove(selected);
                uIdNameList.remove(selected);
            });
            
            gpane.add(uIdInst, 0, 3);
            GridPane.setValignment(uIdInst, VPos.TOP);
            gpane.add(addUIdButton, 1, 4);
            gpane.add(remUIdButton, 2, 4);
            gpane.add(uIdListViewer, 1, 3, 2, 1);
            
        mainPane.setCenter(gpane);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                getStartPage(mainPane);
            });
            Button nextButton = new Button("Next");
            nextButton.setOnAction(e -> {
                this.stGenerator = new StGenerator();
                logFileList.forEach(log -> {
                    this.stGenerator.parseLogFile(log);
                });
                uIdFileList.forEach(uID -> {
                    this.stGenerator.parseUIDFile(uID);
                });
                //Scene pg = new Scene(new ProgressBar(0.2));
                getVerblistPage(mainPane);
            });
            nextButton.disableProperty().bind(Bindings.or(
                    Bindings.isEmpty(logNameList), Bindings.isEmpty(uIdNameList)));
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> {
                reset();
                getStartPage(mainPane);
            });
            mainButtonBox.getChildren().addAll(backButton, nextButton, cancelButton);
        mainPane.setBottom(mainButtonBox);
        mainPane.setLeft(getVMenu("Moodle Log"));
    }
    
    private void getVerblistPage(BorderPane mainPane) {
        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("mainCenterBox");
            gpane.setVgap(20);
            gpane.setHgap(20);
            
            Label entry = new Label("Configure standard verblist");
            entry.setId("entry");
            gpane.add(entry, 0, 0);

            Label inst = new Label("Following verb list(s) will be \n"
                    + "used for translation:");            
            ArrayList<File> verbFileList = new ArrayList<>();
            ObservableList<String> verbNameList = FXCollections.observableArrayList();
            ListView<String> verbListViewer = new ListView<>(verbNameList);
            verbListViewer.getStyleClass().add("listviewer");
            
            Button addButton = new Button("Add file");
            addButton.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("D:\\Bachelorarbeit\\"));
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    verbFileList.add(file);
                    verbNameList.add(file.getName());
                }
            });
            Button remButton = new Button("Remove file");
            remButton.disableProperty().bind(Bindings.isEmpty(verbListViewer.getSelectionModel().getSelectedIndices()));
            remButton.setOnAction(e -> {
                int selected = verbListViewer.getSelectionModel().getSelectedIndex();
                verbFileList.remove(selected);
                verbNameList.remove(selected);
            });
            
            gpane.add(inst, 0, 1);
            GridPane.setValignment(inst, VPos.TOP);
            gpane.add(addButton, 1, 2);
            gpane.add(remButton, 2, 2);
            gpane.add(verbListViewer, 1, 1, 2, 1);
            
        mainPane.setCenter(gpane);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                getMoodlePage(mainPane);
            });
            Button nextButton = new Button("Next");
            nextButton.setOnAction(e -> {
                this.verbList = new XAPIVerbList();
                verbFileList.forEach(file -> {
                    this.verbList.init(file);
                });
                //Scene pg = new Scene(new ProgressBar(0.2));
                getXAPIPage(mainPane);
            });
            nextButton.disableProperty().bind(Bindings.isEmpty(verbNameList));
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> {
                reset();
                getStartPage(mainPane);
            });
            mainButtonBox.getChildren().addAll(backButton, nextButton, cancelButton);
        mainPane.setBottom(mainButtonBox);
        mainPane.setLeft(getVMenu("Moodle Log"));
    }
    
    private void getXAPIPage(BorderPane mainPane) {
        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("mainCenterBox");
            gpane.setVgap(20);
            gpane.setHgap(20);
            
            Label entry = new Label("Configure xAPI source(s)");
            entry.setId("entry");
            gpane.add(entry, 0, 0);

            Label inst = new Label("Following xAPI files) will be \n"
                    + "extracted:");            
            ArrayList<File> xAPIFileList = new ArrayList<>();
            ObservableList<String> xAPINameList = FXCollections.observableArrayList();
            ListView<String> xAPIListViewer = new ListView<>(xAPINameList);
            xAPIListViewer.getStyleClass().add("listviewer");
            
            Button addLogButton = new Button("Add file");
            addLogButton.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File("D:\\Bachelorarbeit\\"));
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    xAPIFileList.add(file);
                    xAPINameList.add(file.getName());
                }
            });
            Button remLogButton = new Button("Remove file");
            remLogButton.setOnAction(e -> {
                int selected = xAPIListViewer.getSelectionModel().getSelectedIndex();
                xAPIFileList.remove(selected);
                xAPINameList.remove(selected);
            });
            
            gpane.add(inst, 0, 1);
            GridPane.setValignment(inst, VPos.TOP);
            gpane.add(addLogButton, 1, 2);
            gpane.add(remLogButton, 2, 2);
            gpane.add(xAPIListViewer, 1, 1, 2, 1);
        mainPane.setCenter(gpane);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                getVerblistPage(mainPane);
            });
            Button nextButton = new Button("Next");
            nextButton.setOnAction(e -> {
                if (!xAPIFileList.isEmpty()) {
                    this.xapiReader = new XAPIReader();
                    xAPIFileList.forEach(file -> {
                        this.xapiReader.parseFile(file);
                    });
                }
                //Scene pg = new Scene(new ProgressBar(0.2));
                getExtractPage(mainPane);
            });
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> {
                reset();
                getStartPage(mainPane);
            });
            mainButtonBox.getChildren().addAll(backButton, nextButton, cancelButton);
        mainPane.setBottom(mainButtonBox);
        mainPane.setLeft(getVMenu("xAPI Log"));
    }
    
    private void getExtractPage(BorderPane mainPane) {
        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("mainCenterBox");
            gpane.setVgap(20);
            gpane.setHgap(20);
            
            Label entry = new Label("Extract source files");
            entry.setId("entry");
            Label inst = new Label("Click on Extract to start extraction.");
            
            gpane.add(entry, 0, 0);
            gpane.add(inst, 0, 1);
        mainPane.setCenter(gpane);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                getXAPIPage(mainPane);
            });
            Button extractButton = new Button("Extract");
            extractButton.setOnAction(e -> {
                if (!this.xapiReader.isEmpty()) {
                    this.xapiReader.extract(statementList, actorList, verbList);
                }
                this.stGenerator.extract(statementList, actorList, verbList);
                //Scene pg = new Scene(new ProgressBar(0.2));
                getOptionsPage(mainPane);
            });
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> {
                reset();
                getStartPage(mainPane);
            });
            mainButtonBox.getChildren().addAll(backButton, extractButton, cancelButton);
        mainPane.setBottom(mainButtonBox);
        mainPane.setLeft(getVMenu("Extraction"));
    }
    
    private void getOptionsPage(BorderPane mainPane) {
                GridPane gpane = new GridPane();
        gpane.getStyleClass().add("mainCenterBox");
            gpane.setVgap(20);
            gpane.setHgap(20);
            
            Label entry = new Label("Options");
            entry.setId("entry");
            gpane.add(entry, 0, 0);

            CheckBox sortBox = new CheckBox("Sorting by datetime");
            //Label inst = new Label("Sorting by datetime");
            
            gpane.add(sortBox, 0, 1);
            GridPane.setValignment(sortBox, VPos.TOP);
        mainPane.setCenter(gpane);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button nextButton = new Button("Next");
            nextButton.setOnAction(e -> {
                this.statementList.sortByDate();
                getCreatePage(mainPane);
            });
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> {
                reset();
                getStartPage(mainPane);
            });
            mainButtonBox.getChildren().addAll(nextButton, cancelButton);
        mainPane.setBottom(mainButtonBox);
        mainPane.setLeft(getVMenu("Options"));
    }
    
    private void getCreatePage(BorderPane mainPane) {
        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("mainCenterBox");
            gpane.setVgap(20);
            gpane.setHgap(20);
            
            Label entry = new Label("Creating merged file");
            entry.setId("entry");
            gpane.add(entry, 0, 0);

            Label inst = new Label("Select a save position.");      
            TextField savedir = new TextField();
            savedir.setPromptText("Directory");
            Button browseButton = new Button("Browse");
            browseButton.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save File");
                fileChooser.setInitialDirectory(new File("D:\\Bachelorarbeit\\"));
                fileChooser.setInitialFileName("xapi-sample.json");
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON", "*.json"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                );
                File file = fileChooser.showSaveDialog(null);
                if (file != null) {
                    savedir.setText(file.toString());
                }
            });
            
            gpane.add(inst, 0, 1);
//            GridPane.setValignment(inst, VPos.TOP);
            gpane.add(savedir, 0, 2);
            gpane.add(browseButton, 0, 3);
            
            
        mainPane.setCenter(gpane);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button backButton = new Button("Back");
            backButton.setOnAction(e -> {
                getOptionsPage(mainPane);
            });
            Button nextButton = new Button("Create");
            nextButton.setOnAction(e -> {
                XAPIWriter writer = new XAPIWriter();
                writer.exportToJson(savedir.getText(), statementList);
                //Scene pg = new Scene(new ProgressBar(0.2));
                getFinishPage(mainPane);
            });
            Button cancelButton = new Button("Cancel");
            cancelButton.setOnAction(e -> {
                reset();
                getStartPage(mainPane);
            });
            mainButtonBox.getChildren().addAll(backButton, nextButton, cancelButton);
        mainPane.setBottom(mainButtonBox);
        mainPane.setLeft(getVMenu("Create"));
    }
    
    private void getFinishPage(BorderPane mainPane) {
        GridPane gpane = new GridPane();
        gpane.getStyleClass().add("mainCenterBox");
            gpane.setVgap(20);
            gpane.setHgap(20);
            
            Label entry = new Label("Finished");
            entry.setId("entry");
            gpane.add(entry, 0, 0);

            Label inst = new Label("Translation and merge was successfull. \n"
                    + " Click on Exit to close this program."); 
            
            gpane.add(inst, 0, 1);
        mainPane.setCenter(gpane);
        
        HBox mainButtonBox = new HBox(10.0);
        mainButtonBox.getStyleClass().add("mainButtonBox");
            Button exitButton = new Button("Exit");
            exitButton.setOnAction(e -> {
                Platform.exit();
            });
            Button restartButton = new Button("Restart");
            restartButton.setOnAction(e -> {
                reset();
                getStartPage(mainPane);
            });
            mainButtonBox.getChildren().addAll(exitButton, restartButton);
        mainPane.setBottom(mainButtonBox);
    }
}
