package studyplanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class StudyPlannerApp extends JFrame {
    private JTextField subjectField, topicField;
    private JComboBox<String> statusBox;
    private JTextArea displayArea;

    public StudyPlannerApp() {
        setTitle("Study Planner App");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        subjectField = new JTextField(10);
        topicField = new JTextField(10);
        statusBox = new JComboBox<>(new String[]{"Done", "Not Done"});
        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);

        JButton addBtn = new JButton("Add Topic");
        JButton viewBtn = new JButton("View All");

        add(new JLabel("Subject:"));
        add(subjectField);
        add(new JLabel("Topic:"));
        add(topicField);
        add(new JLabel("Status:"));
        add(statusBox);
        add(addBtn);
        add(viewBtn);
        add(new JScrollPane(displayArea));

        addBtn.addActionListener(e -> addTopic());
        viewBtn.addActionListener(e -> viewTopics());
    }

    private void addTopic() {
        String subject = subjectField.getText().trim();
        String topic = topicField.getText().trim();
        String status = (String) statusBox.getSelectedItem();

        if (subject.isEmpty() || topic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        StudyTopic st = new StudyTopic(subject, topic, status);
        StudyManager.saveTopic(st);
        JOptionPane.showMessageDialog(this, "Topic added successfully!");
        subjectField.setText("");
        topicField.setText("");
    }

    private void viewTopics() {
        List<String> list = StudyManager.loadTopics();
        displayArea.setText("");
        for (String s : list) {
            displayArea.append(s + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudyPlannerApp().setVisible(true));
    }
}