package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> taskList = new ArrayList<>();

    private static final String filePath = "taskList.txt";

    private static void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String details = task.getDetails();
            String done = task.isCompleted()
                    ? "done"
                    : "not-done";
            if (task.getClass() == ToDo.class) {
                String type = "T";
                fw.write(type + ' ' + done + ' ' + details);
            } else {

                LocalDate date = task.getDate();
                String dateStr = "";
                if (date == null) {
                    dateStr = "null";
                } else {
                    String year = "" + task.getDate().getYear();
                    String month = task.getDate().getMonthValue() < 10
                            ? "0" + task.getDate().getMonthValue()
                            : "" + task.getDate().getMonthValue();
                    String day = "" + task.getDate().getDayOfMonth();
                    dateStr = year + '-' + month + '-' + day;
                }

                if (task.getClass() == Deadline.class) {
                    String type = "D";
                    String by = ((Deadline) task).getBy() == null
                            ? "null"
                            : ((Deadline) task).getBy();
                    String time = "" + task.getTime();
                    fw.write(type + ' ' + done + ' ' + details + ' ' + by + ' '
                            + dateStr + ' ' + time);
                } else if (task.getClass() == Event.class) {
                    String type = "E";
                    String at = ((Event) task).getAt() == null
                            ? "null"
                            : ((Event) task).getAt();
                    String start = "" + task.getTime();
                    String end = "" + ((Event) task).getEndTime();
                    fw.write(type + ' ' + done + ' ' + details + ' ' + at + ' '
                            + dateStr + ' ' + start + ' ' + end);
                }
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private static void getFileContents() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String task = s.next();
            String done = s.next();
            String details = s.next();
            switch (task) {
            case "T":
                taskList.add(new ToDo(details));
                break;
            case "D":
                String by = s.next();
                if (by == null) {
                    String date = s.next();
                    int time = Integer.parseInt(s.next());
                    taskList.add(new Deadline(details, null, date, time));
                } else {
                    taskList.add(new Deadline(details, by, null, -1));
                }
                break;
            case "E":
                String at = s.next();
                if (at == null) {
                    String date = s.next();
                    int start = Integer.parseInt(s.next());
                    int end = Integer.parseInt(s.next());
                    taskList.add(new Event(details, null, date, start, end));
                } else {
                    taskList.add(new Event(details, at, null, -1, -1));
                }
                break;
            }
            if (done == "done") {
                taskList.get(taskList.size() - 1).complete();
            }
            s.nextLine();
        }
    }

    private static Boolean isDate(String str) {
        try {
            if (str.length() != 10) {
                return false;
            }
            if (str.charAt(4) != str.charAt(7)) {
                return false;
            }
            int year = Integer.parseInt(str.substring(0, 4));
            int month = Integer.parseInt(str.substring(5, 7));
            if (month < 0 || month > 12) {
                return false;
            }
            int day = Integer.parseInt(str.substring(8, 10));
            if (day < 0 || day > 31) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Boolean isTime(String str) {
        try {
            if (str.length() != 4) {
                return false;
            }
            int time = Integer.parseInt(str);
            if (time < 0 || time > 2359) {
                return false;
            } else if (time % 100 > 59){
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String createTask(String task, String description) throws InvalidInputException {

        if (description.length() == 0) {
            throw new InvalidInputException(task + " task needs a description.");
        }

        String center = null;
        switch (task) {
        case "todo":
            center = "";
            break;
        case "deadline":
            center = "/by";
            break;
        case "event":
            center = "/at";
            break;
        }

        assert center != null;
        int centerIndex = description.indexOf(center);
        if (centerIndex == -1) {
            throw new InvalidInputException("To create a " + task + " task: enter \"" + task + " (task details) "
                    + center + " (task details)\"");
        }

        String details = null;
        String aftCenter = null;
        String date = null;
        int time1 = -1;
        int time2 = -1;

        if (!task.equals("todo")) {

            details = description.substring(0, centerIndex).trim();
            aftCenter = description.substring(centerIndex).trim();
            if (details.length() == 0 || aftCenter.length() <= center.length()) {
                throw new InvalidInputException(task + " task must have details before and after " + center + ".");
            }

            aftCenter = aftCenter.substring(3).trim();

            if (task.equals("deadline")) {
                if (aftCenter.length() == 15) {
                    if (isDate(aftCenter.substring(0, 10)) && isTime(aftCenter.substring(11))) {
                        date = aftCenter.substring(0, 4) + '-' + aftCenter.substring(5, 7) + '-'
                                + aftCenter.substring(8, 10);
                        time1 = Integer.parseInt(aftCenter.substring(11));
                        aftCenter = null;
                    }
                }
            } else if (task.equals("event")) {
                if (aftCenter.length() == 20) {
                    if (isDate(aftCenter.substring(0, 10)) && isTime(aftCenter.substring(11, 15))
                            && isTime(aftCenter.substring(16))) {
                        date = aftCenter.substring(0, 4) + '-' + aftCenter.substring(5, 7) + '-'
                                + aftCenter.substring(8, 10);
                        time1 = Integer.parseInt(aftCenter.substring(11, 15));
                        time2 = Integer.parseInt(aftCenter.substring(16));
                        aftCenter = null;
                    }
                }
            }
        }

        switch (task) {
        case "todo":
            taskList.add(new ToDo(description));
            break;
        case "deadline":
            taskList.add(new Deadline(details, aftCenter, date, time1));
            break;
        case "event":
            taskList.add(new Event(details, aftCenter, date, time1, time2));
            break;
        }

        String taskDetails = taskList.get(taskList.size() - 1).toString();
        return "Got it. I've added this task:\n" +
                "\t\t" + taskDetails + "\n" +
                "\t" + "Now you have " + taskList.size() + " tasks in the list.";
    }

    public static void main(String[] args) throws IOException {

        try {
            getFileContents();
        } catch(FileNotFoundException e) {
            new File(filePath);
        }

        String line = "------------------------------------------------------------------------------" +
                        "-------------------------------\n\n";
        String savedTasks = "";
        if (!taskList.isEmpty()) {
            savedTasks += "\tSaved tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                savedTasks += "\t\t" + (i + 1) + ". " + taskList.get(i).toString();
                if (i != taskList.size() - 1) savedTasks += '\n';
            }
            savedTasks += "\n\n";
        }
        // greeting
        System.out.print(
                line
                + "\tHello! I'm Duke\n\n"
                + savedTasks
                + "\tWhat can I do for you?\n\n"
                + line
        );

        Scanner scanner = new Scanner(System.in);
        try {

            String task;
            String text;
            boolean leave = false;
            while (!leave) {
                // get task
                task = scanner.next();
                // get text
                text = scanner.nextLine().trim();

                try {
                    switch (task) {
                    case "bye":
                        text = "Bye. Hope to see you again soon!";
                        leave = true;
                        saveTasks();
                        break;
                    case "list":
                        if (taskList.isEmpty()) text = "There are no items in the list.";
                        else {
                            text = "";
                            for (int i = 0; i < taskList.size(); i++) {
                                if (i != 0) text += "\t";
                                text += (i + 1) + ". " + taskList.get(i).toString();
                                if (i != taskList.size() - 1) text += "\n";
                            }
                        }
                        break;
                    case "done":
                        try {
                            int index = Integer.parseInt(text);
                            taskList.get(index - 1).complete();
                            text = "Nice! I've marked this task as done:\n" +
                                    "\t\t" + taskList.get(index - 1).toString();
                        } catch (NumberFormatException e) {
                            throw new InvalidInputException("To complete a task: enter \"done (task number)\"");
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidInputException("Task number does not exist.");
                        }
                        break;
                    case "delete":
                        try {
                            int index = Integer.parseInt(text);
                            text = "Noted. I've removed this task: \n" +
                                    "\t\t" + taskList.get(index - 1).toString() + "\n" +
                                    "\tNow you have " + (taskList.size() - 1) + " tasks in the list.";
                            taskList.remove(index - 1);
                        } catch (NumberFormatException e) {
                            throw new InvalidInputException("To delete a task: enter \"delete (task number)\"");
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidInputException("Task number does not exist.");
                        }
                        break;
                    case "todo": case "deadline": case "event":
                        text = createTask(task, text);
                        break;
                    case "date":
                        if (text.length() != 15) {
                            throw new InvalidInputException(
                                    "Enter the date and time after \"date\" in this format: YYYY-MM-DD (24hr time).");
                        } else {
                            if (isDate(text.substring(0, 10)) && isTime(text.substring(11))) {
                                LocalDate date = LocalDate.parse(text.substring(0, 4)
                                        + '-' + text.substring(5, 7) + '-'
                                        + text.substring(8, 10));
                                int time = Integer.parseInt(text.substring(11));
                                text = "";
                                for (int i = 0; i < taskList.size(); i++) {
                                    if (date.equals(taskList.get(i).getDate()) &&
                                            taskList.get(i).getTime() == time) {
                                        text += "\t\t" + taskList.get(i).toString() + '\n';
                                    }
                                }
                                if (text.equals("")) {
                                    text = "No tasks are due/happening.";
                                } else {
                                    text = "These tasks are due/happening:\n"
                                            + text;
                                }
                            } else {
                                throw new InvalidInputException(
                                        "Date and time format is incorrect. Correct format: YYYY-MM-DD (24hr time).");
                            }
                        }
                        break;
                    default:
                        throw new InvalidInstructionException(task);
                    }
                } catch (InvalidInputException e) {
                    text = e.toString();
                } catch (InvalidInstructionException e) {
                    text = e.toString();
                }

                // print text
                System.out.print(
                        "\n" + line +
                        "\t" + text + "\n" +
                        "\n" + line
                );
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            try {
                saveTasks();
            } catch (IOException ie) {
                System.out.println(ie);
            }
            System.out.println(e);
        }
    }
}
