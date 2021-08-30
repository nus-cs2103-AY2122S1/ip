package logic;

import dao.TaskDao;
import model.Command;
import model.Deadline;
import model.Event;
import model.Task;
import model.TimedItem;
import model.ToDos;
import ui.IUi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Logic class that would handle all the logics and processing, together with the temporary data layer of the commands
 */
public class CommandLogicUnitImpl implements ICommandLogicUnit {
    /** Data access object for Task */
    private final TaskDao taskDao;
    
    /** UI that is responsible to interacts with user */
    private final IUi ui;
    
    /**
     * Constructor of CommandLogicUnitImpl that processes all the available commands.
     *
     * @param taskDao TaskDao.
     */
    public CommandLogicUnitImpl(TaskDao taskDao, IUi ui) {
        this.taskDao = taskDao;
        this.ui = ui;
    }
    
    @Override
    public void processCommand(Command command, Map<String, String> arguments) {
        switch (command) {
        case BYE:
            processBye();
            break;
        case LIST: {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            Optional<String> date = Optional.ofNullable(arguments.getOrDefault("date", null));
            Optional<LocalDate> localDateOptional =
                    date.filter(dateStr -> !dateStr.equals(""))
                            .map(dateStr -> LocalDate.parse(dateStr, formatter));
            
            processList(localDateOptional);
            break;
        }
        case DEADLINE: {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            
            processAdd(new Deadline(
                    arguments.getOrDefault("description", "default deadline desc"),
                    LocalDateTime.parse(arguments.getOrDefault("timing", LocalDateTime.now().toString()), formatter)
            ));
            break;
        }
        case EVENT: {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            
            processAdd(new Event(
                    arguments.getOrDefault("description", "default event"),
                    LocalDateTime.parse(arguments.getOrDefault("timing", LocalDateTime.now().toString()), formatter)
            ));
            break;
        }
        case TODOS:
            processAdd(new ToDos(arguments.getOrDefault("description", "default todo")));
            break;
        case DONE:
            // convert 1-indexing to 0-indexing
            processDone(Integer.parseInt(arguments.getOrDefault("index", "-1")) - 1);
            break;
        case DELETE:
            processDelete(Integer.parseInt(arguments.getOrDefault("index", "-1")) - 1);
            break;
        case FIND:
            processFind(Optional.ofNullable(arguments.getOrDefault("keyword", null)));
            break;
        default:
            ui.printSentence("command not recognized by processor");
        }
    }
    
    private void processBye() {
        ui.printSentence(" Bye. Hope to see you again soon!");
        System.exit(0);
    }
    
    /**
     * Prints the entire list of tasks whether its done or not done.
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private void processList(Optional<LocalDate> localDateOptional) {
        localDateOptional.ifPresentOrElse(
                x -> ui.printIndexedList(taskDao.getAll()
                        .stream()
                        .filter(task -> task instanceof TimedItem)
                        .filter(task -> ((TimedItem) task).getTime().toLocalDate().isEqual(x))
                        .collect(Collectors.toList())),
                () -> ui.printIndexedList(taskDao.getAll())
        );
    }
    
    private void processAdd(Task task) {
        taskDao.addTask(task);
        
        ui.printSentence(" Got it. I've added this task: \n"
                + "\t" + task.toString() + "\n"
                + " Now you have " + taskDao.getSize() + " tasks in the list.");
    }
    
    /**
     * Processes the DONE Command.
     *
     * @param index 0-indexed integer
     */
    private void processDone(int index) {
        taskDao.markDone(index);
        
        ui.printSentence("Nice! I've marked this task as done: \n"
                + "\t [X] " + taskDao.getTask(index).getDesc());
    }
    
    /**
     * Processes the DELETE Command.
     *
     * @param index 0-indexed integer
     */
    private void processDelete(int index) {
        Task deletedTask = taskDao.deleteTask(index);
        
        ui.printSentence(" Noted. I've removed this task: \n"
                + "\t" + deletedTask.toString() + "\n"
                + " Now you have " + taskDao.getSize() + " tasks in the list.");
    }
    
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private void processFind(Optional<String> keyword) {
        keyword.ifPresentOrElse(s -> {
                    List<Task> tasks = taskDao.getByKeyword(s);
                    ui.printIndexedList(tasks);
                },
                () -> processList(Optional.empty()));
    }
}
