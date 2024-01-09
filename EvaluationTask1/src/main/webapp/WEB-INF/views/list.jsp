<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BOOKリスト</title>
</head>
<body>
    <h2>BOOKリスト</h2>

    <p><%= (String)request.getAttribute("message") %></p>

    <table border="1">
        <thead>
            <tr>
                <th>Book_ID</th>
                <th>JANコード</th>
                <th>ISBNコード</th>
                <th>書籍名称</th>
                <th>書籍名称カナ</th>
                <th>価格</th>
                <th>登録日時</th>
                <th>更新日時</th>
            </tr>
        </thead>
        <tbody>
            <%
            ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, String>>) request.getAttribute("rows");
            if (rows != null) {
                for (HashMap<String, String> columns : rows) {
            %>
                    <tr>
                        <td><%= columns.get("id") %></td>
                        <td><%= columns.get("JANコード") %></td>
                        <td><%= columns.get("ISBN_CD") %></td>
                        <td><%= columns.get("BOOK_NM") %></td>
                        <td><%= columns.get("BOOK_KANA") %></td>
                        <td><%= columns.get("PRICE") %></td>
                        <td><%= columns.get("ISSUE_DATE") %></td>
                        <td><%= columns.get("CREATE_DATETIME") %></td>
                        <td><%= columns.get("UPDATE_DATETIME") %></td>
                    </tr>
            <%
                }
            }
            %>
        </tbody>
    </table>

    <p><a href="edit">編集</a></p>

</body>
</html>
