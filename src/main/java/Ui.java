import java.util.Scanner;

public class Ui {
    private final Scanner in;

    private static final String DIVIDER = "-----------------------------------------------------------------------------------------";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readFromUser() {
        return in.nextLine();
    }

    public void print(String output) {
        System.out.println(output);
    }

    public void print(String output, Object... args) {
        System.out.printf(output, args);
    }

    public void printDivider() {
        print(DIVIDER);
    }
}
