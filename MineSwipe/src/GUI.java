import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.*;

public class GUI extends JFrame {
    //private GameState m_curState = GameState.TITLE_STATE;
    private Map map;
    private int spacing;
    private int width;
    private int height;
    private int mouseX;
    private int mouseY;
    private int side;

    public GUI(Map map) {
        this.setTitle("MineSwipe");
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)size.getWidth();
        height = (int)size.getWidth();
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.map = map;
        this.spacing = 5;
        this.side = 80;

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);

        Board board = new Board();
        this.setContentPane(board);
    }


    public class Board extends JPanel {
        public void paintComponent(Graphics g) {
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
            g2d.fillRect(0,0,width,height);

            /*Toolkit t=Toolkit.getDefaultToolkit();
            Image i=t.getImage("LOGO.png");
            g.drawImage(i, 0,0,this);*/
            for (int x = 0; x < map.getSizeX(); x++) {
                for (int y = 0; y < map.getSizeY(); y++) {
                    g.setColor(Color.lightGray);
                    if (mouseX >= spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2
                            && mouseX < spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2 +side
                            && mouseY>=spacing + y *side + 1.5*side
                            && mouseY<spacing + y *side + side - 2 * spacing + 1.5*side) {
                        g.setColor(Color.darkGray);
                    }
                    g.fillRect(spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2, spacing + y * side + side, side - 2 * spacing, side - 2 * spacing);
                    if(map.getMap()[x][y].isVisible() == true){
                        g.setColor(Color.white);
                    }
                    if(map.getMap()[x][y].isVisible() == true){
                        g.setColor(Color.black);
                        g.drawString(Integer.toString(map.getMap()[x][y].getValue()),1000,1500);
                    }
                    repaint();
                }
            }
            /*switch(m_curState) {
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
            }*/
        }
    }
/*    public enum GameState {
        TITLE_STATE,
        MAINGAME_STATE,
        DEATH_STATE,
    }*/
/*    public void GameStateUpdate() {
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
    }*/
    public class Move implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }
    public class Click implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
        public int inBoxX(){
            for (int x = 0; x < map.getSizeX(); x++) {
                if(mouseX >= spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2
                        && mouseX < spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2 +side){
                    System.out.println(x);
                    return x;
                }
            }
            return -1;
        }
        public int inBoxY(){
            for (int y = 0; y < map.getSizeY(); y++) {
                if(mouseY>=spacing + y *side + 1.5*side
                        && mouseY<spacing + y *side + side - 2 * spacing + 1.5*side){
                    System.out.println(y);
                    return y;
                }
            }
            return -1;
        }
    }
}