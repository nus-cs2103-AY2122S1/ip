package duke;

import java.util.Scanner;

/**
 *  This is my attempt at Duke!
 *  I have sought help from peers like Charlton Tan, Wilbur Fong and Ng Jia Yuan.
 * @author A0222594A
 *
 */

public class Duke {

    private  UI ui;
    private  Parser parser;
    private TaskList list;
    private final Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new UI();
        Data data = new Data(filePath);
        list = new TaskList(data.loadData(), data);
        parser = new Parser(list, data);
    }


    /**
     * Calls the appropriate methods depending on what the user has input.
     */
    public void start() {
        boolean isRunning = true;
        UI.greet();
        String input = sc.nextLine();
        Command command = parser.inputToCommand(input);
        while (isRunning) {
            switch (command) {
            case BYE:
                isRunning = false;
                ui.exit();
                break;
            case LIST:
                TaskList.showList();
                break;
            case DEADLINE:
                try {
                    parser.addDeadline(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DELETE:
                try {
                    TaskList.delete(input);
                    TaskList.update();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    parser.addEvent(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case TODO:
                try {
                    parser.addTodo(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DONE:
                try {
                    TaskList.markDone(input);
                    TaskList.update();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                //If no cases above are entered,Duke will not understand the command and prompt the user.
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (isRunning) {
                input = sc.nextLine();
                command = parser.inputToCommand(input);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/data.txt");
        duke.start();
    }
}
