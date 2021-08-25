package duke.storage;

import duke.exceptions.DukeException;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class used to handle reading and writing to file.
 * Contains methods that
 * (i)    creates data folder and tasks file.
 * (ii)   writes to tasks file.
 * (iii)  reads from tasks file.
 * (iv)   extracts date from string of text.
 * (v)    extracts time from string of text for Deadline task.
 * (vi)   extracts time from string of text for Event task.
 */
public class Storage {
    /**
     * The createFile() method creates a folder data and a file, tasks.txt, if file is not found in the directory.
     */
    public static void createFile() {
        File dir = new File("data/");
        File tasks = new File("data/tasks.txt");
        try {
            dir.mkdir();
            if (tasks.createNewFile()) {
                System.out.println(tasks.getName() + " created");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * The writeToFile() method writes all tasks and their respective statuses to the tasks.txt file.
     *
     * @param tList the TaskList object used to keep track of all tasks.
     */
    public static void writeToFile(TaskList tList) {
        ArrayList<Task> tasks = tList.getTaskList();
        try {
            FileWriter writer = new FileWriter("data/tasks.txt");
            for (Task t : tasks) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * The readFromFile() method reads all previously entered tasks from tasks.txt and stores them in the Duke.
     *
     * @param tList tList the TaskList object used to keep track of all tasks.
     */
    public static void readFromFile(TaskList tList) {
        try {
            File tasks = new File("data/tasks.txt");
            Scanner sc = new Scanner(tasks);
            while (sc.hasNextLine()) {
                String atHand = sc.nextLine();
                String taskType = atHand.substring(1, 2);
                String done = (atHand.charAt(4) == ' ') ? " " : "X";
                String des;
                Task t;

                if (taskType.equals("T")) {
                    des = atHand.substring(7);
                    t = new ToDo(done, des);
                } else if (taskType.equals("E")) {
                    int openBracket = atHand.indexOf('(');
                    des = atHand.substring(7, openBracket - 1);
                    LocalDate date = LocalDate.parse(atHand.substring(openBracket + 5, openBracket + 15));
                    ArrayList<LocalTime> startEnd = extractTimeEvent(atHand);
                    t = new Event(done, des, date, startEnd.get(0), startEnd.get(1));
                } else if (taskType.equals("D")) {
                    int openBracket = atHand.indexOf('(');
                    des = atHand.substring(7, openBracket - 1);
                    LocalDate date = LocalDate.parse(atHand.substring(openBracket + 5, openBracket + 15));
                    LocalTime time = extractTimeDeadline(atHand);
                    t = new Deadline(done, des, date, time);
                } else {
                    throw new DukeException("Task Type not recognised. Task not loaded into Duke chat-bot");
                }
                tList.add(t);
            }

        } catch (FileNotFoundException e) {
            return;
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * The extractDate() method extracts the date of a task from a string of text.
     *
     * @param des the user input into the Duke chat-box.
     * @return LocalDate object to represent the date that accompanies a task.
     * @throws DukeException when date is not properly formatted.
     */
    public static LocalDate extractDate(String des) throws DukeException {
        try {
            int startIndex = des.indexOf('/') + 4;
            int endIndex = startIndex + 10;
            return LocalDate.parse(des.substring(startIndex, endIndex));
        } catch (DateTimeParseException e) {
            throw new DukeException("Date not properly formatted");
        }
    }

    /**
     * The extractTimeDeadline() method extracts the time of a Deadline task from a string of text.
     *
     * @param des the user input into the Duke chat-box.
     * @return LocalTime object to represent the time that accompanies a Deadline task.
     * @throws DukeException when date is not properly formatted.
     */
    public static LocalTime extractTimeDeadline(String des) throws DukeException {
        try {
            String time = des.substring(des.lastIndexOf('-') + 4, des.lastIndexOf('-') + 9);
            return LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Time not properly formatted");
        }
    }

    /**
     * The extractTimeEvent() method extracts the timings of an Event task from a string of text.
     *
     * @param des the user input into the Duke chat-box.
     * @return ArrayList of LocalTime objects to represent the timings that accompany an Event task.
     * The start time is stored at index 0 while the end time is stored at index 1.
     * @throws DukeException when date is not properly formatted.
     */
    public static ArrayList<LocalTime> extractTimeEvent(String des) throws DukeException {
        try {
            String time = des.substring(des.lastIndexOf('-') + 4, des.lastIndexOf('-') + 15);
            String firstTime = time.substring(0, 5);
            String secondTime = time.substring(6);
            ArrayList<LocalTime> result = new ArrayList<>();
            result.add(LocalTime.parse(firstTime));
            result.add(LocalTime.parse(secondTime));
            return result;
        } catch (DateTimeParseException e) {
            throw new DukeException("Time not properly formatted");
        }
    }
}
