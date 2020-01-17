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

@WebServlet("/DBBookSelectServlet")
public class DBBookSelectServlet extends HttpServlet {
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
		} 
		catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SERVICE INVOKED");
		PrintWriter out = resp.getWriter();
		out.println("<h1>Welcome to Servlets</h1>");	
		fetchBookDetailsAsTable(out);
	
	}
	public void fetchBookDetailsAsTable(PrintWriter out) {
		// Get ojdbc14.jar
		// Load the driver
		try {
			//Create the statement
			Statement statement = connection.createStatement();
			//Execute the query
			ResultSet resultSet = statement.executeQuery("select * from book");
			out.println("<table border='1'><tr>");
			out.println("<td>Book Id</td>");
			out.println("<td>Book Name</td>");
			out.println("<td>Book Author</td>");
			out.println("</tr>");
			while(resultSet.next()) {
				out.println("<tr>");
				out.println("<td>" + resultSet.getInt("book_id") + "</td>");
				out.println("<td>" + resultSet.getString("book_name") + "</td>");
				out.println("<td>" + resultSet.getString("book_author") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}

	
	