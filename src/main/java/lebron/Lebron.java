package lebron;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lebron.task.Deadline;
import lebron.task.Events;
import lebron.task.Task;
import lebron.task.TaskList;
import lebron.task.ToDo;


/**
 * This class represents the chat bot.
 *
 * @author Nigel Tan
 */

public class Lebron {

    final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    public static final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Available commands that the bot can understand.
     */
    public enum Command {
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find"),
        OTHER(" ");

        private String text;

        Command(String text) {
            this.text = text;
        }

        /**
         * Creates a Command enum value from a string.
         *
         * @param text the text.
         * @return the desired Command enum.
         */
        public static Command fromString(String text) {
            for (Command c : Command.values()) {
                if (c.text.equalsIgnoreCase(text)) {
                    return c;
                }
            }
            return OTHER;
        }
    }

    /**
     * Constructor for the chatbot.
     *
     * @throws IOException if stream to file is missing or invalid.
     */
    public Lebron() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        try {
            ArrayList<Task> loadList = storage.loadFileContents(FILE_PATH);
            taskList = new TaskList(loadList);
        } catch (Exception e) {
            ArrayList<Task> loadList = new ArrayList<>();
            taskList = new TaskList(loadList);
        }
    }

    /**
     * Runs the bot.
     *
     * @throws IOException if stream to file is missing or invalid.
     */
    public void run() throws IOException {
        int position = 0;
        ui.greet();
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        while (!text.equals("bye")) {
            String commandWord = parser.parseText(text);
            String[] splitWords = parser.split(text);
            Command command = Command.fromString(commandWord);
            switch (command) {
            case LIST:
                ui.replyDisplay(taskList);
                break;
            case DONE:
                int pos = Integer.parseInt(splitWords[1]);
                taskList.markDone(pos - 1);
                storage.saveToFile(taskList.getLst());
                break;
            case TODO:
                try {
                    taskList.add(new ToDo(splitWords[1]));
                    storage.saveToFile(taskList.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE
                            + "    :( OOPS! The description of a todo cannot be empty.\n"
                            + HORIZONTAL_LINE);
                }
                break;
            case DEADLINE:
                try {
                    String[] splitBy = splitWords[1].split("/by ", 2);
                    taskList.add(new Deadline(splitBy[0], splitBy[1]));
                    storage.saveToFile(taskList.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE
                            + "    :( OOPS! The description or a time of a deadline cannot be empty.\n"
                            + HORIZONTAL_LINE);
                }
                break;
            case EVENT:
                try {
                    String[] splitAt = splitWords[1].split("/at ", 2);
                    taskList.add(new Events(splitAt[0], splitAt[1]));
                    storage.saveToFile(taskList.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE
                            + "    :( OOPS! The description or a time of an event cannot be empty.\n"
                            + HORIZONTAL_LINE);
                }
                break;
            case DELETE:
                int pos2 = Integer.parseInt(splitWords[1]);
                taskList.delete(pos2 - 1);
                storage.saveToFile(taskList.getLst());
                break;
            case FIND:
                String keyword = splitWords[1];
                ui.replyFind(taskList, keyword);
                break;
            case OTHER:
                System.out.println(HORIZONTAL_LINE
                        + "    :( OOPS! I'm sorry, but I don't know what that means.\n"
                        + HORIZONTAL_LINE);
                break;
            default:
                break;
            }
            text = sc.nextLine();
        }
        ui.exit();
    }

    public static void main(String[] args) throws Exception {
        new Lebron().run();
    }


}
