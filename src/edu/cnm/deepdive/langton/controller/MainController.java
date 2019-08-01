package edu.cnm.deepdive.langton.controller;

import edu.cnm.deepdive.langton.Main;
import edu.cnm.deepdive.langton.model.Terrain;
import edu.cnm.deepdive.langton.view.TerrainView;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;

public class MainController {

  @FXML
  private TerrainView terrainView;

  @FXML
  private ToggleButton runToggle;

  @FXML
  private Slider populationSize;

  private boolean running;

  private Terrain terrain;

  private AnimationTimer timer;

  @FXML
  private void initialize(){
   terrain = new Terrain((int) populationSize.getValue(), new Random());
   timer = new AnimationTimer() {
     @Override
     public void handle(long now) {
       terrainView.draw(terrain.getPatches());
     }
   };
  }



  @FXML
  private void toggleRun(ActionEvent actionEvent) {
    if(runToggle.isSelected()){
      start();
    } else {
      stop();
    }
  }

  private void start(){
    running = true;
    timer.start();
    new Runner().start();

  }

  public void stop(){
    runToggle.setDisable(true);
    running = false;
    timer.stop();
  }


  private class Runner extends Thread{

    @Override
    public void run() {
      while (running){
        terrain.tick();
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          //Do nothing
        }
      }
      Platform.runLater(() -> runToggle.setDisable(false));
    }
  }

  public void reset(ActionEvent actionEvent){
    //TODO fix run toggle
    toggleRun(actionEvent);
    stop();
    initialize();
    start();
  }


}
