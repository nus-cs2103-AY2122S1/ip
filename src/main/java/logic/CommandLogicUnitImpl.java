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
import java.util.List;
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
     * This will also starts the reminder tasks.
     *
     * @param taskDao TaskDao.
     */
    public CommandLogicUnitImpl(TaskDao taskDao, IUi ui) {
        this.taskDao = taskDao;
        this.ui = ui;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void processCommand(Command command, CommandArgument argument) {
        switch (command) {
        case BYE:
            processBye();
            break;
        case LIST:
            LocalDate localDate = argument.getTiming().toLocalDate();
            processList(localDate);
            break;
        case DEADLINE:
            Deadline deadline = new Deadline(
                    argument.getDescription(),
                    argument.getTiming()
            );
            
            processAdd(deadline);
            break;
        case EVENT:
            Event event = new Event(
                    argument.getDescription(),
                    argument.getTiming()
            );
            
            processAdd(event);
            break;
        case TODOS:
            ToDos task = new ToDos(argument.getDescription());
            processAdd(task);
            break;
        case DONE:
            Integer doneIndex = argument.getIndex();
            assert doneIndex != null;
            
            processDone(doneIndex);
            break;
        case DELETE:
            Integer deleteIndex = argument.getIndex();
            assert deleteIndex != null;
            
            processDelete(deleteIndex);
            break;
        case FIND:
            processFind(argument.getDescription());
            break;
        default:
            ui.printSentence("command not recognized by processor");
        }
    }
    
    private void processBye() {
        ui.printSentence(" Bye. Hope to see you again soon!");
        System.exit(0);
    }
    
    private void processList(LocalDate localDate) {
        if (localDate == null) {
            ui.printIndexedList(taskDao.getAll());
            return;
        }
        
        ui.printIndexedList(taskDao.getAll()
                .stream()
                .filter(task -> task instanceof TimedItem)
                .filter(task -> ((TimedItem) task).getTime().toLocalDate().isEqual(localDate))
                .collect(Collectors.toList()));
    }
    
    private void processAdd(Task task) {
        taskDao.addTask(task);
        
        String sentence = " Got it. I've added this task: \n"
                + "\t" + task.toString() + "\n"
                + " Now you have " + taskDao.getSize() + " tasks in the list.";
        
        ui.printSentence(sentence);
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
    
    private void processFind(String keyword) {
        if (keyword == null) {
            processList(null);
            return;
        }
        
        List<Task> tasks = taskDao.getByKeyword(keyword);
        ui.printIndexedList(tasks);
    }
}
