package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
public class StringTransformability {

  @EpiTest(testDataFile = "string_transformability.tsv")

  // Uses BFS to find the least steps of transformation.
  public static int transformString(Set<String> D, String s, String t) {

    if(!D.contains(s)){
      return -1;
    }


    Set<String> visited = new HashSet<>();
    Queue<String> queue = new ArrayDeque<>();
    queue.add(s);
    int count = 0;
    while(!queue.isEmpty()){

      String currentWord = queue.pop();
      visited.add(currentWord);

      for(String word : D){
        if(oneLetterDifference(currentWord, word)){

          if(word.compareTo(t) == 0){ //found the end word, return length
            return count;
          }
          if(!visited.contains(word)){
            count++;
            // add to queue after doing work
          }
        }
      }

    }
    return dfs(D, visited, s, t);
  }


  public static boolean oneLetterDifference(String s, String t){
    if (s.length() != t.length()){
      return false;
    }
    int difference = 0;


    for(int i = 0; i < t.length(); i++){
      if(t.charAt(i) != s.charAt(i)){
        difference++;
      }
    }

    if(difference == 1){
      return true;
    }

    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
