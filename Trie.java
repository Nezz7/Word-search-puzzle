
public class Trie {
    private Node root;
    public Trie (){
        root = new Node();
    }
    public void insertWord (String s){
        root.insertWord(s);
    }
    public boolean findWord (String s){
        return root.findWord(s);
    } 
}
