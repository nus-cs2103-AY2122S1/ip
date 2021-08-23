import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.File;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.lang.StringBuilder;

public class Duke {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private static TaskList tList = new TaskList();
    private static String selfIntro = "Hello, I'm Duck\nWhat do you need?";
    private static String goodBye = "See ya next time! *quack* *quack* *quack*";
    private static String logo =
            "    __\n" +
            "___( o)>\n" +
            "\\ <_. )\n" +
            " `---'   hjw";

    private static String tasksFile = "data/tasks.txt";

    private static void printLine(String content) {
        System.out.println(
                "------------------------------------------------\n"
                        + "Duck says:\n"
                        + content
                        + "\n------------------------------------------------"
        );

    }

    private static void greet() {
        //System.out.println(logo);
        printLine(selfIntro);
    }

    private static void sayBye() {
        printLine(goodBye);
    }

    private static boolean exit() {
        File f = new File(tasksFile);

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                printLine("Something went wrong while creating data file, with error message:\n"
                        + e.getMessage()
                );
                printLine(f.getAbsolutePath());
                return false;
            }
        }

        FileWriter fw;

        try {
            fw = new FileWriter(tasksFile);
            for (int i = 0; i < tList.getListSize(); i++) {
                fw.write(tList.getTaskSaveFormat(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            printLine("Something went wrong while saving your tasks, with error message:\n"
                    + e.getMessage()
            );
            return false;
        }
        Duke.sayBye();
        return true;
    }

    private static String addTask(String input, TaskType tType) throws DukeCommandException {
        String msg;
        String[] inputArr;
        String[] taskArr;

        switch (tType) {
        case TODO:
            inputArr = input.split(" ", 2);
            if(inputArr.length != 2 || inputArr[1].equals("")) {
                throw new DukeCommandException("todo");
            }
            msg = tList.addTask(new ToDo(inputArr[1]));
            break;
            //Possible exception: no string after todo.
        case DEADLINE:
            inputArr = input.split(" ", 2);
            if (inputArr.length != 2) {
                throw new DukeCommandException("deadline");
            }
            taskArr = inputArr[1].split(" /by ", 2);
            if (taskArr.length != 2 || taskArr[1].equals("")) {
                throw new DukeCommandException("deadline");
            }
            try {
                msg = tList.addTask(new Deadline(taskArr[0], DukeDate.parseDateInput(taskArr[1])));
            } catch (DukeArgumentException e) {
                msg = e.getMessage();
            }
            break;
        case EVENT:
            inputArr = input.split(" ", 2);
            if (inputArr.length != 2) {
                throw new DukeCommandException("event");
            }
            taskArr = inputArr[1].split(" /at ", 2);
            if (taskArr.length != 2 || taskArr[1].equals("")) {
                throw new DukeCommandException("event");
            }
            try {
                msg = tList.addTask(new Event(taskArr[0], DukeDate.parseDateInput(taskArr[1])));
            } catch (DukeArgumentException e) {
                msg = e.getMessage();
            }
            break;
        default:
            msg = "If you see this, something has went terribly wrong";
        }

        return msg;
    }

    private static String loadTasks() {
        File f = new File(tasksFile);
        String[] taskInfo;
        String nextTask;
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                nextTask = s.nextLine();
                if (nextTask.length() == 0) {
                    break;
                }
                taskInfo = nextTask.split("[|]", 4);
                switch (taskInfo[0]) {
                case "T":
                    tList.addTask(new ToDo(taskInfo[2],taskInfo[1] == "c"));
                    break;
                case "D":
                    tList.addTask(new Deadline(taskInfo[2], taskInfo[3], taskInfo[1] == "c"));
                    break;
                case "E":
                    tList.addTask(new Event(taskInfo[2], taskInfo[3], taskInfo[1] == "c"));
                    break;
                default:
                    return "you may have a corrupted/edited save file. Tasks partially loaded";
                }
            }
        } catch (FileNotFoundException e) {
            return "Save file not found";
        }
        return "Tasks successfully loaded!";
    }

    public static void runDuck() {
        loadTasks();
        Scanner sc = new Scanner(System.in);
        String userInput;
        Duke.greet();
        boolean isDone = false;
        String[] inputArr;
        while (!isDone) {
            userInput = sc.nextLine();
            inputArr = userInput.split(" ", 2);

            try {
                if (inputArr.length == 1) {
                    switch (inputArr[0]) {
                    case "bye":
                        isDone = Duke.exit();
                        break;
                    case "list":
                        printLine(tList.getTasks());
                        break;
                    case "todo":
                        throw new DukeCommandException("todo");
                    case "deadline":
                        throw new DukeCommandException("deadline");
                    case "done":
                        throw new DukeCommandException("done");
                    case "event":
                        throw new DukeCommandException("event");
                    case "delete":
                        throw new DukeCommandException("delete");
                    default:
                        throw new DukeCommandException(inputArr[0]);
                    }
                } else {
                    switch (inputArr[0]) {
                        case "done":
                            try {
                                printLine(
                                        tList.markComplete(
                                                Integer.parseInt(inputArr[1])
                                        )
                                );
                            } catch (NumberFormatException nfe) {
                                printLine("Incorrect argument for command Done, must be an integer");
                            }
                            break;
                        case "todo":
                            printLine(addTask(userInput, TaskType.TODO));
                            break;
                        case "deadline":
                            printLine(addTask(userInput, TaskType.DEADLINE));
                            break;
                        case "event":
                            printLine(addTask(userInput, TaskType.EVENT));
                            break;
                        case "delete":
                            try {
                                printLine(
                                        tList.deleteTask(
                                                Integer.parseInt(inputArr[1])
                                        )
                                );
                            } catch (NumberFormatException nfe) {
                                printLine("Incorrect argument for command Delete, must be an integer");
                            }
                            break;
                        default:
                            throw new DukeCommandException(inputArr[0]);
                    }
                }
            } catch (DukeCommandException dce) {
                printLine(dce.getMsg());
            }
        }
    }

    public static void main(String[] args) {
        runDuck();
    }
}
