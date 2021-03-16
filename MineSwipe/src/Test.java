public class Test {
    public static void main(String[] args) {
        Map tester = new Map(Map.Difficulty.MEDIUM);
        Display display = new Display(tester);
        display.show();
        GUI gui = new GUI(tester);
    }
}
