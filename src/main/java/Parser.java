import java.time.format.DateTimeParseException;

/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {
    Duke duke;

    /**
     * Constructor of the class `Parser`.
     */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Based on the command received, either quit the program or process an event.
     */
    public boolean parse(String command, Ui ui) {
        String[] splitted = command.split(" ", 2);
        Processor processor = null;

        if (command.equals("bye")) {
            processor = new ExitProcessor(this.duke);
        } else if (command.equals("list")) {
            processor = new GetListProcessor(this.duke);
        } else if (splitted[0].equals("done")) {
            try {
                int index = Integer.parseInt(splitted[1]) - 1;
                processor = new TaskDoneProcessor(this.duke.getTaskAt(index), this.duke);
            } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                ui.showInvalidTaskNoError();
            }
        } else if (splitted[0].equals("delete")) {
            try {
                int index = Integer.parseInt(splitted[1]) - 1;
                processor = new DeleteATaskProcessor(this.duke.getTaskAt(index), this.duke);
            } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                ui.showInvalidTaskNoError();
            }
        } else if (splitted[0].equals("todo")) {
            if (splitted.length >= 2) {
                processor = new AddATaskProcessor(new ToDo(splitted[1]), this.duke);
            } else {
                ui.showMissingDetailsError("description","todo", "");
            }
        } else if (splitted[0].equals("deadline")) {
            if (splitted.length >= 2) {
                String[] information = splitted[1].split("/by");
                if (information.length == 2) {
                    try {
                        processor = new AddATaskProcessor(new Deadline(information[0], information[1]), this.duke);
                    } catch (DateTimeParseException dateTimeParseException) {
                        ui.showInvalidTimeError("yyyy-MM-dd HH:mm");
                    }
                } else if (information.length < 2) {
                    ui.showMissingDetailsError(
                            "time", "deadline", "/by yyyy-MM-dd HH:mm");
                } else {
                    ui.showMultipleTimeSlotsError("deadline");
                }
            } else {
                ui.showMissingDetailsError(
                        "description", "deadline", "/by yyyy-MM-dd HH:mm");
            }
        } else if (splitted[0].equals("event")) {
            if (splitted.length >= 2) {
                String[] information = splitted[1].split("/at");
                if (information.length == 2) {
                    try {
                        processor = new AddATaskProcessor(new Event(information[0], information[1]), this.duke);
                    } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                        ui.showInvalidTimeError("yyyy-MM-dd HH:mm to yyyy-MM-dd HH:mm");
                    }
                } else if (information.length < 2) {
                    ui.showMissingDetailsError("time", "event",
                            "/at yyyy-MM-dd HH:mm to yyyy-MM-dd HH:mm");
                } else {
                    ui.showMultipleTimeSlotsError("event");
                }
            } else {
                ui.showMissingDetailsError("description", "event",
                        "/at yyyy-MM-dd HH:mm to yyyy-MM-dd HH:mm");
            }
        } else {
            ui.showInvalidCommandError();
        }
        if (processor != null) {
            boolean running = processor.process();
            System.out.println(processor);
            return running;
        } else {
            return true;
        }
    }
}
