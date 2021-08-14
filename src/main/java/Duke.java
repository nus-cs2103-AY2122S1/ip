import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String wrapOutput(String s) {
        // Align list items properly
        // Adapted regex from https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java

        String mstr = s.replaceAll("(?m)^", "\t\t\t ");
        return "\n\t@Herb:~$" + mstr.substring(3) + "\n";
    }

    public static void main(String[] args) {
        String welcomeMessage = "\n\tHi! I'm Herbert, you can call me Herb  ٩(˘◡˘)۶\n"
                + "\tHow can I help you?\n\n"
                + "\tYou can type:\n"
                + "\t\t `list` to get a list of input texts\n"
                + "\t\t `bye` to end this chat.\n";
        String endMessage = "\n\tSad to see you go :(\n\t...shutting down...";

        ArrayList<String> texts = new ArrayList<>();

        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("--> ");
            String input = sc.nextLine();
            if (input.equals("list")) {
                if  (texts.size() == 0) {
                    System.out.println(wrapOutput("No texts added yet!"));
                    continue;
                }
                String res = "";
                for (int i = 0; i < texts.size(); i++) {
                    res += (i + 1) + ". " + texts.get(i) + "\n";
                }
                System.out.println(wrapOutput(res.substring(0, res.length() - 1)));
            } else if (input.equals("bye")) {
                System.out.println(endMessage);
                break;
            } else {
                texts.add(input);
                System.out.println(wrapOutput("Added: " + input));
            }
        }
    }
}
