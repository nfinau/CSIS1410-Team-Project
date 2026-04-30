

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

public class GuiStudentTaskTracker extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTaskTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiStudentTaskTracker frame = new GuiStudentTaskTracker();
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
	public GuiStudentTaskTracker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Set<String> filterSet = Set.of("All", "Low", "Medium Priority", "High Priority", "Complete");
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

	private void createInteractionPanelMain(Set<String> filterSet, JPanel mainPanel) {
		
		
		
		
		
	}

}
