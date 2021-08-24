package duke;

import java.util.Scanner;

/**
 *  This is my attempt at DukePakage.Duke.
 * @author Zachary Lau --> I have sought feedback and suggestions from Charlton Tan, Wilbur Fong and Jia Yuan.
 *
 */

public class Duke {

    private  UI ui;
    private  Parser parser;
    private  ToDoList list;
    private final Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new UI();
        Data data = new Data(filePath);
        list = new ToDoList(data.loadData(), data);
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
                    ToDoList.showList();
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
                        ToDoList.delete(input);
                        ToDoList.update();
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
                        ToDoList.markDone(input);
                        ToDoList.update();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case FIND:
                    try {
                        parser.find(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    //If no cases above are entered, DukePakage.Duke will not understand the command and prompt the user.
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
