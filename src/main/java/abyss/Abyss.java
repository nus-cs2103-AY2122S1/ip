package abyss;

import java.util.Scanner;

public class Abyss {
    private static Task[] list = new Task[100];
    private static int count = 0;

    public static void main(String[] args) {
        printLogo();

        Scanner sc = new Scanner(System.in);
        reply("Hello beautiful. Welcome to the Abyss.", "What can we do for you today?");
        String cmd = sc.nextLine();
        while (!cmd.equalsIgnoreCase("exit")) {
            if (cmd.equalsIgnoreCase("list")) {
                list();
            } else if (cmd.matches("done \\d+")) {
                String index = cmd.substring(5);
                int i = Integer.parseInt(index);
                if (i <= count) {
                    markAsDone(i);
                } else {
                    addToList(cmd);
                }
            } else if (!cmd.isBlank()) {
                addToList(cmd);
            }
            cmd = sc.nextLine();
        }
        reply("Exiting the Abyss. We anticipate your return.");
    }

    private static void addToList(String s) {
        list[count] = new Task(s);
        count++;
        String reply = "added: " + s;
        System.out.println(formatReply(reply));
    }

    private static void reply(String... s) {
        System.out.println(formatReply(s));
    }

    private static void list() {
        System.out.println(formatListReply(list));
    }

    private static void markAsDone(int i) {
        Task task = list[i - 1];
        task.markAsDone();
        String markedTask = "  " + task.toString();
        System.out.println(formatReply("Task is swallowed by the Abyss.", markedTask));
    }

    private static String formatReply(String... s) {
        String reply = "\t......................................................\n";
        for (int i = 0; i < s.length; i++) {
            if (s[i] == null) {
                break;
            }
            reply += "\t " + s[i] + "\n";
        }
        reply += "\n\t......................................................";
        return reply;
    }

    private static String formatListReply(Task[] list) {
        String reply = "\t......................................................\n";
        for (int i = 0; i < list.length; i++) {
            Task task = list[i];
            if (task == null) {
                break;
            }
            reply += "\t " + (i + 1) + "." + task.toString() + "\n";
        }
        if (list[0] == null) {
            reply += "\t Nothing added yet.\n";
        }
        reply += "\n\t......................................................";
        return reply;
    }

    private static void printLogo() {
        String logo =
                "  ('-.    .-. .-')                .-')     .-')   \n"
                        + "  ( OO ).-.\\  ( OO )              ( OO ).  ( OO ). \n"
                        + "  / . --. / ;-----.\\  ,--.   ,--.(_)---\\_)(_)---\\_)\n"
                        + "  | \\-.  \\  | .-.  |   \\  `.'  / /    _ | /    _ | \n"
                        + ".-'-'  |  | | '-' /_).-')     /  \\  :` `. \\  :` `. \n"
                        + " \\| |_.'  | | .-. `.(OO  \\   /    '..`''.) '..`''.)\n"
                        + "  |  .-.  | | |  \\  ||   /  /\\_  .-._)   \\.-._)   \\\n"
                        + "  |  | |  | | '--'  /`-./  /.__) \\       /\\       /\n"
                        + "  `--' `--' `------'   `--'       `-----'  `-----' \n";
        System.out.println(logo);
    }
}
