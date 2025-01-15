/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojBackEnd;

import Objects.Response;
import java.security.NoSuchAlgorithmException;
import org.json.JSONArray;
import org.json.JSONObject;
import static utils.SQLQueries.sendSQLQuery;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author HP User
 */

import Objects.Student;
import Objects.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import utils.SQLQueries;

public class DatabaseHelper {
    //author: Zhu Zhu man
    /**
     * Checks if a user exist in the teacher table and student table
     * returns an integer for further checking
     * @param name the input of username
     * @return  1 for student, 2 for teacher 0 for not exist
     */
    public int userExists(String name){
        //Selecting all students with input name from the student table
        String query = "SELECT * FROM dmojStudent WHERE Username = '" + name + "';";
        String response = sendSQLQuery(query);
        //return 1 if result is not empty, that user already exist in student table
        if (!(response.equals("[]"))) {
            return 1;  
        }
        query = "SELECT * FROM dmojTeacher WHERE Username = '" + name + "';";
        response = sendSQLQuery(query);
        if (!(response.equals("[]"))) {
            return 2;  
        }
        
        return 0;
    }
    /**
     * send sql queries to databases
     * will not add a new user if the user already exist
     * @param name the username of user
     * @param password the password of user
     * @return 1 for user exists, 0 for successfully added
     */
    //Zhu Zhu man
    public int registerUser(String name, String password)  {
        //if the user already exist, return 1
        if(userExists(name)==1 || userExists(name)==2){
           return 1; 
        }
        //add student to the database
        String query="INSERT into dmojStudent VALUES(NULL,'"+name+"','"+password+"');";
        sendSQLQuery(query);
        return 0;
    }
    /**
     * this code checks if the corresponding name and password exists in a database
     * @param name the username if users input
     * @param password the password of a user's input
     * @return 1 for a student login, 2 for a teacher login, 3 for wrong password, 0 for not exsisting user
     */
    //Zhu Zhu man
    public int loginUser(String name, String password) {
        //only doing this if the user exists 
        if (userExists(name)==1) {
            // check if username and password match
            String query = "SELECT password FROM dmojStudent WHERE Username = '" + name + "';";
            String response = sendSQLQuery(query);
            JSONArray jsonArray = new JSONArray(response);
            JSONObject userInfo = jsonArray.getJSONObject(0);
            String storedPassword = userInfo.getString("password");
            //if password matches
            if (storedPassword.equals(password)) {
                // successfull login
                System.out.println("Login successful!");
                return 1;
            } 
            // else the user's name and password do not match
            else {
                System.out.println("Error: Incorrect username or password.");
                return 3;
            }
        }
        else if(userExists(name)==2){
            String query = "SELECT password FROM dmojTeacher WHERE Username = '" + name + "';";
            String response = sendSQLQuery(query);
            JSONArray jsonArray = new JSONArray(response);
            
            JSONObject userInfo = jsonArray.getJSONObject(0);
            String storedPassword = userInfo.getString("password");
            //if password matches
            if (storedPassword.equals(password)) {
                // successfull login
                System.out.println("Login successful!");
                return 2;
            } 
            // else the user's name and password do not match
            else {
                System.out.println("Error: Incorrect username or password.");
                return 3;
            }
        }
        else{
            System.out.println("Error: User needs to register first.");
            // return 0 if user does not exists
            return 0;
        }
    }
    /**
     * allows the user to open a window to read texts from a txt file
     * this one I copied from a website
     */
    //zhu zhu man
    public String readFile() {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a text file");

        // Open the file chooser dialog
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Check if the selected file is a .txt file
            if (selectedFile.getName().endsWith(".txt")) {
                try {
                    // Read the file content into a string
                    return new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                } catch (IOException e) {
                    System.err.println("Error reading the file: " + e.getMessage());
                }
            } else {
                System.err.println("Please select a valid .txt file.");
            }
        } else {
            System.out.println("File selection canceled.");
        }
        return null; // Return null if the operation fails or is canceled
    }

    
    public static List<String> getStudentNames() {
        
        String query = "SELECT Username FROM dmojStudent;";
        String response = sendSQLQuery(query);
        JSONArray jsonArray = new JSONArray(response);
        List<String> students = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userInfo = jsonArray.getJSONObject(i);
            String studentName = userInfo.getString("Username");
            students.add(studentName);
        }
        return students;
    }
    
    public static List<Response> getResponse(String username) {
        String query = "SELECT dr.QuestionID, dr.Grade FROM dmojResponse dr JOIN dmojStudent ds ON dr.StudentID = ds.StudentID WHERE ds.Username = " + username + ";";
        String response = sendSQLQuery(query);
        JSONArray jsonArray = new JSONArray(response);
        List<Response> responses = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userInfo = jsonArray.getJSONObject(i);
            int questionID = userInfo.getInt("QuestionID");
            int grade = userInfo.getInt("Grade");
            Response r = new Response(questionID, grade);
            responses.add(r);
        }
        return responses;
    }
    /**
     * this method gets all the text of questions stored in table
     * @return a list of string that can be used in a panel for displaying
     */
    //Zhu Zhu man
    public List<String> getQuestions(){
        //select all questions from databse
        String query = "SELECT Text FROM dmojQuestion;";
        String response = sendSQLQuery(query);
        JSONArray jsonArray = new JSONArray(response);
        List<String> questions = new ArrayList<>();
        //repeat for any question
        for (int i = 0; i < jsonArray.length(); i++) {
            //add the texts for each of it to the list
            JSONObject questionInfo = jsonArray.getJSONObject(i);
            String s=questionInfo.getString("Text");
            questions.add(s);
        }
        return questions;
    }
    /**
     * this method insert a new response into the response panel, or update it if the reponse already exist
     * @param studentID student id of each student(a foreign key in the response table)
     * @param questionID question id of each questions(a foreign key in the response table)
     * @param response the text
     */
    public void addResponse(int studentID, int questionID, String response){
        //first check if the user has the question before(checkis if there is corresponding studentID/questionID)
        String query="SELECT * FROM dmojResponse WHERE StudentID = "+studentID+ " AND QuestionID ="+questionID+";";
        String result = sendSQLQuery(query);
        //if not
        if (result.equals("[]")) {
            //add a new reponse to the table
            query="INSERT INTO dmojResponse VALUES(null,"+studentID+","+questionID+",'"+response+"',0);";
            sendSQLQuery(query);  
        }
        else{
            //if it exist, then only updates the text of reposne
            query="UPDATE dmojResponse SET CODE = '"+response+"' WHERE StudentID = "+studentID+ " AND QuestionID ="+questionID+";";
            sendSQLQuery(query);
        }
        
    }
    /**
     * getting the userid by entering corresponding username and password(since no repeated users are allowed)
     * @param name
     * @param password
     * @return the id of student from database
     */
    public int getStudentID(String name, String password){
        //checks the userID with the corresponding username and password
        String query="SELECT StudentID FROM dmojStudent WHERE username = '"+name+"' AND password = '"+password+"';)";
        String response=sendSQLQuery(query);
        //get the only item
        JSONArray jsonArray = new JSONArray(response);
        JSONObject j = jsonArray.getJSONObject(0);
        int id=j.getInt("StudentID");
        System.out.println(id);
        //return
        return id;
    }
   
}
