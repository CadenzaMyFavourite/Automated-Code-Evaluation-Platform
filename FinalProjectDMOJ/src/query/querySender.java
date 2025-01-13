/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package query;

import static utils.SQLQueries.sendSQLQuery;

/**
 *
 * @author HP User
 */
public class querySender {
    public static void main(String[] args) {
       
       String query="INSERT INTO dmojQuestion VALUES(null,'print hello world','d');";
       sendSQLQuery(query);
    }
}
