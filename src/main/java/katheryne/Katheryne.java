package katheryne;

// import task classes

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import katheryne.command.Command;

@JsonTypeInfo(use = NAME, include = PROPERTY)

public class Katheryne {
    /**
     * Chat bot katheryne.Katheryne, used for simple todo lists
     */
    public static void main(String[] args) {
        // initialise variables
        final String pathName = "tasks.json";
        Storage storage = new Storage();
        Ui ui = new Ui();
        TaskList lst = new TaskList();

        // initialise Katheryne
        try {
            storage.loadTasks(lst, pathName);
        } catch (KatheryneException e) {
            ui.showErrorMessage(e);
        }
        ui.greet(lst);

        while (ui.getIsRunning()) {
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
