import java.util.Scanner;

public class Session {

    private final static String SEPARATOR = "_____________________________________________________________________";
    private final static String GREETING = "Hello! I'm Jaden\nHow Are You? It's Been A While Since We Last Spoke.";
    private final static String GOODBYE_OUTPUT = "Each Day Brings Me Further Away From Our Past And Closer To The Truth. #Bye";

    private Scanner input;
    private TaskList taskList;

    public Session() {
        this.input = new Scanner(System.in);
        this.taskList = new TaskList(100);
        this.greet();

        this.handleInputs();
    }

    private void handleInputs() {
        inputLoop: while (true) {
            if (this.input.hasNext()) {
                String currInput = this.input.nextLine();
                switch (currInput) {
                    case "bye":
                        this.bye();
                        break inputLoop;
                    case "list":
                        this.taskList.listTasks();
                        break;
                    default:
                        this.taskList.addTask(currInput);
                }
            }
        }
    }

    public static void output(String s) {
        System.out.println(SEPARATOR);
        System.out.println(s);
        System.out.println(SEPARATOR);
    }

    private void greet() {
        output(GREETING);
    }

    private void bye() {
        output(GOODBYE_OUTPUT);
    }
}
