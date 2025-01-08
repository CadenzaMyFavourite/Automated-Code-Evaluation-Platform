/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalprojectdmoj;

/**
 *
 * @author HP User
 */

import Objects.Student;
import Objects.Teacher;
import java.util.ArrayList;
import utils.SQLQueries;

public class DatabaseHelper {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();
    
    public void loadUsers() {
        String query, response;
        query = "INSERT INTO `dmojStudent`(`StudentID`, `Username`, `Password`) VALUES (null,'123456789','Oscar')";
        response = SQLQueries.sendSQLQuery(query);
        
        System.out.print("Response from server:");
        System.out.println(response);
    }
    
    public static void main(String[] args) {
        DatabaseHelper d = new DatabaseHelper();
        d.loadUsers();
    }
}
