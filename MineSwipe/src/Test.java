public class Test {
    public static void main(String[] args) {
        Map tester = new Map(Difficulty.EZ);
        tester.mines();
        Display display = new Display(tester);
        display.show();
    }
}
