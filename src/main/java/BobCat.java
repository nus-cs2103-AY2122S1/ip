import java.io.IOException;
import java.util.Scanner;

import exception.LogicException;
import exception.ParserException;
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
        } catch (ParserException | LogicException e) {
            ui.respond("Memory may have been corrupted! Starting from blank state...");
            executor.clearStorage();
        } finally {
            ui.respond(new String[] {"Initialisation done!", "What can I do for you?"});
        }

        while(true) {
            String inp = scanObj.nextLine();
            try {
                String[] results = executor.executeCommand(inp);
                ui.respond(results);
            } catch (ParserException | LogicException e) {
                ui.respond(e.getMessage());
            }
            if (inp.equals("bye")) {
                break;
            }
        }
        scanObj.close();
    }
}
