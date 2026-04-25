import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public ArrayList<Task> getAllTasks() {
        // Added to support GUI display (used by GuiTaskTracker to retrieve all tasks)
        return taskList;
    }

    public Task newTask(String title, String description, TaskPriority priority, boolean isComplete) {
        int newId = taskList.size() + 1;
        Task task = new Task(title, description, priority, isComplete, newId);
        taskList.add(task);
        return task;
    }

    public boolean deleteTask(int taskId) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTaskId() == taskId) {
                taskList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean saveTask(Task task) {
        if (task == null) {
            return false;
        }
        taskList.add(task);
        return true;
    }

    public boolean updateTask(int taskId, String title, String description, TaskPriority priority, boolean isComplete) {
        Task task = findTaskById(taskId);

        if (task == null) {
            return false;
        }

        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setComplete(isComplete);
        return true;
    }

    public Task findTaskById(int taskId) {
        for (Task task : taskList) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public ArrayList<Task> filterList(String filter) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task task : taskList) {
            if (filter.equalsIgnoreCase("LOW") && task.getPriority() == TaskPriority.LOW) {
                filteredTasks.add(task);
            } else if (filter.equalsIgnoreCase("MEDIUM") && task.getPriority() == TaskPriority.MEDIUM) {
                filteredTasks.add(task);
            } else if (filter.equalsIgnoreCase("HIGH") && task.getPriority() == TaskPriority.HIGH) {
                filteredTasks.add(task);
            } else if (filter.equalsIgnoreCase("COMPLETE") && task.isComplete()) {
                filteredTasks.add(task);
            } else if (filter.equalsIgnoreCase("ALL")) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }

    public void taskComplete(int taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            task.setComplete(true);
        }
    }

    public void readWriteTaskData() {
        System.out.println("readWriteTaskData() not implemented yet.");
    }

    public void displayAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task task : taskList) {
            System.out.println(task);
            System.out.println("--------------------");
        }
    }
}
