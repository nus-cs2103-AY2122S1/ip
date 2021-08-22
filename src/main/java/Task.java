import java.util.ArrayList;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    private boolean isDone;
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
        }

        @Override
        public String toString() {
            return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    private class Deadline extends Task {
        private String time;
        private LocalDate localDate;
        private String deadLineTiming;

        public Deadline(String description, String time) {
            super(description);
            this.time = time;
            localDate = Task.this.findDate(time);
            deadLineTiming = Task.this.findTime(time);
            if (deadLineTiming != null) Task.this.convertTime(deadLineTiming);
        }

        @Override
        public String toString() {
            return localDate == null
            ? "[D]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "(" + time.replace(" ", ": ") + ")"
            : "[D]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "by " + localDate.getDayOfMonth() + " " + localDate.getMonth()
                    + " " + localDate.getYear() + " " + (deadLineTiming == null ? "" : deadLineTiming);
        }
    }

    private class Event extends Task {
        private String time;
        private LocalDate localDate;
        private String deadLineTiming;

        public Event(String description, String time) {
            super(description);
            this.time = time;
            localDate = Task.this.findDate(time);
            deadLineTiming = Task.this.findTime(time);
            if (deadLineTiming != null) Task.this.convertTime(deadLineTiming);
        }

        @Override
        public String toString() {
            return localDate == null
                    ? "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "(" + time.replace(" ", ": ") + ")"
                    : "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "by " + localDate.getDayOfMonth() + " " + localDate.getMonth()
                    + " " + localDate.getYear() + " " + (deadLineTiming == null ? "" : deadLineTiming);
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
        Integer time = Integer.parseInt(input);
        String postfix;
        String prefix;
        if (time < 1200) {
            time = time / 100;
            prefix = Integer.toString(time);
            postfix = "AM";
        } else {
            time = (time - 1200) / 100;
            prefix = Integer.toString(time);
            postfix = "PM";
        }
        return prefix + " " + postfix;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void taskDone() {
        this.isDone = true;
    }

    public void markDone(int index) {
        tasks.get(index).taskDone();
    }

    public void delete(int index) {
        tasks.remove(index);
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

    public static Task retrieveTask(int index) {
        return tasks.get(index);
    }

    public static int listLength() {
        return tasks.size();
    }

    public static void displayList() {
        System.out.println("\nHere are the tasks in your list:\n--------------------");
        if (tasks.size() == 0) {
            System.out.println("List is empty!");
        } else {
            int index = 1;
            for (Task t : tasks) {
                System.out.println(index + ". " + t);
                index++;
            }
        }
    }
}
