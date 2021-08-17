public enum Command {
    LIST {
        @Override
        public void validateArguments(String arguments) {}
    },
    TODO {
        @Override
        public void validateArguments(String arguments) throws DukeException {
            if (arguments.isEmpty()) {
                throw new DukeException("Invalid use of the 'todo' command.\n\nTo add a new todo, use 'todo <name>'.");
            }
        }
    },
    DEADLINE {
        @Override
        public void validateArguments(String arguments) throws DukeException {
            String[] deadlineDetails = arguments.split(" /by ");
            if (deadlineDetails.length < 2) {
                throw new DukeException("Invalid use of the 'deadline' command.\n\n" +
                        "To add a new deadline, use 'deadline <name> /by <due-date>'.");
            }
        }
    },
    EVENT {
        @Override
        public void validateArguments(String arguments) throws DukeException {
            String[] eventDetails = arguments.split(" /at ");
            if (eventDetails.length < 2) {
                throw new DukeException("Invalid use of the 'event' command.\n\n" +
                        "To add a new event, use 'event <name> /at <event-timestamp>'.");
            }
        }
    },
    DONE {
        @Override
        public void validateArguments(String arguments) throws DukeException {
            if (arguments.isEmpty()) {
                throw new DukeException("Invalid use of the 'done' command.\n\n" +
                        "To mark a task as done, use 'done <task-number>'.");
            }
        }
    },
    DELETE {
        @Override
        public void validateArguments(String arguments) throws DukeException {
            if (arguments.isEmpty()) {
                throw new DukeException("Invalid use of the 'delete' command.\n\n" +
                        "To delete a task, use 'delete <task-number>'.");
            }
        }
    },
    BYE {
        @Override
        public void validateArguments(String arguments) {}
    };

    public abstract void validateArguments(String arguments) throws DukeException;
}
