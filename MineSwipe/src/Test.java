public class Test {
    public static void main(String[] args) {
        Map tester = new Map(Difficulty.EZ);
        Display display = new Display(tester);
        display.show();
        GUI gui = new GUI(tester);
    }
}
