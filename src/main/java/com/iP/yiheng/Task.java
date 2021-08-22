package com.iP.yiheng;

import java.io.IOException;
import java.util.ArrayList;

public class Task {
    protected String description;
    private boolean isDone;
    private static final TaskFile file = new TaskFile();
    private static ArrayList<Task> tasks = new ArrayList<>();

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
        private String time;

        public Deadline(String description, String time) {
            super(description);
            this.time = time;
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
        }

        @Override
        public String toString() {
            return "[D]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "(" + time + ")";
        }
    }

    private class Event extends Task {
        private String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
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
        }

        @Override
        public String toString() {
            return "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "(" + time + ")";
        }
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
        file.loadFile(tasks);
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
