public class Display {
    private Map map;
    private String[][] visible;
    public Display(Map map){
        this.map = map;
        this.visible = new String[map.getSizeX()][map.getSizeY()];
        for(int x = 0; x<map.getSizeX();x++) {
            for (int y = 0; y<map.getSizeY();y++) {
                visible[x][y]= "*";
            }
        }
    }
    /*
    * het teken '*' gebruiken we als iets niet visible is, voor zichtbare dingen gebruiken we de waarde zelf
    * (0-8,B of F)
    * */
    public void setVisible(int xPos, int yPos){
        visible[xPos][xPos]=map.check(xPos,yPos);
    }

}
