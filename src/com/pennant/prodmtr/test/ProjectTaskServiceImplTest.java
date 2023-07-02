package com.pennant.prodmtr.test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.Dao.Interface.ProjectTaskdao;
import com.pennant.prodmtr.model.Dto.ProjectTaskDTO;
import com.pennant.prodmtr.model.Entity.ProjectTask;
import com.pennant.prodmtr.service.Impl.ProjectTaskServiceImpl;
import com.pennant.prodmtr.service.Interface.TaskService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ProjectTaskServiceImplTest {

    @Mock
    private TaskService taskService;

    @Mock
    private ProjectTaskdao projectTaskDao;

    @InjectMocks
    private ProjectTaskServiceImpl projectTaskService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProjectTask() {
        // Mocking expected output
        ProjectTask projectTask = new ProjectTask();
        // Set the properties of projectTask if needed

        when(projectTaskDao.save(projectTask)).thenReturn(projectTask);

        // Call the method to be tested
        ProjectTask resultProjectTask = projectTaskService.createProjectTask(projectTask);

        // Verify the result
        // Add your assertions here to check if the resultProjectTask matches the expected projectTask
    }

    @Test
    public void testGetProjectTaskDTOList() {
        // Mocking expected output
        List<ProjectTaskDTO> expectedProjectTasks = new ArrayList<>();
        // Add sample data to the expectedProjectTasks list if needed

        when(projectTaskDao.getProjectTaskDTOList()).thenReturn(expectedProjectTasks);

        // Call the method to be tested
        List<ProjectTaskDTO> resultProjectTasks = projectTaskService.getProjectTaskDTOList();

        // Verify the result
        // Add your assertions here to check if the resultProjectTasks list matches the expectedProjectTasks list
    }

    // Add more test methods for other methods in ProjectTaskServiceImpl if needed...
}

