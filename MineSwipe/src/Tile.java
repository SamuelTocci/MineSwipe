public class Tile {
    private boolean isVisible;
    private int value;

    public Tile() {
        this.isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isBomb(){
        return false;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
    }
}
