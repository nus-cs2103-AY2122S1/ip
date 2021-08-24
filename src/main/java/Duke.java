import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke(String fileLocation) {
        try {
            Storage storage = new Storage(fileLocation);
            tasks = new TaskList(storage);
        } catch (IOException e) {
            System.out.println("Something went wrong with accessing " + fileLocation);
        } catch (FileParseException e) {
            System.out.println(fileLocation + " is not of the proper format. "
                    + "Either modify it to the right format or delete the file.");
        }
        ui = new Ui(tasks);
    }

    public void run() {
        ui.start();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
