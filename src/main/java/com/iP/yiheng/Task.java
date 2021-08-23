package com.iP.yiheng;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    private boolean isDone;
    private static final TaskFile file = new TaskFile();
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private Task(String description) {
        this.description = description;
        this.isDone = false;
        tasks.add(this);
    }

    public Task() {}

    private class Todo extends Task {
        public Todo(String description) {
            super(description);
            try {
                file.saveTask(this); // Saves task to hard disk
                System.out.println("\nDuke: Got it. I have added this task:\n" +
                        this + "\nNow you have " +
                        Task.listLength() + " tasks in the list.");
            } catch (IOException e) {
                System.out.println("File not found. Please ensure you have the data folder with tasks.txt.");
            }
        }

        public Todo(String description, boolean isExisting) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    private class Deadline extends Task {
        private final String time;
        private LocalDate localDate;
        private String deadLineTiming;

        public Deadline(String description, String time) {
            super(description);
            this.time = time;
            localDate = Task.this.findDate(time);
            deadLineTiming = Task.this.findTime(time);
            if (deadLineTiming != null) deadLineTiming = Task.this.convertTime(deadLineTiming);
            try {
                file.saveTask(this); // Saves task to hard disk
                System.out.println("\nDuke: Got it. I have added this task:\n" +
                        this + "\nNow you have " +
                        Task.listLength() + " tasks in the list.");
            } catch (IOException e) {
                System.out.println("File not found. Please ensure you have the data folder with tasks.txt.");
            }
        }

        public Deadline(String description, String time, boolean isExisting) {
            super(description);
            this.time = time;
            Task.this.parseTime(time,localDate, deadLineTiming);
        }

        @Override
        public String toString() {
            String status = this.getStatusIcon();
            if (localDate == null) {
                return "[D]" + "[" + status + "] " + this.description
                        + "(" + time + ")";
            } else {
                String endTime = deadLineTiming == null ? "" : " "+ deadLineTiming;
                return "[D]" + "[" + status + "] " + this.description
                        + "(" + localDate.getDayOfMonth() + " " + localDate.getMonth()
                        + " " + localDate.getYear() + endTime + ")";
            }
        }
    }

    private class Event extends Task {
        private final String time;
        private LocalDate localDate;
        private String deadLineTiming;

        public Event(String description, String time) {
            super(description);
            this.time = time;
            localDate = Task.this.findDate(time);
            deadLineTiming = Task.this.findTime(time);
            if (deadLineTiming != null) deadLineTiming = Task.this.convertTime(deadLineTiming);
            try {
                file.saveTask(this); // Saves task to hard disk
                System.out.println("\nDuke: Got it. I have added this task:\n" +
                        this + "\nNow you have " +
                        Task.listLength() + " tasks in the list.");
            } catch (IOException e) {
                System.out.println("File not found. Please ensure you have the data folder with tasks.txt.");
            }
        }

        public Event(String description, String time, boolean isExisting) {
            super(description);
            this.time = time;
            Task.this.parseTime(time,localDate, deadLineTiming);
        }

        @Override
        public String toString() {
            String status = this.getStatusIcon();
            if (localDate == null) {
                return "[E]" + "[" + status + "] " + this.description
                        + "(" + time + ")";
            } else {
                String endTime = deadLineTiming == null ? "" : " "+ deadLineTiming;
                return "[E]" + "[" + status + "] " + this.description
                        + "(" + localDate.getDayOfMonth() + " " + localDate.getMonth()
                        + " " + localDate.getYear() + endTime + ")";
            }
        }
    }

    private LocalDate findDate(String input) {
        String regex = "(\\d{4}-\\d{2}-\\d{2})"; // Regex to find date of the form yyyy-mm-dd
        Matcher m = Pattern.compile(regex).matcher(input);
        if (m.find()) {
            return LocalDate.parse(m.group(1));
        }
        return null;
    }

    private void parseTime(String input, LocalDate ld, String deadlineTiming) {
        String[] parsedTime = input.split(" ");
        String timeFormat = parsedTime[2] + "-"
                            + matchMonth(parsedTime[1]) + "-"
                            + (parsedTime[0].length() == 1 ? "0" + parsedTime[0] : parsedTime[0]);
        if (parsedTime.length > 3) {
            double timing = Double.parseDouble(parsedTime[3]) * 100
                    + (parsedTime[4].equals("PM") ? 1200 : 0);
            int flattenedTiming = (int) timing;
            deadlineTiming = Integer.toString(flattenedTiming);
        }
        ld = LocalDate.parse(timeFormat);
    }

    private final String[] months = new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL",
                                            "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
                                            "OCTOBER", "NOVEMBER", "DECEMBER"};
    private String matchMonth(String month) {
        int index = 0;
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                index = i + 1;
                break;
            }
        }
        return index < 10
                ? "0" + index
                : Integer.toString(index);
    }

    private String findTime(String input) {
        StringBuilder sb = new StringBuilder();
        int index = input.length() - 1;
        for (int i = 0; i < 4; i++) {
            sb.append(input.charAt(index));
            index--;
        }
        sb.reverse();
        String regex = "^\\d{4}$";
        Matcher m = Pattern.compile(regex).matcher(sb.toString());
        if (m.find()) {
            return sb.toString();
        } else {
            return null;
        }
    }

    private String convertTime(String input) {
        double time = Double.parseDouble(input);
        String postfix;
        String prefix;
        if (time < 1300) {
            time = time / 100.0;
            postfix = "AM";
        } else {
            time = (time - 1200.0) / 100.0;
            postfix = "PM";

        }
        prefix = String.format("%.2f", time);
        return prefix + " " + postfix;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void taskDone() {
        this.isDone = true;
        file.overwriteList(tasks);
    }

    public boolean markDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Please enter a proper index!");
            return false;
        } else {
            tasks.get(index).taskDone();
            return true;
        }
    }

    public void delete(int index) {
        tasks.remove(index);
        file.overwriteList(tasks);
    }

    public void add(String input) {
        input = input.trim();
        String emptyDescriptionError = null;
        String emptyTimelineError = null;
        String[] splitTask = input.split(" ", 2);

        switch (splitTask[0]) {
            case "todo":
                if (splitTask.length == 1) {
                    emptyDescriptionError = "todo";
                    break;
                }
                new Todo(splitTask[1]);
                break;
            case "deadline":
                if (splitTask.length == 1) {
                    emptyDescriptionError = "deadline";
                    break;
                }

                String[] splitTime = splitTask[1].split("/", 2);
                if (splitTime.length == 1) {
                    emptyTimelineError = "deadline";
                    break;
                }

                new Deadline(splitTime[0], splitTime[1]);
                break;
            case "event":
                if (splitTask.length == 1) {
                    emptyDescriptionError = "event";
                    break;
                }

                String[] splitTimeEvent = splitTask[1].split("/", 2);
                if (splitTimeEvent.length == 1) {
                    emptyTimelineError = "event";
                    break;
                }

                new Event(splitTimeEvent[0], splitTimeEvent[1]);
                break;
            default:
                throw new DukeException.InvalidCommandException(input);
        }

        if (emptyDescriptionError != null) {
            throw new DukeException.EmptyDescriptionException(emptyDescriptionError);
        } else if (emptyTimelineError != null) {
            throw new DukeException.EmptyTimelineDescription(emptyTimelineError);
        }
    }

    protected void loadArrayList() {
        file.loadFile();
        file.overwriteList(tasks);
    }

    protected void addExisting(char taskChar, char taskStatus, String description, String timeline) {
        Task currentTask;

        if (taskChar == 'T') {
            currentTask = new Todo(description, true);
        } else if (taskChar == 'D') {
            currentTask = new Deadline(description, timeline, true);
        } else {
            currentTask = new Event(description, timeline, true);
        }

        if (taskStatus == 'X') {
            currentTask.taskDone();
        }
    }

    public static Task retrieveTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Please enter a proper index!");
            return null;
        } else {
            return tasks.get(index);
        }
    }

    public static int listLength() {
        return tasks.size();
    }

    public static void displayList() {
        System.out.println("\nHere are the tasks in your list:\n--------------------");
        file.printTaskFile();
    }
}
