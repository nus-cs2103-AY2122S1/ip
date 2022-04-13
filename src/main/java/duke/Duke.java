package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Duke is an application to help store and track tasks.
 * Users are able to add and delete various types of tasks such as deadlines, events, and things to do.
 * Duke can also list the existing tasks and mark tasks as done.
 */

@SuppressWarnings("checkstyle:Regexp")
public class Duke {

    private int index = 0;
    private final String filePath;
    private final TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke object.
     *
     * @param filePath
     */

    public Duke(String filePath) {
        this.filePath = filePath;
        Storage store = new Storage(filePath, this);
        tasks = new TaskList(store.loadTasks());
        this.ui = new Ui();
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Main function to handle the flow of CLI application
     * Technically not needed since there is the GUi, but I will be keeping this anyway
     *
     * @param args
     */

    public static void main(String[] args) {
        Duke bot = new Duke("data/Duke.txt");
        Scanner scanObj = new Scanner(System.in);

        bot.ui.printGreeting();

        while (true) {
            String userInput = scanObj.nextLine().trim();
            Parser c = new Parser(bot.tasks, userInput, bot.getIndex(), bot);
            String[] splitInput = userInput.split(" ");
            try {
                if (userInput.equals("bye")) {
                    c.bye_execute();
                    try {
                        FileWriter fw = new FileWriter(bot.filePath);
                        fw.write(c.generateTasks());
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("unable to write to file :<");
                    } finally {
                        break;
                    }
                } else if (userInput.equals("list")) {
                    c.list_execute();
                } else if (splitInput[0].equals("done")) {
                    c.done_execute();
                } else if (splitInput[0].equals("todo")) {
                    c.todo_execute();
                } else if (splitInput[0].equals("event")) {
                    c.event_execute();
                } else if (splitInput[0].equals("deadline")) {
                    c.deadline_execute();
                } else if (splitInput[0].equals("delete")) {
                    c.delete_execute();
                } else if (splitInput[0].equals("find")) {
                    c.find_execute();
                } else {
                    System.out.println("sorry! i'm not taught that command yet :<");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("sorry! i'm not taught that command yet :<<");
            }
        }
    }

    public String getResponse(String input) {
        Duke bot = new Duke("data/Duke.txt");

        bot.ui.printGreeting();

        Parser c = new Parser(bot.tasks, input, bot.getIndex(), bot);
        String[] splitInput = input.split(" ");
        try {
            if (input.equals("bye")) {
                return c.bye_execute();
            } else if (input.equals("list")) {
                return c.list_execute();
            } else if (splitInput[0].equals("done")) {
                return c.done_execute();
            } else if (splitInput[0].equals("todo")) {
                return c.todo_execute();
            } else if (splitInput[0].equals("event")) {
                return c.event_execute();
            } else if (splitInput[0].equals("deadline")) {
                return c.deadline_execute();
            } else if (splitInput[0].equals("delete")) {
                return c.delete_execute();
            } else if (splitInput[0].equals("find")) {
                return c.find_execute();
            } else if (input.equals("stattask")) {
                return c.statTaskExecute();
            } else if (input.equals("statdone")) {
                return c.statDoneExecute();
            } else if (input.equals("statnotdone")) {
                return c.statNotDoneExecute();
            } else {
                return "sorry! i'm not taught that command yet :<";
            }
        } catch (IndexOutOfBoundsException e) {
            return "sorry! i'm not taught that command yet :<<";
        }
    }
}
