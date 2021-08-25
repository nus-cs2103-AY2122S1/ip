package duke;

/**
 * The Bhutu chatbot app
 */
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws DukeException{
        Ui.greet();

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        boolean exit = false;

        Storage storage = Storage.initStorage("data/", "data/duke/txt");
        storage.readFile(taskList);

        //Echo
        while (!exit) {
            String input = scanner.nextLine();

            Ui.divider();
            exit = Parser.parser(input, taskList);
            storage.saveToFile(taskList);
            Ui.divider();
        }
    }

}

