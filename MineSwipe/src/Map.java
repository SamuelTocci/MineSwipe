import java.util.Random;

public class Map {
    private Tile[][] map;
    private int sizeX;
    private int sizeY;
    private int nrOfMines;

    // initializing and creating map with allemaal "0"
    public Map(Difficulty difficulty) {
        switch (difficulty) {
            case HARD:
                this.sizeX = 16;
                this.sizeY = 30;
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

            Tile[][] map = new Tile[sizeX][sizeY];
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    map[x][y] = new NrTile();
                }
            }

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

        public int getNrOfMines () {
            return nrOfMines;
        }

        //functionality
        public Tile check ( int xPos, int yPos){
            return map[xPos][yPos];
        }
        /*
         * zet mines op het veld
         * */
        public void mines () {
            Random random = new Random();
            for (int i = 0; i < nrOfMines + 1; i++) {
                int randX = random.nextInt(sizeX);
                int randY = random.nextInt(sizeY);
                if (!map[randX][randY].isBomb()) {
                    map[randX][randY] = new BombTile();
                } else {
                    i--;
                }
            }
        }
        /*
         * als startplek op bom is, bom verplaatsen naar links boven
         * */
        public void startMove ( int posX, int posY){
            if (map[posX][posY].isBomb()) {
                map[posX][posY] = new NrTile();
                for (int x = 0; x < sizeX; x++) {
                    for (int y = 0; y < sizeY; y++) {
                        if (!map[x][y].isBomb()) {
                            map[x][y] = new BombTile();
                            break;
                        }
                    }
                }
            }

        }
        /*
         * zet nummers op de map
         * */
        public void preSolve () {
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    if (!map[x][y].isBomb()) {
                        int bcount = 0;
                        for (int a = -1; a < 1; a++) {
                            for (int b = -1; y < 1; b++) {
                                    if (map[x + a][x + b].isBomb()) {
                                        bcount++;
                                        map[x][y].setValue(bcount);
                                    }
                            }
                        }
                    }
                }
            }
        }
}