package com.pennant.prodmtr.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.Dao.Interface.UserDao;
import com.pennant.prodmtr.model.Entity.Role;
import com.pennant.prodmtr.model.Entity.Subtask;
import com.pennant.prodmtr.model.Entity.Task;
import com.pennant.prodmtr.model.Entity.User;
import com.pennant.prodmtr.service.Impl.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdatePassword() {
        Integer userId = 1;
        String newPassword = "newPassword";

        // Assuming userDao.UpdatePassword() is a void method
        userService.UpdatePassword(userId, newPassword);

        // Verify that userDao.UpdatePassword() was called with the correct arguments
        verify(userDao).UpdatePassword(userId, newPassword);
    }

    @Test
    public void testGetUserActivities() {
        Role userRole = new Role();
        userRole.setRoleId((short) 1);

        String taskStatus = "REVW";
        List<Task> tasks = new ArrayList<>();
        // Add tasks to the list
        when(userDao.getUserActivities(taskStatus)).thenReturn(tasks);

        List<Task> resultTasks = userService.getUserActivities(userRole);

        // Add assertions here to check if the resultTasks list matches the expected tasks list
    }

    @Test
    public void testGetUserSubtaskActivities() {
        Role userRole = new Role();
        userRole.setRoleId((short) 2);

        String taskStatus = "REVW";
        List<Subtask> subtasks = new ArrayList<>();
        // Add subtasks to the list
        //when(userDao.getUserSubtaskActivities(taskStatus)).thenReturn(subtasks);

        //List<Subtask> resultSubtasks = userService.getUserSubtaskActivities(userRole);

        // Add assertions here to check if the resultSubtasks list matches the expected subtasks list
    }

    @Test
    public void testVerifyUser_ValidUser() {
        HttpSession session = Mockito.mock(HttpSession.class);
        User user = new User();
        user.setUserDisplayName("username");
        user.setUserPassword("password");

        User myUser = new User();
        myUser.setUserDisplayName("username");
        myUser.setUserPassword("password");

        when(userDao.verifyUser(user.getUserDisplayName())).thenReturn(myUser);

        String result = userService.verifyUser(user, session);

        // Assert that the returned value is "productivity" since the user is verified
        Assert.assertEquals(result, "productivity");
        // Verify that the user was set in the session
        verify(session).setAttribute("user", myUser);
    }

    @Test
    public void testVerifyUser_InvalidUser() {
        HttpSession session = Mockito.mock(HttpSession.class);
        User user = new User();
        user.setUserDisplayName("username");
        user.setUserPassword("password");

        when(userDao.verifyUser(user.getUserDisplayName())).thenReturn(null);

        String result = userService.verifyUser(user, session);

        // Assert that the returned value is "login" since the user is not found
        Assert.assertEquals(result, "login");
        // Verify that the user was not set in the session
        verify(session, never()).setAttribute(any(), any());
    }

    @Test
    public void testVerifyUser_InvalidPassword() {
        HttpSession session = Mockito.mock(HttpSession.class);
        User user = new User();
        user.setUserDisplayName("username");
        user.setUserPassword("password");

        User myUser = new User();
        myUser.setUserDisplayName("username");
        myUser.setUserPassword("wrongPassword");

        when(userDao.verifyUser(user.getUserDisplayName())).thenReturn(myUser);

        String result = userService.verifyUser(user, session);

        // Assert that the returned value is "login" since the password is incorrect
        Assert.assertEquals(result, "login");
        // Verify that the user was not set in the session
        verify(session, never()).setAttribute(any(), any());
    }
}

