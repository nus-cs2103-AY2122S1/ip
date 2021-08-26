package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final String TIME_PARSE_ERROR = "Hmm.. Seems like the time format is foreign to me. \n"
            + "Please use the following format:\n"
            + "yyyy-MM-dd HH:MM (e.g 2020-05-19 15:30)";

    /**
     * Returns full command in shape of a list of strings
     */
    public Command parse(String userInput) throws DukeExceptions {
        String[] splitUserInput = userInput.split(" ", 2);
        String command = splitUserInput[0].strip();

        switch (command) {
        case "bye":
            if (splitUserInput.length > 1 && !(splitUserInput[1].equals(""))) {
                throw new DukeExceptions("What was that? You weren't suppose to say anything after bye!");
            }
            return new ByeCommand();

        case "list":
            if (splitUserInput.length > 1 && !(splitUserInput[1].equals(""))) {
                throw new DukeExceptions("List command cannot take any arguements");
            }
            return new ListCommand();

        case "delete":
            try {
                if (splitUserInput.length == 1) {
                    throw new DukeExceptions("I need to know which task you want to delete");
                }
                int index = Integer.parseInt(splitUserInput[1]);
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeExceptions("Oops, delete command can only take a single integer as the argument!");
            }

        case "done":
            try {
                if (splitUserInput.length == 1) {
                    throw new DukeExceptions("I need to know which task you want to mark as finished");
                }
                int index = Integer.parseInt(splitUserInput[1]);
                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeExceptions("Oops, done command can only take a single integer as the argument!");
            }

        case "event":
            try {
                if (splitUserInput.length == 1 || splitUserInput[1].equals("")) {
                    throw new DukeExceptions("Oops," +
                            "you need to tell me the description and the time of the event");
                }
                String[] splitBody = splitUserInput[1].split("/at", 2);
                if (splitBody[0].equals("")) {
                    throw new DukeExceptions("Oops," +
                            "you need to tell me the description and the time of the event");

                } else if (splitBody.length == 1 || splitBody[1].strip().equals("")) {
                    throw new DukeExceptions("Oops! I need to know when the event is. \n" +
                            "Use the /at argument followed by yyyy-MM-dd HH:mm please");
                }

                String desc = splitBody[0].strip();
                LocalDateTime time = LocalDateTime.parse(splitBody[1].strip(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Event event = Event.create(desc, time);
                return new EventCommand(event);
            } catch (DateTimeParseException e) {
                throw new DukeExceptions(TIME_PARSE_ERROR);
            } catch (DukeExceptions e) {
                throw e;
            }

        case "deadline":
            if (splitUserInput.length == 1 || splitUserInput[1].equals("")) {
                throw new DukeExceptions("Oops, " +
                        "you need to tell me the description and the time of the deadline");
            }
            try {
                String[] splitBody = splitUserInput[1].split("/by", 2);
                if (splitBody[0].equals("")) {
                    throw new DukeExceptions("Oops," +
                            "you need to tell me the description and the time of the deadline");
                } else if (splitBody.length == 1 || splitBody[1].strip().equals("")) {
                    throw new DukeExceptions("Oops! I need to know when the deadline is. \n" +
                            "Use the /by argument followed by yyyy-MM-dd HH:mm please");
                }
                String desc = splitBody[0].strip();
                LocalDateTime time = LocalDateTime.parse(splitBody[1].strip(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                );
                Deadline deadline = Deadline.create(desc, time);
                return new DeadlineCommand(deadline);

            } catch (DateTimeParseException e) {
                throw new DukeExceptions(TIME_PARSE_ERROR);
            } catch (Exception e) {
                throw e;
            }

        case "todo":
            if (splitUserInput.length == 1 || splitUserInput[1].equals("")) {
                throw new DukeExceptions("Oops, " +
                        "you need to tell me the description of the task on hand");
            }
            ToDo toDo = ToDo.create(splitUserInput[1]);
            return new ToDoCommand(toDo);

        case "":
            throw new DukeExceptions("Can you type louder .. I mean.. input anything? I got nothing");

        default:
            throw new DukeExceptions("Sorry.. I don't know what your command means");
        }
    }
}
