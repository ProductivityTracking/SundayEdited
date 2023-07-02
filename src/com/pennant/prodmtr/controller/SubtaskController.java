package com.pennant.prodmtr.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pennant.prodmtr.model.Entity.Subtask;
import com.pennant.prodmtr.model.Input.SubtaskInput;
import com.pennant.prodmtr.service.Interface.SubtaskService;
import com.pennant.prodmtr.service.Interface.TaskService;

@Controller
public class SubtaskController {

	@Autowired
	public SubtaskService subtaskService;
	private TaskService taskService;
	
	private final static Logger logger=LoggerFactory.getLogger(SubtaskController.class);

	/**
     * Saves a new subtask based on the provided SubtaskInput object.
     *
     * @param subtaskInput The SubtaskInput object containing the details of the new subtask.
     * @param model        The Spring Model object to add attributes for the view.
     * @return Redirects to the "tasks" view on successful save, or "Taskslist" view on constraint violation.
     */
    @RequestMapping(value = "/saveSubtask", method = RequestMethod.GET)
    public String saveSubtask(@Validated SubtaskInput subtaskInput, Model model) {
        try {
            // Logging input details for debugging purposes
            logger.info(subtaskInput.getSubtaskDescription());
            logger.info(subtaskInput.getCreationDate());
            logger.info("The Task id is:{}",subtaskInput.getTaskId());
            logger.info("The subtask id is :{}",subtaskInput.getSubtaskId());
            logger.info("The number of hours spent is :{}",subtaskInput.getNumberOfHours());

            subtaskService.setNewSubTask(subtaskInput);
            logger.info("New subtask saved. Redirecting to the tasks view.");
            return "redirect:tasks";
        } catch (DataIntegrityViolationException ex) {
            // Handle the constraint violation exception
            ex.printStackTrace(); // or log the error
            logger.info("Constraint violation occurred. Setting error attribute and returning 'Taskslist' view.");
            model.addAttribute("error", "Constraint violation occurred. Please try again.");
            // Logging input details again for error debugging
            logger.info(subtaskInput.getSubtaskDescription());
            logger.info(subtaskInput.getCreationDate());
            logger.info("The task id is:{}",subtaskInput.getTaskId());
            logger.info("The subtask id is:{}",subtaskInput.getSubtaskId());
            logger.info("The hours are:{}",subtaskInput.getNumberOfHours());
            logger.info("Returning 'Taskslist' view to display the error.");
            return "Taskslist"; // Show an error page to the user
        }
    }

    /**
     * Retrieves a new SubtaskInput object and sets its taskId attribute based on the provided taskId.
     * This method is typically used to display a form for creating a new subtask.
     *
     * @param taskId The ID of the task for which the subtask is being created.
     * @param model  The Spring Model object to add attributes for the view.
     * @return The name of the view to be rendered, which is "createsubtask" on success, or "errorPage" on exception.
     */
    @RequestMapping(value = "/createSubtask", method = RequestMethod.GET)
    public String getSubtaskForm(@RequestParam("taskId") int taskId, Model model) {
        try {
            // Create a new SubtaskInput object and set its taskId attribute
            SubtaskInput subtaskInput = new SubtaskInput();
            subtaskInput.setTaskId(taskId);

            logger.info("New SubtaskInput object created and taskId attribute set.");
            // Add the SubtaskInput object and taskId attribute to the Model for the view to access
            model.addAttribute("subtaskInput", subtaskInput);
            model.addAttribute("taskId", taskId);

            logger.info("Returning 'createsubtask' view to display the subtask creation form.");
            return "createsubtask";
        } catch (Exception e) {
            // Handle the exception here, you can log it or show an error page
            e.printStackTrace();
            logger.error("An exception occurred while processing the request.");
            // Assuming you have an error page called "errorPage" to show in case of an exception
            return "errorPage";
        }
    }


    /**
     * Retrieves a Subtask object based on the compositeId and sets it as a model attribute.
     * This method is typically used to display a form for updating the status of a subtask.
     *
     * @param compositeId The composite ID of the subtask, usually a combination of taskId and subtaskId.
     * @param model       The Spring Model object to add attributes for the view.
     * @return The name of the view to be rendered, which is "SubtaskStatusUpdate" on success, or "errorPage" on exception.
     */
    @RequestMapping(value = "/setSubTaskStatus", method = RequestMethod.GET)
    public String setSubTaskStatus(@RequestParam("compositeId") String compositeId, Model model) {
        try {
            // Call a function to find the Subtask object based on the compositeId
            Subtask subtask = subtaskService.findSubtask(compositeId);

            logger.info("Found Subtask with compositeId: {}", compositeId);
            // Add the Subtask object as a model attribute for the view to access
            model.addAttribute("subtask", subtask);

            logger.info("Returning 'SubtaskStatusUpdate' view to display the subtask status update form.");
            return "SubtaskStatusUpdate";
        } catch (Exception e) {
            // Handle the exception here, you can log it or show an error page
            e.printStackTrace();
            logger.error("An exception occurred while processing the request.");
            // Assuming you have an error page called "errorPage" to show in case of an exception
            return "errorPage";
        }
    }


