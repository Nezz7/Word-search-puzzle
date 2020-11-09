
public class Node {
    private Node [] child;
    private boolean isWord;
    public Node() {
       child = new Node[26];
       isWord = false;
    }
    void insertWord (String s){
        if (s.length() == 0){
            isWord = true;
        }else {
            int c = s.charAt(0) - 'a';
            if (child[c] == null){
                child[c] = new Node();
            }
            child[c].insertWord(s.substring(1));
        }
    }
    
    boolean findWord (String s){
        if (s.length() == 0){
            return isWord;
        }
        int c = s.charAt(0) - 'a';
        if (child[c] == null){
            return false;
        }
        return child[c].findWord(s.substring(1));
    }
  
}
