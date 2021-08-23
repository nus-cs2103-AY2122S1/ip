import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    
    private Ui ui;
    private Storage storage;
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
    }
    
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
    
    public void run() {
        ArrayList<Task> taskList = storage.load();

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
                            storage.setDBEntryDone(toSetDone.databaseEntry());
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
                            storage.deleteDBEntry(deleted.databaseEntry());
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
                        storage.addDBEntry(newTask.databaseEntry());
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
}
