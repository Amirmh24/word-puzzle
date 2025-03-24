import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RobotPanel extends JPanel {
    private final GameFrame gameFrame;
    private final Board board;
    private final int rows, cols;
    private final JLabel label;

    public RobotPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.board = gameFrame.getBoard();
        this.rows = board.getRows();
        this.cols = board.getCols();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension dimension = new Dimension(200, 1000);
        setPreferredSize(dimension);
        label = new JLabel(board.getNotSolvedString());
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 18));

        JButton hint = new JButton("Hint");
        hint.setBackground(Color.white);
        hint.setFont(new Font(hint.getFont().getName(), Font.BOLD, 26));
        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (board.getNotSolved().size() > 0)
                    solve(board.getNotSolved().get(0).toUpperCase());
                repaint();
            }
        });
        add(hint);
        add(label);
    }

    public void solve(String string) {
        ArrayList<int[]> indexes = findChar(string.charAt(0));
        boolean found = false;
        for (int[] index : indexes) {
            for (int i = 1; i <= 8 && !found; i++) {
                Dir dir = new Dir(i);
                try {
                    if (findWord(string, index[0], index[1], dir)) {
                        int l = string.length() - 1;
                        int r1 = index[0], c1 = index[1], r2 = r1 + dir.getR() * l, c2 = c1 + dir.getC() * l;
                        int[] type = Dir.getType(r1, c1, r2, c2);
                        gameFrame.getBoardPanel().update(r1, c1, r2, c2, type);
                        board.getNotSolved().remove(string.toLowerCase());
                        found = true;
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }

    private boolean findWord(String string, int r0, int c0, Dir dir) {
        String found = "";
        int r = r0, c = c0;
        for (int i = 0; i < string.length(); i++) {
            found = found + board.getChars()[r][c];
            r = r + dir.getR();
            c = c + dir.getC();
        }
        return found.toUpperCase().equals(string.toUpperCase());
    }

    public ArrayList<int[]> findChar(char c) {
        ArrayList<int[]> indexes = new ArrayList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (board.getChars()[i][j] == c) {
                    int[] index = {i, j};
                    indexes.add(index);
                }
        return indexes;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        label.setText(board.getNotSolvedString());
    }
}
