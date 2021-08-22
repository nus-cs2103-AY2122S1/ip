import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class stores all TaskList of the user.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";

    /**
     * Adds a new task according to instruction
     *
     * @param t input from the user
     * @throws DukeException if the input is invalid
     */
    public void addTask(String t) throws DukeException{
        String[] ss = t.split(" ");
        switch (ss[0]) {
            case("todo"):
                String i = "";
                for (int q = 1; q < ss.length;q++) {
                    i += ss[q];
                    if (q != ss.length - 1) {
                        i += " ";
                    }
                }
                if (i != "") {
                    Todo todo = new Todo(i);
                    this.taskList.add(todo);
                    noteAdded(todo);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case("deadline"):
                int j = findTime("/by", ss);
                String[] info = getInfo(j, ss);
                if (info[0] != "" && info[1] != "") {
                    Deadline ddl;
                    if (info[1].length() == 10) {
                        LocalDate date = getDate(info[1]);
                        ddl = new Deadline(info[0], date);
                    } else if (info[1].length() == 16) {
                        LocalDateTime time = getTime(info[1]);
                        ddl = new Deadline(info[0], time);
                    } else {
                        throw new DukeException("Please enter time in the form of dd/MM/yyyy HH:mm or dd/MM/yyyy.");
                    }
                    this.taskList.add(ddl);
                    noteAdded(ddl);
                } else {
                    throw new DukeException("☹ OOPS!!! The description and time of a deadline cannot be empty.");
                }
                break;
            case("event"):
                int k = findTime("/at", ss);
                String[] info2 = getInfo(k, ss);
                if (info2[0] != "" && info2[1] != "") {
                    Event e;
                    if (info2[1].length() >= 10) {
                        LocalDate date = getDate(info2[1].substring(0, 10));
                        String time="";
                        if (info2[1].length()>=11) {
                            time = info2[1].substring(11);
                        }
                        e = new Event(info2[0], date, time);
                    } else {
                        throw new DukeException("Please enter time in the form of dd/MM/yyyy time.");
                    }
                    this.taskList.add(e);
                    noteAdded(e);
                } else {
                    throw new DukeException("☹ OOPS!!! The description and time of an event cannot be empty.");
                }
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void noteAdded(Task t) {
        int total = taskList.size();
        String sOrNot = "";
        if (total <= 1) {
            sOrNot = "task";
        } else {
            sOrNot = "TaskList";
        }
        Duke.saveFile();
        Ui.sayAdd(t, total, sOrNot);
    }

    /**
     * The toString method to print all TaskList in the list.
     */
    public void printTaskList() {
        System.out.println(div);
        System.out.println(ind2 + "Here are the TaskList in your list:");
        Ui.printTasks(taskList);
        System.out.println(div);
    }

    /**
     * The method to complete a task
     *
     * @param pos position of the task to be completed
     * @throws DukeException if the position is invalid throws exceptions
     */
    public void complete(int pos) throws DukeException {
        if (this.taskList.size()>pos-1 && pos > 0) {
            String p = this.taskList.get(pos-1).finished();
            Duke.saveFile();
            Ui.sayDone(p);
        } else {
            throw new DukeException("☹ OOPS!!! There isn't a task with index " + pos + " in your list.");
        }
    }

    /**
     * The method to delete a task
     *
     * @param pos position of the task to be deleted
     * @throws DukeException if the position is invalid throws exceptions
     */
    public void delete(int pos) throws DukeException {
        if (this.taskList.size()>pos-1 && pos > 0) {
            Task deleted = this.taskList.get(pos-1);
            int total = taskList.size();
            String sOrNot = "";
            if (total <= 1) {
                sOrNot = "task";
            } else {
                sOrNot = "TaskList";
            }
            this.taskList.remove(deleted);
            Duke.saveFile();
            Ui.sayDelete(deleted, total, sOrNot);
        } else {
            throw new DukeException("☹ OOPS!!! There isn't a task with index " + pos + " in your list.");
        }
    }

    private static int findTime(String s, String[] arr) {
        for (int i = 0; i < arr.length;i++) {
            if (arr[i].equals(s)) {
                return i;
            }
        }
        return 0;
    }

    private static String[] getInfo(int j, String[] ss) {
        String[] result = new String[2];
        String name = "";
        String time = "";
        if (j != 0) {
            int counter = 1;
            while (counter < j) {
                name += ss[counter];
                if (counter != j - 1) {
                    name += " ";
                }
                counter++;
            }
            counter++;
            while(counter < ss.length) {
                time += ss[counter];
                if (counter != ss.length - 1) {
                    time += " ";
                }
                counter ++;
            }
        }
            result[0] = name;
            result[1] = time;
            return result;
    }

    /**
     * Loads TaskList previously stored in local hard disk into the arraylist.
     *
     * @param f the file read from disk
     */
    public static void loadList(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String next = s.nextLine();
            String[] n = next.split(" \\| ");
            switch (n[0]) {
                case("T"):
                    Todo t = new Todo(n[2]);
                    int completed = Integer.parseInt(n[1]);
                    if (completed == 1) {
                        t.setFinish();
                    }
                    taskList.add(t);
                    break;
                case("E"):
                    DateTimeFormatter ff = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String time = "";
                    if (n.length> 4) {
                        time = n[4];
                    }
                    Event e = new Event(n[2], LocalDate.parse(n[3], ff), time);
                    int c = Integer.parseInt(n[1]);
                    if (c == 1) {
                        e.setFinish();
                    }
                    taskList.add(e);
                    break;
                case("D"):
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    Deadline d;
                    if (n[3].length() == 10) {
                        d = new Deadline(n[2], LocalDate.parse(n[3], formatter2));
                    } else {
                        d = new Deadline(n[2], LocalDateTime.parse(n[3], formatter));
                    }
                    int cd = Integer.parseInt(n[1]);
                    if (cd == 1) {
                        d.setFinish();
                    }
                    taskList.add(d);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Gets all the TaskList listed.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    private LocalDateTime getTime(String t) throws DukeException{
        LocalDateTime dateTime;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dateTime = LocalDateTime.parse(t, formatter);
        } catch (DateTimeParseException d) {
            throw new DukeException("Please enter time in the form of dd/MM/yyyy HH:mm.");
        }

        return dateTime;
    }

    private LocalDate getDate(String t) throws DukeException{
        LocalDate date;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(t, formatter);
        } catch (DateTimeParseException d) {
            throw new DukeException("Please enter time in the form of dd/MM/yyyy.");
        }
        return date;
    }

    /**
     * Returns all TaskList on a single day
     *
     * @param s string indicating the date
     * @return an arraylist of all relevant TaskList
     * @throws DukeException if the date format is incorrect
     */
    public static ArrayList<Task> getOnADay(String s) throws DukeException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate target;
        try {
            target = LocalDate.parse(s, formatter);
        } catch (DateTimeParseException d) {
            throw new DukeException("Please enter date in the form of dd/MM/yyyy.");
        }
        ArrayList<Task> result = new ArrayList<>();
        for (Task t: taskList) {
            if (target.equals(t.getDate())) {
                result.add(t);
            }
        }
        System.out.println(div);
        System.out.println(ind2 + "Here are the TaskList on " + target + ": ");
        Ui.printTasks(result);
        System.out.println(div);
        return result;
    }


    /**
     * Checks validity of the task position to be finished
     *
     * @param next input from user
     */
    public void getDone(String next) {
        String emp = next.substring(4, 5);
        if (emp.equals(" ")) {
            String index = next.substring(5);
            try {
                int position = Integer.parseInt(index);
                try {
                    this.complete(position);
                } catch (DukeException dukeException) {
                    Ui.showError(dukeException);
                }
            } catch (NumberFormatException numberFormatException) {
                Ui.myPrint("☹ OOPS!!! Please enter a valid number, such as done 3");
            }
        } else {
            Ui.myPrint("☹ OOPS!!! Please enter a valid number, such as done 3");
        }
    }

    /**
     * Checks validity of the task position to be deleted
     *
     * @param next input from user
     */
    public void getDelete(String next) {
        String emp = next.substring(6, 7);
        if (emp.equals(" ")) {
            String index = next.substring(7);
            try {
                int position = Integer.parseInt(index);
                try {
                    this.delete(position);
                } catch (DukeException dukeException) {
                    Ui.showError(dukeException);
                }
            } catch (NumberFormatException numberFormatException) {
                Ui.myPrint("☹ OOPS!!! Please enter a valid number, such as delete 3");
            }
        } else {
            Ui.myPrint("☹ OOPS!!! Please enter a valid number, such as delete 3");
        }
    }
}
