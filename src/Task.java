public class Task {
    private String title;
    private String description;
    private TaskPriority priority;
    private boolean isComplete;
    private int taskId;

    public Task(String title, String description, TaskPriority priority, boolean isComplete, int taskId) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isComplete = isComplete;
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nPriority: " + priority +
                "\nCompleted: " + isComplete;
    }
}
