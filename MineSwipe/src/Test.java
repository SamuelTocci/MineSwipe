public class Test {
    public static void main(String[] args) {
        Map tester = new Map(Difficulty.MEDIUM);
        Display display = new Display(tester);
        tester.resolve(1,2);
        display.show();
        GUI gui = new GUI(tester);
    }
}
