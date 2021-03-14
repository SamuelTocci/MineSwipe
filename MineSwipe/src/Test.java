public class Test {
    public static void main(String[] args) {
        Map tester = new Map(Difficulty.EZ);
        Display display = new Display(tester);
        tester.resolve(1,2);
        display.show();
        display.showVisibility();
        GUI gui = new GUI();
    }
}
