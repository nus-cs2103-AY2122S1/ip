import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    
    public Duke() {
        
    }
    
    public static void main(String[] args) {
        new Duke().run();
    }
    
    public void run() {
        ArrayList<Task> taskList = launchDuke();

        Scanner input = new Scanner(System.in);
        boolean isBye = false;
        int listLength = taskList.size();

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
                            setDBEntryDone(toSetDone.databaseEntry());
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
                            deleteDBEntry(deleted.databaseEntry());
                            System.out.print("  Noted. I've removed this task:\n    "
                                    + deleted.listEntry()
                                    + "\n  Now you have " + --listLength + " tasks in the list.\n");
                        }
                    }

                } else if (text.split("\\s+")[0].equals("find")) {
                    if (text.split("\\s+").length == 1) {
                        throw new DukeException(DukeExceptionType.INVALIDFIND);
                    } else {
                        LocalDate desiredDate = LocalDate.parse(text.split("\\s+")[1]);
                        System.out.println("  Here are the tasks for the given day:");
                        for (int i = 0; i < listLength; ++i) {
                            Task currTask = taskList.get(i);
                            if (currTask.isTodayTask(desiredDate)) {
                                System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
                            }
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
                                String[] deadline = details[1].split(" ");
                                if (deadline.length == 1) {
                                    newTask = new Deadline(details[0], LocalDate.parse(details[1]));
                                } else {
                                    newTask = new Deadline(details[0], LocalDate.parse(deadline[0]),
                                            LocalTime.parse(deadline[1]));
                                }
                            }
                            break;
                        }
                        case "event": {
                            String[] details = taskDetails.split(" /at ");

                            if (details.length == 1) { // period of event not given
                                throw new DukeException(DukeExceptionType.EVENTPERIOD);
                            } else {
                                String[] periodRange = details[1].split(" ");
                                if (periodRange.length == 2) {
                                    newTask = new Event(details[0], LocalDate.parse(periodRange[0]),
                                            LocalDate.parse(periodRange[1]));
                                } else if (periodRange.length == 3) {
                                    newTask = new Event(details[0], LocalDate.parse(periodRange[0]),
                                            LocalTime.parse(periodRange[1]), LocalTime.parse(periodRange[2]));
                                } else if (periodRange.length == 4) {
                                    newTask = new Event(details[0],
                                            LocalDate.parse(periodRange[0]), LocalTime.parse(periodRange[1]),
                                            LocalDate.parse(periodRange[2]), LocalTime.parse(periodRange[3]));
                                } else {
                                    throw new DukeException(DukeExceptionType.INVALIDPERIOD);
                                }
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
                        addDBEntry(newTask.databaseEntry());
                        System.out.print("  Got it. I've added this task:\n    "
                                + newTask.listEntry()
                                + "\n  Now you have " + listLength + " tasks in the list.\n");
                    }
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());

            } catch (NumberFormatException e) { // throws if index given in done/delete functions is not an integer
                System.out.println(new DukeException(DukeExceptionType.INVALIDDONE).getMessage());

            } catch (DateTimeParseException e) {
                System.out.println(new DukeException(DukeExceptionType.INVALIDDATETIME).getMessage());

            } finally {
                System.out.println("  ____________________________________________________________\n");
            }
        }

        input.close();
    }
    
    public ArrayList<Task> launchDuke() {
        ArrayList<Task> savedTasks = new ArrayList<>(100);
        
        try {
            File data = new File("data");
            File duke = new File("data/duke.txt");

            System.out.println("  ____________________________________________________________");
            System.out.println("  Loading Duke...");
            
            if (data.mkdir()) {
                System.out.println("  Data directory does not exist, it has been created!");
            }
            if (duke.createNewFile()) {
                System.out.println("  Hard disk does not exist, a new one has been created!");
            }
            
            System.out.println("  ____________________________________________________________");
            
            BufferedReader reader = new BufferedReader(new FileReader(duke));
            String currLine = reader.readLine();

            while (currLine != null) {
                String[] taskString = currLine.split(" \\| ", 3);
                Task newTask;
                
                if (taskString[0].equals("D")) {
                    String[] deadlineDetails = taskString[2].split(" ");
                    if (deadlineDetails.length == 2) {
                        newTask = new Deadline(deadlineDetails[0], LocalDate.parse(deadlineDetails[1]));
                    } else if (deadlineDetails.length == 3) {
                        newTask = new Deadline(deadlineDetails[0], 
                                LocalDate.parse(deadlineDetails[1]), LocalTime.parse(deadlineDetails[2]));
                    } else {
                        throw new DukeException(DukeExceptionType.DB_READ);
                    }
                    
                } else if (taskString[0].equals("E")) {
                    String[] periodDetails = taskString[2].split(" ");
                    if (periodDetails.length == 3) {
                        newTask = new Event(periodDetails[0], 
                                LocalDate.parse(periodDetails[1]), LocalDate.parse(periodDetails[2]));
                    } else if (periodDetails.length == 4) {
                        newTask = new Event(periodDetails[0], LocalDate.parse(periodDetails[1]), 
                                LocalTime.parse(periodDetails[2]), LocalTime.parse(periodDetails[3]));
                    } else if (periodDetails.length == 5) {
                        newTask = new Event(periodDetails[0], 
                                LocalDate.parse(periodDetails[1]), LocalTime.parse(periodDetails[2]), 
                                LocalDate.parse(periodDetails[3]), LocalTime.parse(periodDetails[4]));
                    } else {
                        throw new DukeException(DukeExceptionType.DB_READ);
                    }
                    
                    
                } else if (taskString[0].equals("T")) {
                    newTask = new Todo(taskString[2]);

                } else {
                    throw new DukeException(DukeExceptionType.DB_READ);
                }
                
                if (Integer.parseInt(taskString[1]) == 1) {
                    newTask.setDone();
                }
                
                savedTasks.add(newTask);
                currLine = reader.readLine();
            }

        } catch (DukeException e) {
            System.out.println(e.getMessage());
            
        } catch (IOException e) {
            System.out.println(new DukeException(DukeExceptionType.DB_LAUNCH).getMessage());
        }
        
        return savedTasks;
    }

    public void addDBEntry(String s) {
        try {
            File duke = new File("data/duke.txt");
            FileWriter writer = new FileWriter(duke, true);
            writer.write(s + "\n");
            writer.close();
            
        } catch (IOException e){
            System.out.println(new DukeException(DukeExceptionType.DB_ADD).getMessage());
        }
    }

    public void setDBEntryDone(String s) {
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

    public void deleteDBEntry(String s) {
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
