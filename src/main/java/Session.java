import java.util.Scanner;

public class Session {

    private final static String SEPARATOR = "_____________________________________________________________________";
    private final static String GREETING = "Hello! I'm Jaden\nHow Are You? It's Been A While Since We Last Spoke.";
    private final static String GOODBYE_OUTPUT = "Each Day Brings Me Further Away From Our Past And Closer To The Truth. #Bye";

    private Scanner input;
    private TaskList taskList;
    private boolean isActive;

    public Session() {
        this.input = new Scanner(System.in);
        this.taskList = new TaskList(100);
        this.isActive = true;
        this.greet();
        this.listenForInputs();
    }

    private void listenForInputs() {
        while (this.isActive) {
            if (this.input.hasNext()) {
                String currInput = this.input.nextLine();
                this.handleInput(currInput);
            }
        }
    }

    private void handleInput(String currInput) {
        String[] splitInput = currInput.split(" ");
        switch(splitInput[0]) {
            case "bye":
                this.bye();
                break;
            case "list":
                this.taskList.listTasks();
                break;
            default:
                if(splitInput[0].equals("done")) {
                    int taskIndex = Integer.parseInt(currInput.split(" ")[1]);
                    this.taskList.markAsDone(taskIndex);
                } else {
                    this.taskList.addTask(currInput);
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
        this.isActive = false;
    }
}
