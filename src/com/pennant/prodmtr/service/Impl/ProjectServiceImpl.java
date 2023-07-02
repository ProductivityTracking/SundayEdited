package com.pennant.prodmtr.service.Impl;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pennant.prodmtr.Dao.Interface.ProjectDao;
import com.pennant.prodmtr.model.Dto.ProjectDto;
import com.pennant.prodmtr.model.Dto.ProjectFilter;
import com.pennant.prodmtr.model.Entity.Project;
import com.pennant.prodmtr.model.Input.ProjectInput;
import com.pennant.prodmtr.service.Interface.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = Logger.getLogger(ProjectServiceImpl.class.getName());

    private final ProjectDao projectDao;

    @Autowired
    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * Retrieves a list of all projects.
     *
     * @return A list of ProjectDto containing all project details.
     */
    public List<ProjectDto> getAllProjects() {
        // Log the retrieval of all projects for tracking purposes.
        logger.info("Retrieving all projects.");

        return projectDao.getAllProjects();
    }

    /**
     * Filters projects based on the provided ProjectFilter.
     *
     * @param projectFilter The filter criteria to apply to the projects.
     * @return A list of ProjectDto containing the filtered project details.
     */
    public List<ProjectDto> filterProjects(ProjectFilter projectFilter) {
        // TODO: Implement the filterProjects method to call the appropriate DAO method for filtering projects.
        logger.warning("filterProjects method is not implemented yet.");

        // Temporary return statement to avoid compilation errors.
        return projectDao.getAllProjects();
    }

    /**
     * Retrieves a specific project by its ID.
     *
     * @param projId The ID of the project to retrieve.
     * @return A ProjectDto containing the details of the specified project.
     */
    public ProjectDto getProjectById(Integer projId) {
        // Log the retrieval of a specific project by its ID for tracking purposes.
        logger.info("Retrieving project with ID: " + projId);

        return projectDao.getProjectById(projId);
    }

    /**
     * Creates a new project based on the provided ProjectInput.
     *
     * @param projectInput The input containing details of the project to be created.
     */
    public void setNewProject(ProjectInput projectInput) {
        // Set the project start date to the current date.
        Date date = new Date(System.currentTimeMillis());
        projectInput.setProjectStartDate(date);

        // Set the project status to "O" (Open) by default.
        projectInput.setProjectStatus("O");

        // Convert the ProjectInput to a Project entity.
        Project project = projectInput.toEntity();

        // Save the new project using the ProjectDao.
        projectDao.setNewProject(project);

        // Log the creation of the new project for tracking purposes.
        logger.info("Created new project with ID: " + project.getProjectId());
    }

    // Add methods for updating and deleting projects if needed
}
