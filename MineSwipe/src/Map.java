import java.util.Random;

public class Map {
    private String[][] map;
    private int sizeX;
    private int sizeY;
    private int nrOfMines;

    // initializing and creating map with allemaal "0"
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

    //getters en setters
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

    //functionality
    public String check(int xPos, int yPos){
        return map[xPos][yPos];
    }
    /*
    * zet mines op het veld
    * */
    public void mines(){
        Random random = new Random();
        for(int i = 0; i<nrOfMines+1;i++){
            int randX = random.nextInt(sizeX);
            int randY = random.nextInt(sizeY);
            if (map[randX][randY] != "B"){
                map[randX][randY] = "B";
            }
            else{i--;}
        }
    }
    /*
    * als startplek op bom is, bom verplaatsen naar links boven
    * */
    public void startMove(int posX, int posY){
        if(map[posX][posY]=="B"){
            map[posX][posY]= "0";
            for(int x = 0; x<sizeX;x++) {
                for (int y = 0; y<sizeY;y++) {
                    if(map[x][y] == "0"){
                        map[x][y]= "B";
                        break;
                    }
                }
            }
        }

    }
    /*
    * zet nummers op de map
    * */
    public void preSolve(){
        for(int x = 0; x<sizeX;x++) {
            for (int y = 0; y<sizeY;y++) {
                if(map[x][y] != "B"){
                    int bcount = 0;
                    for(int a = -1; a<1;a++) {
                        for (int b = -1; y<1;b++) {
                            if(a != 0 && b !=0){
                                if(map[x+a][x+b]=="B"){
                                    bcount++;
                                    map[x][y]= Integer.toString(bcount);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}