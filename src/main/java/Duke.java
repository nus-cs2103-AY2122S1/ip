import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        boolean running = true;

        while (running) {
            System.out.print("Command >> ");
            String command = sc.nextLine();

            switch (command) {
                case "bye":
                    System.out.println(dukeSays("¡Adiós! See you soon!"));
                    running = false;
                    break;
                case "list":
                    tm.list();
                    break;
                default:
                    tm.add(new Task(command));
                    System.out.println(dukeSays("added: " + command));
            }
        }
    }

    private static String dukeSays(String str) {
        return "Duke says >> " + str + "\n";
    }
}

