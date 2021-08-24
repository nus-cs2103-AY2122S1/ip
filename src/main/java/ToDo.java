public class ToDo extends Task{
    private static final String MISSING_TODO_MESSAGE = "Please input text after the todo command";
    private static final String INVALID_SAVE_MESSAGE = "Todo save is given in the wrong format";
    public static final char SYMBOL = 'T';

    private ToDo(String text, boolean isDone) {
        super(text, isDone);
    }

    public static ToDo newTodo(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(MISSING_TODO_MESSAGE);
        }
        String todoText = input.trim();

        return new ToDo(todoText, false);
    }

    public static ToDo newToDoFromSave(String input) throws DukeException {
        String[] inputArr = input.split("\\|");
        if (inputArr.length != 2) {
            throw new DukeException(INVALID_SAVE_MESSAGE);
        }
        String isDone = inputArr[0].trim();
        String text = inputArr[1].trim();
        return new ToDo(text, isDone.equals("1"));
    }

    public String getSaveFormat(){
        return String.format("%c | %d | %s", SYMBOL, super.getDoneInt(), this.getText());
    };

    @Override
    public String toString() {
        return String.format("[%c]%s", SYMBOL, super.toString());
    }
}
