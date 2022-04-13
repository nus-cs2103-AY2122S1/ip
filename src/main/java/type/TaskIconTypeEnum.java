package type;

/**
 * Encapsulates valid values for a task icon.
 */
public enum TaskIconTypeEnum {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    private String icon;

    TaskIconTypeEnum(String icon) {
        this.icon = icon;
    }

    /**
     * Returns string representation of a task icon type enum.
     *
     * @return String representation of a task icon type enum
     */
    @Override
    public String toString() {
        return this.icon;
    }

    /**
     * Gets enum that represents the input value.
     *
     * @param value Input value.
     * @return `TaskIconTypeEnum`.
     */
    public static TaskIconTypeEnum getEnum(String value) {
        for (TaskIconTypeEnum e: TaskIconTypeEnum.values()) {
            if (e.icon.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
