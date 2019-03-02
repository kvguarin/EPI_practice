package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.*;
public class MatrixEnclosedRegions {

  // TODO - you fill in here.
  public static void fillSurroundedRegions(List<List<Character>> board) {
    

    //make set that has visited white squares
    //find white squares around the border and add those to visited by using BFS/DFS
    //mark surrounded white regions as black

    // left and right side of board
    for(int i = 0; i < board.size(); i ++){
      if(board.get(i).get(0) == 'W'){ //if it is white, travse and add to set
        Coordinate coordinate = new Coordinate(i, 0);
        reachableWhiteSquareTraversal(board, coordinate);
      }

      if(board.get(i).get(board.get(i).size() - 1) == 'W'){ //if it is white, travse and add to set
        Coordinate coordinate = new Coordinate(i, board.get(i).size() - 1);
        reachableWhiteSquareTraversal(board, coordinate);
      }
    }

    // top and bottom of board
    for(int i = 0; i < board.get(0).size(); i++){
      if (board.get(0).get(i) == 'W'){
        Coordinate coordinate = new Coordinate(0, i);
        reachableWhiteSquareTraversal(board, coordinate);
      }

      if (board.get(board.size() - 1).get(i) == 'W'){
        Coordinate coordinate = new Coordinate(board.size() - 1, i);
        reachableWhiteSquareTraversal(board, coordinate);
      }
    }


    for(int i = 0 ;i < board.size(); i++){
      for(int j = 0; j < board.get(i).size(); j++){
        if(board.get(i).get(j) == 'T'){
          board.get(i).set(j, 'W');
        }else if (board.get(i).get(j) == 'W'){
          board.get(i).set(j, 'B');
        }
      }
    }

    return;
  }

  //sets all reachable white  squares to true
  public static void reachableWhiteSquareTraversal(List<List<Character>> board, Coordinate coordinate){
    Queue<Coordinate> queue = new LinkedList<Coordinate>();
    queue.add(coordinate);

    while(!queue.isEmpty()){
      Coordinate current = queue.remove();
      board.get(current.x).set(current.y, 'T');

      List<Coordinate> adjacentList = new ArrayList<>();
      adjacentList.add(new Coordinate(current.x, current.y - 1));
      adjacentList.add(new Coordinate(current.x, current.y + 1));
      adjacentList.add(new Coordinate(current.x - 1, current.y));
      adjacentList.add(new Coordinate(current.x + 1, current.y));

      for(Coordinate adj : adjacentList){
        if (adj.x > -1 && adj.x < board.size()){
          if (adj.y > -1 && adj.y < board.get(0).size()){
            if (board.get(adj.x).get(adj.y) == 'W'){
              queue.add(adj);
            }
          }
        }
      }
    }

  }


  public static class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
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




  @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>>
  fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
