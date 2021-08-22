package duke;

import duke.exception.DukeException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        ChatBot bot = new ChatBot();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        // create object of Scanner to take inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Boolean running = true;

        while (running) {

            String[] inputs = input.split(" ", 2);
            String str = inputs[0];

            try {
                // i wonder if those length checking exceptions can be replaced with a method.
                switch (str) {
                case "bye":
                    bot.handleExit();
                    running = false;
                    break;
                case "list":
                    bot.handleList();
                    input = sc.nextLine();
                    break;
                case "done":
                    // check if a number is specified
                    if (inputs.length == 1) {
                        throw new DukeException("The task number cannot be empty you dum dum");
                    }
                    // check if the following string is a number
                    int index = Integer.parseInt(inputs[1]);
                    // check if the number is valid.
                    if (index <= 0 || index > bot.getTotalTasks()) {
                        throw new DukeException("Please enter a valid number");
                    }
                    bot.handleDone(index);
                    input = sc.nextLine();
                    break;
                case "deadline":
                    if (inputs.length == 1) {
                        throw new DukeException("Please specify the deadline description");
                    }
                    String[] info = inputs[1].split("/by", 0);
                    if (info.length == 1) {
                        throw new DukeException("Please specify the deadline time");
                    }
                    try {
                        LocalDateTime parsedDate = LocalDateTime.parse(info[1].trim(), formatter);
                        bot.handleDeadline(info[0].trim(), parsedDate);
                        input = sc.nextLine();
                    } catch (DateTimeParseException e) {
                        throw new DukeException("The format of date & time is wrong. Please use {dd/mm/yyyy hhmm}");
                    }
                    break;
                case "todo":
                    if (inputs.length == 1) {
                        throw new DukeException("Please specify the todo description");
                    }
                    bot.handleTodo(inputs[1]);
                    input = sc.nextLine();
                    break;
                case "event":
                    if (inputs.length == 1) {
                        throw new DukeException("Please specify the event description");
                    }
                    info = inputs[1].split("/at");
                    if (info.length == 1) {
                        throw new DukeException("Please specify the time");
                    }
                    try {
                        LocalDateTime parsedDate = LocalDateTime.parse(info[1].trim(), formatter);
                        bot.handleEvent(info[0].trim(), parsedDate);
                        input = sc.nextLine();
                    } catch (DateTimeParseException e) {
                        throw new DukeException("The format of date & time is wrong. Please use {dd/mm/yyyy hhmm}");
                    }
                    break;
                case "delete":
                    if (inputs.length == 1) {
                        throw new DukeException("The task number to delete cannot be empty you dum dum");
                    }
                    // check if the following string is a number
                    index = Integer.parseInt(inputs[1]);
                    // check if the number is valid.
                    if (index <= 0 || index > bot.getTotalTasks()) {
                        throw new DukeException("Please enter a valid number");
                    }
                    bot.handleDelete(index);
                    input = sc.nextLine();
                    break;
                default:
                    //maybe add a help command
                    bot.handleWrongCommand();
                    input = sc.nextLine();

                }
            } catch (DukeException e) {
                bot.handleErrorMessage(e.getMessage());
                input = sc.nextLine();
            } catch (NumberFormatException e) {
                bot.handleErrorMessage("Please enter a valid number after done");
                input = sc.nextLine();
            }
        }
        sc.close();

    }
}


