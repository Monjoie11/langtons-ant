package edu.cnm.deepdive.langton.model;

import com.sun.org.apache.xpath.internal.operations.Or;
import java.awt.Color;
import java.util.Random;

public class Terrain {

  private static final int WORLD_SIZE = 200;

  private int[][] patches;
  private Ant[] ants;

  public Terrain(int populationSize, Random rng) {
    patches = new int[WORLD_SIZE][WORLD_SIZE];
    ants = new Ant[populationSize];
    for (int i =0; i < populationSize; i++){
      int row = rng.nextInt(WORLD_SIZE);
      int column = rng.nextInt(WORLD_SIZE);
      Orientation orientation = Orientation.values()[rng.nextInt(Orientation.values().length)];
      int color = Color.HSBtoRGB(i / (float) populationSize, 1, 1);
      ants[i] = new Ant(color, row, column, orientation);
    }

  }

  public void tick(){
    for(Ant ant: ants){
      ant.crawl();
    }
  }

  public int[][] getPatches(){
    //FIXME Return a safe copy.
    return patches;
  }

  public class Ant{

    private final int id;
    private int row;
    private int column;
    private Orientation orientation;

    public Ant(int id, int row, int column,
        Orientation orientation) {
      this.id = id;
      this.row = row;
      this.column = column;
      this.orientation = orientation;
    }

    public void crawl(){
      if (patches[row][column] == 0){
        patches[row][column] = id;
        orientation = orientation.rightTurn();
      }else{
        patches[row][column] = 0;
        orientation = orientation.leftTurn();
      }
      row = Math.floorMod(row + orientation.getRowOffset(), patches.length);
      column = Math.floorMod(column + orientation.getColumnOffset(), patches[row].length);
    }


  }

  public enum Orientation{
    NORTH(-1,0),
    EAST(0,1),
    SOUTH(1,0),
    WEST(0,-1);

    private final int rowOffset;
    private final int columnOffset;

    Orientation(int rowOffset, int columnOffset) {
      this.rowOffset = rowOffset;
      this.columnOffset = columnOffset;
    }

    public int getRowOffset() {
      return rowOffset;
    }

    public int getColumnOffset() {
      return columnOffset;
    }

    public Orientation leftTurn(){
      Orientation[] values = Orientation.values();
      return values[Math.floorMod(ordinal() - 1, values.length)];
    }

    public Orientation rightTurn(){
      Orientation[] values = Orientation.values();
      return values[Math.floorMod(ordinal() + 1, values.length)];
    }

  }

}
