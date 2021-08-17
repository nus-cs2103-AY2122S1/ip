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

        String separator =  "\n▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂\n";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Woof from\n" + logo);
        System.out.println("Woof! I'm Biscuit.\nWhat can I do for you?" + separator);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon! \uD83D\uDC3E");
                break;
            } else {
                System.out.println(separator + input + separator);
            }
        }
    }
}
