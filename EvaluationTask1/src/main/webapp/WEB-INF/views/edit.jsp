<%@ page language="java"
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Todo編集</title>
  <style>ul {list-style: none; margin: 0; padding: 0;} li {float: left; margin-right: 20px; }</style>
</head>
<body>
    <h1>Todo編集</h1>
     <% String message = (String)request.getAttribute("message"); %>
   
    <p><%= message%></p>
    <form action="update" method="get">
      <input type="hidden" name="id" value='<%= request.getAttribute("id") %>'>
      <label for="title">タイトル</label><br>
      <input type="text" name="title" value='<%= request.getAttribute("title") %>'><br>
      <br>
      <label for="content">本文</label><br>
      <textarea name="content" id="" cols="30" rows="10"><%= request.getAttribute("content") %></textarea>
      <p></p>
      
       <label for="pet-select">優先度を選択:</label>

	<select name="upup" id="pet-select">
  	　<option value="">--Please choose priority--</option>
	  <option value="0">high</option>
	  <option value="1">normal</option>
	  <option value="2">low</option>
    </select>
      <button type="submit">保存する</button>
      <p><a href='show?id=<%= request.getAttribute("id") %>'>キャンセル</a></p>
  </form>
   
</body>
</html>
