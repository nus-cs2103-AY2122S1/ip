package duke;

//public class duke.Duke {
//    private static ArrayList<duke.Task> taskList = new ArrayList<>();
//    private static duke.TaskList taskLIst = new duke.TaskList();
//    private static int index = 0;
//    private static void addTask(duke.Task task) {
//        taskList.add(task);
//        index++;
//    }
//    private static void deleteTask(int taskIndex) {
//        taskList.remove(taskIndex);
//        index--;
//    }
//    private static String convertTaskToText(duke.Task task) {
//        String result = task.type + "|";
//        if(task.isCompleted()) {
//            result += "1|";
//        }else {
//            result += "0|";
//        }
//        result += task.getTaskContent();
//        if(task.type.equals("D") || task.type.equals("E")) {
//            result += "|" + task.getTiming();
//        }
//        return result;
//    }
//    private static duke.Task convertTextToTask(String text) {
//        String[] str = text.split("\\|");
//        duke.Task newTask;
//        if(str[0].equals("T")) {
//            newTask = new duke.ToDo(str[2]);
//        }else if(str[0].equals("D")) {
//            newTask = new duke.Deadline(str[2], str[3]);
//        }else {
//            newTask = new Event(str[2], str[3]);
//        }
//        if(str[1].equals("1")) {
//            newTask.markCompleted();
//        }
//        return newTask;
//    }
//    private static void writeToFile(Path path) {
//        StringBuilder combinedTask = new StringBuilder();
//        for(int i = 0; i < index; i++) {
//            combinedTask.append(convertTaskToText(taskList.get(i))).append("\n");
//        }
//        try {
//            FileWriter writer = new FileWriter(String.valueOf(path), false);
//            writer.write(combinedTask.toString());
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("error occurred 2");
//        }
//    }
//    private static void createFile(Path directoryPath, Path filePath) {
//        File newDirectory = new File(String.valueOf(directoryPath));
//        File newFile = new File(String.valueOf(filePath));
//        try {
//            newDirectory.mkdir();
//            newFile.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("error occurred 1");
//        }
//    }
//    private static void saveTask() {
//        String home = System.getProperty("user.home");
//        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "iP", "data", "duke.txt");
//        java.nio.file.Path directoryPath = java.nio.file.Paths.get(home, "iP", "data");
//        boolean directoryExists = java.nio.file.Files.exists(filePath);
//        if(!directoryExists) {
//            createFile(directoryPath, filePath);
//        }
//        writeToFile(filePath);
//    }
//    private static void loadTask() {
//        String home = System.getProperty("user.home");
//        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "iP", "data", "duke.txt");
//        try {
//            File myObj = new File(String.valueOf(filePath));
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                taskList.add(convertTextToTask(data));
//                index++;
//            }
//            myReader.close();
//            taskList.forEach(task -> {
//                System.out.println(task.toString());
//            });
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) {
//        System.out.println("Hello! I'm duke.Duke\n" +
//                "What can I do for you?");
//        System.out.println("Here are your tasks");
//        loadTask();
//        Scanner sc = new Scanner(System.in);
//        while(true) {
//            String input = sc.nextLine();
//            if(input.equals("bye")) {
//                System.out.println("Bye. Hope to see you again soon!");
//                break;
//            } else if(input.split(" ")[0].equals("delete")) {
//                duke.Task taskToBeDeleted = taskList.get(Integer.parseInt(input.split(" ")[1]) - 1);
//                deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
//                System.out.println("Noted. I've removed this task: \n" +
//                         "  " + taskToBeDeleted.toString() +"\n" +
//                        "Now you have " + index + " tasks in the list.");
//                saveTask();
//            } else if (input.equals("list")) {
//                System.out.println("Here are the tasks in your list:");
//                for(int i = 0; i < index; i++){
//                    System.out.println((i + 1) + ". " + taskList.get(i).toString());
//                }
//            }else if(input.split(" ")[0].equals("done")){
//                int taskIndex = Integer.parseInt(input.split(" ")[1]);
//                taskList.get(taskIndex - 1).markCompleted();
//                System.out.println("Nice! I've marked this task as done:\n" + taskList.get(taskIndex - 1).toString());
//                saveTask();
//            } else if(input.split("todo").length == 0) {
//                try {
//                    throw new duke.DukeException.emptyToDoDescriptionException();
//                } catch (duke.DukeException.emptyToDoDescriptionException e) {
//                    e.exceptionMessage();
//                }
//            }else if(input.split("deadline").length == 0) {
//                try {
//                    throw new duke.DukeException.emptyDeadlineDescriptionException();
//                } catch (duke.DukeException.emptyDeadlineDescriptionException e) {
//                    e.exceptionMessage();
//                }
//            }else if(input.split("event").length == 0) {
//                try {
//                    throw new duke.DukeException.emptyEventDescriptionException();
//                } catch (duke.DukeException.emptyEventDescriptionException e) {
//                    e.exceptionMessage();
//                }
//            } else if(input.split("event")[0].equals("")) {
//                String taskContent = input.split("event ")[1];
//                duke.Task newEvent = new Event(taskContent);
//                addTask(newEvent);
//                System.out.println("Got it. I've added this task: \n" +
//                        "  " + newEvent.toString() + "\n" +
//                        "Now you have " + index + " tasks in the list.");
//                saveTask();
//            } else if(input.split("deadline")[0].equals("")) {
//                String taskContent = input.split("deadline ")[1];
//                duke.Task newEvent = new duke.Deadline(taskContent);
//                addTask(newEvent);
//                System.out.println(" Got it. I've added this task: \n" +
//                        "  " + newEvent.toString() + "\n" +
//                        "Now you have " + index + " tasks in the list.");
//                saveTask();
//            } else if(input.split("todo")[0].equals("")) {
//
//                String taskContent = input.split("todo ")[1];
//                duke.Task newEvent = new duke.ToDo(taskContent);
//                addTask(newEvent);
//                System.out.println("Got it. I've added this task: \n" +
//                        "  " + newEvent.toString() + "\n" +
//                        "Now you have " + index + " tasks in the list.");
//                saveTask();
//            }
//            else {
//                try{
//                    throw new duke.DukeException.invalidInputException();
//                } catch (duke.DukeException.invalidInputException e) {
//                    e.exceptionMessage();
//                }
//            }
//        }
//    }
//
//}

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for a new Duke object.
     *
     * @param filePath File path for the storage file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = storage.loadTask();
        ui = new Ui(storage, taskList);
    }

    /**
     * Executes the Duke object.
     */
    public void run() {
        boolean isExit = false;
        ui.greet();
        taskList.printTask();
        while(!isExit) {
            String input = ui.readLine();
            ui.handleInput(input);
            ui.showLine();
            isExit = ui.handleExit();
        }
    }

    /**
     * Creates new Dukc object and runs it.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}