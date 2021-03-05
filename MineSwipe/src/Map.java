import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<String>> map;

    public Map(int sizeX, int sizeY, int nOfMines) {

        map = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < sizeX; i++) {
            map.add(new ArrayList<String>());
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                map.get(i).add("0");
            }
        }
        System.out.print(map);
    }
}



