package duke.exception;
public class DukeException extends RuntimeException {

    public enum Type {
        EmptyDone {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! You haven't specified the task you have completed.";
            }
        },
        EmptyDelete {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! You haven't specified the task you want to delete.";
            }
        },
        InvalidTaskNumber {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! The task number you have specified is invalid.";
            }
        },
        EmptyTodo {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! The description of a todo cannot be empty.";
            }
        },
        EmptyEvent {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! The description of an event cannot be empty.";
            }
        },
        EmptyDeadline {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! The description of a deadline cannot be empty.";
            }
        },
        EmptyFind {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! The query for find cannot be empty.";
            }
        },
        EmptyUpdate {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! The update cannot be empty.";
            }
        },
        InvalidCommand {
            @Override
            public String getMessage() {
                return "(O_O) OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        };

        public abstract String getMessage();
    }

    public DukeException(Type type) {
        super(type.getMessage());
    }
}
