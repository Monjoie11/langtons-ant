package edu.cnm.deepdive.langton.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TerrainView extends Canvas {

 public void draw(int[][] patches){
   GraphicsContext context = getGraphicsContext2D();
   double cellHeight = getHeight() / patches.length;
   double cellWidth = getWidth() / patches[0].length;
   context.clearRect(0, 0, getWidth(), getHeight());
   for(int i = 0; i < patches.length; i++){
     for(int j = 0; j < patches[i].length; j++){
       if(patches[i][j] != 0){
         java.awt.Color color = new java.awt.Color(patches[i][j]);

         context.setFill(new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
         context.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
       }
     }
   }
 }

}
