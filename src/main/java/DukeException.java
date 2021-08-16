public class DukeException extends Exception {
    DukeException(String errorMessage) {
        super(errorMessage);
    }
}

    class EmptyTaskListException extends DukeException {
        EmptyTaskListException() {
            super("There are no tasks in your to-do list! ☹");
        }
    }

    class InvalidTaskException extends DukeException {
        InvalidTaskException() {
          super("There is no such task in your to-do list! ☹");
      }
    }

    class UnknownInputException extends DukeException {
        UnknownInputException() {
            super("I'm sorry, I don't know what that means! ☹");
        }
    }

    class EmptyTaskNumberException extends DukeException {
        EmptyTaskNumberException() {
            super("You haven't mentioned the task number! ☹");
        }
    }

    class EmptyTaskDescriptionException extends DukeException {
        EmptyTaskDescriptionException() {
            super("Please provide a task description! ☹");
        }
    }

    class NoTimeException extends DukeException {
        NoTimeException() {
            super("Please provide a timeline for your task! ☹");
        }
    }