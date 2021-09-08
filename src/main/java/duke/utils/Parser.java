package duke.utils;

import java.time.LocalDate;

import duke.commands.*;

import duke.exceptions.InvalidInputException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Parser {

    /**
     * Return command according to user input
     * 
     * @param command the user input string
     * @return the corresponding command according to given command String
     * @throws InvalidInputException if input is invalid
     */
    public static Command parse(String command) throws InvalidInputException {
        String[] commands = command.split(" ", 2);
        command = commands[0];
        int index;

        String description = commands.length > 1 ? commands[1] : "";

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "sort":
                return new SortCommand();
            case "done":
                index = Integer.parseInt(description) - 1;

                return new DoneCommand(index);
            case "delete":
                index = Integer.parseInt(description) - 1;

                return new DeleteCommand(index);
            case "find":
                if (description.isBlank()) {
                    throw new InvalidInputException("find's description cannot be empty!");
                } else {
                    return new FindCommand(description);
                }
            case "todo":
                if (description.isBlank()) {
                    throw new InvalidInputException("todo's description cannot be empty!");
                } else {
                    Task newTask = new ToDo(description);

                    return new AddCommand(newTask);
                }
            case "deadline":
                if (description.isBlank()) {
                    throw new InvalidInputException("deadline's description cannot be empty!");
                } else {
                    String[] split = description.split("/by", 2);
                    description = split[0].trim();
                    LocalDate time = LocalDate.parse(split[1].trim());

                    Task newTask = new Deadline(description, time);

                    return new AddCommand(newTask);
                }
            case "event":
                if (description.isBlank()) {
                    throw new InvalidInputException("event's description cannot be empty!");
                } else {
                    String[] split = description.split("/from", 2);
                    description = split[0].trim();
                    String[] time = split[1].split("/to", 2);
                    LocalDate startTime = LocalDate.parse(time[0].trim());
                    LocalDate endTime = LocalDate.parse(time[1].trim());

                    Task newTask = new Event(description, startTime, endTime);

                    return new AddCommand(newTask);
                }
            default:
                throw new InvalidInputException("Invalid command");
        }
    }
}