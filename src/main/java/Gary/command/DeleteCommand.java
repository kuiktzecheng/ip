package Gary.command;

import java.io.IOException;

import Gary.GaryException;
import Gary.Storage;
import Gary.TaskList;
import Gary.Ui;
import Gary.task.Task;

/**
 * Represents a command to delete a task from the task list in the Gary chatbot.
 */
public class DeleteCommand extends Command {
    private int deletedIndex;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param deletedIndex The index of the task to be deleted in the task list.
     */
    public DeleteCommand(int deletedIndex) {
        assert deletedIndex >= 0 : "Deleted index should not be negative";
        this.deletedIndex = deletedIndex;
    }

    /**
     * Executes the delete command, which removes a task from the {@code TaskList},
     * updates the user through {@code Ui}, and saves the updated task list to storage.
     *
     * @param taskList The {@code TaskList} object containing tasks to be manipulated.
     * @param ui The {@code Ui} object for user interaction, used to display messages.
     * @param storage The {@code Storage} object for saving and loading tasks.
     * @return A message indicating the task has been deleted.
     * @throws GaryException If an error occurs while saving the task list or if the index is out of bounds.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws GaryException {
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert deletedIndex >= 0 && deletedIndex < taskList.size() : "Deleted index is out of bounds";

        try {
            Task deletedTask = taskList.removeTask(deletedIndex);
            storage.saveTask(taskList);
            return ui.deleteTask(deletedTask, taskList.size());
        } catch (IOException e) {
            throw new GaryException("An error occurred while saving the task list. Try again.");
        } catch (IndexOutOfBoundsException e) {
            throw new GaryException("Task list index is out of bounds!");
        }
    }

    /**
     * Indicates that the DeleteCommand does not terminate the application.
     *
     * @return false as the delete command does not terminate the application.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
