package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke class
 * Uses storage class to gather data from text file
 * When run, parses input into parser class which utilizes ui class to give an output
 * After each command it saves to the storage text file
 */
public class Duke {

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("C:\\Users\\65906\\IdeaProjects\\ip\\duke.txt").run();
    }

    /**
     * Starts the duke bot
     *
     * @throws IOException
     */
    private void run() throws IOException {
        ui.showWelcome();
        Parser duke = new Parser(this.tasks);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            ui.showLine();
            String str = sc.nextLine();
            duke.parse(str);
            ui.showLine();
            storage.save(tasks);
        }


    }


}
