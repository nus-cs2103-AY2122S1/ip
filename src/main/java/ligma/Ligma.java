package ligma;

import ligma.command.Command;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.IOException;
//handle date time exception somewhere
//System.out.println(PARTITION
//                    +"\n Time must be in yyyy-mm-dd format.\n" + PARTITION);
// String print = t == TaskType.DEADLINE
//                            ? "for deadlines using '/by'"
//                            : "for events using '/at'";
//            System.out.println(PARTITION
//                    + String.format("\n Time must be stipulated %s\n", print)
//                    + PARTITION);

public class Ligma {

    private static Storage storage;
    private TaskList tasks;

    public Ligma(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.printErrorMessage("Failed to load in tasks.");
        }
    }

    public void run() {
        Ui.introduceSelf();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                Command c = Parser.parseCommand(sc.nextLine());
                c.execute(tasks, storage);
                if (c.isExit()) break;
            } catch (InputMismatchException | NoSuchMethodException e1) {
                Ui.printErrorMessage(e1);
            } catch (DateTimeParseException e2) {
                Ui.printErrorMessage("Time must be in yyyy-mm-dd format.");
            }
        }
    }

}
