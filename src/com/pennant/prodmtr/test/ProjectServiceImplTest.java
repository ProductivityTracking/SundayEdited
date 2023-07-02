package com.pennant.prodmtr.test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.Dao.Interface.ProjectDao;
import com.pennant.prodmtr.model.Dto.ProjectDto;
import com.pennant.prodmtr.model.Dto.ProjectFilter;
import com.pennant.prodmtr.model.Entity.Project;
import com.pennant.prodmtr.model.Input.ProjectInput;
import com.pennant.prodmtr.service.Impl.ProjectServiceImpl;
import com.pennant.prodmtr.service.Interface.ProjectService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ProjectServiceImplTest {

    @Mock
    private ProjectDao projectDao;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProjects() {
        // Create some mock ProjectDto objects for testing
        List<ProjectDto> expectedProjects = createMockProjectDtoList();
        
        // Mock the behavior of the projectDao.getAllProjects() method
        when(projectDao.getAllProjects()).thenReturn(expectedProjects);

        // Call the method to be tested
        List<ProjectDto> resultProjects = projectService.getAllProjects();

        // Verify the result
        assertNotNull(resultProjects);
        assertEquals(resultProjects.size(), expectedProjects.size());
        // Add more assertions to check if the result matches the expectedProjects
        // For example, you can check if the properties of each ProjectDto in the list are equal
    }

    @Test
    public void testFilterProjects() {
        // Create a mock ProjectFilter object for testing
        ProjectFilter projectFilter = new ProjectFilter();
        // Set properties of projectFilter with mock data for testing

        // Create some mock ProjectDto objects for testing
        List<ProjectDto> expectedFilteredProjects = createMockProjectDtoList();
        
        // Mock the behavior of the projectDao.getFilterProjects(projectFilter) method
        when(projectDao.getFilterProjects(projectFilter)).thenReturn(expectedFilteredProjects);

        // Call the method to be tested
        List<ProjectDto> resultFilteredProjects = projectService.filterProjects(projectFilter);

        // Verify the result
        assertNotNull(resultFilteredProjects);
        assertEquals(resultFilteredProjects.size(), expectedFilteredProjects.size());
        // Add more assertions to check if the result matches the expectedFilteredProjects
    }

    @Test
    public void testGetProjectById() {
        int projectId = 1; // Replace with the project ID you want to test
        ProjectDto expectedProject = createMockProjectDto(); // Create the expected ProjectDto object
        
        // Mock the behavior of the projectDao.getProjectById(projectId) method
        when(projectDao.getProjectById(projectId)).thenReturn(expectedProject);

        // Call the method to be tested
        ProjectDto resultProject = projectService.getProjectById(projectId);

        // Verify the result
        assertNotNull(resultProject);
        // Add your assertions here to check if the resultProject matches the expectedProject
    }

    @Test
    public void testSetNewProject() {
        ProjectInput projectInput = createMockProjectInput(); // Create the ProjectInput object for testing

        // Mock the behavior of the projectInput.toEntity() method
        Project project = projectInput.toEntity();
        // If your toEntity() method is more complex, you may need to mock additional behavior here
        
        // Call the method to be tested
        projectService.setNewProject(projectInput);

        // Verify that the projectDao.setNewProject() method is called with the correct Project object
        verify(projectDao).setNewProject(project);
    }

    // Helper methods to create mock objects for testing
    private List<ProjectDto> createMockProjectDtoList() {
        // Create and return a list of mock ProjectDto objects
        List<ProjectDto> projects = new ArrayList<>();
        // Add mock ProjectDto objects to the list
        // Example:
        // projects.add(new ProjectDto(/* Set properties of the object for testing */));
        return projects;
    }

    private ProjectDto createMockProjectDto() {
        // Create and return a mock ProjectDto object
        // Example:
        // return new ProjectDto(/* Set properties of the object for testing */);
        return null; // Replace this with the actual creation of the mock object
    }

    private ProjectInput createMockProjectInput() {
        // Create and return a mock ProjectInput object
        // Example:
        // return new ProjectInput(/* Set properties of the object for testing */);
        return null; // Replace this with the actual creation of the mock object
    }
}
