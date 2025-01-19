/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojFrontEnd;

import DmojBackEnd.DatabaseHelper;
import Objects.Response;
import static com.sun.java.accessibility.util.AWTEventMonitor.addComponentListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import utils.CardSwitcher;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author readingsdoc.
 */
public class DmojStudentScorePanel extends JPanel{
    public static final String CARD_NAME = "studentScore";
    DatabaseHelper d;
    CardSwitcher switcher = null;
    private static String username;
    
    
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String user) {
        
        username = user;
        
    }
    
    /**
     * Creates new form DmojTeacherListPanel
     */
    public DmojStudentScorePanel(CardSwitcher p,DatabaseHelper d) {
        switcher=p;
        this.d=d;
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                setUpPanels();
            }
        });
    }
    
    private void setUpPanels() {
        removeAll(); // Clear any existing components
        setLayout(new BorderLayout());
        System.out.println("Hello This works.");


        // Fetch the list of responses for the given username
        List<Response> responses = d.getResponse(username);
        d.sortResponse(responses);


        // Set up the main panel with a grid layout for QuestionID and Grade
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add column headers
        gbc.gridy = 0;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("QuestionID"), gbc);
        gbc.gridx = 1;
        mainPanel.add(new JLabel("Grade"), gbc);

        // Populate the list of QuestionID and Grade
        for (int i = 0; i < responses.size(); i++) {
            Response response = responses.get(i);

            // Add QuestionID label
            gbc.gridy = i + 1;
            gbc.gridx = 0;
            mainPanel.add(new JLabel(String.valueOf(response.getQuestionID())), gbc);

            // Add Grade label
            gbc.gridx = 1;
            mainPanel.add(new JLabel(String.valueOf(response.getGrade())), gbc);
        }
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switcher.switchToCard(DmojTeacherMenu.CARD_NAME);
            }
        });
        // Wrap the main panel in a scroll pane
        JPanel bottomPanel = new JPanel(); 
        
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) { 
        this.setUpPanels();
    }
    
}
