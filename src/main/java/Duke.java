import duke.*;
//included package
import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private File
            file;
    private Parser p;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(filePath);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(filePath);
        }
        ui = new Ui(tasks, storage);
        p = new Parser();
        this.file = new File(filePath);
        tasks.readFromFile();
    }

    public void run() {
        System.out.println("Hello! This is Duke, your very own chat bot.");
        System.out.println("What can I help you with ?");
        while (true) {
            String fullCommand = ui.input();
            if (!fullCommand.equals("bye")) {
                try {
                    p.parse(fullCommand, tasks, storage, file);
                } catch (DukeException e) {
                    System.out.println(e.getMsg());
                }
            } else {
                System.out.println("It's sad to see you go :(");
                System.out.println("Goodbye, hope to you another day!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! This is Duke, your very own chat bot.");
        System.out.println("What can I help you with ?");
        Client client = new Client();
        ArrayList<duke.Task> list = new ArrayList<duke.Task>();

        try {
            String filePath = "./data/duke.txt";
            File savedData = new File(filePath);
            Scanner r = new Scanner(savedData);
            while (r.hasNextLine()) {
                String userInput = r.nextLine();
                String[] split = userInput.split(" ");
                String description = "";
                for (int i = 2; i < split.length - 2; i++) {
                    description = description + split[i] + " ";
                }
                String day = split[split.length - 1].substring(0, split[split.length - 1].length() - 1);
                System.out.println(day);
                if (userInput.substring(0,3).equals("[T]")) {
                    list.add(new duke.Todo(description));
                } else if (userInput.substring(0,3).equals("[D]")) {
                    String[] temp = userInput.split("/by");
                    String firstDeadline = temp[0].substring(9);
                    String[] splitDate = temp[1].split(" ");
                    String date = splitDate[1];
                    String[] breakingDate = date.split("/");
                    String year = breakingDate[2];
                    String month = breakingDate[1];
                    String currentDate = breakingDate[0];
                    int i = Integer.parseInt(currentDate);
                    if (i < 10) {
                        currentDate = "0" + currentDate;
                    }
                    String finalDateFormat = year + "-" + month + "-" + currentDate;
                    LocalDate date1 = LocalDate.parse(finalDateFormat);
                    System.out.println(date1);
                    list.add(new duke.Deadline(description, date1));
                } else if (userInput.substring(0,3).equals("[E]")) {
                    String[] tempEvent = userInput.split("/at");
                    String firstEvent = tempEvent[0].substring(6);
                    String[] splitDate = tempEvent[1].split(" ");
                    String date = splitDate[1];
                    String[] breakingDate = date.split("/");
                    String year = breakingDate[2];
                    String month = breakingDate[1];
                    String currentDate = breakingDate[0];
                    int i = Integer.parseInt(currentDate);
                    if (i < 10) {
                        currentDate = "0" + currentDate;
                    }
                    String finalDateFormat = year + "-" + month + "-" + currentDate;
                    LocalDate date1 = LocalDate.parse(finalDateFormat);
                    list.add(new duke.Event(description, date1));
                }
            }
            r.close();
        } catch (FileNotFoundException e) {
            try {
                String path = "data/";
                File makeFile = new File("data/duke.txt");
                Files.createDirectories(Paths.get(path));
                makeFile.createNewFile();
            } catch (IOException a) {
                System.out.println("Error encountered");
            }
        }

        while (true) {
            String input = client.input();
            if (input.equals("")) {
                break;
            }
            duke.Task taskInput = new duke.Task(input);
            if (input.equals("bye")) {
                System.out.println("It's sad to see you go :(");
                System.out.println("Goodbye, hope to you another day!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + "." + list.get(i).toString()); //".[" + list.get(i).getStatusIcon() + "] " + list.get(i).description);
                }
            } else if (input.startsWith("done") && input.length() < 9) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                list.get(value - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[X] " + list.get(value-1).description);
                appendListToFile(list);
            } else if (input.startsWith("todo")) {
                if (input.length() < 6) {
                    System.out.println(new duke.NullTaskError().getMsg("todo"));
                } else {
                    String firstTodo = input.substring(5);
                    list.add(new duke.Todo(firstTodo));
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(new duke.Todo(firstTodo));
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                }
                appendListToFile(list);
            } else if (input.startsWith("deadline")) {
                if (input.length() < 10) {
                    System.out.println(new duke.NullTaskError().getMsg("deadline"));
                } else {
                    String[] temp = input.split("/by");
                    String firstDeadline = temp[0].substring(9);
                    String[] splitDate = temp[1].split(" ");
                    String date = splitDate[1];
                    String[] breakingDate = date.split("/");
                    String year = breakingDate[2];
                    String month = breakingDate[1];
                    String currentDate = breakingDate[0];
                    int i = Integer.parseInt(currentDate);
                    if (i < 10) {
                        currentDate = "0" + currentDate;
                    }
                    String finalDateFormat = year + "-" + month + "-" + currentDate;
                    LocalDate date1 = LocalDate.parse(finalDateFormat);
                    String dateForObject = date1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    list.add(new duke.Deadline(firstDeadline, date1));
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(new duke.Deadline(firstDeadline, date1));
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                }
                appendListToFile(list);
            } else if (input.startsWith("event")) {
                if (input.length() < 7) {
                    System.out.println(new duke.NullTaskError().getMsg("event"));
                } else {
                    String[] tempEvent = input.split("/at");
                    String firstEvent = tempEvent[0].substring(6);
                    System.out.println(firstEvent);
                    String[] splitDate = tempEvent[1].split(" ");
                    String date = splitDate[1];
                    String[] breakingDate = date.split("/");
                    String year = breakingDate[2];
                    String month = breakingDate[1];
                    String currentDate = breakingDate[0];
                    int i = Integer.parseInt(currentDate);
                    if (i < 10) {
                        currentDate = "0" + currentDate;
                    }
                    String finalDateFormat = year + "-" + month + "-" + currentDate;
                    LocalDate date1 = LocalDate.parse(finalDateFormat);
                    System.out.println(date1);
                    list.add(new duke.Event(firstEvent, date1));
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(new duke.Event(firstEvent, date1));
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                }
                appendListToFile(list);
            } else if (input.startsWith("delete") && input.length() < 11) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                duke.Task removedTask = list.get(value - 1);
                list.remove(value - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(removedTask.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                appendListToFile(list);
            } else {
                duke.DukeException e = new duke.NonExistentKeyword();
                System.out.println(e.getMsg());
            }
            appendListToFile(list);
        }
    }
    public static void appendListToFile(ArrayList<duke.Task> listOfTasks) {
        try {
            File fileStorage = new File("data/duke.txt");
            FileWriter w = new FileWriter(fileStorage);
            String str = "";
            for (duke.Task t : listOfTasks) {
                str += t.toString() + "\n";
            }
            w.write(str);
            w.close();
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
     */
}

