package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(String data) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("No previous data found.\nLet's start a new To-Do List!");
        }

        taskList = new ArrayList<>();
        Scanner sc = new Scanner(data);

        while (sc.hasNext()) {
            int taskType = Integer.parseInt(sc.nextLine());
            boolean isDone = Boolean.parseBoolean(sc.nextLine());

            switch (taskType) {
            case 0:
                String tDescription = sc.nextLine();
                Todo tTask = new Todo(isDone, tDescription);

                taskList.add(tTask);
                break;
            case 1:
                String dDescription = sc.nextLine();
                String by = sc.nextLine();
                Deadline dTask = new Deadline(isDone, dDescription, by);

                taskList.add(dTask);
                break;
            case 2:
                String eDescription = sc.nextLine();
                String at = sc.nextLine();
                Event eTask = new Event(isDone, eDescription, at);

                taskList.add(eTask);
                break;
            default:
                System.out.println("File is invalid!");
                break;
            }
        }
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public Task add(String input, int taskType) throws DukeException {
        Task task;

        switch (taskType) {
        case 0:
            task = new Todo(input);
            break;
        case 1:
            task = new Deadline(input);
            break;
        case 2:
            task = new Event(input);
            break;
        default:
            throw new DukeException("Not a valid Task!!");
        }

        taskList.add(task);
        return task;
    }

    public Task done(int i) throws DukeException{
        if (i > taskList.size() || i < 1) {
            throw new DukeException("Please enter a valid task number!");
        }

        if (!taskList.get(i - 1).markAsDone()) {
            return null;
        }

        return taskList.get(i - 1);
    }

    public Task delete(int i) throws DukeException {
        if (i > taskList.size() || i < 1) {
            throw new DukeException("Please enter a valid task number!");
        }

        return taskList.remove(i - 1);
    }

    public String checkOut() {
        String str = "";

        for (int i = 0; i < taskList.size(); i++) {
            str += taskList.get(i).saveAsString();
        }

        return str;
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";
        int size = taskList.size();

        for (int i = 0; i < size - 1; i++) {
            str += String.format("%d.%s\n", i + 1, taskList.get(i));
        }

        str += String.format("%d.%s", size, taskList.get(size - 1));

        return str;
    }

    public static abstract class Task {
        private String task;
        private boolean isDone;
        private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");

        private String isDone() {
            return (this.isDone ? "X" : " ");
        }

        private boolean markAsDone() {
            if (isDone) {
                System.out.println("This task has already been done.");
                return false;
            } else {
                isDone = true;
                return true;
            }
        }

        @Override
        public String toString() {
            String str = String.format("[%s] %s", this.isDone(), this.task);

            return str;
        }

        public String saveAsString() {
            return isDone + "\n" + task;
        }
    }

    private static class Todo extends Task {
        public Todo(String input) throws DukeException {
            String[] inputs = input.split(" ");

            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please add in description of the Todo task.");
            }

            int tFirst = input.indexOf(" ");
            String tTask = input.substring(tFirst + 1);

            super.task = tTask;
        }

        public Todo(boolean isDone, String description) {
            super.isDone =  isDone;
            super.task = description;
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        @Override
        public String saveAsString() {
            return 0 + "\n" + super.saveAsString() + "\n";
        }
    }

    private static class Deadline extends Task {
        private LocalDateTime by;

        public Deadline(String input) throws DukeException {
            String[] inputs = input.split(" ");
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please add in description of the Deadline task.");
            }

            if (!input.contains("/by")) {
                throw new DukeException("Invalid input! Please add in the deadline for the task.");
            }

            int dFirst = input.indexOf(" ");
            int dSecond = input.indexOf("/");

            try {
                String dTask = input.substring(dFirst + 1, dSecond - 1);
                LocalDateTime by = LocalDateTime.parse(input.substring(dSecond + 4), super.inputFormat);
                this.by = by;
                super.task = dTask;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please follow this format when entering date and time:\n"
                        + "DD/MM/YYYY 24-Hour Time Format" + " e.g. (01/01/2020 2359)");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please enter the Deadline task in this format:\n"
                        + "Deadline task_description /by date_and_time");
            }
        }

        public Deadline(boolean isDone, String description, String by) {
            super.isDone = isDone;
            super.task = description;
            this.by = LocalDateTime.parse(by, super.inputFormat);
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by.format(super.outputFormat) + ")";
        }

        @Override
        public String saveAsString() {
            return 1 + "\n" + super.saveAsString() + "\n" + by.format(super.inputFormat) + "\n";
        }
    }

    private static class Event extends Task {
        private LocalDateTime at;

        public Event(String input) throws DukeException {
            String[] inputs = input.split(" ");
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please add in description of the Event task.");
            }

            if (!input.contains("/at")) {
                throw new DukeException("Invalid input! Please add in information about the event.");
            }

            int eFirst = input.indexOf(" ");
            int eSecond = input.indexOf("/");

            try {
                String eTask = input.substring(eFirst + 1, eSecond - 1);
                LocalDateTime at = LocalDateTime.parse(input.substring(eSecond + 4), super.inputFormat);
                this.at = at;
                super.task = eTask;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please follow this format when entering date and time:\n"
                        + "DD/MM/YYYY 24-Hour Time Format" + " e.g. (01/01/2020 2359)");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please enter the Event task in this format:\n"
                        + "Event task_description /by date_and_time");
            }
        }

        public Event(boolean isDone, String description, String at) {
            super.isDone = isDone;
            super.task = description;
            this.at = LocalDateTime.parse(at, super.inputFormat);
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at.format(super.outputFormat) + ")";
        }

        @Override
        public String saveAsString() {
            return 2 + "\n" + super.saveAsString() + "\n" + at.format(super.inputFormat) + "\n";
        }
    }
}
