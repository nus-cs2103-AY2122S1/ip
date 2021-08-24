package addon;

import addon.Ui.IncorrectFormatException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tasklist {

    protected ArrayList<Task> list;

    public Tasklist(ArrayList<Task> list) {
        this.list = list;
    }

    public void addEntry(String[] args, LocalDateTime date) {
        if (args[0].equals("T")) {
            addon.Tasklist.Todo add = new addon.Tasklist.Todo(args[1]);
            this.list.add(add);
            System.out.println(Ui.bar + "\n     New todo added: " + args[1] + "\n\n     Now you have " + this.list.size() + " tasks in your list\n" + Ui.bar);
            Storage.appendFile(add);
        } else if (args[0].equals("E")) {
            Event add = new Event(args[1], date);
            this.list.add(add);
            System.out.println(Ui.bar + "\n     New event added: " + args[1] + " on " + Ui.printDate(date, true) + "\n\n     Now you have " + this.list.size() + " tasks in your list\n" + Ui.bar);
            Storage.appendFile(add);
        } else {
            Deadline add = new Deadline(args[1], date);
            this.list.add(add);
            System.out.println(Ui.bar + "\n     New deadline added: " + args[1] + " by " + Ui.printDate(date, true) + "\n\n     Now you have " + this.list.size() + " tasks in your list\n" + Ui.bar);
            Storage.appendFile(add);
        }
    }

    public void removeEntry(int num) throws IncorrectFormatException {
        if (num >= 1 && num <= this.list.size() + 1) {
            System.out.println(Ui.bar + "\n    Nice! I've removed this task off the face of the Earth:\n\n    " + (list.get(num - 1)).toString() + "\n\n    Now you have " + (list.size() - 1) + " tasks in the list.\n" + Ui.bar);
            this.list.remove(num - 1);
            Storage.rewriteFile(this.list);
        } else {
            throw new IncorrectFormatException("List does not contain this item number. Try again?");
        }
    }

    public void changeDone(int num) throws IncorrectFormatException {
        if (num >= 1 && num <= this.list.size() + 1) {
            (list.get(num - 1)).markDone();
            System.out.println(Ui.bar + "\n     Nice! I've marked the following as " + ((list.get(num - 1)).isDone ? "undone: " : "done: ") + "\n\n     " + (list.get(num - 1)).toString() + "\n" + Ui.bar);
            Storage.rewriteFile(this.list);
        } else {
            throw new IncorrectFormatException("List does not contain this item number. Try again?");
        }
    }

    public void listEntries() {
        System.out.println(Ui.bar);
        if (this.list.size() == 0) {
            System.out.println("    LIST EMPTY.");
        } else {
            System.out.println("    Here is your current list:\n");
            for (int i = 1; i <= this.list.size(); ++i) {
                System.out.println("    " + i + "." + (list.get(i - 1)).toString());
            }
        }
        System.out.println(Ui.bar);
    }

    public void filterDates(LocalDateTime date) {
        System.out.println(Ui.bar + "\n    Here are your " + Ui.printDate(date, false) + " tasks: \n");
        for(Task i : list) {
            if (i instanceof Event) {
                Event o = (Event)i;
                if (o.date.getYear() == date.getYear() && o.date.getMonth().equals(date.getMonth()) && o.date.getDayOfMonth() == date.getDayOfMonth()) {
                    System.out.println("    " + i.toString());
                }
            } else if (i instanceof Deadline) {
                Deadline o = (Deadline)i;
                if (o.date.getYear() == date.getYear() && o.date.getMonth().equals(date.getMonth()) && o.date.getDayOfMonth() == date.getDayOfMonth()) {
                    System.out.println("    " + i.toString());
                }
            }
        }
        System.out.println(Ui.bar + "\n");
    }
    public void filterNames(String keyword) {
        System.out.println(Ui.bar + "\n    Here are your tasks with the word \"" + keyword + "\": \n");
        for(Task i : list) {
            String[] words = i.description.split(" ");
            for (String s : words) {
                boolean found = false;
                if (s.equalsIgnoreCase(keyword)) {
                    found = true;
                }
                if (found) {
                    System.out.println("    " + i.toString());
                }
                break;
            }
        }
        System.out.println(Ui.bar + "\n");
    }

    public void clearList() {
        this.list.clear();
        System.out.println(Ui.bar + "\n    LIST CLEARED\n" + Ui.bar);
        Storage.rewriteFile(this.list);
    }

    /**
     * Parent class Task, contains doneness and task name
     */
    public static class Task {

        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String toString() {
            return ((isDone ? "[X] " : "[ ] ") + this.description);
        }

        public void markDone() {
            isDone = !isDone;
        }
    }

    /**
     * Child class of Task, adds deadline field.
     */
    public static class Deadline extends Task {

        protected LocalDateTime date;

        public Deadline(String description, LocalDateTime by) {
            super(description);
            this.date = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + Ui.printDate(date, true) + ")";
        }
    }

    /**
     * Child class of Task
     */
    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    /**
     * Child class of Task, adds date field.
     */
    public static class Event extends Task {

        protected LocalDateTime date;

        public Event(String description, LocalDateTime at) {
            super(description);
            this.date = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + Ui.printDate(date, true) + ")";
        }
    }
}
