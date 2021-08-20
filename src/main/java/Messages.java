public enum Messages {
    KNOWN {
        public String toString() {
            return "I'm sorry, but I don't know what that means :-(";
        }
    },
    EXIST {
        public String toString() {
            return "The task selected does not exist";
        }
    },
    EMPTY {
        public String toString() {
            return "The field(s) of %s cannot be empty.";
        }
    },
    TIME {
        public String toString() {
            return "Date & time format entered is invalid. Follow d/MM/yyyy [HHmm].";
        }
    }
}
