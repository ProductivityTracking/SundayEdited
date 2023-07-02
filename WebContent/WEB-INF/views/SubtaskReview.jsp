<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Subtask Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 20px;
        }

        .subtask-details {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 20px;
            margin-bottom: 20px;
        }

        .subtask-details h2 {
            margin-top: 0;
            color: #333;
        }

        .subtask-details p {
            margin: 0;
            line-height: 1.5;
        }

        .subtask-details label {
            display: inline-block;
            width: 150px; /* Adjust the width as needed */
            font-weight: bold;
            margin-bottom: 5px; /* Add spacing between rows */
        }

        .subtask-details input[type="text"],
        .subtask-details select {
            width: 300px;
            margin-bottom: 10px;
        }

        .subtask-details textarea {
            width: 300px;
            height: 100px;
            margin-bottom: 10px;
        }

        .subtask-details button[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="subtask-details">
        <h2>Subtask Details</h2>
        <p>
            <label>Task Id:</label>
            ${subtask.primaryKey.taskId}
        </p>
        <p>
            <label>Subtask Id:</label>
            ${subtask.primaryKey.subtaskId}
        </p>
        <p>
            <label>Description:</label>
            ${subtask.subtaskDescription}
        </p>
        <p>
            <label>No of Hours:</label>
            ${subtask.numberOfHours}
        </p>
        <p>
            <label>Creation Date:</label>
            ${subtask.creationDate}
        </p>
        <p>
            <label>Subtask Status:</label>
            ${subtask.sbts_status}
        </p>

        <form id="subtaskForm" action="setSubtaskRemarks" method="POST">
            <input type="hidden" name="taskId" value="${subtask.primaryKey.taskId}" />
            <input type="hidden" name="subtaskId" value="${subtask.primaryKey.subtaskId}" />
            
            <p>
                <select id="status" name="sbtsStatus">
                    <c:choose>
                        <c:when test="${user.userRole.roleId == 1 || user.userRole.roleId == 2}">
                            <option value="REVW">Review</option>
                            <option value="REFC">Refactor</option>
                            <option value="COMP">Completed</option>
                        </c:when>
                        <c:otherwise>
                            <option value="REVW">Review</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </p>
            <p>
                <button type="submit" >Submit</button>
            </p>
        </form>
    </div>
    
        <script>
    </script>
</body>
</html>
