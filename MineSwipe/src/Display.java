public class Display {
    private Map map;

    public Display(Map map){
        this.map = map;
    }

    public void show(){
        for (int y = 0; y < map.getSizeY(); y++) {
            System.out.println();
           for (int x = 0; x < map.getSizeX(); x++) {
                if (false){
                    System.out.print("@");
                }
                else{
                    System.out.print(map.check(x,y));
                }
            }
        }
    }

}

