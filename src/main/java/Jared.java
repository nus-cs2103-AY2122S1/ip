import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Jared {
    private static ArrayList<Task> history = new ArrayList<Task>();
    private static final String filename = "data.txt";
    /**
     * Main function
     */
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                "Hello! I'm Jared\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        Scanner scan = new Scanner(System.in);
        System.out.println(intro);

        try {
            loadData();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        while (scan.hasNextLine()) {
            String next = scan.nextLine();
            String[] inputArr = next.split(" ",2);
            String command = inputArr[0];
            try {
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                    scan.close();
                } else if (command.equals("list")) {
                    list();
                } else if (command.equals("done")) {
                    done(next);
                } else if (command.equals("delete")) {
                    delete(next);
                } else {
                    add(command, next);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static void add(String command, String next) throws DukeException {
        Task newTask;
        String desc;
        String dateStr;
        String[] dateTime;
        LocalDate date;
        LocalTime time;

        if (command.equals("todo")) {
            try {
                desc = next.split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            newTask = new Todo(desc);
        } else if (command.equals("deadline")) {
            try {
                String body = next.split(" ", 2)[1];
                desc = body.split("/by", 2)[0].trim();
                dateStr = body.split("/by", 2)[1].trim();
                dateTime = dateStr.split(" ");
                String d = dateTime[0];
                date = LocalDate.parse(d);

                if (dateTime.length > 1) {
                    String t = dateTime[1];
                    time = LocalTime.parse(t);
                    newTask = new Deadline(desc, date, time);
                } else {
                    newTask = new Deadline(desc, date);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
                throw new DukeException("Invalid date. Please enter the date (yyyy-mm-dd)");
            }
        } else if (command.equals("event")) {
            try {
                String body = next.split(" ", 2)[1];
                desc = body.split("/at", 2)[0].trim();
                dateStr = body.split("/at", 2)[1].trim();
                dateTime = dateStr.split(" ");
                String d = dateTime[0];
                date = LocalDate.parse(d);

                if (dateTime.length > 1) {
                    String t = dateTime[1];
                    time = LocalTime.parse(t);
                    newTask = new Event(desc, date, time);
                } else {
                    newTask = new Event(desc, date);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date and time. Please enter the date (yyyy-mm-dd)");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        history.add(newTask);
        saveData();

        System.out.println(String.format("Got it. I've added this task:\n" +
                        "%s\nNow you have %d tasks in the list.",
                newTask, history.size())
        );
    }

    private static void list() {
        String res = "Here are the tasks in your list:\n";
        for (int i = 0; i < history.size(); i++) {
            Task currTask = history.get(i);
            res += String.format("%d. %s\n", i + 1, currTask.toString());
        }
        System.out.println(res);
    }

    private static void done(String next) throws DukeException {
        int taskNum;
        Task currTask;
        try {
            taskNum = Integer.valueOf(next.split(" ", 2)[1]);
            currTask = history.get(taskNum-1);
        } catch (Exception e) {
            throw new DukeException("Invalid task number provided.");
        }
        currTask.markDone();
        saveData();
        String res = String.format("Nice! I've marked this task as done:\n%s",
                currTask.toString());
        System.out.println(res);
    }

    /**
     * @param next String entered by the user.
     * @throws DukeException
     */

    private static void delete(String next) throws DukeException {
        int taskNum;
        Task currTask;
        int index;
        try {
            taskNum = Integer.valueOf(next.split(" ", 2)[1]);
            index = taskNum - 1;
            currTask = history.get(index);
        } catch (Exception e) {
            throw new DukeException("Invalid task number provided.");
        }
        history.remove(index);
        saveData();
        System.out.println(String.format("Noted. I've removed this task:\n" +
                "%s\nNow you have %d tasks in the list.", currTask.toString(), history.size()));
    }

    private static void loadData() throws DukeException {
        File f = new File(filename);

        try {
            f.createNewFile();
            Scanner reader = new Scanner(f);
            while (reader.hasNext()) {
                String data = reader.nextLine();
                String[] dataArr = data.split(" _ ");
                Task t;
                LocalDate date;
                LocalTime time;
                switch (dataArr[0]) {
                    case "T":
                        t = new Todo(dataArr[2]);
                        break;
                    case "D":
                        date = LocalDate.parse(dataArr[3]);
                        try {
                            time = LocalTime.parse(dataArr[4]);
                            t = new Deadline(dataArr[2], date, time);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            t = new Deadline(dataArr[2], date);
                        }
                        break;
                    case "E":
                        date = LocalDate.parse(dataArr[3]);
                        try {
                            time = LocalTime.parse(dataArr[4]);
                            t = new Event(dataArr[2], date, time);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            t = new Event(dataArr[2], date);
                        }
                        break;
                    default:
                        System.out.println("task failed to load");
                        continue;
                }
                if (dataArr[1].equals("1")) {
                    t.markDone();
                }
                history.add(t);
            }
        } catch (IOException e) {
            throw new DukeException("Error");
        }
    }

    private static void saveData() {
        try {
            FileWriter fw = new FileWriter(filename);
            String res = "";
            for (Task t : history) {
                res += (t.saveFormat() + "\n");
            }
            fw.write(res);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

