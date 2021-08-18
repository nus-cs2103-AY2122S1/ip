import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    private static final String LINEBREAK = "____________________________________________________________";
    List<String> commands = new ArrayList<String>();

    private final Map<String, Consumer<String>> FUNCTIONS = new HashMap<>() {
        {
            put("list", (input) -> list());
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
        for (int i = 0; i < commands.size(); i++) {
            System.out.printf(" %s. %s\n", i + 1, commands.get(i));
        }
        System.out.println(LINEBREAK);
    }

    private void addTask(String task) {
        print(" SO YOU WANT TO: " + task);
        commands.add(task);
    }

    private void run() {
        greet();
        Scanner input = new Scanner(System.in);

        while (true) {
            String inputString = input.nextLine().trim();

            if (inputString.equals("bye")) {
                print(" LIVE OUT YOUR PATHETIC LIFE, WEAKLING.");
                break;
            } else {
                FUNCTIONS.getOrDefault(inputString, (task) -> addTask(task)).accept(inputString);
            }
            System.out.print("WHAT ELSE DO YOU WANT, INSECT?\n ");
        }
        input.close();
    }

    public static void main(String[] args) {
        Duke Squirtle = new Duke();
        Squirtle.run();
    }
}
