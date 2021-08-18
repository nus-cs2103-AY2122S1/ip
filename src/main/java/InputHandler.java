package inputs;

public abstract class InputHandler {
    protected Messages ui;
    protected ToDoList toDoList;

    public InputHandler(Messages ui, ToDoList toDoList) {
        this.toDoList = toDoList;
        this.ui = ui;
    }

    public abstract String handle(String input) throws EmptyDescriptionException;

}
