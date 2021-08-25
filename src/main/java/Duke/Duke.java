package Duke;

import Duke.Commands.Command;
import Duke.Commands.EditCommand;
import Duke.Errors.DukeError;
import Duke.Parser.CustomDateFormatter;
import Duke.Storage.Storage;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Task.ToDo;
import Duke.Ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static boolean byeCheck(String response) {
        return response.equals("bye") || response.startsWith("bye ");
    }

    public static String pluralOrNo(int cap) {
        return cap <= 1 ? "" : "s";
    }

    public static LocalDate getLocalDateFromString(String str) {
        CustomDateFormatter formatter = new CustomDateFormatter();
        return formatter.formatWithoutTime(str);
    }

    public static Task getTask(String task) {
        Character taskType = task.charAt(0);
        switch(taskType) {
        case 'T':
            String[] todoArray = task.split("_~_");
            ToDo newToDo = new ToDo(todoArray[2]);
            if (todoArray[1].equals("1")) {
                newToDo.markDone();
            }
            return newToDo;
        case 'D':
            String[] deadlineArray = task.split("_~_");
            Deadline newDeadline = new Deadline(deadlineArray[2], getLocalDateFromString(deadlineArray[3]));
            if (deadlineArray[1].equals("1")) {
                newDeadline.markDone();
            }
            return newDeadline;
        case 'E':
            String[] eventArray = task.split("_~_");
            Event newEvent = new Event(eventArray[2], getLocalDateFromString(eventArray[3]));
            if (eventArray[1].equals("1")) {
                newEvent.markDone();
            }
            return newEvent;
        default:
            return new ToDo("Please delete this entry. I am a bug!");
        }

    }

    public static ArrayList<Task> initialiseArrayList() {
        ArrayList<Task> newArrayList = new ArrayList<>(100);
        try {
            File taskList = new File("./data/duke.txt");
            Scanner myScanner = new Scanner(taskList);

            while (myScanner.hasNextLine()) {
                String nextLine = myScanner.nextLine();
                if (nextLine.length() > 0) {
                    newArrayList.add(getTask(nextLine));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found! Check that ~/data/duke.txt is there.");
            e.printStackTrace();
        }
        return newArrayList;
    }

    public static String listCleaner(String input) {
        if (input.strip().length() > 4) {
            return "error 1";
        } else {
            return "list";
        }
    }

    public static String todoCleaner(String input) {
        if (input.strip().length() < 5) {
            return "error 2";
        } else {
            return input.strip();
        }
    }

    public static String deadlineCleaner(String input) {
        if (input.split("/by")[0].strip().length() == 8) {
            return "error 3";
        } else if (!input.contains("/by") || input.split("/by").length < 2 || input.split("/by")[1].strip().length() < 1) {
            return "error 4";
        } else {
            if (getLocalDateFromString(input.split("/by")[1].strip()) == null) {
                return "error 12";
            }
            return input.strip();
        }
    }

    public static String eventCleaner(String input) {
        if (input.split("/at")[0].strip().length() == 5) {
            return "error 5";
        } else if (!input.contains("/at") || input.split("/at").length < 2 || input.split("/at")[1].strip().length() < 1) {
            return "error 6";
        } else {
            if (getLocalDateFromString(input.split("/at")[1].strip()) == null) {
                return "error 12";
            }
            return input.strip();
        }
    }

    public static String markDoneCleaner(String input, int currentCapacity) {
        if (input.strip().split(" ").length < 2) {
            return "error 7";
        } else if (input.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = input.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return "error 9";
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity || intToCheck < 1) {
                return "error 9";
            } else {
                intToCheck -= 1;
                return "done " + intToCheck;
            }
        }
    }

    public static String markUndoCleaner(String input, int currentCapacity) {
        if (input.strip().split(" ").length < 2) {
            return "error 7";
        } else if (input.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = input.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return "error 9";
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity || intToCheck < 1) {
                return "error 9";
            } else {
                intToCheck -= 1;
                return "undo " + intToCheck;
            }
        }
    }

    public static String deleteCleaner(String input, int currentCapacity) {
        if (input.strip().split(" ").length < 2) {
            return "error 7";
        } else if (input.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = input.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return "error 9";
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity || intToCheck < 1) {
                return "error 9";
            } else {
                intToCheck -= 1;
                return "delete " + intToCheck;
            }
        }
    }

    public static String inputCleaner(String input, int currentCapacity) {
        if (input.length() < 1) {
            return "";
        } else if (input.contains("\n")) {
            return "error 0";
        } else if (input.contains("_~_")) {
            return "error 11";
        }
        String[] stringArray = input.split(" ");
        String firstWord = stringArray[0];
        switch(firstWord) {
        case "list":
            return listCleaner(input);
        case "todo":
            if (currentCapacity >= 100) {
                return "error 10";
            }
            return todoCleaner(input);
        case "deadline":
            if (currentCapacity >= 100) {
                return "error 10";
            }
            return deadlineCleaner(input);
        case "event":
            if (currentCapacity >= 100) {
                return "error 10";
            }
            return eventCleaner(input);
        case "done":
            return markDoneCleaner(input, currentCapacity);
        case "undo":
            return markUndoCleaner(input, currentCapacity);
        case "delete":
            return deleteCleaner(input, currentCapacity);
        default:
            return "error -1";
        }
    }

    public static Task todoParser(String input) {
        String description = input.substring(5).strip();
        return addTaskToFile(new ToDo(description));
    }

    public static Task eventParser(String input) {
        String withoutEvent = input.substring(6).strip();
        String[] eventArray = withoutEvent.split("/at");
        String description = eventArray[0].strip();
        LocalDate date = getLocalDateFromString(eventArray[1].strip());
        return addTaskToFile(new Event(description, date));
    }

    public static Task deadlineParser(String input) {
        String withoutDeadline = input.substring(9).strip();
        String[] deadlineArray = withoutDeadline.split("/by");
        String description = deadlineArray[0].strip();
        LocalDate date = getLocalDateFromString(deadlineArray[1].strip());
        return addTaskToFile(new Deadline(description, date));
    }

    public static ArrayList<Task> markDoneParser(String input, ArrayList<Task> arrayList) {
        int index = Integer.valueOf(input.substring(5));
        arrayList.get(index).markDone();
        editTaskOnFile(index, "done");
        System.out.printf("Nice! I've marked this task as done:\n    %s\n", arrayList.get(index));
        return arrayList;
    }

    public static ArrayList<Task> markUndoParser(String input, ArrayList<Task> arrayList) {
        int index = Integer.valueOf(input.substring(5));
        arrayList.get(index).markUndone();
        editTaskOnFile(index, "undo");
        System.out.printf("I've marked this task as undone:\n    %s\n", arrayList.get(index));
        return arrayList;
    }

    public static ArrayList<Task> deleteParser(String input, ArrayList<Task> arrayList) {
        int index = Integer.valueOf(input.substring(7));
        Task deletedTask = arrayList.remove((int) index);
        editTaskOnFile(index, "delete");
        int size = arrayList.size();
        System.out.printf("Noted. Ive removed this task:\n    %s\nNow you have %d task%s in the list\n", deletedTask, size, pluralOrNo(size));
        return arrayList;
    }

    public static String newTaskAddedMessage(Task newTask, int size) {
        String message = "Got it. I've added this task:\n    %s\nNow you have %d task%s in the list";
        return String.format(message, newTask, size, pluralOrNo(size));
    }

    public static ArrayList<Task> inputParser(String input, ArrayList<Task> arrayList) {
        String[] stringArray = input.split(" ");
        String firstWord = stringArray[0];
        int size = arrayList.size();
        switch(firstWord) {
        case "todo":
            Task newToDo = todoParser(input);
            arrayList.add(newToDo);
            System.out.println(newTaskAddedMessage(newToDo, size += 1));
            return arrayList;
        case "event":
            Task newEvent = eventParser(input);
            arrayList.add(newEvent);
            System.out.println(newTaskAddedMessage(newEvent, ++size));
            return arrayList;
        case "deadline":
            Task newDeadline = deadlineParser(input);
            arrayList.add(newDeadline);
            System.out.println(newTaskAddedMessage(newDeadline, ++size));
            return arrayList;
        case "done":
            arrayList = markDoneParser(input, arrayList);
            return arrayList;
        case "undo":
            arrayList = markUndoParser(input, arrayList);
            return arrayList;
        case "delete":
            arrayList = deleteParser(input, arrayList);
            return arrayList;
        default:
            return null;
        }
    }

    public static DukeError getError(int code) {
        switch(code) {
            case -1:
                return DukeError.INVALID_COMMAND;
            case 0:
                return DukeError.ESCAPE_CHARACTER;
            case 1:
                return DukeError.POLLUTED_LIST_COMMAND;
            case 2:
                return DukeError.EMPTY_TODO_DESCRIPTION;
            case 3:
                return DukeError.EMPTY_DEADLINE_DESCRIPTION;
            case 4:
                return DukeError.EMPTY_DEADLINE_DATE;
            case 5:
                return DukeError.EMPTY_EVENT_DESCRIPTION;
            case 6:
                return DukeError.EMPTY_EVENT_DATE;
            case 7:
                return DukeError.EMPTY_LIST_NUMBER;
            case 8:
                return DukeError.TOO_MANY_INPUTS;
            case 9:
                return DukeError.INVALID_LIST_NUMBER;
            case 10:
                return DukeError.LIST_FULL;
            case 11:
                return DukeError.SEPARATOR_DETECTED;
            case 12:
                return DukeError.INVALID_DATE_FORMAT;
            default:
                return null;
        }
    }

    public static void editTaskOnFile(int lineNumber, String type) {
        int actualLineNumber = lineNumber + 1;
        try {
            File myFile = new File("./data/duke.txt");
            Scanner myScanner = new Scanner(myFile);
            String newFileString = "";
            int currentLine = 1;
            while (myScanner.hasNextLine()) {
                String nextLine = myScanner.nextLine();
                if (currentLine == actualLineNumber) {
                    switch (type) {
                    case "done":
                        char[] doneArray = nextLine.toCharArray();
                        doneArray[4] = '1';
                        newFileString += String.valueOf(doneArray) + "\n";
                        break;
                    case "undo":
                        char[] undoArray = nextLine.toCharArray();
                        undoArray[4] = '0';
                        newFileString += String.valueOf(undoArray) + "\n";
                        break;
                    case "delete":
                        newFileString += "";
                        break;
                    default: newFileString += "";
                    }
                } else {
                    newFileString += nextLine + "\n";
                }
                currentLine += 1;
            }
            myScanner.close();
            if (newFileString.endsWith("\n")) {
                newFileString = newFileString.substring(0, newFileString.length() - 1);
            }
            FileWriter myWriter = new FileWriter(myFile);
            myWriter.write(newFileString);
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException caught~");
            e.printStackTrace();
        }
    }

    public static Task addTaskToFile(Task task) {
        try {
            File myFile = new File("./data/duke.txt");
            Scanner myScanner = new Scanner(myFile);
            if (myScanner.hasNextLine()) {
                FileWriter myWriter = new FileWriter(myFile, true);
                myWriter.write("\n" + task.getDataString());
                myWriter.close();
            } else {
                FileWriter myWriter = new FileWriter(myFile, true);
                myWriter.write(task.getDataString());
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("IOException from FileWriter caught.");
            e.printStackTrace();
        }
        return task;
    }

    public static void errorHandler(String input) {
        int code = Integer.valueOf(input.substring((6)));
        System.out.println(getError(code));
    }

    public static void listPrinter(ArrayList<Task> arrayList) {
        int currentCapacity = arrayList.size();
        if (currentCapacity > 0) {
            String toPrint = "Here are the tasks in your list:\n";
            for (int i = 0; i < currentCapacity; i++) {
                Task task = arrayList.get(i);
                toPrint += String.format("%d. %s\n", i + 1, task);
            }
            System.out.print(toPrint);
        } else {
            System.out.println("You have no tasks!");
        }
    }

//    private Ui ui;
//    private Storage storage;
//    private TaskList tasks;
//
//    public Duke(String filePath) {
//        ui = new Ui();
//
//    }
//
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            String fullCommand = ui.readCommand();
//        }
//
//        String temp = ui.readCommand();
//        System.out.println(temp);
//    }


//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        ArrayList<Task> userTaskList = initialiseArrayList();
        int currentCapacity = userTaskList.size();
        System.out.println("Henlo, Duke here! How can I be of assistance?");
        while (true) {
            String response = myScanner.nextLine();
            if (byeCheck(response)) {
                break;
            } else {
                String cleanInput = inputCleaner(response, currentCapacity);
                if (cleanInput.startsWith("error ")) {
                    errorHandler(cleanInput);
                    continue;
                } else if (cleanInput.startsWith("list")) {
                    listPrinter(userTaskList);
                } else {
                    userTaskList = inputParser(cleanInput, userTaskList);
                    currentCapacity = userTaskList.size();
                    continue;
                }
            }
        }
        System.out.println("Goodbye!");

    }
}
