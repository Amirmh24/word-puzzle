import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {
    private static final ImageIcon imageNull = new ImageIcon("null.png");
    private static final ImageIcon image1 = new ImageIcon("1.png");
    private static final ImageIcon image2 = new ImageIcon("2.png");
    private static final ImageIcon image3 = new ImageIcon("3.png");
    private static final ImageIcon image4 = new ImageIcon("4.png");
    private final GameFrame gameFrame;
    private final Board board;
    private final int rows, cols;
    private final JButton[][] buttons;
    private int r1 = -1, c1 = -1, r2 = -1, c2 = -1;


    public BoardPanel(GameFrame gameFrame) {
        this.gameFrame=gameFrame;
        this.board = gameFrame.getBoard();
        rows = board.getRows();
        cols = board.getCols();
        buttons = new JButton[rows][cols];
        setLayout(new GridLayout(rows, cols));
        Dimension dimension = new Dimension(1200, 1000);
        setPreferredSize(dimension);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton("" + board.getChars()[i][j], imageNull);
                buttons[i][j].setBackground(Color.white);
                buttons[i][j].setFont(new Font(buttons[i][j].getFont().getName(), Font.BOLD, 26));
                buttons[i][j].setHorizontalTextPosition(JButton.CENTER);
                buttons[i][j].setVerticalTextPosition(JButton.CENTER);
                int I = i, J = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (r1 == -1) {
                            r1 = I;
                            c1 = J;
                        } else {
                            r2 = I;
                            c2 = J;
                            try {
                                checkWord(r1, c1, r2, c2);
                            } catch (Exception ignored) {
                            }
                            resetCLick();
                        }
                    }
                });
                add(buttons[i][j]);
            }
    }

    public void checkWord(int r1, int c1, int r2, int c2) throws Exception {
        int[] type = Dir.getType(r1, c1, r2, c2);
        String string = "";
        if (type == null)
            throw new Exception("Invalid click");
        int r = r1, c = c1;
        while (r != r2 || c != c2) {
            string = string + board.getChars()[r][c];
            r = r + type[0];
            c = c + type[1];
        }
        string = (string + board.getChars()[r][c]).toLowerCase();
        if (board.getNotSolved().contains(string)) {
            System.out.println("***" + string + "***");
            update(r1, c1, r2, c2, type);
            board.getNotSolved().remove(string);
            gameFrame.getRobotPanel().repaint();
        } else System.out.println(string);
    }

    public void update(int r1, int c1, int r2, int c2, int[] type) {
        int r = r1, c = c1;
        ImageIcon imageIcon = getImageType(type);
        while (r != r2 || c != c2) {
            buttons[r][c].setIcon(imageIcon);
            r = r + type[0];
            c = c + type[1];
        }
        buttons[r][c].setIcon(imageIcon);
    }



    private static ImageIcon getImageType(int[] type) {
        if (type[1] == 0) return image1;
        else if (type[0] == 0) return image2;
        else if (type[0] == type[1]) return image3;
        else if (type[0] == -type[1]) return image4;
        return imageNull;
    }

    private void resetCLick() {
        r1 = -1;
        c1 = -1;
        r2 = -1;
        c2 = -1;
    }
}
