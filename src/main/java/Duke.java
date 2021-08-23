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
    
    private Ui ui;
    
    public Duke() {
        ui = new Ui();
    }
    
    public static void main(String[] args) {
        new Duke().run();
    }
    
    public void run() {
        ArrayList<Task> taskList = launchDuke();

        Scanner input = new Scanner(System.in);
        boolean isBye = false;
        int listLength = taskList.size();

        ui.showWelcome();

        while (!isBye) {
            try {
                ui.showInput();
                String text = input.nextLine().trim();

                ui.showOpenLine();

                if (text.split("\\s+")[0].equals("bye")) { // bye function: exits the loop, ends process
                    ui.showBye();
                    isBye = true;

                } else if (text.split("\\s+")[0].equals("list")) { // list function: iterates through taskList, prints Tasks' listEntry
                    ui.showList(taskList, listLength);

                } else if (text.split("\\s+")[0].equals("done")) { // done function: sets a task to done
                    if (text.split("\\s+").length == 1) {
                        throw new DukeException(DukeExceptionType.INVALIDINDEX);
                    } else {
                        int toSet = Integer.parseInt(text.split("\\s+")[1]);
                        if (toSet > listLength || toSet < 1) {
                            throw new DukeException(DukeExceptionType.INVALIDINDEX);
                        } else {
                            Task toSetDone = taskList.get(toSet - 1);
                            setDBEntryDone(toSetDone.databaseEntry());
                            toSetDone.setDone();
                            ui.showDone(toSetDone);
                        }
                    }

                } else if (text.split("\\s+")[0].equals("delete")) { // delete function: delete a task
                    if (text.split("\\s+").length == 1) {
                        throw new DukeException(DukeExceptionType.INVALIDINDEX);
                    } else {
                        int toDelete = Integer.parseInt(text.split("\\s+")[1]);
                        if (toDelete > listLength || toDelete < 1) {
                            throw new DukeException(DukeExceptionType.INVALIDINDEX);
                        } else {
                            Task deleted = taskList.remove(toDelete - 1);
                            deleteDBEntry(deleted.databaseEntry());
                            ui.showDelete(deleted, --listLength);
                        }
                    }

                } else if (text.split("\\s+")[0].equals("find")) {
                    if (text.split("\\s+").length == 1) {
                        throw new DukeException(DukeExceptionType.INVALIDFIND);
                    } else {
                        LocalDate desiredDate = LocalDate.parse(text.split("\\s+")[1]);
                        ui.showFind(taskList, listLength, desiredDate);
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
                        ui.showAdd(newTask, listLength);
                    }
                }

            } catch (DukeException e) {
                ui.showException(e);

            } catch (NumberFormatException e) { // throws if index given in done/delete functions is not an integer
                ui.showException(new DukeException(DukeExceptionType.INVALIDINDEX));

            } catch (DateTimeParseException e) {
                ui.showException(new DukeException(DukeExceptionType.INVALIDDATETIME));

            } finally {
                ui.showCloseLine();
            }
        }

        input.close();
    }
    
    public ArrayList<Task> launchDuke() {
        ArrayList<Task> savedTasks = new ArrayList<>(100);
        
        try {
            File data = new File("data");
            File duke = new File("data/duke.txt");

            ui.showOpenLine();
            ui.showInitialise();
            
            if (data.mkdir()) {
                ui.showNewDataDirectory();
            }
            if (duke.createNewFile()) {
                ui.showNewHardDisk();
            }
            
            ui.showCloseLine();
            
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
            ui.showException(e);
            
        } catch (IOException e) {
            ui.showException(new DukeException(DukeExceptionType.DB_LAUNCH));
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
            ui.showException(new DukeException(DukeExceptionType.DB_ADD));
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
            ui.showException(new DukeException(DukeExceptionType.DB_DONE));
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
            ui.showException(new DukeException(DukeExceptionType.DB_DELETE));
        }
    }
}
