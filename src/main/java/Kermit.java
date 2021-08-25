import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Kermit {
    private Storage storage;
    private ToDo taskList;
    private Ui ui;

    private static LocalDate formatUserDateFormat(String s) throws KermitException {
        String[] components = s.split("-");
        String day = components[0];
        String month = components[1];
        String year = components[2];
        try {
            LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new KermitException("BAH That's not a time is it?? Try writing like this DD/MM/YYYY");
        }
    }

    public Kermit(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new ToDo(storage.load());
        } catch (KermitException e) {
            ui.showLoadingError();
            taskList = new ToDo();
        }
    }

    public void run() {
        ui.showIntroMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (KermitException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
    public static void main(String[] args){
        new Kermit("data/tasks.txt").run();
    }
}