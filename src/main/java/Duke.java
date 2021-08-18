import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    private static final String LINEBREAK = "____________________________________________________________";
    List<Task> tasks = new ArrayList<Task>();

    private final Map<String, Consumer<String>> FUNCTIONS = new HashMap<>() {
        {
            put("list", (input) -> list());
            put("done", (input) -> markDone(input));
            put("todo", (input) -> addToDo(input));
            put("deadline", (input) -> addDeadline(input));
            put("event", (input) -> addEvent(input));
        }
    };

    private void print(String s) {
        System.out.printf("%s\n%s\n%s\n", LINEBREAK, s, LINEBREAK);
    }

    private void greet() {
        System.out.print("               _,........__\n");
        System.out.print("            ,-'            \"`-.\n");
        System.out.print("          ,'                   `-.\n");
        System.out.print("        ,'                        \\\n");
        System.out.print("      ,'                           .\n");
        System.out.print("      .'\\               ,\"\".       `\n");
        System.out.print("     ._.'|             / |  `       \\\n");
        System.out.print("     |   |            `-.'  ||       `.\n");
        System.out.print("     |   |            '-._,'||       | \\\n");
        System.out.print("     .`.,'             `..,'.'       , |`-.\n");
        System.out.print("     l                       .'`.  _/  |   `.\n");
        System.out.print("     `-.._'-   ,          _ _'   -\" \\  .     `\n");
        System.out.print("`.\"\"\"\"\"'-.`-...,---------','         `. `....__.\n");
        System.out.print(".'        `\"-..___      __,'\\          \\  \\     \\\n");
        System.out.print("\\_ .          |   `\"\"\"\"'    `.           . \\     \\\n");
        System.out.print("  `.          |              `.          |  .     L\n");
        System.out.print("    `.        |`--...________.'.        j   |     |\n");
        System.out.print("      `._    .'      |          `.     .|   ,     |\n");
        System.out.print("         `--,\\       .            `7\"\"' |  ,      |\n");
        System.out.print("            ` `      `            /     |  |      |    _,-'\"\"\"`-.\n");
        System.out.print("             \\ `.     .          /      |  '      |  ,'          `.\n");
        System.out.print("              \\  v.__  .        '       .   \\    /| /              \\\n");
        System.out.print("               \\/    `\"\"\\\"\"\"\"\"\"\"`.       \\   \\  /.''                |\n");
        System.out.print("                `        .        `._ ___,j.  `/ .-       ,---.     |\n");
        System.out.print("                ,`-.      \\         .\"     `.  |/        j     `    |\n");
        System.out.print("               /    `.     \\       /         \\ /         |     /    j\n");
        System.out.print("              |       `-.   7-.._ .          |\"          '         /\n");
        System.out.print("              |          `./_    `|          |            .     _,'\n");
        System.out.print("              `.           / `----|          |-............`---'\n");
        System.out.print("                \\          \\      |          |\n");
        System.out.print("               ,'           )     `.         |\n");
        System.out.print("                7____,,..--'      /          |\n");
        System.out.print("                                  `---.__,--.'\n");
        System.out.println(
                " BOW BEFORE ME, FOR I AM SQUIRTLE, DESTROYER OF MEN, TAKER OF LIVES.\n THE GODS FEARED MY EXISTENCE, SO THEY BANISHED ME TO YOUR MORTAL REALM TO SAVE YOUR MISERABLE LIFE.");
        System.out.println(LINEBREAK);
        System.out.print("SO WHAT DO YOU WANT DO, INSECT?\n ");
    }

    private void list() {
        System.out.println(LINEBREAK);
        System.out.println("YOU WISH FOR THESE FOOLISH THINGS:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(" %s. %s\n", i + 1, tasks.get(i));
        }
        System.out.println(LINEBREAK);
    }

    private void markDone(String input) {
        int index = Integer.parseInt(input);
        Task currentTask = tasks.get(index - 1);
        if (currentTask.getIsDone()) {
            print("YOU ALREADY DID THIS YOU FOOL.");
        }
        currentTask.setIsDone(true);
        print("YOU SAY YOU'VE COMPLETED THIS TASK:\n " + currentTask);
    }

    private void addToDo(String taskName) {
        Task newTask = new ToDo(taskName);
        tasks.add(newTask);
        print(" MORTAL, YOU'VE ADDED THIS TASK: " + newTask);
    }

    private void addEvent(String input) {
        String[] args = input.split("/at", 2);
        Task newEvent = new Event(args[0].trim(), args[1].trim());
        tasks.add(newEvent);
        print(" MORTAL, YOU'VE ADDED THIS EVENT: " + newEvent);
    }

    private void addDeadline(String input) {
        String[] args = input.split("/by", 2);
        Task newEvent = new Deadline(args[0].trim(), args[1].trim());
        tasks.add(newEvent);
        print(" MORTAL, YOU'VE ADDED THIS DEADLINE: " + newEvent);
    }

    private void serve() {
        greet();
        Scanner input = new Scanner(System.in);

        while (true) {
            // Extract first word as command
            String inputString = input.nextLine().trim();
            String[] cmd = inputString.split(" ", 2);

            if (inputString.equals("bye")) {
                print(" LIVE OUT YOUR PATHETIC LIFE, WEAKLING.");
                break;
            } else {
                FUNCTIONS.getOrDefault(cmd[0], (task) -> addToDo(inputString)).accept(cmd.length > 1 ? cmd[1] : "");
            }
            System.out.print("WHAT ELSE DO YOU WANT, INSECT?\n ");
        }
        input.close();
    }

    public static void main(String[] args) {
        Duke Squirtle = new Duke();
        Squirtle.serve();
    }
}
