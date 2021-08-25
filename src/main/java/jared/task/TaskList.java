package jared.task;

import jared.common.DukeException;
import jared.storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Deals with tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds a task to the list.
     * @param command User input string command.
     * @param next Full user input string.
     * @throws DukeException If user input command is invalid.
     */
    public void add(String command, String next) throws DukeException {
        Task newTask;
        String desc;
        String dateStr;
        String[] dateTime;
        LocalDate date;
        LocalTime time;

        if (command.equals("todo")) {
            try {
                desc = next.split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            newTask = new Todo(desc);
        } else if (command.equals("deadline")) {
            try {
                String body = next.split(" ", 2)[1];
                desc = body.split("/by", 2)[0].trim();
                dateStr = body.split("/by", 2)[1].trim();
                dateTime = dateStr.split(" ");
                String d = dateTime[0];
                date = LocalDate.parse(d);

                if (dateTime.length > 1) {
                    String t = dateTime[1];
                    time = LocalTime.parse(t);
                    newTask = new Deadline(desc, date, time);
                } else {
                    newTask = new Deadline(desc, date);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
                throw new DukeException("Invalid date. Please enter the date (yyyy-mm-dd)");
            }
        } else if (command.equals("event")) {
            try {
                String body = next.split(" ", 2)[1];
                desc = body.split("/at", 2)[0].trim();
                dateStr = body.split("/at", 2)[1].trim();
                dateTime = dateStr.split(" ");
                String d = dateTime[0];
                date = LocalDate.parse(d);

                if (dateTime.length > 1) {
                    String t = dateTime[1];
                    time = LocalTime.parse(t);
                    newTask = new Event(desc, date, time);
                } else {
                    newTask = new Event(desc, date);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date and time. Please enter the date (yyyy-mm-dd)");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        tasks.add(newTask);
        storage.saveData(tasks);

        System.out.println(String.format("Got it. I've added this task:\n"
                        + "%s\nNow you have %d tasks in the list.",
                newTask, tasks.size())
        );
    }

    /**
     * Lists the tasks.
     */
    public void list() {
        String res = "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            res += String.format("%d. %s\n", i + 1, currTask.toString());
        }
        System.out.println(res);
    }

    /**
     * Marks the specified task as done.
     * @param next Full user input string.
     * @throws DukeException If task is invalid.
     */
    public void done(String next) throws DukeException {
        int taskNum;
        Task currTask;

        try {
            taskNum = Integer.valueOf(next.split(" ", 2)[1]);
            currTask = tasks.get(taskNum-1);
        } catch (Exception e) {
            throw new DukeException("Invalid task number provided.");
        }

        currTask.markDone();
        storage.saveData(tasks);

        String res = String.format("Nice! I've marked this task as done:\n%s",
                currTask);
        System.out.println(res);
    }

    /**
     * Deletes task from list.
     * @param next Full user input string.
     * @throws DukeException If task is invalid.
     */
    public void delete(String next) throws DukeException {
        int taskNum;
        Task currTask;
        int index;
        try {
            taskNum = Integer.valueOf(next.split(" ", 2)[1]);
            index = taskNum - 1;
            currTask = tasks.get(index);
        } catch (Exception e) {
            throw new DukeException("Invalid task number provided.");
        }
        tasks.remove(index);
        storage.saveData(tasks);
        System.out.println(String
                .format("Noted. I've removed this task:\n" + "%s\nNow you have %d tasks in the list.",
                        currTask.toString(), tasks.size()));
    }

}
