import java.util.Scanner;
public class Duke {
    private static TaskList tList = new TaskList();
    private static String selfIntro = "Hello, I'm Duck\nWhat do you need?";

    private static String goodBye = "See ya next time! *quack* *quack* *quack*";

    private static String logo =
            "⠀⠀⠀⠀⠀⠀⠀⣠⡀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠤⠤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢀⣾⣟⠳⢦⡀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠉⠉⠉⠉⠉⠒⣲⡄\n" +
            "⠀⠀⠀⠀⠀⣿⣿⣿⡇⡇⡱⠲⢤⣀⠀⠀⠀⢸⠀⠀⠀1984⠀⣠⠴⠊⢹⠁\n" +
            "⠀⠀⠀⠀⠀⠘⢻⠓⠀⠉⣥⣀⣠⠞⠀⠀⠀⢸⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⢸⠀\n" +
            "⠀⠀⠀⠀⢀⣀⡾⣄⠀⠀⢳⠀⠀⠀⠀⠀⠀⢸⢠⡄⢀⡴⠁⠀⠀⠀⠀⠀⡞⠀\n" +
            "⠀⠀⠀⣠⢎⡉⢦⡀⠀⠀⡸⠀⠀⠀⠀⠀⢀⡼⣣⠧⡼⠀⠀⠀⠀⠀⠀⢠⠇⠀\n" +
            "⠀⢀⡔⠁⠀⠙⠢⢭⣢⡚⢣⠀⠀⠀⠀⠀⢀⣇⠁⢸⠁⠀⠀⠀⠀⠀⠀⢸⠀⠀\n" +
            "⠀⡞⠀⠀⠀⠀⠀⠀⠈⢫⡉⠀⠀⠀⠀⢠⢮⠈⡦⠋⠀⠀⠀⠀⠀⠀⠀⣸⠀⠀\n" +
            "⢀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⡀⣀⡴⠃⠀⡷⡇⢀⡴⠋⠉⠉⠙⠓⠒⠃⠀⠀\n" +
            "⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⡼⠀⣷⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⡞⠀⠀⠀⠀⠀⠀⠀⣄⠀⠀⠀⠀⠀⠀⡰⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⢧⠀⠀⠀⠀⠀⠀⠀⠈⠣⣀⠀⠀⡰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";

    private static void greet() {
        System.out.println(logo);
        printLine(selfIntro);
    }

    private static void sayBye() {
        printLine(goodBye);
    }

    public static void printLine(String content) {
        System.out.println(
                "------------------------------------------------\n"
                + "Duck says:\n"
                + content
                + "\n------------------------------------------------"
            );

    }

    public static void runDuck() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        Duke.greet();
        boolean bye = false;
        while (!bye) {
            userInput = sc.nextLine();
            switch(userInput)
            {
                case "bye":
                    bye = true;
                    Duke.sayBye();
                    break;
                case "list":
                    printLine(tList.getTasks());
                    break;
                default:
                    printLine(tList.addTask(userInput));
            }
        }
    }

    public static void main(String[] args) {
        runDuck();
    }
}