    /**
     * Updates the remarks of a subtask based on the provided SubtaskInput object.
     *
     * @param subtaskInput The SubtaskInput object containing the necessary data for updating the subtask remarks.
     * @param model        The Spring Model object to add attributes for the view.
     * @return The name of the view to be rendered, which is "redirect:activity" on success, or "errorPage" on exception.
     */
    @RequestMapping(value = "/setSubtaskRemarks", method = RequestMethod.POST)
    public String setSubtaskRemarks(SubtaskInput subtaskInput, Model model) {
        try {
            logger.info("Approving subtask status.");
            // Call the service method to update the subtask remarks
            subtaskService.updateSubtaskApproval(subtaskInput);

            logger.info("Redirecting to 'activity' view.");
            return "redirect:activity";
        } catch (Exception e) {
            // Handle the exception here, you can log it or show an error page
            e.printStackTrace();
            logger.error("An exception occurred while processing the request.");
            // Assuming you have an error page called "errorPage" to show in case of an exception
            return "errorPage";
        }
    }

    /**
     * Retrieves all subtasks associated with the given user ID and sets them as a model attribute.
     *
     * @param session The HttpSession object containing the user session.
     * @param model   The Spring Model object to add attributes for the view.
     * @return The name of the view to be rendered, which is "subtasksHome" on success, or "errorPage" on exception.
     */
    public String getAllSubtasksByUserId(HttpSession session, Model model) {
        try {
            // Call the service method to retrieve all subtasks by user ID
            List<Subtask> subtasks = subtaskService.getAllSubtasksByUserId(session);

            logger.info("Setting 'subtasks' model attribute with the retrieved subtasks.");
            // Add the retrieved subtasks as a model attribute for the view to access
            model.addAttribute("subtasks", subtasks);

            return "subtasksHome";
        } catch (Exception e) {
            // Handle the exception here, you can log it or show an error page
            e.printStackTrace();
            logger.error("An exception occurred while processing the request.");
            // Assuming you have an error page called "errorPage" to show in case of an exception
            return "errorPage";
        }
    }
    
    
    /**
     * Retrieves the details of a specific subtask based on the provided SubtaskInput object
     * and sets it as a model attribute for displaying in the view.
     *
     * @param subtaskInput The SubtaskInput object containing the necessary data to find the subtask details.
     * @param model        The Spring Model object to add attributes for the view.
     * @return The name of the view to be rendered, which is "SubtaskReview" on success, or "errorPage" on exception.
     */
    @RequestMapping(value = "/getSubtaskDetails", method = RequestMethod.POST)
    public String getSubtaskDetails(SubtaskInput subtaskInput, Model model) {
        try {
            logger.info("Fetching the subtask based on the provided input.");
            // Call the service method to find the subtask details
            Subtask subtask = subtaskService.findSubtaskByInput(subtaskInput);

            logger.info("Setting 'subtask' model attribute with the retrieved subtask details.");
            // Add the retrieved subtask as a model attribute for the view to access
            model.addAttribute("subtask", subtask);

            logger.info("Returning 'SubtaskReview' view to display subtask details.");
            return "SubtaskReview";
        } catch (Exception e) {
            // Handle the exception here, you can log it or show an error page
            e.printStackTrace();
            logger.error("An exception occurred while processing the request.");
            // Assuming you have an error page called "errorPage" to show in case of an exception
            return "errorPage";
        }
    }

    /**
     * Updates the status of a subtask based on the provided SubtaskInput object.
     *
     * @param subtaskInput The SubtaskInput object containing the necessary data for updating the subtask status.
     * @param model        The Spring Model object to add attributes for the view.
     * @return The name of the view to be rendered, which is "redirect:subtasksHome" on success, or "errorPage" on exception.
     */
    @RequestMapping(value = "/setSubtaskStatus", method = RequestMethod.POST)
    public String setSubtaskStatus(SubtaskInput subtaskInput, Model model) {
        try {
            logger.info("Updating the subtask status based on the provided input.");
            // Call the service method to update the subtask status
            subtaskService.updateSubtaskStatus(subtaskInput);

            logger.info("Redirecting to 'subtasksHome' view after updating the status.");
            return "redirect:subtasksHome";
        } catch (Exception e) {
            // Handle the exception here, you can log it or show an error page
            e.printStackTrace();
            logger.error("An exception occurred while processing the request.");
            // Assuming you have an error page called "errorPage" to show in case of an exception
            return "errorPage";
        }
    }

//	@RequestMapping(value = "/setSubtaskReview", method = RequestMethod.POST)
//	public String setSubtaskStatus(SubtaskInput subtaskInput, Model model) {
//		subtaskService.updateSubtaskStatus(subtaskInput);
//		return "redirect:subtasksHome";
//	}
	
	/*
	 * @RequestMapping(value = "/setSubTaskStatus", method = RequestMethod.GET) public String
	 * setSubTaskStatus(@RequestParam("compostiteId") String compostiteId, Model model) { Subtask subtask =
	 * subtaskService.getSubTaskByCompositeId(compostiteId); // subtaskService.setSubtask return "createsubtask"; }
	 */
}
