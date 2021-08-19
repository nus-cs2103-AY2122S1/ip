import java.util.ArrayList;
import java.util.Scanner;

public class Pilcrow {
    public static void main(String[] args) {
        ArrayList<String> textHistory = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                System.out.println(Pilcrow.printHistory(textHistory));
            } else {
                textHistory.add(text);
                text = "Added: \n" + text;
                System.out.println(text);
            }
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

    public static String printHistory(ArrayList<String> history) {
        String historyText = "";
        for (int i = 0; i < history.size(); i++) {
            historyText += (i + 1) + ". " + history.get(i) + "\n";
        }
        return historyText;
    }
}