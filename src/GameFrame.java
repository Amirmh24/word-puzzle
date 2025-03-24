import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final BoardPanel boardPanel;
    private final RobotPanel robotPanel;
    private final Board board;
    public GameFrame() throws HeadlessException {
        Dimension dimension = new Dimension(1400, 1000);
        setSize(dimension);
        setResizable(false);
        setLayout(new BorderLayout());
        board = new Board();
        boardPanel = new BoardPanel(this);
        robotPanel = new RobotPanel(this);
        add(boardPanel, BorderLayout.WEST);
        add(robotPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public RobotPanel getRobotPanel() {
        return robotPanel;
    }

    public Board getBoard() {
        return board;
    }
}
