package com.pennant.prodmtr.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;

import com.pennant.prodmtr.Dao.Interface.AnalyticsDao;
import com.pennant.prodmtr.model.Dto.AnalyticsDto;
import com.pennant.prodmtr.model.Entity.Module;
import com.pennant.prodmtr.model.Entity.ProjectTask;
import com.pennant.prodmtr.model.Entity.Subtask;
import com.pennant.prodmtr.model.Entity.Task;
import com.pennant.prodmtr.model.view.ModuleSummary;
import com.pennant.prodmtr.model.view.ProjectSummary;
import com.pennant.prodmtr.model.view.SubtaskSummary;
import com.pennant.prodmtr.model.view.TaskSummary;
import com.pennant.prodmtr.model.view.TotalAnalysis;
import com.pennant.prodmtr.service.Interface.AnalyticService;

public class AnalyticServiceImpl implements AnalyticService {

	private AnalyticsDao analyticsDao;
	
	@Autowired
	public AnalyticServiceImpl(AnalyticsDao analyticsDao) {
		super();
		this.analyticsDao = analyticsDao;

	}
	// Constructor




	@Override
	public AnalyticsDto getUserProjDetails(int userId) {
		AnalyticsDto a = new AnalyticsDto();
		// Create a HashMap to store project-wise hours
		Map<Short, Long> projectHoursMap = new HashMap<>();
		// Create a HashMap to store module-wise hours
		Map<Short, Long> moduleHoursMap = new HashMap<>();
		// Create a HashMap to store module-wise hours
		Map<Short, Long> taskHoursMap = new HashMap<>();
		Map<Short, Double> subTaskHoursMap = new HashMap<>();
		
		List<ProjectTask> projectTask = analyticsDao.getAllProjectsWorkingHoursByUid(userId);
//		System.out.println("projectTask " + projectTask);
		List<Task> tasks = analyticsDao.getAllProjectsIndvTasksWorkingHoursByUid(userId);
//		System.out.println("tasks " + tasks);
		List<Subtask> subtasks = analyticsDao.getAllSubTasksByUid(userId);
		System.out.println("subtasks " + subtasks);

		List<ProjectSummary> projectSummaries = new ArrayList<>();

		List<ModuleSummary> moduleWiseSummary = new ArrayList<>();
		List<TaskSummary> taskWiseSummary = new ArrayList<>();
		List<SubtaskSummary> subtaskWiseSummary = new ArrayList<>();

		
		setProjectWiseHoursForProjectTasks(projectTask, projectHoursMap);
//		System.out.println("setProjectWiseHoursForProjectTasks "+projectHoursMap);
		setProjectWiseHoursForIndvTasks(tasks, projectHoursMap);
//		System.out.println("setProjectWiseHoursForIndvTasks "+projectHoursMap);
		setProjectNames(projectHoursMap, projectSummaries);
//		System.out.println("setProjectNames "+projectHoursMap);
		
		setmoduleWiseHoursForTask(tasks, moduleWiseSummary, moduleHoursMap);
//		System.out.println("setmoduleWiseHoursForTask "+moduleHoursMap);
		System.out.println("tasks "+tasks);
		System.out.println("taskWiseSummary "+taskWiseSummary);

		setTaskWiseHours(tasks, taskWiseSummary, taskHoursMap);
		System.out.println("setTaskWiseHours "+taskHoursMap);
		
		setSubTaskWiseHours(subtasks, subtaskWiseSummary, subTaskHoursMap);
		System.out.println("setSubTaskWiseHours "+subTaskHoursMap);
			
		a.setProjectSummaries(projectSummaries);
		a.setModuleSummaries(moduleWiseSummary);
		a.setTaskSummaries(taskWiseSummary);
		a.setSubtaskSummaries(subtaskWiseSummary);
		return a;
	}



