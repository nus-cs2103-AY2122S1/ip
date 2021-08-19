public enum Command {
    BYE {
        @Override
        public void verifyArguments(String remainingText) throws DukeException {
        }
    },
    LIST {
        @Override
        public void verifyArguments(String remainingText) throws DukeException {
            if (!remainingText.isEmpty()) {
                throw new DukeException(TOO_MANY_ARGUMENTS_LIST_MESSAGE);
            }
        }
    },
    DONE {
        @Override
        public void verifyArguments(String remainingText) throws DukeException {
            if (remainingText.isEmpty()) {
                throw new DukeException(MISSING_DONE_NUMBER_MESSAGE);
            }
        }
    },
    DELETE {
        @Override
        public void verifyArguments(String remainingText) throws DukeException {
            if (remainingText.isEmpty()) {
                throw new DukeException(MISSING_DELETE_NUMBER_MESSAGE);
            }

        }
    },
    DEADLINE {
        @Override
        public void verifyArguments(String remainingText) throws DukeException {
            if (remainingText.split(" ").length < 3) {
                throw new DukeException(MISSING_DEADLINE_MESSAGE);
            }
        }
    },
    EVENT {
        @Override
        public void verifyArguments(String remainingText) throws DukeException {
            if (remainingText.split(" ").length < 3) {
                throw new DukeException(MISSING_EVENT_MESSAGE);
            }
        }
    },
    TODO {
        @Override
        public void verifyArguments(String remainingText) throws DukeException {
            if (remainingText.isEmpty()) {
                throw new DukeException(MISSING_TODO_MESSAGE);
            }
        }
    };

    private static final String MISSING_DELETE_NUMBER_MESSAGE = "Please input a number after the delete command";
    private static final String MISSING_DONE_NUMBER_MESSAGE = "Please input a number after the done command";
    private static final String MISSING_TODO_MESSAGE = "Please input text after the todo command";
    private static final String MISSING_DEADLINE_MESSAGE = "Some arguments are missing. Use 'deadline <text> /by <datetime>'";
    private static final String MISSING_EVENT_MESSAGE = "Some arguments are missing. Use 'event <text> /at <datetime>'";
    private static final String TOO_MANY_ARGUMENTS_LIST_MESSAGE = "An argument after 'list' is not required. Just 'list' will do.";

    public abstract void verifyArguments(String remainingText) throws DukeException;
}
