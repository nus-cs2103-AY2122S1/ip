import java.util.Scanner;

public class Ui {

    private static String template = "~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printMessage(String message) {
        System.out.println(template + "\n" + message + template);
    }

    public void showWelcome() {
        String startText = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(startText);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
