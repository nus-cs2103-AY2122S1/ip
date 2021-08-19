import java.util.Scanner;

public class Pilcrow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        while (!text.equals("bye")) {
            text = "You entered: \n" + text;
            System.out.println(text);
            text = scanner.nextLine();
        }
        String logo = "  _____\n"
                + " /   | |\n"
                + "|    | |\n"
                + "|    | |\n"
                + " \\___| |\n"
                + "     | |\n"
                + "     | |\n"
                + "    _| |_\n"
                + "   |_|_|_|\n";
        System.out.println("C'est fini.\n" + logo);
    }
}
