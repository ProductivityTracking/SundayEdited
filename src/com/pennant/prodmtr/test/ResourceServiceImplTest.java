package com.pennant.prodmtr.test;

import com.pennant.prodmtr.Dao.Interface.ResourceDao;
import com.pennant.prodmtr.model.Dto.ResourceFilter;
import com.pennant.prodmtr.model.Dto.UserDto;
import com.pennant.prodmtr.model.Entity.User;
import com.pennant.prodmtr.model.Input.UserInput;
import com.pennant.prodmtr.service.Impl.ResourceServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class ResourceServiceImplTest {

    @Mock
    private ResourceDao resourceDAO;

    @InjectMocks
    private ResourceServiceImpl resourceService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllResources() {
        // Create some sample data for the test
        List<UserDto> users = new ArrayList<>();
        // Add data to the list (add appropriate UserDto objects to the list)

        // Stub the behavior of the resourceDAO.getAllResources() method
        when(resourceDAO.getAllResources()).thenReturn(users);

        // Call the method to be tested
        List<UserDto> result = resourceService.getAllResources();

        // Verify the result
        // Add appropriate assertions based on the behavior of getAllResources() method
    }

    @Test
    public void testFilterResources() {
        // Create some sample data for the test
        ResourceFilter resourceFilter = new ResourceFilter();
        // Set appropriate values for the resourceFilter

        List<UserDto> filteredUsers = new ArrayList<>();
        // Add data to the list (add appropriate UserDto objects to the list)

        // Stub the behavior of the resourceDAO.filterResources() method
        when(resourceDAO.filterResources(anyShort(), anyShort())).thenReturn(filteredUsers);

        // Call the method to be tested
        List<UserDto> result = resourceService.filterResources(resourceFilter);

        // Verify the result
        // Add appropriate assertions based on the behavior of filterResources() method
    }

    @Test
    public void testGetResourceByDisplayName() {
        // Create some sample data for the test
        String displayName = "JohnDoe";
        User user = new User();
        // Set appropriate values for the user object

        // Stub the behavior of the resourceDAO.getResourceByDisplayName() method
        when(resourceDAO.getResourceByDisplayName(displayName)).thenReturn(user);

        // Call the method to be tested
        User result = resourceService.getResourceByDisplayName(displayName);

        // Verify the result
        // Add appropriate assertions based on the behavior of getResourceByDisplayName() method
    }

    @Test
    public void testAddUser() {
        // Create some sample data for the test
        UserInput userInput = new UserInput();
        // Set appropriate values for the userInput object

        // Call the method to be tested
        resourceService.addUser(userInput);

        // Verify the behavior
        // Add appropriate assertions or verify if the resourceDAO.addUser() method was called with the expected User object
    }

    @Test
    public void testSave() {
        // Create some sample data for the test
        UserInput existingResource = new UserInput();
        // Set appropriate values for the existingResource object

        // Stub the behavior of the resourceDAO.saveUser() method if necessary
        // when(resourceDAO.saveUser(any(User.class))).thenReturn(...);

        // Call the method to be tested
        resourceService.save(existingResource);

        // Verify the behavior
        // Add appropriate assertions or verify if the resourceDAO.saveUser() method was called with the expected User object
    }

    @Test
    public void testGetAllProjManagers() {
        // Create some sample data for the test
        List<User> projManagers = new ArrayList<>();
        // Add data to the list (add appropriate User objects to the list)

        // Stub the behavior of the resourceDAO.getAllProjManagers() method
        when(resourceDAO.getAllProjManagers()).thenReturn(projManagers);

        // Call the method to be tested
        List<User> result = resourceService.getAllProjManagers();

        // Verify the result
        // Add appropriate assertions based on the behavior of getAllProjManagers() method
    }

    @Test
    public void testGetUsersByProjectId() {
        // Create some sample data for the test
        int projectId = 123;
        List<User> users = new ArrayList<>();
        // Add data to the list (add appropriate User objects to the list)

        // Stub the behavior of the resourceDAO.getUsersByProjectId() method
        when(resourceDAO.getUsersByProjectId(projectId)).thenReturn(users);

        // Call the method to be tested
        List<User> result = resourceService.getUsersByProjectId(projectId);

        // Verify the result
        // Add appropriate assertions based on the behavior of getUsersByProjectId() method
    }

    @Test
    public void testGetResourceById() {
        // Create some sample data for the test
        int userId = 456;
        User user = new User();
        // Set appropriate values for the user object

        // Stub the behavior of the resourceDAO.getResourceById() method
        when(resourceDAO.getResourceById(userId)).thenReturn(user);

        // Call the method to be tested
        User result = resourceService.getResourceById(userId);

        // Verify the result
        // Add appropriate assertions based on the behavior of getResourceById() method
    }

    @Test
    public void testGetResourceByUserId() {
        // Create some sample data for the test
        int userId = 789;
        User user = new User();
        // Set appropriate values for the user object

        // Stub the behavior of the resourceDAO.getResourceByUserId() method
        when(resourceDAO.getResourceByUserid(userId)).thenReturn(user);

        // Call the method to be tested
        User result = resourceService.getResourceByUserId(userId);

        // Verify the result
        // Add appropriate assertions based on the behavior of getResourceByUserId() method
    }

    @Test
    public void testSaveUser() {
        // Create some sample data for the test
        User user = new User();
        // Set appropriate values for the user object

        // Call the method to be tested
        resourceService.save(user);

        // Verify the behavior
        // Add appropriate assertions or verify if the resourceDAO.saveUser() method was called with the expected User object
    }

    // Add more test methods for other methods in the ResourceServiceImpl class

}
