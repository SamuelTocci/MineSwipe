public class Test {
    public static void main(String[] args) {
        Map tester = new Map(Difficulty.EZ);
        System.out.println(tester.getMap());
        Display display = new Display(tester);
        display.show();
    }
}
