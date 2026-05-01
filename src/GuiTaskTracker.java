import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.border.EtchedBorder;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class GuiTaskTracker extends JFrame{
    private TaskManager taskManager;
    private Scanner scanner;
	private JPanel contentPane;
	private JTextField textTaskTitle;

    public GuiTaskTracker(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    public void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter priority (LOW, MEDIUM, HIGH): ");
        String priorityInput = scanner.nextLine().toUpperCase();

        TaskPriority priority;
        try {
            priority = TaskPriority.valueOf(priorityInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority. Defaulting to LOW.");
            priority = TaskPriority.LOW;
        }

        Task task = taskManager.newTask(title, description, priority, false);
        System.out.println("Task created successfully:");
        System.out.println(task);
    }

    public void filterList(String filter) {
        ArrayList<Task> filteredTasks = taskManager.filterList(filter);

        if (filteredTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            return;
        }

        for (Task task : filteredTasks) {
            System.out.println(task);
            System.out.println("--------------------");
        }
    }

    public void cancel() {
        System.out.println("Action canceled.");
    }

    public void taskComplete() {
        System.out.print("Enter task ID to mark complete: ");
        int taskId = Integer.parseInt(scanner.nextLine());

        Task task = taskManager.findTaskById(taskId);
        if (task != null) {
            taskManager.taskComplete(taskId);
            System.out.println("Task marked as complete.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Task Tracker ===");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Filter Tasks");
            System.out.println("4. Mark Task Complete");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    taskManager.displayAllTasks();
                    break;
                case "3":
                    System.out.print("Enter filter (ALL, LOW, MEDIUM, HIGH, COMPLETE): ");
                    String filter = scanner.nextLine();
                    filterList(filter);
                    break;
                case "4":
                    taskComplete();
                    break;
                case "5":
                    System.out.print("Enter task ID to delete: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    if (taskManager.deleteTask(taskId)) {
                        System.out.println("Task deleted successfully.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case "6":
                    running = false;
                    System.out.println("Exiting Student Task Tracker...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
