package Gary.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents a task with a description, start time, and end time.
 * It is a subclass of the {@code Task} class.
 */
public class Event extends Task {
    private LocalDateTime start;  // Start date and time of the event
    private LocalDateTime end;    // End date and time of the event

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs an {@code Event} object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param end The end time of the event in "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = parseDateTime(start);
        this.end = parseDateTime(end);
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime} object using the input formatter.
     *
     * @param dateTime The date-time string to be parsed.
     * @return The parsed {@code LocalDateTime} object.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, INPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the {@code Event} task.
     * The format includes the type of task, its status, start time, and end time.
     *
     * @return A string representation of the {@code Event} task.
     */
    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (from: " + this.start.format(OUTPUT_FORMATTER) + " to: " + this.end.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Converts the {@code Event} task into a string format suitable for saving to a file.
     *
     * @return A string representation of the {@code Event} task for file storage.
     */
    @Override
    public String parseToFile() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.start.format(INPUT_FORMATTER) + " | " + this.end.format(INPUT_FORMATTER);
    }

    /**
     * Checks if this {@code Event} is equal to another object.
     * Two events are considered equal if they have the same description, start time, and end time.
     *
     * @param obj The object to compare with this {@code Event}.
     * @return {@code true} if the specified object is equal to this {@code Event}, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Event otherEvent = (Event) obj;
        return super.equals(otherEvent) &&
                (this.start == null ? otherEvent.start == null : this.start.equals(otherEvent.start)) &&
                (this.end == null ? otherEvent.end == null : this.end.equals(otherEvent.end));
    }
}
