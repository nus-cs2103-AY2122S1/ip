package inputs;

public class EventInput extends InputHandler{


    public EventInput(Messages ui, ToDoList toDoList) throws DukeException {
        super(ui, toDoList);
    }

    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 5) {
            throw new EmptyDescriptionException("error" );
        }
        int charIndex = input.indexOf("/" );
        int atIndex = charIndex + 4;

        String at = input.substring(atIndex);
        String task = input.substring(6, charIndex - 1);

        Task eventTask = new Event(task, at);
        toDoList.add(eventTask);

        return ui.addedTask(eventTask);
    }
}
