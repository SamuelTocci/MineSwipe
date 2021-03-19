import java.util.Random;

public class Map {
    private Tile[][] map;
    private int sizeX;
    private int sizeY;
    private int nrOfMines;
    private int moves;
    private int flagCount;
    public enum Difficulty {
        HARD,
        MEDIUM,
        EZ
    }

    // initializing and creating map with allemaal "0"
    public Map(Difficulty difficulty) {
        moves = 0;
        switch (difficulty) {
            case HARD:
                this.sizeX = 30;
                this.sizeY = 16;
                this.nrOfMines = 99;
                break;
            case MEDIUM:
                this.sizeX = 16;
                this.sizeY = 16;
                this.nrOfMines = 40;
                break;
            case EZ:
                this.sizeX = 8;
                this.sizeY = 8;
                this.nrOfMines = 10;
                break;
            }

            map = new Tile[sizeX][sizeY];
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    map[x][y] = new NrTile();
                }
            }
        /*
         * zet mines op het veld
         * */
        Random random = new Random();
        for (int i = 0; i < nrOfMines; i++) {
            int randX = random.nextInt(sizeX);
            int randY = random.nextInt(sizeY);
            if (!map[randX][randY].isBomb()) {
                map[randX][randY] = new BombTile();
            } else {
                i--;
            }
        }
        flagCount = 0;

    }

        //getters en setters
        public Tile[][] getMap () {
            return map;
        }

        public int getSizeX () {
            return sizeX;
        }

        public int getSizeY () {
            return sizeY;
        }

        public int getNrOfMines () {return nrOfMines; }

        public int getFlagCount () {
        return flagCount;
    }

        /*
         * als startplek op bom is, bom verplaatsen naar links boven
         * */
        public void startMove ( int posX, int posY){
            if(nrOfMines>((sizeX*sizeY)/2)) {
                if (map[posX][posY].isBomb()) {
                    map[posX][posY] = new NrTile();
                    boolean placed = false;
                    while (!placed) {
                        for (int x = 0; x < sizeX; x++) {
                            for (int y = 0; y < sizeY; y++) {

                                if (!map[x][y].isBomb() && !(x == posX && y == posY)) {
                                    map[x][y] = new BombTile();
                                    placed = true;
                                }
                            }
                        }
                    }
                }
            }
            else {

                for (int a = -1; a <= 1; a++) {
                    for (int b = -1; b <= 1; b++) {
                        if (posX + a >= 0 && posY + b >= 0 && posX + a < sizeX && posY + b < sizeY) {
                            if (map[posX + a][posY + b].isBomb()) {
                                map[posX + a][posY + b] = new NrTile();
                                boolean placed = false;
                                while (!placed) {
                                    Random random = new Random();
                                    int randX = random.nextInt(sizeX);
                                    int randY = random.nextInt(sizeY);

                                    if (!map[randX][randY].isBomb() && ((randX - posX > 1 || randX - posX < -1) && (randY - posY > 1 || randY - posY < -1))) {
                                        map[randX][randY] = new BombTile();
                                        placed = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            preSolve();
            visualizer(posX,posY);

        }
        /*
         * zet nummers op de map
         * */
        public void preSolve () {

            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    if (map[x][y].isBomb()) {
                        for (int a = -1; a <= 1; a++) {
                            for (int b = -1; b <= 1; b++) {
                                if (x + a >= 0 && y + b >=   0 && x + a < sizeX && y + b < sizeY) {
                                    if (!map[x + a][y + b].isBomb()) {
                                        map[x + a][y + b].setValue(map[x + a][y + b].getValue() + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    /*
     * wordt opgeroepen bij elke zet en doet al het werk om te bepalen wat er bij de zet moet gebeuren
     * */
    public boolean resolve(int x, int y){

            if (moves == 0){
                moves++;
                startMove(x,y);
                return false;
            }
            else {
                moves++;
                if (map[x][y].isBomb()) {
                    return true;
                }
                visualizer(x, y);
                return false;
            }
    }

    /*
     * maakt de zone rondom de klik zichtbaar
     * */
    public void visualizer(int x,int y){
        if(map[x][y].getValue() == 0 && !map[x][y].isVisible()){
            map[x][y].setVisible(true);
            for (int a = -1; a <= 1; a++) {
                for (int b = -1; b <= 1; b++) {
                    if (x + a >= 0 && y + b >=   0 && x + a < sizeX && y + b < sizeY) {
                        visualizer(x + a, y + b);
                    }
                }
            }
        }
        else{
            map[x][y].setVisible(true);
        }
        if(map[x][y].isFlagged()){
            map[x][y].toggleFlagged();
            flagCount--;
        }
    }

    public boolean flag(int posX, int posY){
        if (moves>1 && !map[posX][posY].isVisible()) {
            if (map[posX][posY].isFlagged()) {
                flagCount--;
                map[posX][posY].toggleFlagged();
                return false;
            }
            if (nrOfMines > flagCount) {
                flagCount++;
                map[posX][posY].toggleFlagged();
            }

            if (flagCount == nrOfMines) {
                return hasWon();
            }
        }
        return false;
    }

    public boolean hasWon(){
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if(map[x][y].isBomb() && !map[x][y].isFlagged()){
                    return false;
                }
                if(!map[x][y].isBomb() && map[x][y].isFlagged()){
                    return false;
                }
            }
        }
        return true;
    }
}