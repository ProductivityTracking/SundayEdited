package com.pennant.prodmtr.test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.Dao.Interface.SprintDao;
import com.pennant.prodmtr.model.Dto.ModuleDTO;
import com.pennant.prodmtr.model.Entity.Sprint;
import com.pennant.prodmtr.model.Entity.Task;
import com.pennant.prodmtr.service.Impl.SprintServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SprintServiceImplTest {

    @Mock
    private SprintDao sprintDao;

    @InjectMocks
    private SprintServiceImpl sprintService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBacklogs() {
        // Mocking expected output
        List<Sprint> expectedBacklogs = new ArrayList<>();
        when(sprintDao.getBaskLogs()).thenReturn(expectedBacklogs);

        // Call the method to be tested
        List<Sprint> resultBacklogs = sprintService.getBacklogs();

        // Verify the result
        // Add your assertions here
    }

    @Test
    public void testGetSprintDetails() {
        // Mocking input and expected output
        int sprintId = 1;
        Sprint expectedSprint = new Sprint();
        when(sprintDao.getSprintDetails(eq(sprintId))).thenReturn(expectedSprint);

        // Call the method to be tested
        Sprint resultSprint = sprintService.getSprintDetails(sprintId);

        // Verify the result
        // Add your assertions here
    }

    @Test
    public void testGetTasks() {
        // Mocking input and expected output
        int modlId = 1;
        List<Task> expectedTasks = new ArrayList<>();
        when(sprintDao.getTasks(eq(modlId))).thenReturn(expectedTasks);

        // Call the method to be tested
        List<Task> resultTasks = sprintService.getTasks(modlId);

        // Verify the result
        // Add your assertions here
    }

    // Add more test methods for other methods in SprintServiceImpl if needed...

    @Test
    public void testStoreSprint() {
        // Mocking input and expected output
        Sprint sprintToStore = new Sprint();
        when(sprintDao.storeSprint(eq(sprintToStore))).thenReturn(sprintToStore);

        // Call the method to be tested
        Sprint resultSprint = sprintService.storeSprint(sprintToStore);

        // Verify the result
        // Add your assertions here
    }

    @Test
    public void testGetSprintModulesByProjectId() {
        // Mocking input and expected output
        int projectId = 1;
        List<ModuleDTO> expectedModules = new ArrayList<>();
        when(sprintDao.getSprintModulesByProjectId(eq(projectId))).thenReturn(expectedModules);

        // Call the method to be tested
        List<ModuleDTO> resultModules = sprintService.getSprintModulesByProjectId(projectId);

        // Verify the result
        // Add your assertions here
    }

    // Add more test methods for other methods in SprintServiceImpl if needed...
}

