import java.util.Scanner;
public class Duke {
    private static TaskList tList = new TaskList();
    private static String selfIntro = "Hello, I'm Duck\nWhat do you need?";

    private static String goodBye = "See ya next time! *quack* *quack* *quack*";

    private static String logo =
            "    __\n" +
            "___( o)>\n" +
            "\\ <_. )\n" +
            " `---'   hjw";

    private static void greet() {
        //System.out.println(logo);
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
        String[] inputArr;
        while (!bye) {
            userInput = sc.nextLine();
            inputArr = userInput.split(" ");
            if (inputArr.length == 1) {
                switch (inputArr[0]) {
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
            } else {
                switch (inputArr[0]) {
                    case "done":
                        try {
                            printLine(
                                    tList.markComplete(
                                            Integer.parseInt(inputArr[1])
                                    )
                            );
                        } catch (NumberFormatException e) {
                            printLine("Bad command. *quack*");
                        }
                        break;
                    default:
                        printLine(tList.addTask(userInput));
                }
            }
        }
    }

    public static void main(String[] args) {
        runDuck();
    }
}
