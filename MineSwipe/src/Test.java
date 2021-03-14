public class Test {
    public static void main(String[] args) {
        Map tester = new Map(Difficulty.EZ);
        Display display = new Display(tester);
        System.out.println(tester.resolve(1,2));
        display.show();
        display.showVisibility();

    }
}