	private void setSubTaskWiseHours(List<Subtask> subtasks, List<SubtaskSummary> subtaskWiseSummary, Map<Short, Double> subTaskHoursMap) {
	    // Iterate over the subtasks and calculate the hours spent on each subtask

	    for (Subtask subtask : subtasks) {
	        Short subtaskId = (short) subtask.getPrimaryKey().getSubtaskId();
	        Double subtaskHours = subtask.getNumberOfHours();

	        // Check if the subtask already exists in the HashMap
	        if (subTaskHoursMap.containsKey(subtaskId)) {
	            // If it exists, add the subtaskHours to its existing value
	            Double existingHours = subTaskHoursMap.get(subtaskId);
	            Double updatedHours = existingHours + subtaskHours;
	            subTaskHoursMap.put(subtaskId, updatedHours);
	        } else {
	            // If it doesn't exist, create a new entry in the HashMap
	            subTaskHoursMap.put(subtaskId, subtaskHours);
	        }
	    }

	    // Iterate over the subTaskHoursMap
	    for (Map.Entry<Short, Double> entry : subTaskHoursMap.entrySet()) {
	        Short subtaskId = entry.getKey();
	        Double subtaskHours = entry.getValue();


	        // Create a SubtaskSummary object and set the subtask ID, hours, and other details
	        SubtaskSummary summary = new SubtaskSummary();
	        summary.setSubtaskId(subtaskId);
	        summary.setTotalWorkingHours(subtaskHours);

	        // Append the SubtaskSummary object to the subtaskWiseSummary list
	        subtaskWiseSummary.add(summary);
	    }
	}

	private void setTaskWiseHours(List<Task> tasks, List<TaskSummary> taskWiseSummary, Map<Short, Long> taskHoursMap) {
	    // Iterate over the tasks and calculate the hours spent on each task
	    for (Task task : tasks) {
	        Short taskId = (short) task.getTaskId();
	        long taskHours = task.getActualHours();

	        // Check if the task already exists in the HashMap
	        if (taskHoursMap.containsKey(taskId)) {
	            // If it exists, add the taskHours to its existing value
	            long existingHours = taskHoursMap.get(taskId);
	            long updatedHours = existingHours + taskHours;
	            taskHoursMap.put(taskId, updatedHours);
	        } else {
	            // If it doesn't exist, create a new entry in the HashMap
	            taskHoursMap.put(taskId, taskHours);
	        }
	    }

	    // Iterate over the taskHoursMap
	    for (Map.Entry<Short, Long> entry : taskHoursMap.entrySet()) {
	        Short taskId = entry.getKey();
	        Long taskHours = entry.getValue();

	        // Get the Task object using the taskId
	        Task task = analyticsDao.getTaskById(taskId); // Replace `getTaskById` with your actual method to retrieve the Task object

	        // Create a TaskSummary object and set the task ID, hours, and other details
	        TaskSummary summary = new TaskSummary();
	        summary.setTaskId(taskId);
	        summary.setTotalWorkingHours(taskHours);
	        summary.setTaskName(task.getTaskName());

	        // Append the TaskSummary object to the taskWiseSummary list
	        taskWiseSummary.add(summary);
	    }
	}
	private void setmoduleWiseHoursForTask(List<Task> tasks, List<ModuleSummary> moduleWiseSummary,
			Map<Short, Long> moduleHoursMap) {

		// Iterate over the tasks and calculate the hours spent on each module
		for (Task task : tasks) {
			Short module = task.getModule().getModuleId();
			long taskHours = task.getActualHours();
			// String modl_name = task.getModule().getModuleName();
			// Check if the module already exists in the HashMap
			if (moduleHoursMap.containsKey(module)) {
				// If it exists, add the taskHours to its existing value
				long existingHours = moduleHoursMap.get(module);
				long updatedHours = existingHours + taskHours;
				moduleHoursMap.put(module, updatedHours);
			} else {
				// If it doesn't exist, create a new entry in the HashMap
				moduleHoursMap.put(module, taskHours);
			}
		}
System.out.println("before entering loop moduleHoursMap "+moduleHoursMap);
		// Iterate over the moduleHoursMap
		for (Map.Entry<Short, Long> entry : moduleHoursMap.entrySet()) {
			Short moduleId = entry.getKey();
			Long moduleHours = entry.getValue();

			// Get the Module object using the moduleId
			Module module = analyticsDao.getModlbyId(moduleId);
			// Module object

			// Create a ModuleSummary object and set the module ID, hours, and name
			ModuleSummary summary = new ModuleSummary();
			summary.setModlId(moduleId);
			summary.setTotalWorkingHours(moduleHours);
			summary.setModlName(module.getModuleName());

			// Append the ModuleSummary object to the moduleWiseSummary list
			moduleWiseSummary.add(summary);
		}

	}

