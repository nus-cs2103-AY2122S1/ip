import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                "████████████████████████████████████████\n"+
                "█▄─▄─▀█▄─▄█─▄▄▄▄█─▄▄▄─█▄─██─▄█▄─▄█─▄─▄─█\n" +
                "██─▄─▀██─██▄▄▄▄─█─███▀██─██─███─████─███\n" +
                "▀▄▄▄▄▀▀▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▀▀▄▄▄▀▀\n\n" +

                "      ████████████████████\n" +
                "    ██░░░░░░░░░░░░░░░░░░░░██\n" +
                "  ██░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "  ██░░░░██░░██░░░░██░░██░░░░██\n" +
                "  ██░░████░░░░░░░░░░░░████░░██\n" +
                "    ██  ██░░░░████░░░░██  ██\n" +
                "        ██░░░░░██░░░░░██                  ██\n" +
                "          ██░░░░░░░░██░░████████        ██░░██\n" +
                "        ██░░████████░░░░░░░░░░░░██      ██░░██\n" +
                "      ██░░░░░░░░░░░░░░░░░░░░░░░░░░██  ██░░░░██\n" +
                "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░██\n" +
                "      ██░░░░░░██░░░░░░██░░░░░░░░░░████\n" +
                "████████████████████████████████████████\n";

        String separator = "════════════════════════════════════════";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Woof from\n" + logo);
        System.out.println("Woof! I'm Biscuit.\nWhat can I do for you?\n" + separator);
        boolean isContinue = true;
        ArrayList<String> list = new ArrayList<>();
        while (isContinue) {
            String input = scanner.nextLine();
            System.out.println(separator);
            switch (input) {
                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    break;
                case "bye":
                    System.out.println("Bye! Hope to see you again soon! \uD83D\uDC3E");
                    isContinue = false;
                    break;
                default:
                    System.out.println("added: " + input);
                    list.add(input);
            }
            System.out.println(separator);
        }
    }
}
