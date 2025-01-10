/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojBackEnd;

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
import javax.swing.JFileChooser;
import utils.SQLQueries;

public class DatabaseHelper {
    //return 1 for student, 2 for teacher 0, for not exsit
    public int userExists(String name){
        //Selecting name if the user exists from the table
        
        String query = "SELECT * FROM dmojStudent WHERE Username = '" + name + "';";
        
        String response = sendSQLQuery(query);
        
        System.out.println(response);
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
    public int registerUser(String name, String password)  {
        //if user exists return 1
        
        if(userExists(name)==1 || userExists(name)==2){
           return 1; 
        }
        String query="INSERT into dmojStudent VALUES(NULL,'"+name+"','"+password+"');";
        System.out.println("hi");
        sendSQLQuery(query);
        return 0;
    }
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
            // return 1 if user does not exists
            return 0;
        }
    }
    public static String readFile() {
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

    
}