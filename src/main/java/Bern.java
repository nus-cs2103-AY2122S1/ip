import java.util.Scanner;
import java.util.ArrayList;

public class Bern {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public enum Command {
        DONE, DEADLINE, EVENT, TODO, BYE, LIST, DELETE, INVALID
    }

    public Bern() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser();
    }

    public void run() {
        Scanner myObj = new Scanner(System.in);

        ArrayList<Task> arListTask = new ArrayList<>();

        new Storage().initialiseArListTask(arListTask);

        System.out.println("Hi! I'm Bern, your trustworthy chatbot.\nWhat can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            ui.processInput(input, arListTask);
            if (parser.isBye(input)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Bern().run();
    }
}
