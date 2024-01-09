package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	
	private int id;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "Bookリストを見てね");
			//このmessageの値がnullの場合Bookリストを見てねと表示される
		}
		
		String url = "jdbc:mysql://localhost/sample2";
		String book = "root";
		//ここではsqlに接続されていて
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//tryでforNameを使い指定したクラスをロード
		//catchで例外をキャッチする
		
		String sql = "SELECT * FROM BOOK WHERE id=?";
		try (Connection connection = DriverManager.getConnection(url);
		PreparedStatement statement =connection.prepareStatement(sql)){
				
				statement.setInt(1, id);	
				
		ResultSet results = statement.executeQuery();
			ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
			//BOOKテーブルからidを取得
			while(results.next()) {
				HashMap<String, String> columns = new HashMap<String, String>();
				
				String id = results.getString("id");
				columns.put("id", id);
				String JAN_CD = results.getString("JAN_CD");
				columns.put("JAN_CD", JAN_CD);
				String ISBN_CD = results.getString("ISBN_CD");
				columns.put("ISBN_CD", ISBN_CD);
				String BOOK_NM = results.getString("BOOK_NM");
				columns.put("BOOK_NM", BOOK_NM);
				String BOOK_KANA = results.getString("BOOK_KANA");
				columns.put("BOOK_KANA", BOOK_KANA);
				String PRICE = results.getString("PRICE");
				columns.put("PRICE", PRICE);
				String ISSUE_DATE = results.getString("ISSUE_DATE");
				columns.put("ISSUE_DATE", ISSUE_DATE);
				String CREATE_DATETIME = results.getString("CREATE_DATETIME");
				columns.put("CREATE_DATETIME", CREATE_DATETIME);
				String UPDATE_DATETIME = results.getString("UPDATE_DATETIME");
				columns.put("UPDATE_DATETIME", UPDATE_DATETIME);
				
				//（変更点）テーブルを作る際のsqlではJANコード、ISBNコード...更新日時に加えて最初にid（データタイプint、NOT NULL、オートインクリメント）をくえた
				
				rows.add(columns);
			}
			request.setAttribute("rows", rows);
		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.getMessage());
		}
		
		String view = "/WEB-INF/views/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
	}