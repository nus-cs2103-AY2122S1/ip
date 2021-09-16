package katheryne;

// import task classes

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import katheryne.command.Command;

@JsonTypeInfo(use = NAME, include = PROPERTY)

/**
 * Chat bot katheryne.Katheryne, used for simple todo lists
 */
public class Katheryne {

    public static void main(String[] args) {
        // initialise variables
        Storage storage = new Storage();
        Ui ui = new Ui();
        TaskList lst = new TaskList();
        String PATH_NAME = "tasks.json";

        // initialise Katheryne
        try {
            storage.loadTasks(lst, PATH_NAME);
        } catch (KatheryneException e) {
            ui.showErrorMessage(e);
        }
        ui.greet(lst);

        while (ui.isRunning) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(lst, ui, storage);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (KatheryneException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
