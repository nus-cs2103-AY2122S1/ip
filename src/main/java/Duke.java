import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class of the ChatBot
 */
public class Duke {

    final static String DIRECTORY_PATHNAME = "./data";
    final static String FILE_PATHNAME = DIRECTORY_PATHNAME + "/task_list.txt";

    public static BufferedWriter WRITER;
    public static BufferedReader READER;

    public enum Command {
        TODO, DEADLINE, EVENT, LIST, DONE, DELETE, COMMANDS, BYE
    }

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static Command stringToCommand(String s) throws DukeException {
        switch (s) {
            case "list":
                return Command.LIST;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "done":
                return Command.DONE;
            case "delete":
                return Command.DELETE;
            case "commands":
                return Command.COMMANDS;
            case "bye":
                return Command.BYE;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static TaskType stringToTaskType(String s) throws DukeException {
        switch (s) {
            case "TD":
                return TaskType.TODO;
            case "DL":
                return TaskType.DEADLINE;
            case "EV":
                return TaskType.EVENT;
            default:
                throw new DukeException("Invalid TaskType detected: " + s);
        }
    }

    public static TaskType classNameToTaskType(String cn) throws DukeException {
        switch (cn) {
            case "Todo":
                return TaskType.TODO;
            case "Deadline":
                return TaskType.DEADLINE;
            case "Event":
                return TaskType.EVENT;
            default:
                throw new DukeException("Invalid ClassName detected: " + cn);
        }
    }

    public static String taskTypeToString(TaskType t) throws DukeException {
        switch(t) {
            case TODO:
                return "TD";
            case DEADLINE:
                return "DL";
            case EVENT:
                return "EV";
            default:
                throw new DukeException("Invalid TaskType detected: " + t);
        }
    }

    public static void printError(Exception e) throws DialogException {
        if (Dialog.have(e.toString())) {
            System.out.println(Dialog.getDialog(e.toString()));
        } else {
            Dialog errorMessage = Dialog.generate(e.toString());
            errorMessage.add("☹ OOPS!!! " + e.getMessage());
            System.out.println(errorMessage);
        }
    }

    public static void addTaskByType(TaskDialog list, TaskType type, boolean isDone, String description, String time) {
        try {
            switch (type) {
                case TODO:
                    list.silentAddTask(new Todo(description, isDone));
                    break;
                case EVENT:
                    list.silentAddTask(new Event(description, time, isDone));
                    break;
                case DEADLINE:
                    list.silentAddTask(new Deadline(description, time, isDone));
                    break;
                default:
                    throw new DukeException("addTask Unsuccessful");
            }
        } catch (DialogException e) {
            System.out.println("Internal Conflict: addTaskByType - " + e);
        }
    }



    public static void main(String[] args) throws DialogException {
        Scanner sc = new Scanner(System.in);

        Dialog greeting = Dialog.generate("greeting");


        // not sure if we are allowed to change the file name
        String logo =
                "     ___       __       __    ______  _______\n" +
                        "        /   \\     |  |     |  |  /      ||   ____|\n" +
                        "       /  ^  \\    |  |     |  | |  ,----'|  |__\n" +
                        "      /  /_\\  \\   |  |     |  | |  |     |   __|\n" +
                        "     /  _____  \\  |  `----.|  | |  `----.|  |____\n" +
                        "    /__/     \\__\\ |_______||__|  \\______||_______|\n";

        greeting.add(logo);
        greeting.add("Hello! I'm Alice, your personal assistant");
        greeting.add("What can I do for you?");
        Dialog commandsList = Dialog.generate("commands");
        commandsList.add("This is the following commands, I can perform:\n");
        commandsList.add("1. 'todo <task description>' - add a todo task to the list");
        commandsList.add("2. 'deadline <task description> /by <by when>' - add a deadline task with specific deadline");
        commandsList.add("3. 'event <task description> /at <at when>' - add an event task with specific time");
        commandsList.add("4. 'list' - show the current task list");
        commandsList.add("5. 'done <task index>' - mark that task as done");
        commandsList.add("6. 'delete <task index>' - delete that task from the list");
        commandsList.add("7. 'commands' - show this current command window");
        commandsList.add("8. 'bye' - end session");
        System.out.println(greeting);
        System.out.println(commandsList);
        Command command;
        String input;
        boolean directoryExists = java.nio.file.Files.exists(Paths.get(DIRECTORY_PATHNAME));

        try {
            if (!directoryExists) {
                Files.createDirectory(Paths.get(DIRECTORY_PATHNAME));
            }
            WRITER = new BufferedWriter(new FileWriter(FILE_PATHNAME, true));
            READER = new BufferedReader(new FileReader(FILE_PATHNAME));
        } catch (Exception e) {
            printError(e);
        }

        TaskDialog list = (TaskDialog) TaskDialog.generate("list");

        READER.lines().forEach((line) -> {
            TaskType type = stringToTaskType(line.substring(0, 2));
            int index1 = line.indexOf("|");
            String isDoneString = line.substring(index1 + 2, index1 + 3);
            int index2 = line.indexOf("|", index1 + 1);
            String description;
            String time = "";
            if (type == TaskType.DEADLINE || type == TaskType.EVENT) {
                int index3 = line.indexOf("|", index2 + 1);
                description = line.substring(index2 + 2, index3 - 1);
                time = line.substring(index3 + 2);
            } else {
                description = line.substring(index2 + 2);
            }
            addTaskByType(list, type, isDoneString.equals("1"), description, time);
        });

        System.out.println(list);

        while (true) {
            try {
                System.out.print("> ");
                input = sc.nextLine();
                command = stringToCommand(input.split(" ")[0]);
                break;
            } catch (DukeException e) {
                Duke.printError(e);
            }
        }


        while (command != Command.BYE) {
            switch (command) {
                case LIST:
                    System.out.println(list);
                    break;
                case TODO: {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                        }
                        list.addTask(new Todo(input.substring(("todo ").length())));
                    } catch (DialogException | EmptyDescriptionException e) {
                        Duke.printError(e);
                    }
                    break;
                }
                case DEADLINE: {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                        } else if (!input.contains("/by")) {
                            throw new EmptyTaggerException("No /by tagger found.");
                        }
                        String dDescription = input.substring(("deadline ").length(), input.indexOf("/"));
                        String by = input.substring(input.indexOf("/by ") + "/by ".length());
                        list.addTask(new Deadline(dDescription, by));
                    } catch (DialogException | EmptyDescriptionException | EmptyTaggerException e) {
                        Duke.printError(e);
                    }
                    break;
                }
                case EVENT: {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyDescriptionException("The description of an event cannot be empty.");
                        } else if (!input.contains("/at")) {
                            throw new EmptyTaggerException("No /at tagger found.");
                        }
                        String eDescription = input.substring(("event ").length(), input.indexOf("/"));
                        String at = input.substring(input.indexOf("/at ") + "/at ".length());
                        list.addTask(new Event(eDescription, at));
                    } catch (DialogException | EmptyDescriptionException | EmptyTaggerException e) {
                        Duke.printError(e);
                    }
                    break;
                }
                case DONE: {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyIndexException("The index of done cannot be empty.");
                        } else if (Integer.parseInt(input.split(" ")[1]) <= 0 || Integer.parseInt(input.split(" ")[1]) > list.taskLength()) {
                            if (list.taskLength() == 0) {
                                throw new InvalidIndexException("Looks like your list is currently empty.");
                            } else {
                                throw new InvalidIndexException("Your list index can only be from 1 to " + list.taskLength() + ".");
                            }
                        } else if (input.split(" ").length > 2) {
                            throw new InvalidArgumentException("The number of arguments seems to exceed for command done.");
                        }
                        list.markTaskAsDone(Integer.parseInt(input.substring(("done ").length())) - 1);
                    } catch (EmptyIndexException | InvalidArgumentException | InvalidIndexException e) {
                        Duke.printError(e);
                    }
                    break;
                }
                case DELETE: {
                    try {
                        if (input.split(" ").length == 1) {
                            throw new EmptyIndexException("The index of delete cannot be empty.");
                        } else if (Integer.parseInt(input.split(" ")[1]) <= 0 || Integer.parseInt(input.split(" ")[1]) > list.taskLength()) {
                            if (list.taskLength() == 0) {
                                throw new InvalidIndexException("Looks like your list is currently empty.");
                            } else {
                                throw new InvalidIndexException("Your list index can only be from 1 to " + list.taskLength() + ".");
                            }
                        } else if (input.split(" ").length > 2) {
                            throw new InvalidArgumentException("The number of arguments seems to exceed for command delete.");
                        }
                        list.deleteTaskByIndex(Integer.parseInt(input.substring(("delete ").length())) - 1);
                    } catch (EmptyIndexException | InvalidArgumentException | InvalidIndexException e) {
                        Duke.printError(e);
                    }
                    break;
                }
                case COMMANDS:
                    System.out.println(commandsList);
                    break;
                default:
                    // UNREACHABLE: already checked via StringToCommand
                    break;
            }
            while (true) {
                try {
                    System.out.print("> ");
                    input = sc.nextLine();
                    command = stringToCommand(input.split(" ")[0]);
                    break;
                } catch (DukeException e) {
                    printError(e);
                }
            }
        }

        Dialog bye = Dialog.generate("bye");
        bye.add("Bye. Hope to see you again soon!");
        System.out.println(bye);


        try {
            list.saveTasks(FILE_PATHNAME);
            READER.close();
            WRITER.close();
        } catch (Exception e) {
            printError(e);
        }

    }
}
