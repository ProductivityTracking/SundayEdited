package com.pennant.prodmtr.service.Impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pennant.prodmtr.Dao.Interface.ProjectTaskdao;
import com.pennant.prodmtr.model.Dto.PTFilterCriteria;
import com.pennant.prodmtr.model.Dto.ProjectDto;
import com.pennant.prodmtr.model.Dto.ProjectTaskDTO;
import com.pennant.prodmtr.model.Entity.ProjectTask;
import com.pennant.prodmtr.model.Entity.Task;
import com.pennant.prodmtr.service.Interface.ProjectTaskService;
import com.pennant.prodmtr.service.Interface.TaskService;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private static final Logger logger = Logger.getLogger(ProjectTaskServiceImpl.class.getName());

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectTaskdao projectTaskdao;

    @Autowired
    private ProjectTaskService projectTaskService;

    /**
     * Creates a new project task and saves it in the database.
     * Additional business logic, if needed, can be implemented here.
     *
     * @param projectTask The ProjectTask entity representing the new project task.
     * @return The created ProjectTask entity with updated information, including the assigned task ID.
     */
    public ProjectTask createProjectTask(ProjectTask projectTask) {
        // Additional business logic, if needed
        projectTask.settaskStatus("INPR");
        ProjectTask createdTask = projectTaskdao.save(projectTask);

        // Log the creation of the project task for tracking purposes.
        logger.info("Created new project task with ID: " + createdTask.getTaskId());

        return createdTask;
    }

    /**
     * Retrieves a list of ProjectTaskDTO objects representing project tasks.
     * TODO: Implement the getProjectTaskDTOList method to call the appropriate DAO method for retrieving project task DTOs.
     *
     * @return The list of ProjectTaskDTO objects representing project tasks.
     */
    public List<ProjectTaskDTO> getProjectTaskDTOList() {
        logger.warning("getProjectTaskDTOList method is not implemented yet.");
        // Temporary return statement to avoid compilation errors.
        return null;
    }

    /**
     * Retrieves a specific ProjectTaskDTO object by its ID.
     * TODO: Implement the getProjectTaskById method to call the appropriate DAO method for retrieving a project task DTO by its ID.
     *
     * @param taskId The ID of the project task to retrieve.
     * @return The ProjectTaskDTO object representing the specified project task.
     */
    public ProjectTaskDTO getProjectTaskById(int taskId) {
        logger.warning("getProjectTaskById method is not implemented yet.");
        // Temporary return statement to avoid compilation errors.
        return null;
    }

    /**
     * Filters project tasks based on the provided criteria.
     *
     * @param filterCriteria The criteria to filter the project tasks.
     * @return The list of filtered ProjectTaskDTO objects representing project tasks.
     */
    public List<ProjectTaskDTO> filterTasks(PTFilterCriteria filterCriteria) {
        // Call the appropriate DAO method to filter tasks based on the provided criteria.
        List<ProjectTaskDTO> filteredTasks = projectTaskdao.filterTasks(filterCriteria);

        // Log the filtering of tasks for tracking purposes.
        logger.info("Filtered tasks based on criteria: " + filterCriteria.toString());

        return filteredTasks;
    }

    /**
     * Sets the number of total and completed tasks for a project in the ProjectDto object.
     * This method retrieves project-related tasks from the database and updates the ProjectDto object accordingly.
     *
     * @param projectId  The ID of the project for which to set the tasks.
     * @param projectDto The ProjectDto object representing the project to be updated.
     */
    public void setTasksToProjDto(int projectId, ProjectDto projectDto) {
        List<Task> tasks = taskService.getTasksByProjectId(projectId);
        List<Task> compTasks = taskService.getCompTasksByProjectId(projectId);

        List<ProjectTask> projectTasks = projectTaskService.getTasksByProjectId(projectId);
        List<ProjectTask> compProjectTasks = projectTaskService.getCompTasksByProjectId(projectId);

        projectDto.setTotalTasks(tasks.size());
        projectDto.setCompletedTasks(compTasks.size());

        projectDto.setTotalIndvTasks(projectTasks.size());
        projectDto.setCompletedIndvTasks(compProjectTasks.size());

        // Log the update of the ProjectDto object with the tasks count for tracking purposes.
        logger.info("Updated ProjectDto with task counts for project with ID: " + projectId);
    }

    /**
     * Retrieves a list of ProjectTask objects representing tasks associated with a project.
     *
     * @param projId The ID of the project for which to retrieve the tasks.
     * @return The list of ProjectTask objects representing tasks associated with the project.
     */
    public List<ProjectTask> getTasksByProjectId(Integer projId) {
        // Call the appropriate DAO method to retrieve tasks associated with the project.
        List<ProjectTask> tasks = projectTaskdao.getTasksByProjectId(projId);

        // Log the retrieval of tasks associated with the project for tracking purposes.
        logger.info("Retrieved tasks associated with project with ID: " + projId);

        return tasks;
    }

    /**
     * Retrieves a list of completed ProjectTask objects associated with a project.
     *
     * @param projectId The ID of the project for which to retrieve completed tasks.
     * @return The list of completed ProjectTask objects associated with the project.
     */
    public List<ProjectTask> getCompTasksByProjectId(int projectId) {
        // TODO: Implement the getCompTasksByProjectId method to call the appropriate DAO method for retrieving completed tasks by project ID.
        logger.warning("getCompTasksByProjectId method is not implemented yet.");

        // Temporary return statement to avoid compilation errors.
        return null;
    }

    /**
     * Retrieves the count of completed tasks for a specific user.
     *
     * @param userId The ID of the user for which to retrieve the count of completed tasks.
     * @return The count of completed tasks for the user.
     */
    public int getCompletedTasksByUserId(int userId) {
        // TODO: Implement the getCompletedTasksByUserId method to call the appropriate DAO method for retrieving completed tasks count by user ID.
        logger.warning("getCompletedTasksByUserId method is not implemented yet.");

        // Temporary return statement to avoid compilation errors.
        return 0;
    }

    /**
     * Retrieves the total count of tasks for a specific user.
     *
     * @param userId The ID of the user for which to retrieve the total count of tasks.
     * @return The total count of tasks for the user.
     */
    public int getTotalTasksByUserId(int userId) {
        int tasks = projectTaskdao.getTotalTasksByUserId(userId);

        // Log the total tasks count for the user for tracking purposes.
        logger.info("Total tasks for user with ID " + userId + ": " + tasks);

        return tasks;
    }

    /**
     * Retrieves the total hours worked by a specific user.
     *
     * @param userId The ID of the user for which to retrieve the total hours worked.
     * @return The total hours worked by the user.
     */
    public double getHoursWorkedByUserId(int userId) {
        // TODO: Implement the getHoursWorkedByUserId method to call the appropriate DAO method or service for calculating the total hours worked by the user.
        logger.warning("getHoursWorkedByUserId method is not implemented yet.");

        // Temporary return statement to avoid compilation errors.
        return 0.0;
    }

    /**
     * Calculates the performance score based on the completed tasks and total tasks.
     *
     * @param completedTasks The number of completed tasks.
     * @param totalTasks     The total number of tasks.
     * @return The performance score as a percentage.
     */
    public double calculatePerformanceScore(int completedTasks, int totalTasks) {
        if (totalTasks == 0) {
            return 0.0; // Avoid division by zero
        }
        return (double) completedTasks / totalTasks * 100.0;
    }

    // Other service methods for updating, deleting project tasks
}
