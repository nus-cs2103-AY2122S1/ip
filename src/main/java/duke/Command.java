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
}
