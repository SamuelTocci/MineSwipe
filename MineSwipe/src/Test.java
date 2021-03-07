public class Test {
    public static void main(String[] args) {

    }
    Map tester = new Map(8, 8,0);

    for(int x = 0; x<tester.getSizeX(); x++) {
        for (int y = 0; y<tester.getSizeY();y++) {
                System.out.print(tester.getMap()[x][y]);
        }
    }

}
