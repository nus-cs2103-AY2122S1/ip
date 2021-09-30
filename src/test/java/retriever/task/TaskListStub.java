package retriever.task;

import java.util.ArrayList;

import retriever.Storage;
import retriever.Ui;
import retriever.exception.IllegalDateFormatException;
import retriever.exception.IllegalDeadlineFormatException;
import retriever.exception.IllegalEventFormatException;
import retriever.exception.IllegalTaskNumberException;
import retriever.exception.IllegalTodoFormatException;
import retriever.exception.RetrieverException;
import retriever.exception.TaskNotFoundException;

public class TaskListStub extends TaskList {
    private Ui ui = new Ui();

    public TaskListStub(Storage taskStorage) {
        super(taskStorage);
    }

    @Override
    public int taskListLength() {
        return new ArrayList<Task>().size();
    }

    @Override
    public void printTaskList() {
        ui.printTaskList(new ArrayList<Task>());
    }

    @Override
    public void deleteTask(String[] parsedUserInput) throws IllegalTaskNumberException, TaskNotFoundException {
        ui.printTaskDeleted(new Todo("Testing"), new ArrayList<Task>().size());
    }

    @Override
    public void markTaskAsDone(String[] parsedUserInput) throws IllegalTaskNumberException, TaskNotFoundException {
        Todo sampleTodo = new Todo("Testing");
        sampleTodo.markAsDone();
        ui.printTaskMarkedAsDone(sampleTodo);
    }

    @Override
    public void addDeadlineTask(String userInput) throws IllegalDeadlineFormatException, IllegalDateFormatException {
        ui.printTaskAdded(new Deadline("Testing", new TaskDateAndTime("12/09/2021")),
                1);
    }

    @Override
    public void addEventTask(String userInput) throws IllegalEventFormatException, IllegalDateFormatException {
        ui.printTaskAdded(new Event("Testing", new TaskDateAndTime("12/09/2021")),
                1);
    }

    @Override
    public void addTodoTask(String userInput) throws IllegalTodoFormatException {
        ui.printTaskAdded(new Todo("Testing"), 1);
    }

    @Override
    public void findTaskWithKeyword(String[] parsedUserInput) {
        ui.printTasksFoundByKeyword(new ArrayList<Task>());
    }

    @Override
    public void viewScheduleForAParticularDay(String[] parsedUserInput) throws RetrieverException {
        ui.printTasksScheduledForTheDay(new ArrayList<Task>());
    }
}
