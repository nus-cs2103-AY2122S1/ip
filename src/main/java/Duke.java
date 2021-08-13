import java.util.*;

public class Duke {
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
        System.out.println(formatReply("Hello beautiful! Welcome to the Abyss\n\t What can I do for you today?"));
        String cmd = sc.nextLine();
        while(!cmd.equalsIgnoreCase("bye")) {
            System.out.println(formatReply(cmd));
            cmd = sc.nextLine();
        }
        System.out.println(formatReply("Bye. Hope to see you again soon!"));
    }

    private static String formatReply(String s) {
        String reply = "\t------------------------------------------------------\n"
                + "\t " + s + "\n\n"
                + "\t------------------------------------------------------";
        return reply;
    }
}
