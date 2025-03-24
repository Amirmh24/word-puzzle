import java.util.*;

public class Board {
    private static final char empty = '*';
    private final Random random = new Random();
//    private final String[] words = {"apple", "banana", "watermelon", "orange", "pineapple", "coconut", "lemon", "strawberry", "cherry", "melon", "cucumber", "tomato", "potato", "pepper", "peach", "kiwi", "olive", "bean", "eggplant", "apricot", "avocado", "plum", "blueberry", "mulberry", "pea", "nut", "grape", "mango", "pear", "pumpkin"};
    private final String[] words = { "cherry", "melon", "cucumber", "tomato", "potato", "pepper", "peach", "kiwi", "olive"};
    private final int rows = 10;
    private final int cols = 10;
    private final char[][] chars = new char[rows][cols];
    private final ArrayList<String> notSolved = new ArrayList<>();

    public Board() {
        Collections.addAll(notSolved, getWords());
        for (char[] row : chars)
            Arrays.fill(row, empty);
        for (String string : words)
            addWord(string.toUpperCase());
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (chars[i][j] == empty)
                    chars[i][j] = (char) (random.nextInt(26) + 65);
    }

    public void addWord(String string) {
        boolean added = false;
        int[] shuffled = shuffleIndex(string.length());
        for (int i = 0; i < string.length() && !added; i++) {
            ArrayList<int[]> indexes = findChar(string.charAt(shuffled[i]));
            while (true) {
                if (indexes.size() == 0 || random.nextInt(5) == 0) {
                    try {
                        addWord(random.nextInt(rows), random.nextInt(cols), shuffled[i], new Word(string, random.nextInt(8) + 1));
                        added = true;
                        break;
                    } catch (Exception ignored) {
                    }
                } else {
                    try {
                        int[] index = indexes.get(random.nextInt(indexes.size()));
                        addWord(index[0], index[1], shuffled[i], new Word(string, random.nextInt(8) + 1));
                        added = true;
                        break;
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    private static int[] shuffleIndex(int length) {
        Integer[] indexes = new Integer[length];
        for (int i = 0; i < length; i++)
            indexes[i] = i;
        List<Integer> list = Arrays.asList(indexes);
        Collections.shuffle(list);
        list.toArray(indexes);
        int[] output = new int[length];
        for (int i = 0; i < length; i++)
            output[i] = indexes[i];
        return output;
    }

    public void addWord(int row, int col, int x, Word word) throws Exception {
        int i = row - x * word.getDir().getR();
        int j = col - x * word.getDir().getC();
        int k = 0;
        boolean same = true;
        while (k < word.length()) {
            if (chars[i][j] == empty || chars[i][j] == word.charAt(k)) {
                if (chars[i][j] == empty)
                    same = false;
                k += 1;
                i += word.getDir().getR();
                j += word.getDir().getC();
            } else throw new Exception("This cell is not empty");
        }
        if (same) throw new Exception("This cell is not empty");
        i = row - x * word.getDir().getR();
        j = col - x * word.getDir().getC();
        k = 0;
        while (k < word.length()) {
            chars[i][j] = word.charAt(k);
            k += 1;
            i += word.getDir().getR();
            j += word.getDir().getC();
        }
    }

    public ArrayList<int[]> findChar(char c) {
        ArrayList<int[]> indexes = new ArrayList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (chars[i][j] == c) {
                    int[] index = {i, j};
                    indexes.add(index);
                }
        return indexes;
    }

    public void print() {
        for (char[] row : chars)
            System.out.println(Arrays.toString(row).replaceAll(",", ""));
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char[][] getChars() {
        return chars;
    }

    public String[] getWords() {
        return words;
    }

    public ArrayList<String> getNotSolved() {
        return notSolved;
    }

    public String getNotSolvedString() {
        String output = "<html>";
        for (String string : notSolved)
            output = ""+output + string + "<br/>";
        return output+"<html>";
    }
}
