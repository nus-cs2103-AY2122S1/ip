import java.util.Scanner;

public class Duke {

//    private Storage storage;
    private TaskList tasks;
    enum CommandEnum {
        ADD,
        LIST,
        EXIT
    }
//    private Ui ui;

    public Duke(TaskList tasks) {
        this.tasks = tasks;
    }

    public static CommandEnum inputConverter(String input) {
        if (input.equals("list")) {
            return CommandEnum.LIST;
        } else if (input.equals("bye")) {
            return CommandEnum.EXIT;
        } else {
            return CommandEnum.ADD;
        }
    }

    public void run() {
        Welcome.of().exec();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Boolean isRunning = true;
        System.out.println("What can I do for you?");
        while (isRunning) {
            input = scanner.nextLine();
            switch (Duke.inputConverter(input)) {
                case ADD:
                    Add.of(this.tasks, input).exec();
                    break;
                case LIST:
                    List.of(this.tasks).exec();
                    break;
                case EXIT:
                    isRunning = false;
                    Exit.of().exec();
                    break;
            }
        }
    }

    public static void main(String[] args) {

        new Duke(new TaskList()).run();
    }
}
