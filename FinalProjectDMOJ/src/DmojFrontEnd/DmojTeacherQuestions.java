/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojFrontEnd;

import DmojBackEnd.DatabaseHelper;
import Objects.Question;
import static com.sun.java.accessibility.util.AWTEventMonitor.addComponentListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import utils.CardSwitcher;

/**
 *
 * @author readingsdoc.
 */


public class DmojTeacherQuestions extends JPanel{
    public static final String CARD_NAME = "questionList";
    private DatabaseHelper d;
    private CardSwitcher s;
    
    public DmojTeacherQuestions(CardSwitcher s, DatabaseHelper d) {
        this.s = s;
        this.d = d;

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                setUpPanels();
            }
        });
    }
    private void setUpPanels() {
        removeAll(); // Clear any existing components
        setLayout(new BorderLayout());

        // Fetch student list from database helper
        List<String> questions = d.getQuestions();

        // Set up the main panel with a grid layout for student names and buttons
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add column headers
        gbc.gridy = 0;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Questions"), gbc);
        gbc.gridx = 1;
        //mainPanel.add(new JLabel("Answer"), gbc);

        // Add student names and buttons
        for (int i = 0; i < questions.size(); i++) {
            String question = questions.get(i);

            // Add student name label
            gbc.gridy = i + 1;
            gbc.gridx = 0;
            mainPanel.add(new JLabel(question), gbc);

            // Add button to navigate to a separate page
            JButton detailsButton = new JButton("View Details");
            detailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Question q = d.getQT(question);
                    DmojTeacherDetailsPanel.setQ(q);
                    s.switchToCard(DmojTeacherDetailsPanel.CARD_NAME);
                }
            });
            gbc.gridx = 1;
            mainPanel.add(detailsButton, gbc);
        }
        
        JButton addButton = new JButton("Add Question");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.switchToCard(DmojTeacherAddPanel.CARD_NAME);
            }
        });
        
        JButton backButton = new JButton("Back");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.switchToCard(DmojTeacherQuestions.CARD_NAME);
            }
        });
        
        JPanel bottomPanel = new JPanel(); 
        bottomPanel.add(addButton);
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
        

        // Wrap the main panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
