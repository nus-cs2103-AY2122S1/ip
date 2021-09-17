package duke;

import java.util.LinkedList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    protected void addTask(Task t) {
        this.tasks.add(t);
    }

    protected Chatbot.ChatContinue addTask(Chatbot.TaskCommands command, String input, Ui ui, FileDB fileDB) throws DukeIOException, DukeDateParseException {
        switch (command) {
        case TASK_COMMAND_DONE:
            return this.markDone(input, ui);
        case TASK_COMMAND_DELETE:
            return this.delete(input, ui);
        case TASK_COMMAND_TODO:
            return this.addTodo(input, fileDB, ui);
        case TASK_COMMAND_DEADLINE:
            return this.addDeadline(input, fileDB, ui);
        case TASK_COMMAND_EVENT:
            return this.addEvent(input, fileDB, ui);
        default:
            ui.showNotSupported();
            return Chatbot.ChatContinue.CHAT_CONTINUE;
        }
    }

    private Boolean hasDuplicatedTask(Task newTask) {
       return tasks.stream().anyMatch(task -> task.equals(newTask));
    }

    private Chatbot.ChatContinue addTodo(String input, FileDB fileDB, Ui ui) throws DukeIOException{
        ToDo todo = new ToDo(input);
        if (this.hasDuplicatedTask(todo)) {
            ui.showDuplicated();
        } else {
            tasks.add(todo);
            fileDB.save(todo);
            ui.showAddTaskSuccessful(todo);
        }
        return Chatbot.ChatContinue.CHAT_CONTINUE;
    }

    private Chatbot.ChatContinue addDeadline(String input, FileDB fileDB, Ui ui) throws DukeIOException, DukeDateParseException {
        Deadline deadline = new Deadline(input);
        if(this.hasDuplicatedTask(deadline)) {
            ui.showDuplicated();
        } else {
            tasks.add(deadline);
            fileDB.save(deadline);
            ui.showAddTaskSuccessful(deadline);
        }
        return Chatbot.ChatContinue.CHAT_CONTINUE;
    }

    private Chatbot.ChatContinue addEvent(String input, FileDB fileDB, Ui ui) throws DukeIOException, DukeDateParseException {
        Event event = new Event(input);
        if(this.hasDuplicatedTask(event)) {
            ui.showDuplicated();
        } else {
            tasks.add(event);
            fileDB.save(event);
            ui.showAddTaskSuccessful(event);
        }
        return Chatbot.ChatContinue.CHAT_CONTINUE;
    }

    public TaskList findTasks(String target) {
        TaskList taskList = new TaskList();
        for(int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.description.contains(target)) {
                taskList.tasks.add(currentTask);
            }
        }
        return taskList;
    }

    private Chatbot.ChatContinue markDone(String args, Ui ui) {
        try {
            int index = Integer.parseInt(args);
            Task task = tasks.get(index - 1);
            task.markDone();
            ui.showMarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeArgumentException("Sorry, that item does not exist!");
        } catch (NumberFormatException e) {
            throw new DukeArgumentException("The index is invalid");
        }
        return Chatbot.ChatContinue.CHAT_CONTINUE;
    }

    private Chatbot.ChatContinue delete(String args, Ui ui) {
        try {
            int index = Integer.parseInt(args);
            if (index > 0 && index <= tasks.size()) {
                Task task = tasks.get(index - 1);
                tasks.remove(index - 1);
                ui.showDeleted(task);
            } else {
                throw new DukeTaskException("duke.Task does not exist!");
            }
        } catch (NumberFormatException e) {
            throw new DukeTaskException("duke.Task does not exist!");
        }
        return Chatbot.ChatContinue.CHAT_CONTINUE;
    }

    protected Chatbot.ChatContinue list(Ui ui) {
        if (tasks.size() == 0) {
            ui.showNothingAdded();
        } else {
            ui.showAllTasks(this.tasks);
        }
        return Chatbot.ChatContinue.CHAT_CONTINUE;
    }

    protected void saveAll(FileDB fileDB) throws DukeIOException{
        fileDB.clearAll();
        for(int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            fileDB.save(t);
        }
    }

    public int size() {
        return tasks.size();
    }
}
