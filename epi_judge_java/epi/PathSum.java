package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PathSum {
  @EpiTest(testDataFile = "path_sum.tsv")

  // TODO - you fill in here.
  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {
    if(tree == null){
      return false;
    }

    if(tree.left == null && tree.right == null){
      if (tree.data == remainingWeight){
        return true;
      }
    }

    boolean left = hasPathSum(tree.left, remainingWeight - tree.data);
    boolean right = hasPathSum(tree.right, remainingWeight - tree.data);
    
    return left || right;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
