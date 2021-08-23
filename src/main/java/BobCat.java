import java.io.IOException;
import java.util.Scanner;

import exception.BobCatException;
import executor.ExecutionUnit;
import view.Ui;

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
            ui.respondError("Memory may have been corrupted! Starting from blank state...");
            executor.clearStorage();
        } finally {
            ui.respond(new String[] {"Initialisation done!", "What can I do for you?"});
        }

        String inp = "";
        while(!inp.equals("bye")) {
            inp = scanObj.nextLine();
            try {
                String[] results = executor.executeCommand(inp);
                ui.respond(results);
            } catch (BobCatException e) {
                ui.respondError(e.getMessage());
            }
        }
        scanObj.close();
    }
}
