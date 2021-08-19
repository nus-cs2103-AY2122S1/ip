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
        ParsedInput parsedInput = null;
        try {
            parsedInput = new ParsedInput(currInput);
        } catch (JadenInputException e) {
            output(e.getMessage());
            return;
        }
        switch (parsedInput.commandType) {
            case TODO:
                this.taskList.addTask(new ToDoTask(parsedInput.taskDescription));
                break;
            case DEADLINE:
                this.taskList.addTask(new DeadlineTask(parsedInput.taskDescription, parsedInput.deadline));
                break;
            case EVENT:
                this.taskList.addTask(new EventTask(parsedInput.taskDescription, parsedInput.eventPeriod));
                break;
            case LIST:
                this.taskList.listTasks();
                break;
            case DONE:
                this.taskList.markAsDone(parsedInput.taskIndex);
                break;
            case BYE:
                this.bye();
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
