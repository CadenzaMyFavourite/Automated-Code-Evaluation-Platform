/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DmojBackEnd;

import java.security.NoSuchAlgorithmException;
import org.json.JSONArray;
import org.json.JSONObject;
import static utils.SQLQueries.sendSQLQuery;

/**
 *
 * @author HP User
 */
public class DatabaseHelper {
    //return 1 for student, 2 for teacher 0, for not exsit
    public int userExists(String name){
        //Selecting name if the user exists from the table
        
        String query = "SELECT * FROM dmojStudent WHERE Username = '" + name + "';";
        
        String response = sendSQLQuery(query);
        
        System.out.println(response);
        if (!(response==null)) {
            return 1;  
        }
        query = "SELECT * FROM dmojTeacher WHERE Username = '" + name + "';";
        response = sendSQLQuery(query);
        if (!(response==null)) {
            return 2;  
        }
        System.out.println("Error: User needs to register first.");
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
            String storedPassword = userInfo.getString("Password");
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
            String storedPassword = userInfo.getString("Password");
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
}
