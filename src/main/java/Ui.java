import java.io.IOException;
import java.util.Scanner;

public class Ui {

    public Ui() {

    }

    public static String introMessage() {
        return "Hello! I'm Joker \n"
                + "What can I do for you?";
    }

    public String getInput() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String input = sc.nextLine();

        return input;
    }
}
