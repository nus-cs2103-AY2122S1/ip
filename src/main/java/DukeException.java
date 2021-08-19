public abstract class DukeException extends Exception{
    abstract void exceptionMessage();

    public static class emptyToDoDescriptionException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
    public static class emptyEventDescriptionException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }
    public static class emptyDeadlineDescriptionException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }
    public static class invalidInputException extends DukeException {
        @Override
        void exceptionMessage() {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
