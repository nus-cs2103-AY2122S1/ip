package duke;

public enum Command {
    DONE {
        public String toString() {
            return "done";
        }
    },
    BYE {
        public String toString() {
            return "bye";
        }
    },
    LIST {
        public String toString() {
            return "list";
        }
    },
    DELETE {
        public String toString() {
            return "delete";
        }
    },
    TODO {
        public String toString() {
            return "todo";
        }
    },
    DEADLINE {
        public String toString() {
            return "deadline";
        }
    },
    EVENT {
        public String toString() {
            return "event";
        }
    },
    FIND {
        public String toString() {
            return "find";
        }
    },
    CLEAR {
        public String toString() {
            return "clear";
        }
    },
    UNDO {
        public String toString() {
            return "undo";
        }
    },
    HELLO {
        public String toString() {
            return "hello";
        }
    },
    HELP {
        public String toString() {
            return "help";
        }
    },
    INVALID {
        public String toString() {
            return "invalid";
        }
    }
}
