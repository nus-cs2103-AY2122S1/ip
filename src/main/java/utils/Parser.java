package utils;

import java.time.LocalDate;

import commands.*;
import exceptions.InvalidInputException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Parser {

    public static Command parse(String command) throws InvalidInputException {
        String[] commands = command.split(" ", 2);
        command = commands[0];

        String description = commands.length > 1 ? commands[1] : "";
        
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            int index = Integer.valueOf(description) - 1;
            return new DoneCommand(index);
        } else if (command.equals("delete")) {
            int index = Integer.valueOf(description) - 1;
            return new DeleteCommand(index);
        } else if (command.equals("find")){
            if (description.isBlank()) {
                throw new InvalidInputException("find's description cannot be empty!");
            } else {
                return new FindCommand(description);
            }
        } else if (command.equals("todo")){
            if (description.isBlank()) {
                throw new InvalidInputException("todo's description cannot be empty!");
            } else {
                Task newTask = new ToDo(description);
                return new AddCommand(newTask);
            }
        } else if (command.equals("deadline")) {
            if (description.isBlank()) {
                throw new InvalidInputException("deadline's description cannot be empty!");
            } else {
                String[] split = description.split("/by", 2);
                description = split[0].trim();
                LocalDate time = LocalDate.parse(split[1].trim());

                Task newTask = new Deadline(description, time);
                return new AddCommand(newTask);
            }
        } else if (command.equals("event")){
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
        } else {
            throw new InvalidInputException("Invalid command");
        }
    }   
}