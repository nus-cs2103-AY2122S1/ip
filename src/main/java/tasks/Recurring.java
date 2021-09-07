package tasks;

public interface Recurring {
    enum Recurrence {
        DAILY,
        WEEKLY,
        MONTHLY,
        NEVER
    }

    static Recurrence stringToRecurrence(String recurrence) {
        switch (recurrence) {
        case "daily":
            return Recurrence.DAILY;
        case "weekly":
            return Recurrence.WEEKLY;
        case "monthly":
            return Recurrence.MONTHLY;
        default:
            return Recurrence.NEVER;
        }
    }

    static String recurrenceToString(Recurrence recurrence) {
        switch (recurrence) {
        case DAILY:
            return "[recurs: daily]";
        case WEEKLY:
            return "[recurs: weekly]";
        case MONTHLY:
            return "[recurs: monthly]";
        default:
            return "";
        }
    }
}
