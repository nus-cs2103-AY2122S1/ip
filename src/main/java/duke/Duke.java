package duke;

import duke.Command.Command;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }
//    private static ArrayList<Task> taskList = new ArrayList<Task>();
//
//    /**
//     * Prints out each task in the taskList with format.
//     *
//     * @return Nothing.
//     */
//    public static void printTasks() {
//        if (taskList.size() == 0) {
//            System.out.println("You have no tasks!\n");
//        } else {
//            System.out.println("Here are your tasks!");
//            for (int i = 0; i<taskList.size(); i++) {
//                String task = taskList.get(i).toString();
//                System.out.printf("%d.%s%n", i + 1, task);
//            }
//            System.out.println("");
//        }
//    }
//
//    /**
//     * Adds a Todo task to the taskList.
//     * Todos are tasks without any date/time attached to them.
//     *
//     * @param input User input.
//     * @throws DukeException If input does not have a description.
//     */
//    public static void addTodo(String input) throws DukeException {
//        try {
//            String task = input.substring(5);
//            Todo todo = new Todo(task);
//            taskList.add(todo);
//            System.out.println("Okay! I've added this task:\n" + todo.toString());
//            System.out.printf("You have %d task(s) in the list.\n\n", taskList.size());
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new DukeException("The description of a Todo cannot be empty!");
//        }
//    }
//
//    /**
//     * Adds a Deadline task to the taskList.
//     * Deadlines are tasks that need to be done before a specific date/time.
//     *
//     * @param input User input.
//     * @throws DukeException If input format for deadline is invalid.
//     */
//    public static void addDeadline(String input) throws DukeException {
//        try {
//            String task = input.substring(9);
//            int split = task.indexOf("/by");
//            String description = task.substring(0,split);
//            String by = task.substring(split+4);
//            Deadline deadline = new Deadline(description, by);
//            taskList.add(deadline);
//            System.out.println("Okay! I've added this task:\n" + deadline.toString());
//            System.out.printf("You have %d task(s) in the list.\n\n", taskList.size());
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new DukeException("Invalid format. Please try again.\n");
//        } catch (DateTimeParseException e) {
//            throw new DukeException("Please enter the date with format [yyyy-mm-dd].\n");
//        }
//    }
//
//    /**
//     * Adds an Event task to the taskList.
//     * Events are tasks that start and end at a specific time.
//     *
//     * @param input User input.
//     * @throws DukeException If input format for Event is invalid.
//     */
//    public static void addEvent(String input) throws DukeException {
//        try {
//            String task = input.substring(6);
//            int split = task.indexOf("/at");
//            String description = task.substring(0,split);
//            String at = task.substring(split+4);
//            Event event = new Event(description, at);
//            taskList.add(event);
//            System.out.println("Okay! I've added this task:\n" + event.toString());
//            System.out.printf("You have %d task(s) in the list.\n\n", taskList.size());
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new DukeException("Invalid format. Please try again.\n");
//        }  catch (DateTimeParseException e) {
//        throw new DukeException("Please enter the date and time with format [yyyy-mm-dd HH:mm].\n");
//        }
//    }
//
//    /**
//     * Marks a task in the taskList as done.
//     *
//     * @param input User input.
//     * @throws DukeException If task number is outside the range of taskList.
//     */
//    public static void markTask(String input) throws DukeException {
//        try {
//            int index = Integer.parseInt(input.substring(5));
//            Task task = taskList.get(index - 1);
//            task.markDone();
//            System.out.println("Okay! This task has been marked done:");
//            System.out.println(task.toString() + "\n");
//        } catch (IndexOutOfBoundsException | NumberFormatException e) {
//            throw new DukeException("Invalid Task. Please try again.\n");
//        }
//    }
//
//    /**
//     * Removes a task from the taskList.
//     *
//     * @param input User input.
//     * @throws DukeException If task number is outside the range of tasklist.
//     */
//    public static void deleteTask(String input) throws DukeException {
//        try {
//            int index = Integer.parseInt(input.substring(7));
//            Task task = taskList.get(index - 1);
//            taskList.remove(index - 1);
//            System.out.println("Okay! This task has been removed:");
//            System.out.println(task.toString() + "\n");
//        } catch (IndexOutOfBoundsException | NumberFormatException e) {
//            throw new DukeException("Invalid Task. Please try again.\n");
//        }
//    }
//
//    /**
//     * Reads the duke.txt file and loads the tasks into the taskList array.
//     * If the directory or file does not exist, it will be created.
//     *
//     * @throws FileNotFoundException If file is not found.
//     * @throws DukeException If the file has bad format.
//     */
//    public static void loadFile() throws IOException, DukeException {
//        File directory = new File("src/main/data");
//        if (!directory.exists()) {
//            Path path = Paths.get("src/main/data");
//            Files.createDirectories(path);
//        }
//        File file = new File("src/main/data/duke.txt");
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNext()) {
//            String[] data = scanner.nextLine().split("\\|");
//            String taskType = data[0].trim();
//            String statusIcon = data[1].trim();
//            String description = data[2].trim();
//            if (taskType.equalsIgnoreCase("T")) {
//                Todo todo = new Todo(description.trim());
//                if (statusIcon.equals("1")) {
//                    todo.markDone();
//                }
//                taskList.add(todo);
//            } else if (taskType.equalsIgnoreCase("D")) {
//                String by = data[3].trim();
//                Deadline deadline = new Deadline(description, by);
//                if (statusIcon.equals("1")) {
//                    deadline.markDone();
//                }
//                taskList.add(deadline);
//            } else if (taskType.equalsIgnoreCase("E")) {
//                String at = data[3].trim();
//                Event event = new Event(description, at);
//                if (statusIcon.equals("1")) {
//                    event.markDone();
//                }
//                taskList.add(event);
//            } else {
//                throw new DukeException("Uh oh there's something wrong with the file.");
//            }
//        }
//    }
//
//    /**
//     * Writes the tasks from the taskList into the file.
//     *
//     * @throws IOException
//     */
//    public static void writeFile() throws IOException {
//        FileWriter fw = new FileWriter("src/main/data/duke.txt");
//        for (int i = 0; i<taskList.size(); i++) {
//            String taskToWrite = taskList.get(i).toWrite();
//            fw.write(taskToWrite + "\n");
//        }
//        fw.close();
//    }
//
//    /**
//     * Handles the input commands of the user and calls the appropriate method.
//     *
//     * @throws DukeException If input command is not recognized.
//     */
//    public static void handleInput(Scanner scanner) {
//        String input;
//        do{
//                input = scanner.nextLine();
//            try{
//                if (input.equalsIgnoreCase("BYE")) {
//                    System.out.println("Bai bai!");
//                    writeFile();
//                } else if (input.equalsIgnoreCase("LIST")) {
//                    printTasks();
//                } else if (input.toUpperCase().startsWith("DONE")) {
//                    markTask(input);
//                } else if (input.toUpperCase().startsWith("TODO")) {
//                    addTodo(input);
//                } else if (input.toUpperCase().startsWith("DEADLINE")) {
//                    addDeadline(input);
//                } else if (input.toUpperCase().startsWith("EVENT")) {
//                    addEvent(input);
//                } else if (input.toUpperCase().startsWith("DELETE")) {
//                    deleteTask(input);
//                } else {
//                    throw new DukeException("Sorry, I don't understand. :O");
//                }
//            } catch (DukeException | IOException e) {
//                System.out.println(e.getMessage());;
//            }
//        }
//        while (!input.equalsIgnoreCase("BYE"));
//    }

    public void run() {
        ui.showStartup();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseInput(fullCommand);
                c.runCommand(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt").run();
    }
}
