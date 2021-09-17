package bobcat;

import java.io.IOException;
import java.util.Scanner;

import bobcat.exception.BobCatException;
import bobcat.executor.ExecutionUnit;
import bobcat.view.TextUi;

/**
 * Main entrypoint to the BobCat.BobCat Chat Application. Attempts to follow the MVC pattern
 */
public class BobCat {
    /**
     * Provides entrypoint to the BobCat chat app
     */
    public static void main(String[] args) throws IOException {
        Scanner scanObj = new Scanner(System.in);
        TextUi textUi = new TextUi();
        ExecutionUnit executor = new ExecutionUnit("memory/taskList.txt");

        textUi.respond(new String[]{"Hello! I'm BobCat.BobCat!", "Trying to remember what happened..."});
        try {
            executor.initStorage();
        } catch (IOException e) {
            textUi.respond(new String[]{"Memory file not found! Starting from blank state..."});
        } catch (BobCatException e) {
            textUi.respond(new String[]{"Memory may have been corrupted! Starting from blank state..."});
            executor.clearStorage();
        } finally {
            textUi.respond(new String[] {"Initialisation done!", "What can I do for you?"});
        }

        String inp = "";
        while (!inp.equals("bye")) {
            inp = scanObj.nextLine();
            try {
                textUi.respond(executor.executeCommand(inp));
            } catch (BobCatException e) {
                textUi.respondError(e);
            }
        }
        scanObj.close();
    }
}
