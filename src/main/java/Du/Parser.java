package Du;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class Parser {
    private TaskList tasks;

    /**
     * Public constructor for Parser
     * @param tasks
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Takes in commands from user and parse them
     * @param command command taken in
     * @return String response
     */
    public String parse(String command) {
        assert command != null : "Command cannot be nothing!";
        // remove any extra spaces if there is any
        command = command.strip();
        String response = "";
        if (Objects.equals(command, "bye")) {
            response = "Bye. Hope to not see you again:P";
        } else if (Objects.equals(command, "list")) {
            response = tasks.print_list_of_tasks();
        } else {
            response = parse_two_arguments(command);
        }
        assert response != "" : "there's no bot response?";
        return response;
    }

    /**
     * Takes in a command to parse as two arguments
     * @param command cammand taken in
     * @return String response
     */
    public String parse_two_arguments(String command) {
        String response = "";
        String[] split_string = command.split(" ", 2);
        if (Objects.equals(split_string[0], "done")) {
            if (split_string.length <= 1) { return "Oh noes, you need to tell me what item you have finished"; }
            response = tasks.find_finished_task(Integer.parseInt(split_string[1]));
        } else if (Objects.equals(split_string[0], "find")) {
            if (split_string.length <= 1) { return response = "Oh noes, you need to tell me what you want to find"; }
            response = tasks.print(tasks.search(split_string[1]));
        } else if (Objects.equals(split_string[0], "delete")) {
            response = tasks.remove_task(Integer.parseInt(split_string[1]));
            return response;
        } else if (Objects.equals(split_string[0], "todo")) {
            // error handling when to do item is empty
            if (split_string.length <= 1) { return "Oh noes, the todo item cannot be empty, please input again"; }
            Task task = new Todo(split_string[1], false, tasks);
            response = task.log_add_task();
        } else { response = parseOthers(split_string); }
        return response;
    }

    /**
     * Parse the deadline, event and recurring items
     * @param split_string command taken in
     * @return String response
     */
    public String parseOthers(String[] split_string) {
        String response;
        if (Objects.equals(split_string[0], "deadline")) {
            response = parse_Deadline(split_string);
        } else if (Objects.equals(split_string[0], "event")) {
            response = parse_Event(split_string);
        } else if (Objects.equals(split_string[0], "recurring")) {
            response = parse_recurring(split_string);
        } else  {
            response = "Oh noes, I don't understand:(, please input again";
        }
        return response;
    }

    /**
     * Parse the deadline item
     * @param split_string command taken in
     * @return String response
     */
    public String parse_Deadline(String[] split_string) {
        String response = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            // error handling when the deadline item is empty
            if (split_string.length <= 1) {
                response = "Oh noes, the Deadline item cannot be empty, please input again";
                return response;
            }
            String[] task_time = split_string[1].split("/by ", 2);
            // error handling when there is no time for deadline
            if (task_time.length <= 1) {
                response = "Oh noes, the deadline item needs to have a time to be done by, please input again";
                return response;
            }
            try {
                LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
            } catch (DateTimeParseException e) {
                response = "Invalid date format, please input the date in this format: yyyy-MM-dd HH:mm";
                return response;
            }
            LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
            Task task = new Deadline(task_time[0], false, tasks, date);
            response = task.log_add_task();

        return response;
    }

    /**
     * Parse the event item
     * @param split_string command taken in
     * @return String response
     */
    public String parse_Event(String[] split_string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String response = "";
        // error handling when the event item is empty
        if (split_string.length <= 1) {
            response = "Oh noes, the event item cannot be empty, please input again";
            return response;
        }
        String[] task_time = split_string[1].split("/at ", 2);
        // error handling when there is no time for event
        if (task_time.length <= 1) {
            response = "Oh noes, the event item needs to have a time that it is occurring at, please input again";
            return response;
        }
        try {
            LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
        } catch (DateTimeParseException e) {
            response = "Invalid date format, please input the date in this format: yyyy-MM-dd HH:mm";
            return response;
        }
        LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
        Task task = new Event(task_time[0], false, tasks, date);
        response = task.log_add_task();
        return response;
    }

    /**
     * Parse the recurring item
     * @param split_string command taken in
     * @return String response
     */
    public String parse_recurring(String[] split_string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String response = "";
        // error handling when the recurring item is empty
        if (split_string.length <= 1) { return "Oh noes, the recurring item cannot be empty, please input again"; }
        String[] recurring_split = split_string[1].split(", ", 3);
        if (recurring_split.length <= 1) {
            return "Oh noes, you need to input the frequency of recurrence and number of time it occurs";
        }
        if (recurring_split.length <= 2) { return "Oh noes, you need to input the task itself"; }
        try {
            Integer.parseInt(recurring_split[1]);
        } catch (NumberFormatException e) {
            return "you need to input in the following format: recurring <frequency>, <number of times>, deadline/event ";
        }
        int times = Integer.parseInt(recurring_split[1]);
        String[] deadline_event_string = recurring_split[2].split(" ", 2);
        if (Objects.equals(deadline_event_string[0], "deadline")) {
            response = parse_recurring_Deadline(deadline_event_string, times, recurring_split[0]);
        } else if (Objects.equals(deadline_event_string[0], "event")) {
            response = parse_recurring_Event(deadline_event_string, times, recurring_split[0]);
        } else { return "Oh noes, pls input either a deadline or event for it to be recurring"; }
        return response;
    }

    /**
     * Add the frequency to the local date time
     * @param frequency frequency of recurring item
     * @param time current LocalDateTime
     * @return new LocalDateTime
     */
    public LocalDateTime add_frequency(String frequency, LocalDateTime time) {
        if (Objects.equals(frequency, "daily")) {
            return time.plusDays(1);
        } else if (Objects.equals(frequency, "weekly")) {
            return time.plusWeeks(1);
        } else if (Objects.equals(frequency, "monthly")) {
            return time.plusMonths(1);
        } else if (Objects.equals(frequency, "yearly")) {
            return time.plusYears(1);
        }
        return null;
    }

    /**
     * Parse the recurring deadline
     * @param split_string command taken in
     * @param number_of_times number of times the deadline is recurring
     * @param frequency how frequent the deadline is recurring
     * @return String response
     */
    public String parse_recurring_Deadline(String[] split_string, int number_of_times, String frequency) {
        String response = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // error handling when the deadline item is empty
        if (split_string.length <= 1) {
            response = "Oh noes, the Deadline item cannot be empty, please input again";
            return response;
        }
        String[] task_time = split_string[1].split("/by ", 2);
        // error handling when there is no time for deadline
        if (task_time.length <= 1) {
            return "Oh noes, the deadline item needs to have a time to be done by, please input again";
        }
        try {
            LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
        } catch (DateTimeParseException e) {
            return "Invalid date format, please input the date in this format: yyyy-MM-dd HH:mm";
        }
        LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
        Task task = new Deadline(task_time[0], false, tasks, date);
        for (int i = 0; i < number_of_times - 1; i++) {
            date = add_frequency(frequency, date);
            Task t = new Deadline(task_time[0], false, tasks, date);
        }

        response = task.log_add_recurring_task(number_of_times, frequency);

        return response;
    }

    /**
     * Parse the recurring event
     * @param split_string command taken in
     * @param number_of_times number of times the event is recurring
     * @param frequency how frequent the event is recurring
     * @return String response
     */
    public String parse_recurring_Event(String[] split_string, int number_of_times, String frequency) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String response = "";
        // error handling when the event item is empty
        if (split_string.length <= 1) { return "Oh noes, the event item cannot be empty, please input again"; }
        String[] task_time = split_string[1].split("/at ", 2);
        // error handling when there is no time for event
        if (task_time.length <= 1) {
            return "Oh noes, the event item needs to have a time that it is occurring at, please input again";
        }
        try {
            LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
        } catch (DateTimeParseException e) {
            response = "Invalid date format, please input the date in this format: yyyy-MM-dd HH:mm";
            return response;
        }
        LocalDateTime date = LocalDateTime.parse(task_time[1], formatter);
        Task task = new Event(task_time[0], false, tasks, date);
        for (int i = 0; i < number_of_times - 1; i++) {
            date = add_frequency(frequency, date);
            Task t = new Event(task_time[0], false, tasks, date);
        }
        response = task.log_add_recurring_task(number_of_times, frequency);
        return response;
    }

}
