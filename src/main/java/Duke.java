import java.util.Scanner;

public class Duke {
    private static String wrapOutput(String s) {
        return "\n\t@Herb:~$ " + s + "\n";
    }

    public static void main(String[] args) {
        String welcomeMessage = "\n\tHi! I'm Herbert, you can call me Herb  ٩(˘◡˘)۶\n"
                + "\tHow can I help you?\n\n"
                + "\tSay `bye` to end this chat.\n";
        String endMessage = "\n\tSad to see you go :(\n\t...shutting down...";

        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("--> ");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(endMessage);
                break;
            } else {
                System.out.println(wrapOutput(input));
            }
        }
    }
}
