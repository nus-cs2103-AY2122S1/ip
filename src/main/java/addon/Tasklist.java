package addon;

import java.time.LocalDateTime;
import java.util.ArrayList;

import addon.Ui.IncorrectFormatException;

/**
 * Handles methods involving the Arraylists of current tasks, as well as containing the Task classes
 */
public class Tasklist {

    private final ArrayList<Task> list;

    public Tasklist(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds Task entry to the Arraylist
     *
     * @param args Array of strings representing in order: type of task, name of task.
     * @param date LocalDateTime object involved with the task, left as an arbitrary null date if Todotask.
     */
    public void addEntry(String[] args, LocalDateTime date) {
        if (args[0].equals("T")) {
            addon.Tasklist.Todo add = new addon.Tasklist.Todo(args[1]);
            this.list.add(add);
            System.out.println(Ui.BAR + "\n    New todo added: " + args[1] + "\n\n    Now you have " + this.list.size()
                    + " tasks in your list\n" + Ui.BAR);
            Storage.appendFile(add);
        } else if (args[0].equals("E")) {
            Event add = new Event(args[1], date);
            this.list.add(add);
            System.out.println(Ui.BAR + "\n    New event added: " + args[1] + " on " + Ui.printDate(date, true)
                    + "\n\n    Now you have " + this.list.size() + " tasks in your list\n" + Ui.BAR);
            Storage.appendFile(add);
        } else {
            Deadline add = new Deadline(args[1], date);
            this.list.add(add);
            System.out.println(Ui.BAR + "\n    New deadline added: " + args[1] + " by " + Ui.printDate(date, true)
                    + "\n\n    Now you have " + this.list.size() + " tasks in your list\n" + Ui.BAR);
            Storage.appendFile(add);
        }
    }
    /**
     * Removes specified task entry from the Arraylist
     *
     * @param num Index number of the item to be removed.
     */
    public void removeEntry(int num) throws IncorrectFormatException {
        if (num >= 1 && num <= this.list.size() + 1) {
            System.out.println(Ui.BAR + "\n    Nice! I've removed this task off the face of the Earth:\n\n    "
                    + (list.get(num - 1)).toString() + "\n    Now you have " + list.size()
                    + " tasks in the list.\n" + Ui.BAR);
            this.list.remove(num - 1);
            Storage.rewriteFile(this.list);
        } else {
            throw new IncorrectFormatException("List does not contain this item number. Try again?");
        }
    }

    /**
     * Changes the "Done" status of the specified task entry from the Arraylist
     *
     * @param num Index number of the item to be changed.
     */
    public void changeDone(int num) throws IncorrectFormatException {
        if (num >= 1 && num <= this.list.size() + 1) {
            (list.get(num - 1)).markDone();
            System.out.println(Ui.BAR + "\n     Nice! I've marked the following as "
                    + ((list.get(num - 1)).isDone ? "done: " : "undone: ") + "\n     "
                    + (list.get(num - 1)).toString() + "\n" + Ui.BAR);
            Storage.rewriteFile(this.list);
        } else {
            throw new IncorrectFormatException("List does not contain this item number. Try again?");
        }
    }

    /**
     * Lists out the current entries in the Arraylist.
     */
    public void listEntries() {
        System.out.println(Ui.BAR);
        if (this.list.size() == 0) {
            System.out.println("    LIST EMPTY.");
        } else {
            System.out.println("    Here is your current list:\n");
            for (int i = 1; i <= this.list.size(); ++i) {
                System.out.println("    " + i + "." + (list.get(i - 1)).toString());
            }
        }
        System.out.println(Ui.BAR);
    }

    /**
     * Lists out the current entries in the Arraylist that have the same date as the one specified.
     *
     * @param date LocalDateTime object of date that is to be queried.
     */
    public void filterDates(LocalDateTime date) {
        System.out.println(Ui.BAR + "\n    Here are your " + Ui.printDate(date, false) + " tasks: \n");
        for (Task i : list) {
            if (i instanceof Event) {
                Event o = (Event) i;
                if (o.date.getYear() == date.getYear() && o.date.getMonth().equals(date.getMonth())
                        && o.date.getDayOfMonth() == date.getDayOfMonth()) {
                    System.out.println("    " + i.toString());
                }
            } else if (i instanceof Deadline) {
                Deadline o = (Deadline) i;
                if (o.date.getYear() == date.getYear() && o.date.getMonth().equals(date.getMonth())
                        && o.date.getDayOfMonth() == date.getDayOfMonth()) {
                    System.out.println("    " + i.toString());
                }
            }
        }
        System.out.println(Ui.BAR + "\n");
    }

    /**
     * Lists out the current entries in the Arraylist that contain the specified keyword.
     *
     * @param keyword Keyword to be queried
     */
    public void filterNames(String keyword) {
        System.out.println(Ui.BAR + "\n    Here are your tasks with the word \"" + keyword + "\": \n");
        for (Task i : list) {
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
        System.out.println(Ui.BAR + "\n");
    }

    /**
     * Wipes the Arraylist and savefile clear.
     */
    public void clearList() {
        this.list.clear();
        System.out.println(Ui.BAR + "\n    LIST CLEARED\n" + Ui.BAR);
        Storage.rewriteFile(this.list);
    }

    /**
     * Parent class Task, contains doneness and task name
     */
    public static class Task {

        protected String description;
        protected boolean isDone;

        /**
         * Task constructor.
         * @param description Name of task.
         */
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

        /**
         * Deadline constructor.
         * @param description Name of deadline.
         * @param by Deadline of task.
         */
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

        /**
         * Todo constructor.
         * @param description Name of task.
         */
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

        /**
         * Event constructor.
         * @param description Name of event.
         * @param at Date of event.
         */
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
