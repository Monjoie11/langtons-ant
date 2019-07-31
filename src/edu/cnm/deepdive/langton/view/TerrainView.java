package edu.cnm.deepdive.langton.view;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TerrainView extends Canvas {

  private Map<Integer, Color> colors = new HashMap<>();



 public void draw(int[][] patches){
   GraphicsContext context = getGraphicsContext2D();
   double cellHeight = getHeight() / patches.length;
   double cellWidth = getWidth() / patches[0].length;
   context.clearRect(0, 0, getWidth(), getHeight());
   for(int i = 0; i < patches.length; i++){
     for(int j = 0; j < patches[i].length; j++){
       if(patches[i][j] != 0){
         context.setFill(getColor(patches[i][j]));
         context.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
       }
     }
   }
 }

 private Color getColor(int key) {
   Color color = colors.get(key);
   if (color == null) {
     java.awt.Color awtColor = new java.awt.Color(key);
     color = new Color(awtColor.getRed()/255.0, awtColor.getGreen()/255.0, awtColor.getBlue()/255.0,
         awtColor.getAlpha()/255.0);
     colors.put(key, color);
   }
   return color;
 }

}
