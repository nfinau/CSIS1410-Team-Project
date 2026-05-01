import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


public class GuiTaskTracker extends JFrame{
	private static final long serialVersionUID = 1L;
    private static TaskManager taskManager;
    private Scanner scanner;
	private JPanel contentPane;
	private JTextField textTaskTitle;

   
    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTaskTracker frame = new GuiTaskTracker(taskManager);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public GuiTaskTracker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Set<String> filterSet = Set.of("All", "Low Priority", "Medium Priority", "High Priority", "Complete");
		Set<String> prioritySet = Set.of("Low", "Medium", "High"); // Change to use priority enum
		
		
		//Creation of CardLayout and ContentPane
		CardLayout cl = new CardLayout();
		contentPane = new JPanel(cl);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel mainPanel = new JPanel();
		JPanel taskInfoPanel = new JPanel();
		taskInfoPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(mainPanel, "mainPanel");
		contentPane.add(taskInfoPanel, "taskInfoPanel");
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		
		// Creation of MainPanel assets
		JLabel lblTaskTracker = new JLabel("Student Task Tracker");
		mainPanel.add(lblTaskTracker, BorderLayout.NORTH);
		lblTaskTracker.setFont(new Font("PingFang HK", Font.BOLD, 20));
		lblTaskTracker.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel interactionPanelMain = new JPanel();
		mainPanel.add(interactionPanelMain, BorderLayout.SOUTH);
		
		
		
		
		// Creation of TaskInfoPanel assets
		JLabel lblTaskInfo = new JLabel("New/Edit Task");
		lblTaskInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaskInfo.setFont(new Font("PingFang HK", Font.BOLD, 20));
		taskInfoPanel.add(lblTaskInfo, BorderLayout.NORTH);
		
		JPanel interactionPanelInfo = new JPanel();
		interactionPanelInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
		taskInfoPanel.add(interactionPanelInfo, BorderLayout.SOUTH);
		
		createTaskDetailsPanel(prioritySet, mainPanel, taskInfoPanel);
		
		JScrollPane scrollTaskList = new JScrollPane();
		mainPanel.add(scrollTaskList, BorderLayout.CENTER);
		
		
		// MainPanel button
		JButton btnNewTask = new JButton("New Task");
		btnNewTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Transfering to Task Info Panel with empty task details.");
				cl.show(contentPane, "taskInfoPanel");
				lblTaskInfo.setText("New Task");
			}
		});
		interactionPanelMain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnNewTask.setPreferredSize(new Dimension(75, 25));
		btnNewTask.setBorder(new EmptyBorder(3, 5, 3, 5));
		btnNewTask.setOpaque(true);
		btnNewTask.setBackground(new Color(144, 218, 92));
		btnNewTask.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		interactionPanelMain.add(btnNewTask);
		
		JComboBox<Object> dropDownFilter = new JComboBox<>(filterSet.toArray());
		dropDownFilter.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		interactionPanelMain.add(dropDownFilter);
		
		
		// TaskInfoPanel buttons
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Transfering to Main Panel with new task created"
						+ " from inputed information.");
				cl.show(contentPane, "mainPanel");
			}
		});
		btnSave.setPreferredSize(new Dimension(75, 25));
		btnSave.setMargin(new Insets(0, 2, 0, 25));
		btnSave.setBackground(new Color(144, 218, 92));
		btnSave.setOpaque(true);
		btnSave.setBorder(new MatteBorder(1, 8, 1, 8, (Color) new Color(144, 218, 92)));
		btnSave.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		interactionPanelInfo.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Transfering to Main Panel with no new task created.");
				cl.show(contentPane, "mainPanel");
			}
		});
		btnCancel.setPreferredSize(new Dimension(75, 25));
		btnCancel.setOpaque(true);
		btnCancel.setBackground(new Color(216, 125, 119));
		btnCancel.setBorder(new MatteBorder(1, 8, 1, 8, (Color) new Color(216, 125, 119)));
		btnCancel.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		interactionPanelInfo.add(btnCancel);

	}
	
	 public GuiTaskTracker(TaskManager taskManager) {
	        this.taskManager = taskManager;
	        this.scanner = new Scanner(System.in);
	    }

	private void createTaskDetailsPanel(Set<String> prioritySet, JPanel mainPanel, JPanel taskInfoPanel) {
		JPanel panelTaskDetails = new JPanel();
		taskInfoPanel.add(panelTaskDetails, BorderLayout.CENTER);
		panelTaskDetails.setLayout(new GridLayout(3, 2, 10, 10));
		
		JLabel lblTaskTitle = new JLabel("Title:");
		lblTaskTitle.setBorder(new EmptyBorder(0, 8, 0, 0));
		lblTaskTitle.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		panelTaskDetails.add(lblTaskTitle);
		
		textTaskTitle = new JTextField();
		textTaskTitle.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		panelTaskDetails.add(textTaskTitle);
		textTaskTitle.setColumns(10);
		
		JLabel lblTaskDescription = new JLabel("Description:");
		lblTaskDescription.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		lblTaskDescription.setBorder(new EmptyBorder(0, 8, 0, 0));
		panelTaskDetails.add(lblTaskDescription);
		
		JTextArea textTaskDescription = new JTextArea();
		textTaskDescription.setRows(3);
		textTaskDescription.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		panelTaskDetails.add(textTaskDescription);
		
		JLabel lblTaskPriority = new JLabel("Priority: ");
		lblTaskPriority.setBorder(new EmptyBorder(0, 8, 0, 0));
		lblTaskPriority.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		panelTaskDetails.add(lblTaskPriority);
		
		JComboBox<Object> dropDownPriority = new JComboBox<>(prioritySet.toArray());
		dropDownPriority.setFont(new Font("PingFang HK", Font.PLAIN, 14));
		panelTaskDetails.add(dropDownPriority);
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
