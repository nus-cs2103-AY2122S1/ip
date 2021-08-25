package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class Storage {
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
                    int closeBracket = atHand.indexOf(')');
                    des = atHand.substring(7, openBracket - 1);
                    LocalDate date = LocalDate.parse(atHand.substring(openBracket + 5, openBracket + 15));
                    ArrayList<LocalTime> startEnd = extractTimeEvent(atHand);
                    t = new Event(done, des, date, startEnd.get(0), startEnd.get(1));
                } else if (taskType.equals("D")) {
                    int openBracket = atHand.indexOf('(');
                    int closeBracket = atHand.indexOf(')');
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

    public static LocalDate extractDate(String des) throws DukeException {
        try {
            int startIndex = des.indexOf('/') + 4;
            int endIndex = startIndex + 10;
            return LocalDate.parse(des.substring(startIndex, endIndex));
        } catch (DateTimeParseException e) {
            throw new DukeException("Date not properly formatted");
        }
    }

    public static LocalTime extractTimeDeadline(String des) throws DukeException {
        try {
            String time = des.substring(des.lastIndexOf('-') + 4, des.lastIndexOf('-') + 9);
            return LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Time not properly formatted");
        }
    }

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
