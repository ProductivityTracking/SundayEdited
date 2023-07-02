<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sub Tasks List</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        .clickable-row {
            cursor: pointer;
        }
    </style>
    <script>
        function handleClick(subtaskId,taskId) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'getSubtaskDetails';
            form.style.display = 'none';

            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'subtaskId';
            input.value = subtaskId;

            const input2 = document.createElement('input');
            input2.type = 'hidden';
            input2.name = 'taskId';
            input2.value = taskId;

            
            form.appendChild(input);
            form.appendChild(input2);
            document.body.appendChild(form);

            form.submit();
        }
    </script>
</head>
<body>
    <h1>Sub Task List</h1>
    <table>
        <thead>
            <tr>
                <th>SubTask ID</th>
                <th>Task ID</th>
                <th>Subtask Description</th>
                <th>Approval Status</th>
                <th>Number of Hours</th>
                <th>Creation Date</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${subtasks}" var="subtask">
                <tr class="clickable-row" onclick="handleClick('${subtask.primaryKey.subtaskId}','${subtask.primaryKey.taskId}')">
                    <td>${subtask.primaryKey.subtaskId}</td>
                    <td>${subtask.primaryKey.taskId}</td>
                    <td>${subtask.subtaskDescription}</td>
                    <td>${subtask.apprStatus}</td>
                    <td>${subtask.numberOfHours}</td>
                    <td>${subtask.creationDate}</td>
                    <td>${subtask.sbts_status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
