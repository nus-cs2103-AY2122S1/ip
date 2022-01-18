public class DukeException {
    public void noSuchTaskException() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public void noDescriptionException() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }
    public void invalidDeadlineSyntax() {
        System.out.println("☹ OOPS!!! Your syntax for adding deadline task is wrong. \r\n Please follow the command: deadline <Description> /by <Date/Time/DueDate>");
    }
    public void invalidEventSyntax() {
        System.out.println("☹ OOPS!!! Your syntax for adding event task is wrong. \r\n Please follow the command: event <Description> /at <Date/Time/DueDate/Place>");
    }
}
