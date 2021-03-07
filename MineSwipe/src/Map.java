
public class Map {
    private String[][] map;
    private int sizeX;
    private int sizeY;
    private int nrOfMines;

    public Map(int sizeX, int sizeY, int nrOfMines) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.nrOfMines = nrOfMines;

        String[][] map = new String[sizeX][sizeY];
        for(int x = 0; x<sizeX;x++) {
            for (int y = 0; y<sizeY;y++) {
                map[x][y] = "0";
            }
        }

    }

    public String[][] getMap() {
        return map;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getNrOfMines() {
        return nrOfMines;
    }
}



