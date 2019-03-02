package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MatrixConnectedRegions {

    public static class Coordinate {
    public int x, y, row, column;

    public Coordinate(int row, int column) {
      this.x = row;
      this.y = column;
      this.row = x;
      this.column = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Coordinate that = (Coordinate)o;
      if (x != that.x || y != that.y) {
        return false;
      }
      return true;
    }
  }

  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    // TODO - you fill in here.

    //first, check if x and y are valid
    int row = x;
    int column = y;

    if (row < 0 || row > image.size() || column < 0 || column > image.get(0).size()){
        return;
    }

    Boolean startColor = image.get(row).get(column);
    Queue<Coordinate> queue = new LinkedList<Coordinate>();
    Coordinate start = new Coordinate(row, column);
    queue.add(start);


    while(!queue.isEmpty()){
      Coordinate current = queue.remove();
      // List<Boolean> currentRow = image.get(current.row);
      // currentRow.set(current.column, !startColor);

      image.get(current.row).set(current.column, !startColor);

      List<Coordinate> adjList = new ArrayList<Coordinate>();
      // System.out.println("Current Coordiate x : " )
      adjList.add(new Coordinate(current.row, current.column - 1));
      adjList.add(new Coordinate(current.row, current.column + 1));
      adjList.add(new Coordinate(current.row - 1, current.column));
      adjList.add(new Coordinate(current.row + 1, current.column));

      for (Coordinate adj : adjList){
        if( adj.row > -1 && adj.row < image.size()){
          if(adj.column > -1 && adj.column < image.get(0).size()){

            if(image.get(adj.row).get(adj.column) == startColor){
              queue.add(adj);
            }

          }
        }
      }


    }





    return;
  }
  @EpiTest(testDataFile = "painting.tsv")
  public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                     int x, int y,
                                                     List<List<Integer>> image)
      throws Exception {
    List<List<Boolean>> B = new ArrayList<>();
    for (int i = 0; i < image.size(); i++) {
      B.add(new ArrayList<>());
      for (int j = 0; j < image.get(i).size(); j++) {
        B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
