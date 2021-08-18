import java.util.Scanner;

public class InputHandler {
    private final Scanner sc;

    public InputHandler() {
        sc = new Scanner(System.in);
    }
    public String getInput() {
        return sc.nextLine();
    }
}
