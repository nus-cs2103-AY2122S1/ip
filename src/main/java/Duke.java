import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the chat bot.
 *
 * @author Nigel Tan
 */

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private Response response;
    final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    public enum Command {
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        OTHER(" ");

        private String text;

        Command(String text) {
            this.text = text;
        }

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
     * @throws IOException
     */
    public Duke() throws IOException {
        storage = new Storage(FILE_PATH);
        try {
            ArrayList<Task> taskList = storage.loadFileContents(FILE_PATH);
            response = new Response(taskList);
        } catch (Exception e) {
            ArrayList<Task> taskList = new ArrayList<>();
            response = new Response(taskList);
        }
    }

    public void run() throws IOException {
        int position = 0;
        response.greet();
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        while (!text.equals("bye")) {
            String[] splitWords = text.split(" ", 2);
            String commandWord = splitWords[0];
            Command command = Command.fromString(commandWord);
            switch (command) {
            case LIST:
                response.display();
                break;
            case DONE:
                int pos = Integer.parseInt(splitWords[1]);
                response.markDone(pos - 1);
                storage.saveToFile(response.getLst());
                break;
            case TODO:
                try {
                    response.add(new ToDo(splitWords[1]));
                    storage.saveToFile(response.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE +
                            "    :( OOPS! The description of a todo cannot be empty.\n" +
                            HORIZONTAL_LINE);
                }
                break;
            case DEADLINE:
                try {
                    String[] splitBy = splitWords[1].split("/by ", 2);
                    response.add(new Deadline(splitBy[0], splitBy[1]));
                    storage.saveToFile(response.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE +
                            "    :( OOPS! The description or a time of a deadline cannot be empty.\n" +
                            HORIZONTAL_LINE);
                }
                break;
            case EVENT:
                try {
                    String[] splitAt = splitWords[1].split("/at ", 2);
                    response.add(new Events(splitAt[0], splitAt[1]));
                    storage.saveToFile(response.getLst());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(HORIZONTAL_LINE +
                            "    :( OOPS! The description or a time of an event cannot be empty.\n" +
                            HORIZONTAL_LINE);
                }
                break;
            case DELETE:
                int pos2 = Integer.parseInt(splitWords[1]);
                response.delete(pos2 - 1);
                storage.saveToFile(response.getLst());
                break;
            case OTHER:
                System.out.println(HORIZONTAL_LINE +
                        "    :( OOPS! I'm sorry, but I don't know what that means.\n" +
                        HORIZONTAL_LINE);
                break;
            }
            text = sc.nextLine();
        }
        response.exit();
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }


}
