package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "Bookリストを見てね");
			//このmessageの値がnullの場合Bookリストを見てねと表示される
		}
		
		int postId = Integer.parseInt(request.getParameter("book_id"));
		
		String url = "jdbc:mysql://localhost/sample2";
		String user = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT * FROM BOOK WHERE id =?";
		try (Connection connection = DriverManager.getConnection(url);
		PreparedStatement statement =connection.prepareStatement(sql);
		){
			
			statement.setInt(1, postId);
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				
				
				String id = results.getString("id");
				request.setAttribute("id", id);
				String JAN_CD = results.getString("JAN_CD");
				request.setAttribute("JAN_CD", JAN_CD);
				String ISBN_CD = results.getString("ISBN_CD").replace("¥n","<br>");
				request.setAttribute("ISBN_CD", ISBN_CD);
				String BOOK_NM = results.getString("BOOK_NM").replace("¥n","<br>");
				request.setAttribute("BOOK_NM", BOOK_NM);
				String BOOK_KANA = results.getString("BOOK_KANA").replace("¥n","<br>");
				request.setAttribute("BOOK_KANA", BOOK_KANA);
				String PRICE = results.getString("PRICE");
				request.setAttribute("PRICE", PRICE);
				String ISSUE_DATE = results.getString("ISSUE_DATE");
				request.setAttribute("ISSUE_DATE", ISSUE_DATE);
				String CREATE_DATETIME = results.getString("CREATE_DATETIME");
				request.setAttribute("CREATE_DATETIME", CREATE_DATETIME);
				String UPDATE_DATETIME = results.getString("UPDATE_DATETIME");
				request.setAttribute("UPDATE_DATETIME", UPDATE_DATETIME);
			}
			
		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.getMessage());
		}
		
		String view = "/WEB-INF/views/edit.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}


}