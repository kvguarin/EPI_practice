package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  // TODO - you fill in here.
  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if(tree == null){
      return true;
    }
    return isSymmetric(tree.left, tree.right);
  }

  public static boolean isSymmetric(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right){
    if(left == null && right == null){
      return true;
    }
    if(left == null || right == null){
      return false;
    }

    if(left.data != right.data){
      return false;
    }
    boolean sym1 = isSymmetric(left.left, right.right);
    boolean sym2 = isSymmetric(left.right, right.left);

    return sym1 && sym2;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
