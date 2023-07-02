package com.pennant.prodmtr.Dao.Interface;

import java.util.List;

import com.pennant.prodmtr.model.Entity.Subtask;
import com.pennant.prodmtr.model.Entity.Task;
import com.pennant.prodmtr.model.Entity.User;


public interface UserDao {

	public void UpdatePassword(Integer id, String password);

	public List<Task> getUserActivities(String taskType);

	List<Subtask> getUserSubtaskActivities(int userId);

	public User verifyUser(String userDisplayName);

	public List<Subtask> getAllReviewSubtasks();
}
