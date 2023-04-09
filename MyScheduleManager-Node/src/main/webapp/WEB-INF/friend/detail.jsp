<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:useBean class="model.friend.FriendInfo" id="friend" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>友人詳細</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detailFriend.css">
</head>
<body>
<h1>友人情報詳細</h1>
<p>友人のステータスを確認することができます</p>

<form method="post">
<table border="1" id="table">
<tr><td>フレンドコード：</td><td>${friend.friendId }</td></tr>
<tr><td>ユーザーコード：</td><td>${friend.userId }</td></tr>
<tr><td>　　　　顔写真：</td><td><img src="${imageLink }" class="photo"></td></tr>
<tr><td>　　　友人詳細：</td><td>${friend.introduction }</td></tr>
<tr><td>　　　友人氏名：</td><td>${friend.name }</td></tr>
<tr><td>　　　　　職業：</td><td>${friend.status }</td></tr>
<tr><td>　　　　　性別：</td><td>${friend.sex }</td></tr>
<tr><td>　　　Eメール：</td><td>${friend.email }</td></tr>
<tr><td>　　　　　住所：</td><td>${friend.address }</td></tr>
<tr><td>　　　電話番号：</td><td>${friend.tel }</td></tr>
</table>

<p id="button">
<%--予定詳細画面から参照された場合は編集ボタンを表示しない --%>
<c:if test="${friendList != null }">
<button type="submit" name="state" value="edit" formaction="FriendServlet">編集</button>
</c:if>
<%--友人検索結果から参照された場合は予定詳細画面へ遷移させない --%>
<c:if test="${schedule != null }">
<button type="submit" name="state" value="detail,${schedule.scheduleId }" formaction="ScheduleServlet">予定詳細画面に戻る</button>
</c:if>
<%--予定詳細画面から参照された場合は編集ボタンを表示しない --%>
<c:if test="${friendList != null }">
<button type="submit" name="state" value="listReturn" formaction="FriendServlet">友人検索結果へ戻る</button>
</c:if>

</p>
</form>
</body>
</html>