import java.util.Scanner;

public class Duke {

//    private Storage storage;
    private TaskList tasks;

//    private Ui ui;

    public Duke(TaskList tasks) {
        this.tasks = tasks;
    }


    public void run() {
        Welcome.of().exec();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("What can I do for you?");
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                List.of(this.tasks).exec();
            } else if (input.startsWith("done")) {
                int value = Character.getNumericValue(input.charAt(5));
                Done.of(tasks, value).exec();
            } else {
                Add.of(this.tasks, input).exec();
            }
        }
        Exit.of().exec();
    }

    public static void main(String[] args) {

        new Duke(new TaskList()).run();
    }
}
