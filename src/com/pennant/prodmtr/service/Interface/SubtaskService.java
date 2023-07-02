package com.pennant.prodmtr.service.Interface;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.pennant.prodmtr.model.Entity.Subtask;
import com.pennant.prodmtr.model.Entity.User;
import com.pennant.prodmtr.model.Input.SubtaskInput;

public interface SubtaskService {

	public void saveSubtask(Subtask subtask);

	public void setNewSubTask(SubtaskInput subtaskInput);

	public List<Subtask> getUnapprovedSubtasks(User user);

	Subtask findSubtask(String compositeId);

	void updateSubtaskApproval(SubtaskInput subtaskInput);

	public List<Subtask> getAllSubtasksByUserId(HttpSession session);

	public Subtask findSubtaskByInput(SubtaskInput subtaskInput);

	public void updateSubtaskStatus(SubtaskInput subtaskInput);
}
