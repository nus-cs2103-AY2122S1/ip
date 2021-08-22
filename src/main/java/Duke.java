import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try {
            File data = new File("data");
            File duke = new File("data/duke.txt");

            if (data.mkdir()) {
                System.out.println("  Data directory does not exist, it has been created!");
            }
            if (duke.createNewFile()) {
                System.out.println("  Hard disk does not exist, a new one has been created!");
            }

            Scanner input = new Scanner(System.in);
            boolean isBye = false;
            ArrayList<Task> taskList = new ArrayList<>(100);
            int listLength = 0;

            System.out.println("  ____________________________________________________________");
            System.out.print("  Hello! I'm Duck.\n  What's up?\n");
            System.out.println("  ____________________________________________________________\n");

            while (!isBye) {
                try {
                    System.out.print("> ");
                    String text = input.nextLine().trim();

                    System.out.println("  ____________________________________________________________");

                    if (text.split("\\s+")[0].equals("bye")) { // bye function: exits the loop, ends process
                        System.out.println("  See you next time!");
                        isBye = true;

                    } else if (text.split("\\s+")[0].equals("list")) { // list function: iterates through taskList, prints Tasks' listEntry
                        System.out.println("  Here are the tasks in your list:");
                        for (int i = 0; i < listLength; ++i) {
                            System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
                        }

                    } else if (text.split("\\s+")[0].equals("done")) { // done function: sets a task to done
                        if (text.split("\\s+").length == 1) {
                            throw new DukeException(DukeExceptionType.INVALIDDONE);
                        } else {
                            int toSet = Integer.parseInt(text.split("\\s+")[1]);
                            if (toSet > listLength || toSet < 1) {
                                throw new DukeException(DukeExceptionType.INVALIDDONE);
                            } else {
                                Task toSetDone = taskList.get(toSet - 1);
                                Duke.setDBEntryDone(toSetDone.databaseEntry());
                                toSetDone.setDone();
                                System.out.print("  Nice! I've marked this task as done:\n    "
                                        + toSetDone.listEntry() + "\n");
                            }
                        }

                    } else if (text.split("\\s+")[0].equals("delete")) { // delete function: delete a task
                        if (text.split("\\s+").length == 1) {
                            throw new DukeException(DukeExceptionType.INVALIDDELETE);
                        } else {
                            int toDelete = Integer.parseInt(text.split("\\s+")[1]);
                            if (toDelete > listLength || toDelete < 1) {
                                throw new DukeException(DukeExceptionType.INVALIDDELETE);
                            } else {
                                Task deleted = taskList.remove(toDelete - 1);
                                Duke.deleteDBEntry(deleted.databaseEntry());
                                System.out.print("  Noted. I've removed this task:\n    "
                                        + deleted.listEntry()
                                        + "\n  Now you have " + --listLength + " tasks in the list.\n");
                            }
                        }

                    } else { // task function: add tasks
                        if (text.split("\\s+").length == 1) { // task details not given or not valid task
                            switch (text) {
                            case "deadline":
                                throw new DukeException(DukeExceptionType.DEADLINEDESC);

                            case "event":
                                throw new DukeException(DukeExceptionType.EVENTDESC);

                            case "todo":
                                throw new DukeException(DukeExceptionType.TODODESC);

                            default:
                                throw new DukeException(DukeExceptionType.INVALIDINPUT);
                            }

                        } else {
                            Task newTask;

                            // split text string, first string will be the task type and second string will be task details
                            String[] taskString = text.split("\\s+", 2);
                            String taskType = taskString[0];
                            String taskDetails = taskString[1];

                            // determine type of task, create new task
                            switch (taskType) {
                            case "deadline": {
                                String[] details = taskDetails.split(" /by ");

                                if (details.length == 1) { // time of deadline not given
                                    throw new DukeException(DukeExceptionType.DEADLINETIME);
                                } else {
                                    newTask = new Deadline(details[0], details[1]);
                                }
                                break;
                            }
                            case "event": {
                                String[] details = taskDetails.split(" /at ");

                                if (details.length == 1) { // period of event not given
                                    throw new DukeException(DukeExceptionType.EVENTPERIOD);
                                } else {
                                    newTask = new Event(details[0], details[1]);
                                }
                                break;
                            }
                            case "todo":
                                newTask = new Todo(taskDetails);
                                break;
                            default:  // taskName is invalid
                                throw new DukeException(DukeExceptionType.INVALIDINPUT);
                            }
                            // add task to taskList
                            taskList.add(listLength++, newTask);
                            Duke.addDBEntry(newTask.databaseEntry());
                            System.out.print("  Got it. I've added this task:\n    "
                                    + newTask.listEntry()
                                    + "\n  Now you have " + listLength + " tasks in the list.\n");
                        }
                    }

                } catch (DukeException e) {
                    System.out.println(e.getMessage());

                } catch (NumberFormatException e) { // throws if index given in done/delete functions is not an integer
                    System.out.println(new DukeException(DukeExceptionType.INVALIDDONE).getMessage());

                } finally {
                    System.out.println("  ____________________________________________________________\n");
                }
            }

            input.close();
            
        } catch (IOException e) {
            System.out.println(new DukeException(DukeExceptionType.DB_LAUNCH).getMessage());
        }
    }

    public static void addDBEntry(String s) {
        try {
            File duke = new File("data/duke.txt");
            FileWriter writer = new FileWriter(duke, true);
            writer.write(s + "\n");
            writer.close();
        } catch (IOException e){
            System.out.println(new DukeException(DukeExceptionType.DB_ADD).getMessage());
        }
    }

    public static void setDBEntryDone(String s) {
        try {
            File updated = new File("data/updated.txt");
            File duke = new File("data/duke.txt");
            updated.createNewFile();

            FileWriter writer = new FileWriter(updated, true);

            BufferedReader reader = new BufferedReader(new FileReader(duke));
            String currLine = reader.readLine();

            while (currLine != null) {
                if (currLine.equals(s)) {
                    String[] toSetDone = currLine.split(" \\| ", 3);
                    writer.write(toSetDone[0] + " | 1 | " + toSetDone[2] + "\n");
                } else {
                    writer.write(currLine + "\n");
                }
                currLine = reader.readLine();
            }

            writer.close();

            duke.delete();
            updated.renameTo(duke);
        } catch (IOException e) {
            System.out.println(new DukeException(DukeExceptionType.DB_DONE).getMessage());
        }
    }

    public static void deleteDBEntry(String s) {
        try {
            File updated = new File("data/updated.txt");
            File duke = new File("data/duke.txt");
            updated.createNewFile();

            FileWriter writer = new FileWriter(updated, true);

            BufferedReader reader = new BufferedReader(new FileReader(duke));
            String currLine = reader.readLine();

            while (currLine != null) {
                if (!currLine.equals(s)) {
                    writer.write(currLine + "\n");
                }
                currLine = reader.readLine();
            }

            writer.close();

            duke.delete();
            updated.renameTo(duke);
        } catch (IOException e) {
            System.out.println(new DukeException(DukeExceptionType.DB_DELETE).getMessage());
        }
    }
}
