public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        GuiTaskTracker guiTaskTracker = new GuiTaskTracker(taskManager);

        guiTaskTracker.showMenu();
    }
}
