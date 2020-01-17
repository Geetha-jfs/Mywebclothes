package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/labcs-web-clothes/dbselect")
public class DBSelectServlet extends HttpServlet {
	Connection connection = null;
	@Override
	public void init() throws ServletException {
		try {
			System.out.println("INIT INVOKED");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			 connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SERVICE INVOKED");
		PrintWriter out = resp.getWriter();
		out.println("<h1>Welcome to Servlets</h1>");	
		fetchEmployeeDetails(out);
		generateTable(out);
		fetchEmployeeDetailsAsTable(out);
	}
	
	public void fetchEmployeeDetails(PrintWriter out) {
		// Get ojdbc14.jar
		// Load the driver
		try {
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			ResultSet resultSet = statement.executeQuery("select * from employees");
			while(resultSet.next()) {
				int employeeId = resultSet.getInt("employee_id");
				String firstName = resultSet.getString("first_name");
				System.out.println(employeeId + ">" + firstName);
				out.println(employeeId + ">" + firstName + "<br/>");
			}
			System.out.println(resultSet);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	public void generateTable(PrintWriter out) {
		out.println("<table><tr><td>Employee Id</td><td>First Name</td></tr>");

		out.println("</table>");
	}
	
	public void fetchEmployeeDetailsAsTable(PrintWriter out) {
		// Get ojdbc14.jar
		// Load the driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Driver loaded successfully!");
			//Get the connection
			 connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
			//System.out.println("Connection Established!");
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			ResultSet resultSet = statement.executeQuery("select * from employees");
			out.println("<table border='1'><tr>");
			out.println("<td>Employee Id</td>");
			out.println("<td>First Name</td>");
			out.println("<td>Last Name</td>");
			out.println("<td>Salary</td>");
			out.println("<td>Hire Date</td>");
			out.println("</tr>");
			while(resultSet.next()) {
				out.println("<tr>");
				out.println("<td>" + resultSet.getInt("employee_id") + "</td>");
				out.println("<td>" + resultSet.getString("first_name") + "</td>");
				out.println("<td>" + resultSet.getString("last_name") + "</td>");
				out.println("<td>" + resultSet.getFloat("salary") + "</td>");
				out.println("<td>" + resultSet.getDate("HIRE_DATE") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}




	





