import java.util.Scanner;

public class Duke {
    private static String[] list = new String[100];
    private static int index = 0;

    public static void main(String[] args) {
        String logo =
                "  ('-.    .-. .-')                .-')     .-')   \n"
                +  "  ( OO ).-.\\  ( OO )              ( OO ).  ( OO ). \n"
                +  "  / . --. / ;-----.\\  ,--.   ,--.(_)---\\_)(_)---\\_)\n"
                +  "  | \\-.  \\  | .-.  |   \\  `.'  / /    _ | /    _ | \n"
                +  ".-'-'  |  | | '-' /_).-')     /  \\  :` `. \\  :` `. \n"
                +  " \\| |_.'  | | .-. `.(OO  \\   /    '..`''.) '..`''.)\n"
                +  "  |  .-.  | | |  \\  ||   /  /\\_  .-._)   \\.-._)   \\\n"
                +  "  |  | |  | | '--'  /`-./  /.__) \\       /\\       /\n"
                +  "  `--' `--' `------'   `--'       `-----'  `-----' \n";
        System.out.println(logo);

        Scanner sc = new Scanner(System.in);
        System.out.println(formatReply("Hello beautiful. Welcome to the Abyss.", "What can we do for you today?"));
        String cmd = sc.nextLine();
        while(!cmd.equalsIgnoreCase("exit")) {
            if(cmd.equalsIgnoreCase("list")) {
                printList();
            } else if (!cmd.isBlank()) {
                addToList(cmd);
            }
            cmd = sc.nextLine();
        }
        System.out.println(formatReply("Exiting the Abyss. We anticipate your return."));
    }

    private static void addToList(String s) {
        list[index] = s;
        index++;
        String reply = "added: " + s;
        System.out.println(formatReply(reply));
    }

    private static void printList() {
        System.out.println(formatListReply(list));
    }

    private static String formatReply(String ...s) {
        String reply = "\t------------------------------------------------------\n";
        for(int i = 0; i < s.length; i++) {
            if(s[i] == null) {
                break;
            }
            reply += "\t " + s[i] + "\n";
        }
        reply += "\n\t------------------------------------------------------";
        return reply;
    }

    private static String formatListReply(String ...s) {
        String reply = "\t------------------------------------------------------\n";
        for(int i = 0; i < s.length; i++) {
            if(s[i] == null) {
                break;
            }
            reply += "\t " + (i + 1) +". " + s[i] + "\n";
        }
        if(s[0] == null) {
            reply += "\t Nothing added yet.\n";
        }
        reply += "\n\t------------------------------------------------------";
        return reply;
    }
}
