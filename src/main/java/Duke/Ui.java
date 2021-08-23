package Duke;

public class Ui {

    public Ui() {

    }

    private void respondWith(String input) {
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(input);
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.print("Enter command: ");
    }
}
