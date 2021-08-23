package duke;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            storage.retrieveFileContents();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    public void run() throws IOException, DukeException {
        Scanner sc = new Scanner(System.in);
        String instruction = sc.nextLine();

        while (!Parser.parseCommand(instruction).equals("bye")) {
            try {
                if (Parser.parseCommand(instruction).equals("list")) {
                    int index = 0;
                    if (TaskList.getCounter() == 0) {
                        System.out.println("\tThere are currently no tasks on your list :)");
                    } else {
                        Parser.parseList();
                        //printFileContents("data/jarvis.txt");
                    }
                } else if (Parser.parseCommand(instruction).equals("done")) {
                    Parser.parseDone(instruction);
                } else if (Parser.parseCommand(instruction).equals("delete")) {
                    Parser.parseDelete(instruction);
                } else if (Parser.parseCommand(instruction).equals("todo")) {
                    Parser.parseTodo(instruction);
                } else if (Parser.parseCommand(instruction).equals("deadline")) {
                    Parser.parseDeadline(instruction);
                } else if (Parser.parseCommand(instruction).equals("event")) {
                    Parser.parseEvent(instruction);
                } else if (Parser.parseCommand(instruction).equals("today")) {
                    Parser.parseToday();
                } else {
                    throw new DukeException("\tOOPS!!! I'm sorry, but I don't " +
                            "know what that means :-(");
                }
            } catch (DukeException | IOException e) {
                System.err.println(e);
            }
            System.out.println("----------------------------------");
            instruction = sc.nextLine();
        }
        System.out.println("\t" + "Bye! Hope to see you soon :)");
        System.out.println("----------------------------------");
    }

    public static void main (String[]args) throws IOException, DukeException {
        new Duke("data/jarvis.txt").run();
    }

    private static void printFileContents (String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println("\t" + s.nextLine());
        }
    }
}


