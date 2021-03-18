import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    //private GameState m_curState = GameState.TITLE_STATE;
    private Map map;
    private int spacing;
    private int width;
    private int height;
    private int mouseX;
    private int mouseY;
    private int side;
    public enum State {
        START_MENU,
        PLAY,
        DEATH,
        WON
    }
    private State gState;
    private Font tinderFont;
    private Image icon;
    private Image bombImage;
    private Image flagImage;
    private Image mainImage;

    public GUI() {
        this.setTitle("MineSwipe");
        icon = Toolkit.getDefaultToolkit().getImage("res\\LOGO.png");
        setIconImage(icon);
        Dimension size= Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)size.getWidth();
        height = (int)size.getHeight();
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.spacing = 5;
        this.side = 80;
        bombImage = icon.getScaledInstance(side/2, side/2, Image.SCALE_SMOOTH);
        flagImage = Toolkit.getDefaultToolkit().getImage("res\\Flag.png").getScaledInstance(side/3*2, side/3*2, Image.SCALE_SMOOTH);
        mainImage = icon.getScaledInstance(3*side, 3*side, Image.SCALE_SMOOTH);
        try {
            tinderFont = Font.createFont(Font.TRUETYPE_FONT, new File("res\\Tinder.ttf")).deriveFont(side/4*3f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(tinderFont);
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }
        this.gState = State.START_MENU;

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);

        Board board = new Board();
        this.setContentPane(board);
    }
    public void setgState(State s){
        gState = s;
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
            switch(gState){
                case START_MENU:
                    g.setColor(Color.pink);
                    g.fillRoundRect(width/2-4*side-2*spacing, height/2, 4*side, 2*side,side/4,side/4);
                    g.fillRoundRect(width/2 + 2*spacing, height/2, 4*side, 2*side,side/4,side/4);
                    g.fillRoundRect(width/2 - 2*side, height/2 - 2*side - 4*spacing, 4*side, 2*side,side/4,side/4);
                    g.drawImage(mainImage, width/2-120,height/6 - 2*side - 4*spacing,this);

                    g.setColor(Color.white);
                    g.setFont(tinderFont);
                    g.drawString("MINESWIPE",width/2 -185, height/4);

                    g.setColor(Color.lightGray);
                    if(mouseX >= width/2-4*side && mouseX < width/2-4*side+4*side && mouseY >= height/2 +side/4+2*spacing && mouseY < height/2+2*side+side/4+2*spacing) {
                        g.fillRoundRect(width/2-4*side-2*spacing, height/2, 4*side, 2*side,side/4,side/4);
                    }
                    if(mouseX >= width/2 + 4*spacing && mouseX < width/2+4*side + 4*spacing && mouseY >= height/2 +side/4+2*spacing && mouseY < height/2+2*side+side/4+2*spacing) {
                        g.fillRoundRect(width/2 + 2*spacing, height/2, 4*side, 2*side,side/4,side/4);
                    }
                    if(mouseX >= width/2 - 2*side+spacing && mouseX < width/2 - 2*side+4*side+2*spacing && mouseY >= height/2-2*side+2*spacing && mouseY < height/2+side/4-spacing) {
                        g.fillRoundRect(width/2 - 2*side, height/2 - 2*side - 4*spacing, 4*side, 2*side,side/4,side/4);
                    }
                    g.setColor(Color.black);
                    g.drawString(Map.Difficulty.EZ.toString(),width/2-5*side/2-2*spacing,height/2+side/3*4);
                    g.drawString(Map.Difficulty.MEDIUM.toString(),width/2+side/2-spacing,height/2+side/3*4);
                    g.drawString(Map.Difficulty.HARD.toString(),width/2-side-2*spacing,height/2-side);

                    repaint();
                    break;
                case PLAY:
                    for (int x = 0; x < map.getSizeX(); x++) {
                        for (int y = 0; y < map.getSizeY(); y++) {
                            g.setColor(Color.darkGray);
                            g.fillRoundRect(spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2, spacing + y * side + side, side - 2 * spacing, side - 2 * spacing,side/4, side/4);
                            if(!map.getMap()[x][y].isVisible()){
                                if (mouseX >= spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2
                                        && mouseX < spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2 +side
                                        && mouseY>=spacing + y *side + 1.5*side
                                        && mouseY<spacing + y *side + side - 2 * spacing + 1.5*side) {
                                    g.setColor(Color.lightGray);
                                    g.fillRoundRect(spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2, spacing + y * side + side, side - 2 * spacing, side - 2 * spacing,side/4, side/4);
                                }
                            }
                            if(map.getMap()[x][y].isVisible()){
                                g.setColor(Color.pink);
                                g.fillRoundRect(spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2, spacing + y * side + side, side - 2 * spacing, side - 2 * spacing,side/4, side/4);
                            }
                            if(!map.getMap()[x][y].isVisible()){
                                if(map.getMap()[x][y].isFlagged()){
                                    g.setColor(color2);
                                    g.drawImage(flagImage, spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2+side/8,spacing + y * side + side+side/4*3-side/16-side/2-side/16,this);

                                }
                            }
                            if(map.getMap()[x][y].isVisible() && map.getMap()[x][y].getValue() != 0){
                                g.setColor(Color.black);
                                g.setFont(tinderFont);
                                g.drawString(Integer.toString(map.getMap()[x][y].getValue()),spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2 + side/4,spacing + y * side + side+side/4*3-side/16);
                            }
                            repaint();
                        }
                    }
                    break;
                case DEATH:
                    g2d.setPaint(gradient);
                    g2d.fillRect(0,0,width,height);
                    for (int x = 0; x < map.getSizeX(); x++) {
                        for (int y = 0; y < map.getSizeY(); y++) {
                            g.setColor(Color.pink);
                            g.fillRoundRect(spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2, spacing + y * side + side, side - 2 * spacing, side - 2 * spacing,side/4, side/4);
                            if(map.getMap()[x][y].getValue() != 0){
                                g.setColor(Color.black);
                                g.setFont(tinderFont);
                                g.drawString(Integer.toString(map.getMap()[x][y].getValue()),spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2 + side/4,spacing + y * side + side+side/4*3-side/16);
                            }
                            if(map.getMap()[x][y].isBomb()){
                                g.drawImage(bombImage, spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2+side/5,spacing + y * side + side+side/4*3-side/16-side/2-side/16 + side/16,this);
                            }

                        }
                    }
                    g.setColor(Color.white);
                    g.setFont(tinderFont);
                    g.drawString("GAME OVER",width/2 -200, side/4*3);
                    repaint();
                    break;

                case WON:
                    g2d.setPaint(gradient);
                    g2d.fillRect(0,0,width,height);
                    g.setColor(Color.white);
                    g.setFont(tinderFont);
                    g.drawString("YOU WON",width/2 -200, side/4*3);
                    for (int x = 0; x < map.getSizeX(); x++) {
                        for (int y = 0; y < map.getSizeY(); y++) {
                            g.setColor(Color.pink);
                            g.fillRoundRect(spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2, spacing + y * side + side, side - 2 * spacing, side - 2 * spacing,side/4, side/4);
                            if(map.getMap()[x][y].getValue() != 0){
                                g.setColor(Color.black);
                                g.setFont(tinderFont);
                                g.drawString(Integer.toString(map.getMap()[x][y].getValue()),spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2 + side/4,spacing + y * side + side+side/4*3-side/16);
                            }
                            if(map.getMap()[x][y].isBomb()){
                                g.drawImage(bombImage, spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2+side/5,spacing + y * side + side+side/4*3-side/16-side/2,this);
                            }

                        }
                    }
                    repaint();
                    break;

            }
        }
    }

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
    public class Click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {


        }

        @Override
        public void mousePressed(MouseEvent e) {
            switch (gState) {
                case PLAY:
                    if (inBoxX(mouseX) != -1 && inBoxY(mouseY) != -1) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            map.resolve(inBoxX(mouseX), inBoxY(mouseY));
                            if (map.hasWon()) {
                                setgState(State.WON);
                            }
                            if (map.resolve(inBoxX(mouseX), inBoxY(mouseY))) {
                                setgState(State.DEATH);
                            }
                        }
                        if (SwingUtilities.isRightMouseButton(e)) {
                            map.flag(inBoxX(mouseX), inBoxY(mouseY));
                        }
                    }
                    if(map.hasWon()){
                        gState = State.WON;
                    }
                    break;
                case START_MENU:
                    if (inBoxX(mouseX) == 1 && inBoxY(mouseY) == 1) {
                        map = new Map(Map.Difficulty.EZ);
                        gState = State.PLAY;
                    }
                    if (inBoxX(mouseX) == 2 && inBoxY(mouseY) == 1) {
                        map = new Map(Map.Difficulty.MEDIUM);
                        gState = State.PLAY;
                    }
                    if (inBoxX(mouseX) == 3 && inBoxY(mouseY) == 2) {
                        map = new Map(Map.Difficulty.HARD);
                        gState = State.PLAY;
                    }
                    break;
                case DEATH:
                    gState = State.START_MENU;
                    break;
            }
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

        public int inBoxX(int mX) {
            switch (gState) {
                case PLAY:
                    for (int x = 0; x < map.getSizeX(); x++) {
                        if (mX >= spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2
                                && mX < spacing + x * side + width / 2 - map.getSizeX() / 2 * side - spacing / 2 + side) {
                            return x;
                        }
                    }
                case START_MENU:
                    if (mouseX >= width/2-4*side && mouseX < width/2-4*side+4*side) {
                        return 1;
                    }
                    if (mouseX >= width/2 + 4*spacing && mouseX < width/2+4*side + 4*spacing){
                        return 2;
                        }
                    if (mouseX >= width/2 - 2*side+spacing && mouseX < width/2 - 2*side+4*side+2*spacing){
                        return 3;
                    }
            }
            return -1;
        }

        public int inBoxY(int mY) {
            switch (gState) {
                case PLAY:
                    for (int y = 0; y < map.getSizeY(); y++) {
                        if (mY >= spacing + y * side + 1.5 * side && mY < spacing + y * side + side - 2 * spacing + 1.5 * side) {
                            return y;
                        }
                    }
                case START_MENU:
                    if (mouseY >= height/2 +side/4+2*spacing && mouseY < height/2+2*side+side/4+2*spacing) {
                        return 1;
                    }
                    if (mouseY >= height/2-2*side+2*spacing && mouseY < height/2+side/4-spacing) {
                        return 2;
                    }
            }
            return -1;
        }
    }
}