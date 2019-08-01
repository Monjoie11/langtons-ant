package edu.cnm.deepdive.langton;

import edu.cnm.deepdive.langton.controller.MainController;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static final String RESOURCES_DIR= "resources";
  private static final String LAYOUT_RESOURCE = RESOURCES_DIR + "/main.fxml";
  private static final String RESOURCE_BUNDLE_BASE_NAME = RESOURCES_DIR + "/strings";
  private static final String WINDOW_TITLE_KEY = "window_title";

  private MainController controller;


  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME);
    FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource(LAYOUT_RESOURCE), bundle);
    Parent root = fxmlLoader.load();
    controller = fxmlLoader.getController();
    //TODO Get a reference to controller instance
    Scene scene = new Scene(root);
    stage.setTitle((bundle.getString(WINDOW_TITLE_KEY)));
    //TODO Set other elements of the scene from our resources
    stage.setResizable(false);
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    controller.stop();
    super.stop();
  }


}
