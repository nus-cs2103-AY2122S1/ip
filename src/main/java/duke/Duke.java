package duke;

import java.util.Scanner;

import java.io.IOException;
import java.io.FileWriter;

public class Duke {

    static int index = 0; //will figure out a way to make this private :<
    private final String filePath;
    private final TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.filePath = filePath;
        Storage store = new Storage(filePath);
        tasks = new TaskList(store.loadTasks());
        this.ui = new Ui();
    }

    public static void main(String[] args) {

        Duke bot = new Duke("data/Duke.txt");
        Scanner scanObj = new Scanner(System.in);

        bot.ui.printGreeting();

        while(true) {
            String userInput = scanObj.nextLine().trim();
            Parser c = new Parser(bot.tasks, userInput, index);
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
                } else {
                    System.out.println("sorry! i'm not taught that command yet :<");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("sorry! i'm not taught that command yet :<<");
            }
        }
    }
}
