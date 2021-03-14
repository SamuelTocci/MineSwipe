import javax.swing.*;
import java.awt.geom.Point2D;
import java.awt.*;

public class GUI extends JFrame {
    private GameState m_curState = GameState.TITLE_STATE;

    public GUI() {
        this.setTitle("MineSwipe");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);
    }


    public class Board extends JPanel {
        public void paintComponent(Graphics g) {
            switch(m_curState) {
                case TITLE_STATE:
                    Color color1 = new Color(253, 41, 123);
                    Color color2 = new Color(255, 88, 100);
                    Color color3 = new Color(255, 101, 91);

                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;

                    Point2D start = new Point2D.Float(0, 540);
                    Point2D end = new Point2D.Float(1920, 540);
                    float[] dist = {0.0f, 0.5f, 1.0f};
                    Color[] colors = {color1, color2, color3};
                    LinearGradientPaint gradient = new LinearGradientPaint(start, end, dist, colors);
                    g2d.setPaint(gradient);
                    g2d.fillRect(0,0,1920,1080);

                    Toolkit t=Toolkit.getDefaultToolkit();
                    Image i=t.getImage("LOGO.png");
                    g.drawImage(i, 0,0,this);

                case MAINGAME_STATE:
                    UpdateMainGame();
                    break;

                case DEATH_STATE:
                    UpdateDeath();
                    if(UserPressesRetry()) {
                        m_curState = GameState.MAINGAME_STATE;
                    }
                    break;
            }
        }
    }
    public enum GameState {
        TITLE_STATE,
        MAINGAME_STATE,
        DEATH_STATE,
    }
    public void GameStateUpdate() {
        // handle update
        switch(m_curState) {
            case TITLE_STATE:
                UpdateTitleScreen();
                if(UserPressesEnter()) {
                    m_curState = GameState.MAINGAME_STATE;
                }
                break;
            case MAINGAME_STATE:
                UpdateMainGame();
                break;
            case DEATH_STATE:
                UpdateDeath();
                if(UserPressesRetry()) {
                    m_curState = GameState.MAINGAME_STATE;
                }
                break;
        }
    }

    private boolean UserPressesRetry() {
        return false;
    }

    private boolean UserPressesEnter() {
        return false;
    }

    private void UpdateTitleScreen() {
    }
    private void UpdateMainGame() {
    }
    private void UpdateDeath() {
    }

}