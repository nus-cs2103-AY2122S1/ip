import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Parser {

    private Ui ui;
    private TaskList taskList;

    public Parser(Ui ui, TaskList taskList) {

        this.ui = ui;
        this.taskList = taskList;
    }


    public Command parse(String command) throws DukeException {
        String[] splitInput = command.trim().split(" +");
            switch (splitInput[0]) {

                case "bye":
                    return new ExitCommand(command);

                case "list":
                    if(splitInput.length > 1) {
                        throw new DukeException(ui.commandError());
                    }
                    return new ListCommand(command);

                case "done":
                    return checkDone(command);

                case "delete":
                    return checkDelete(command);

                case "todo":
                case "event":
                case "deadline":
                    return checkInput(command.trim(), splitInput[0].trim());

                default:
                    throw new DukeException(ui.commandError());
            }
        }


    public Command checkInput(String input, String taskType) throws DukeException {
        String[] splitInput2 = input.trim().split(" +", 2);
        if (splitInput2.length == 2) {
            String[] splitTask2 = splitInput2[1].split(" +");
            String[] splitTask = taskType.equals("deadline")
                    ? splitInput2[1].split("/by") :
                    splitInput2[1].split("/at");
            if (splitTask.length == 2 && !splitTask[0].isBlank()) {
                switch (taskType) {
                    case "deadline":
                        try {
                            LocalDate date = LocalDate.parse(splitTask[1].trim());
                        } catch (DateTimeParseException e) {
                            throw new DukeException(ui.dateTimeError());
                        }
                        break;

                    case "event":
                        try {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime dateTime = LocalDateTime.parse(splitTask[1].trim(), dtf);
                        } catch (DateTimeParseException e) {
                            throw new DukeException(ui.dateTimeError());
                        }
                        break;
                }
            } else {
                if (taskType.equals("deadline") && !splitInput2[1].contains("/by")
                        || taskType.equals("event") && !splitInput2[1].contains("/at")) {
                    throw new DukeException(ui.incorrectAtOrBy(taskType));
                }

            }
            return new AddCommand(input, taskType);
        } else {
            throw new DukeException(ui.noDescription(taskType));
        }
    }

    public Command checkDelete(String input) throws DukeException {
        String[] splitInput = input.split(" +");
        if (splitInput.length == 2) {
            int taskNum = Integer.valueOf(splitInput[1]);
            if (taskNum <= 0 || taskNum > taskList.size()) {
                throw new DukeException(ui.deleteInvalidError());
            } else {
                return new DeleteCommand(input);
            }
        } else {
            throw new DukeException(ui.deleteNoNumError());
        }
    }

    public Command checkDone(String input) throws DukeException {
        String[] splitInput = input.trim().split(" +");
        if (splitInput.length == 2) {
            int taskNum = Integer.valueOf(splitInput[1]);
            if (taskNum > taskList.size() || taskNum <= 0) {
                throw new DukeException(ui.doneError());
            } else {
                return new DoneCommand(input, taskNum);
            }
        } else {
            throw new DukeException(ui.doneNoNumError());
        }
    }

}
