package model;

import java.time.LocalDateTime;

/**
 * Model that a time signifying certain semantics.
 */
public interface TimedItem {
    /**
     * Gets the time of the item.
     *
     * @return LocalDateTime.
     */
    LocalDateTime getTime();
}
