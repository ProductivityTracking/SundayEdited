package com.pennant.prodmtr.test ;

import com.pennant.prodmtr.Dao.Interface.SubtaskDao;
import com.pennant.prodmtr.model.Entity.Subtask;
import com.pennant.prodmtr.model.Entity.SubtaskPrimaryKey;
import com.pennant.prodmtr.model.Entity.User;
import com.pennant.prodmtr.model.Input.SubtaskInput;
import com.pennant.prodmtr.service.Impl.SubtaskServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SubtaskServiceImplTest {

    @Mock
    private SubtaskDao subtaskDao;

    @InjectMocks
    private SubtaskServiceImpl subtaskService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveSubtask() {
        // Create some sample data for the test
        Subtask subtask = new Subtask();
        // Set appropriate values for the subtask object

        // Call the method to be tested
        subtaskService.saveSubtask(subtask);

        // Verify the behavior
        // Add appropriate assertions or verify if the subtaskDao.saveSubtask() method was called with the expected Subtask object
    }

    @Test
    public void testSetNewSubTask() {
        // Create some sample data for the test
        SubtaskInput subtaskInput = new SubtaskInput();
        // Set appropriate values for the subtaskInput object

        SubtaskPrimaryKey spk = new SubtaskPrimaryKey();
        // Set appropriate values for the spk object

        Subtask subtask = new Subtask();
        // Set appropriate values for the subtask object

        // Stub the behavior of the subtaskInput.toEntity() method if necessary
        // when(subtaskInput.toEntity()).thenReturn(...);

        // Stub the behavior of the subtaskDao.setNewSubTask() method if necessary
        // when(subtaskDao.setNewSubTask(any(Subtask.class))).thenReturn(...);

        // Call the method to be tested
        subtaskService.setNewSubTask(subtaskInput);

        // Verify the behavior
        // Add appropriate assertions or verify if the subtaskDao.setNewSubTask() method was called with the expected Subtask object
    }

    @Test
    public void testFindSubtask() {
        // Create some sample data for the test
        String compositeId = "123,456";
        int taskId = 123;
        int subtaskId = 456;
        Subtask subtask = new Subtask();
        // Set appropriate values for the subtask object

        // Stub the behavior of the subtaskDao.findSubtask() method
        when(subtaskDao.findSubtask(taskId, subtaskId)).thenReturn(subtask);

        // Call the method to be tested
        Subtask result = subtaskService.findSubtask(compositeId);

        // Verify the result
        // Add appropriate assertions based on the behavior of findSubtask() method
    }

    @Test
    public void testGetUnapprovedSubtasks() {
        // Create some sample data for the test
        User user = new User();
        // Set appropriate values for the user object

        List<Subtask> subtasks = new ArrayList<>();
        // Add data to the list (add appropriate Subtask objects to the list)

        // Stub the behavior of the subtaskDao.getAllUnapprSubtasks() method if necessary
        // when(subtaskDao.getAllUnapprSubtasks()).thenReturn(...);

        // Stub the behavior of the subtaskDao.getUnapprovedSubtasks() method if necessary
        // when(subtaskDao.getUnapprovedSubtasks(anyInt())).thenReturn(...);

        // Call the method to be tested
        List<Subtask> result = subtaskService.getUnapprovedSubtasks(user);

        // Verify the result
        // Add appropriate assertions based on the behavior of getUnapprovedSubtasks() method
    }

    @Test
    public void testUpdateSubtaskApproval() {
        // Create some sample data for the test
        SubtaskInput subtaskInput = new SubtaskInput();
        // Set appropriate values for the subtaskInput object

        // Call the method to be tested
        subtaskService.updateSubtaskApproval(subtaskInput);

        // Verify the behavior
        // Add appropriate assertions or verify if the subtaskDao.updateSubtaskApproval() method was called with the expected SubtaskInput object
    }

    @Test
    public void testGetAllSubtasksByUserId() {
        // Create some sample data for the test
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        // Set appropriate values for the user object

        List<Subtask> subtasks = new ArrayList<>();
        // Add data to the list (add appropriate Subtask objects to the list)

        // Stub the behavior of the session.getAttribute() method if necessary
        when(session.getAttribute("user")).thenReturn(user);

        // Stub the behavior of the subtaskDao.getAllSubtasks() method if necessary
        // when(subtaskDao.getAllSubtasks()).thenReturn(...);

        // Stub the behavior of the subtaskDao.getAllSubtasksByUserId() method if necessary
        // when(subtaskDao.getAllSubtasksByUserId(anyInt())).thenReturn(...);

        // Call the method to be tested
        List<Subtask> result = subtaskService.getAllSubtasksByUserId(session);

        // Verify the result
        // Add appropriate assertions based on the behavior of getAllSubtasksByUserId() method
    }

    @Test
    public void testFindSubtaskByInput() {
        // Create some sample data for the test
        SubtaskInput subtaskInput = new SubtaskInput();
        // Set appropriate values for the subtaskInput object

        int taskId = 123;
        int subtaskId = 456;
        Subtask subtask = new Subtask();
        // Set appropriate values for the subtask object

        // Stub the behavior of the subtaskInput.getTaskId() and subtaskInput.getSubtaskId() methods if necessary
        // when(subtaskInput.getTaskId()).thenReturn(...);
        // when(subtaskInput.getSubtaskId()).thenReturn(...);

        // Stub the behavior of the subtaskDao.findSubtask() method if necessary
        // when(subtaskDao.findSubtask(taskId, subtaskId)).thenReturn(subtask);

        // Call the method to be tested
        Subtask result = subtaskService.findSubtaskByInput(subtaskInput);

        // Verify the result
        // Add appropriate assertions based on the behavior of findSubtaskByInput() method
    }

    @Test
    public void testUpdateSubtaskStatus() {
        // Create some sample data for the test
        SubtaskInput subtaskInput = new SubtaskInput();
        // Set appropriate values for the subtaskInput object

        // Call the method to be tested
        subtaskService.updateSubtaskStatus(subtaskInput);

        // Verify the behavior
        // Add appropriate assertions or verify if the subtaskDao.updateSubtaskStatus() method was called with the expected SubtaskInput object
    }

    // Add more test methods for other methods in the SubtaskServiceImpl class if needed
}
