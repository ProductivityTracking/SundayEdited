package com.pennant.prodmtr.service.Impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pennant.prodmtr.model.Entity.User;
import com.pennant.prodmtr.Dao.Interface.SubtaskDao;
import com.pennant.prodmtr.model.Entity.Subtask;
import com.pennant.prodmtr.model.Entity.SubtaskPrimaryKey;
import com.pennant.prodmtr.model.Input.SubtaskInput;
import com.pennant.prodmtr.service.Interface.SubtaskService;

@Service
@Transactional
public class SubtaskServiceImpl implements SubtaskService {

	@Autowired
	private SubtaskDao subtaskDao;
	
	private static final Logger logger=LoggerFactory.getLogger(SubtaskServiceImpl.class);

	/**
	 * Saves the Subtask entity into the database.
	 * 
	 * @param subtask The Subtask entity to be saved.
	 */
	public void saveSubtask(Subtask subtask) {
		subtaskDao.saveSubtask(subtask);
	}

	/**
	 * Creates a new Subtask based on the provided SubtaskInput and saves it to the database.
	 * 
	 * @param subtaskInput The input data to create a new Subtask.
	 */
	@Override
	public void setNewSubTask(SubtaskInput subtaskInput) {
		SubtaskPrimaryKey spk = new SubtaskPrimaryKey();

		spk.setTaskId(subtaskInput.getTaskId());
		spk.setSubtaskId((subtaskInput.getSubtaskId()));
		Subtask subtask = subtaskInput.toEntity();
		subtask.setPrimaryKey(spk);
		subtask.setApprStatus("NA");
		subtask.getPrimaryKey().getTaskId();

		subtaskDao.setNewSubTask(subtask);

		// Log the creation of a new Subtask.
		logger.info("Created a new Subtask with ID: " + subtask.getPrimaryKey().getSubtaskId());
	}

	/**
	 * Finds a Subtask by its composite ID.
	 * 
	 * @param compositeId The composite ID in the format "taskId,subtaskId".
	 * @return The Subtask entity if found, otherwise null.
	 */
	@Override
	public Subtask findSubtask(String compositeId) {
		String[] ids = compositeId.split(",");
		int taskId = Integer.parseInt(ids[0]);
		int subtaskId = Integer.parseInt(ids[1]);
		// TODO Auto-generated method stub
		return subtaskDao.findSubtask(taskId, subtaskId);
	}

	/**
	 * Retrieves a list of unapproved Subtasks based on the user's role.
	 * 
	 * @param user The User entity representing the logged-in user.
	 * @return A list of unapproved Subtasks based on the user's role.
	 */
	@Override
	public List<Subtask> getUnapprovedSubtasks(User user) {
		int role = user.getUserRole().getRoleId();
		if (role == 1 || role == 2) {
			return subtaskDao.getAllUnapprSubtasks();
		} else {
			return subtaskDao.getUnapprovedSubtasks(user.getUserId());
		}
	}

	/**
	 * Updates the approval status of a Subtask based on the provided SubtaskInput.
	 * 
	 * @param subtaskInput The input data containing Subtask ID and approval status.
	 */
	@Override
	public void updateSubtaskApproval(SubtaskInput subtaskInput) {
		subtaskDao.updateSubtaskApproval(subtaskInput);

		// Log the update of Subtask approval status.
		logger.info("Updated approval status for Subtask with ID: " + subtaskInput.getSubtaskId());
	}

	/**
	 * Retrieves a list of all Subtasks associated with the user's ID based on their role.
	 * 
	 * @param session The HttpSession object to retrieve the logged-in user information.
	 * @return A list of all Subtasks associated with the user's ID based on their role.
	 */
	@Override
	public List<Subtask> getAllSubtasksByUserId(HttpSession session) {
		User user = (User) session.getAttribute("user");
		int role = user.getUserRole().getRoleId();
		if (role == 1 || role == 2) {
			return subtaskDao.getAllSubtasks();
		} else {
			return subtaskDao.getAllSubtasksByUserId(user.getUserId());
		}
	}

	/**
	 * Finds a Subtask by its composite ID using the provided SubtaskInput.
	 * 
	 * @param subtaskInput The input data containing Subtask ID and Task ID.
	 * @return The Subtask entity if found, otherwise null.
	 */
	@Override
	public Subtask findSubtaskByInput(SubtaskInput subtaskInput) {
		int taskId = subtaskInput.getTaskId();
		int subtaskId = subtaskInput.getSubtaskId();
		return subtaskDao.findSubtask(taskId, subtaskId);
	}

	/**
	 * Updates the status of a Subtask based on the provided SubtaskInput.
	 * 
	 * @param subtaskInput The input data containing Subtask ID and status.
	 */
	@Override
	public void updateSubtaskStatus(SubtaskInput subtaskInput) {
		subtaskDao.updateSubtaskStatus(subtaskInput);

		// Log the update of Subtask status.
		logger.info("Updated Subtask status for Subtask with ID: " + subtaskInput.getSubtaskId());
	}
}