	private void setProjectNames(Map<Short, Long> projectHoursMap, List<ProjectSummary> projectSummaries) {
		// Populate the ProjectSummary model with project data and names
		for (Map.Entry<Short, Long> entry : projectHoursMap.entrySet()) {
			short projectId = entry.getKey();
			long hours = entry.getValue();

			// Retrieve the project name using the projectId (You need to implement this logic)
			String projectName = getProjectNameById(projectId);

			ProjectSummary projectSummary = new ProjectSummary();
			projectSummary.setProjId(projectId);
			projectSummary.setProjName(projectName);
			projectSummary.setTotalWorkingHours(hours);

			projectSummaries.add(projectSummary);
		}
	}

	private void setProjectWiseHoursForIndvTasks(List<Task> tasks, Map<Short, Long> projectHoursMap) {
		// Iterate over the Tasks and calculate the hours spent on each project
		for (Task t : tasks) {
			short projectId = t.getProject().getProjectId();
			long taskHours = t.getActualHours();
			// Check if projectId already exists in the HashMap
			if (projectHoursMap.containsKey(projectId)) {
				// If it exists, add the taskHours to its existing value
				long existingHours = projectHoursMap.get(projectId);
				long updatedHours = existingHours + taskHours;
				projectHoursMap.put(projectId, updatedHours);
			} else {
				// If it doesn't exist, create a new entry in the HashMap
				projectHoursMap.put(projectId, taskHours);
			}
		}

	}

	private String getProjectNameById(short projectId) {
		String projname = analyticsDao.getProjectNameById(projectId);
		return projname;
	}

	private void setProjectWiseHoursForProjectTasks(List<ProjectTask> projectTask, Map<Short, Long> projectHoursMap) {
		// Iterate over the ProjectTasks and calculate the hours spent on each project
		for (ProjectTask task : projectTask) {
			short projectId = task.getproject().getProjectId();
			long hours = task.getActualHours();
			// Add the hours to the existing total for the project
			long totalHours = projectHoursMap.getOrDefault(projectId, 0L) + hours;
			projectHoursMap.put(projectId, totalHours);
		}
	}

	@Override
	public List<String> getCompletedTasksByDate() {
	    List<Task> tasks = analyticsDao.getAllCompletedTasks();
	    List<ProjectTask> projecttasks = analyticsDao.getAllCompletedProjectTasks();

	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DAY_OF_MONTH, -30);


	    List<String> completedTasksByDate = new ArrayList<>();

	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

	    for (int i = 0; i < 30; i++) {
	        int completedTasksCount = 0;
	        int completedProjectTasksCount = 0;
	        Date date = calendar.getTime();

	        for (Task task : tasks) {
	            if (isSameDay(task.getTaskCompletedDateTime(), date)) {
	                completedTasksCount++;
	            }
	        }

	        for (ProjectTask projectTask : projecttasks) {
	            if (isSameDay(projectTask.getTaskCompletedDateTime(), date)) {
	                completedProjectTasksCount++;
	            }
	        }

	        int totalCompletedCount = completedTasksCount + completedProjectTasksCount;

	        String formattedDate = dateFormat.format(date);
	        String result = formattedDate + " Tasks: " + totalCompletedCount;
	        completedTasksByDate.add(result);

	        calendar.add(Calendar.DAY_OF_MONTH, 1);
	    }
	    System.out.println("completedTasksByDate "+completedTasksByDate);
	    System.out.println("size "+completedTasksByDate.size());
	    return completedTasksByDate;
	}


    private static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

	@Override
	public TotalAnalysis getTotalAnalysis() {
		TotalAnalysis totalAnalysis = new TotalAnalysis();
		totalAnalysis.setTotalProjects(analyticsDao.getAllProjects().size());
		totalAnalysis.setTotalModl(analyticsDao.getAllModules().size());
		totalAnalysis.setTotalSprints(analyticsDao.getAllSprints().size());
		totalAnalysis.setTotalSubtasks(analyticsDao.getAllCompletedTasks().size());
		int tasks = analyticsDao.getAllTasks().size();
		int projTasks = analyticsDao.getAllProjTasks().size();
		totalAnalysis.setTotalTasks(tasks+projTasks);
		return totalAnalysis;
	}
	
}
