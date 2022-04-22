package com.dwaith.employee.dao;
import  java.sql.*;

import com.dwaith.employee.model.Employee;

public class EmployeeDao {
	
public int employeeRegister(Employee employee) throws ClassNotFoundException {
	
	String INSERT_USERS_SQL = "INSERT INTO employee" +
            "  (id, first_name, last_name, username, password, address, contact) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?);";
        
        String SELECT_COUNT_SQL= "select count(id) as idno from employee";
        
        String dbdriverName = "com.mysql.jdbc.Driver";
        
        int result = 0;
        
        Class.forName(dbdriverName);
        try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false",
					"root", "password");
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(SELECT_COUNT_SQL);
			if (rs.next()) {
				result = rs.getInt("idno");
				System.out.println("idno :: " + rs.getInt("idno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false", "root", "password");
            		//int count=0;
            	
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
                preparedStatement.setInt(1, result+1);
                preparedStatement.setString(2, employee.getFirstName());
                preparedStatement.setString(3, employee.getLastName());
                preparedStatement.setString(4, employee.getUsername());
                preparedStatement.setString(5, employee.getPassword());
                preparedStatement.setString(6, employee.getAddress());
                preparedStatement.setString(7, employee.getContact());

                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                result = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                // process sql exception
                e.printStackTrace();
            }
           
	
	return result;
	
	
	
}
}