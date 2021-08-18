import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static int counter = 0;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t Hello! I'm Duke");
        getCommand();
    }

    public static void getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t What would you like me to do?\n");
        String command = scanner.nextLine();
        String first = command.split(" ")[0];

        try {
            for (DukeCommands d : DukeCommands.values()) {
                if (d.command.equals(first)) {
                    d.action.execute(command);
                    break;
                } else if (d.command.equals("invalid")) {
                    DukeCommands.INVALID.action.execute(command);
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            getCommand();
        }
    }
}
