import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lifeline {
    private Scanner sc;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    Lifeline(String filepath) {
        this.sc = new Scanner(System.in);
        this.storage = new Storage(filepath);
        this.ui = new Ui();
        try {
            this.taskList = storage.load();
        } catch (LifelineException e) {
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    private void getInput() {
        boolean exit = false;
        while (!exit) {
            try {
                String command = ui.readCommand();
                if (command.equals("")) {
                    continue;
                }
                String[] inputs = command.split("\\s", 2);
                System.out.println();
                switch (getInputType(inputs[0])) {
                case LIST:
                    ui.showTaskList(taskList);
                    break;
                case BYE:
                    exit = true;
                    break;
                case DONE:
                    if (inputs.length != 2) {
                        throw new LifelineException("You did not specify an integer! Please use done <number>");
                    }
                    markAsDone(inputs[1]);
                    break;
                case DELETE:
                    if (inputs.length != 2) {
                        throw new LifelineException("You did not specify an integer! Please use delete <number>");
                    }
                    deleteTask(inputs[1]);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    if (inputs.length != 2) {
                        throw new LifelineException("Details of task cannot be blank!");
                    }
                    createTask(inputs[0].trim(), inputs[1].trim());
                    break;
                default:
                    ui.echo(inputs[0]);
                    break;
                }
            } catch (LifelineException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.exit();
    }

    private InputType getInputType(String input) throws LifelineException {
        try {
            return InputType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LifelineException("I am sorry! I don't know what that means! ☹");
        }
    }

    private void createTask(String task, String details) throws LifelineException {
        switch (task) {
        case "todo":
            Task newTask = new ToDo(details);
            addToList(newTask);
            break;
        case "deadline":
            String[] description = details.split("/by", 2);
            if (description.length != 2) {
                throw new LifelineException("Deadline cannot be blank! Use deadline <name> /by <deadline>");
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(description[1].trim(), formatter);
                newTask = new Deadline(description[0].trim(), dateTime);
                addToList(newTask);
            } catch (DateTimeParseException e) {
                throw new LifelineException("Deadline is not of the correct format! Please use deadline <name> /by " +
                        "<dd/MM/yy HHmm>\n");
            }
            break;
        case "event":
            description = details.split("/at", 2);
            if (description.length != 2) {
                throw new LifelineException("Event date/time cannot be blank! Use /at <Day> <Time>");
            }
            String[] eventDateAndDuration = description[1].trim().split("\\s", 2);
            if (eventDateAndDuration.length!= 2) {
                throw new LifelineException("Event date/time not in proper format! Please use event <name> /at " +
                        "<dd/MM/yy> <HHmm>-<HHmm>");
            }
            String eventDate = eventDateAndDuration[0];
            String eventDuration = eventDateAndDuration[1];
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                LocalDate date = LocalDate.parse(eventDate.trim(), dateFormatter);
                String[] duration = eventDuration.split("-", 2);
                if (duration.length != 2) {
                    throw new LifelineException("Event date/time not in proper format! Please use event <name> /at " +
                            "<dd/MM/yy> <HHmm>-<HHmm>");
                }
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
                LocalTime startTime = LocalTime.parse(duration[0], timeFormatter);
                LocalTime endTime = LocalTime.parse(duration[1], timeFormatter);
                newTask = new Event(description[0].trim(), date, startTime, endTime);
                addToList(newTask);
            } catch (DateTimeParseException e) {
                throw new LifelineException("Event date/time not in proper format! Please use event <name> /at " +
                        "<dd/MM/yy> <HHmm>-<HHmm>");
            }
            break;
        default:
            ui.echo(task);
            break;
        }
        storage.save(taskList);
    }

    private void addToList(Task task) {
        taskList.add(task);
        ui.showAddedTask(task);
    }

    private void markAsDone(String index) throws LifelineException {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new LifelineException("Index is out of bounds!");
            }
            taskList.completeTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer! Please use done <number>");
        }
    }

    private void deleteTask(String index) throws LifelineException {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new LifelineException("Index is out of bounds!");
            }
            Task taskToDelete = taskList.get(taskIndex);
            taskList.deleteTask(taskIndex);
            ui.showDeletedTask(taskToDelete);
            ui.showTaskList(taskList);
            storage.save(taskList);
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer!");
        }
    }

    public void start() {
        ui.greet(taskList);
        this.getInput();
    }
}
