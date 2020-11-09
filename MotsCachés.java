
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MotsCachés {

    public static String reverse(String s) {
        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            rev += s.charAt(i);
        }
        return rev;
    }

    public static void main(String[] args) {
        String grilleFile = args[0];
        String dictionnaireFile = args[1];
        long startTime = System.nanoTime();
        File file = new File(dictionnaireFile);
        Scanner in;
        Trie trie = new Trie();

        try {
            in = new Scanner(file);
            while (in.hasNextLine()) {
                String s = in.nextLine();
                trie.insertWord(s);
            }

        } catch (FileNotFoundException e) {
            System.out.println("DictonnaireFile Not Found !!");
        }
        file = new File(grilleFile);
        int n = 0;
        String[] grille = null;
        try {
            in = new Scanner(file);
            n = in.nextInt();
            grille = new String[n + 1];
            int i = 0;
            in.nextLine();
            while (in.hasNextLine()) {
                String s = in.nextLine();
                grille[i] = s;
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("GrilleFile Not Found !!");
        }

        ArrayList<String> words = new ArrayList<String>();
        //horizontalement
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String s = "";
                String rev = "";
                for (int k = j; k < n; k++) {
                    s += grille[i].charAt(k);
                    rev = reverse(s);
                    if (k >= 2 && trie.findWord(s)) {
                        words.add(s);
                    }
                    if (k >= 2 && trie.findWord(rev)) {
                        words.add(rev);
                    }
                }
            }
        }
        // verticalement
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                String s = "";
                String rev = "";
                for (int k = i; k < n; k++) {
                    s += grille[k].charAt(j);
                    rev = reverse(s);
                    if (k >= 2 && trie.findWord(s)) {
                        words.add(s);
                    }
                    if (k >= 2 && trie.findWord(rev)) {
                        words.add(rev);
                    }
                }
            }

        }
        // oblique 
        for (int i = 0; i < n; i++) {
            int x = i;
            int y = 0;
            String s = "";
            while (x < n && y < n) {
                s += grille[x].charAt(y);
                x++;
                y++;
            }
            for (int start = 0; start < s.length(); start++) {
                for (int end = start + 1; end <= s.length(); end++) {
                    String str = s.substring(start, end);
                    String rev = reverse(str);
                    if (trie.findWord(str)) {
                        words.add(str);
                    }
                    if (trie.findWord(rev)) {
                        words.add(rev);
                    }
                }
            }
            x = i;
            y = 0;
            s = "";
            while (x >= 0 && y < n) {
                s += grille[x].charAt(y);
                x--;
                y++;
            }
            for (int start = 0; start < s.length(); start++) {
                for (int end = start + 1; end <= s.length(); end++) {
                    String str = s.substring(start, end);
                    String rev = reverse(str);
                    if (trie.findWord(str)) {
                        words.add(str);
                    }
                    if (trie.findWord(rev)) {
                        words.add(rev);
                    }
                }
            }

        }
        for (int i = 1; i < n; i++) {
            int x = 0;
            int y = i;
            String s = "";
            while (x < n && y < n) {
                s += grille[x].charAt(y);
                x++;
                y++;
            }
            for (int start = 0; start < s.length(); start++) {
                for (int end = start + 1; end <= s.length(); end++) {
                    String str = s.substring(start, end);
                    String rev = reverse(str);
                    if (trie.findWord(str)) {
                        words.add(str);
                    }
                    if (trie.findWord(rev)) {
                        words.add(rev);
                    }
                }

            }
            x = n - 1;
            y = i;
            s = "";
            while (x >= 0 && y < n) {
                s += grille[x].charAt(y);
                x--;
                y++;
            }
            for (int start = 0; start < s.length(); start++) {
                for (int end = start + 1; end <= s.length(); end++) {
                    String str = s.substring(start, end);
                    String rev = reverse(str);

                    if (trie.findWord(str)) {
                        words.add(str);
                    }
                    if (trie.findWord(rev)) {
                        words.add(rev);
                    }
                }
            }

        }
        Collections.sort(words);
        for (String word : words) {
            System.out.println(word);
        }
        System.out.println("La grille contient " + words.size() + " mots du dictionnaire");
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Temps " + duration + " ms");
    }

}
