//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RungeKutta a = new RungeKutta(1000, 0.12, 6);
        a.draw();
    }
}