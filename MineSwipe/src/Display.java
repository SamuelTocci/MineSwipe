public class Display {
    private Map map;

    public Display(Map map){
        this.map = map;
    }

    public void show(){
        for (int y = 0; y < map.getSizeY(); y++) {
            System.out.println();
           for (int x = 0; x < map.getSizeX(); x++) {
                if (map.getMap()[x][y].isBomb()){
                    System.out.print("@" + " ");
                }
                else{
                    System.out.print(map.getMap()[x][y].getValue() + " ");
                }
            }
        }
    }
    public void showVisibility(){
        for (int y = 0; y < map.getSizeY(); y++) {
            System.out.println();
            for (int x = 0; x < map.getSizeX(); x++) {
                System.out.print(map.getMap()[x][y].isVisible() + " ");
            }
        }
    }

}

