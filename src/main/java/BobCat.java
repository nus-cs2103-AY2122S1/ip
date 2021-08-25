import java.io.IOException;
import java.util.Scanner;

import BobCat.exception.BobCatException;
import BobCat.executor.ExecutionUnit;
import BobCat.view.Ui;

/**
 * Main entrypoint to the BobCat Chat Application. Attempts to follow the MVC pattern
 */
public class BobCat {
    public static void main(String[] args) throws IOException {
        Scanner scanObj = new Scanner(System.in);
        Ui ui = new Ui();
        ExecutionUnit executor = new ExecutionUnit("memory/taskList.txt");

        ui.respond(new String[]{"Hello! I'm BobCat!", "Trying to remember what happened..."});
        try {
            executor.initStorage();
        } catch (IOException e) {
            ui.respond(new String[]{"Memory file not found! Starting from blank state..."});
        } catch (BobCatException e) {
            ui.respond(new String[]{"Memory may have been corrupted! Starting from blank state..."});
            executor.clearStorage();
        } finally {
            ui.respond(new String[] {"Initialisation done!", "What can I do for you?"});
        }

        String inp = "";
        while(!inp.equals("bye")) {
            inp = scanObj.nextLine();
            try {
                ui.respond(executor.executeCommand(inp));
            } catch (BobCatException e) {
                ui.respondError(e);
            }
        }
        scanObj.close();
    }
}
