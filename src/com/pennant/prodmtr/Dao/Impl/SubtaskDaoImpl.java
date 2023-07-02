package com.pennant.prodmtr.Dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pennant.prodmtr.Dao.Interface.SubtaskDao;
import com.pennant.prodmtr.model.Entity.Subtask;
import com.pennant.prodmtr.model.Input.SubtaskInput;

@Repository
public class SubtaskDaoImpl implements SubtaskDao {

	@PersistenceContext
	private EntityManager entityManager;
	private Object subtaskId;
	
    private static final Logger logger = LoggerFactory.getLogger(SubtaskDaoImpl.class);


    /**
     * Saves a new subtask to the database.
     *
     * @param subtask The Subtask object to be saved.
     * @return The saved Subtask object.
     */
    public Subtask save(Subtask subtask) {
        logger.info("Saving subtask: {}", subtask);
        entityManager.persist(subtask);
        return subtask;
    }

    /**
     * Saves a subtask to the database.
     *
     * @param subtask The Subtask object to be saved.
     */
    public void saveSubtask(Subtask subtask) {
        logger.info("Saving subtask: {}", subtask);
        entityManager.persist(subtask);
    }

    /**
     * Saves a new subtask to the database.
     *
     * @param subtask The Subtask object to be saved.
     */
    public void setNewSubTask(Subtask subtask) {
        logger.info("Saving new subtask: {}", subtask);
        entityManager.persist(subtask);
    }

    /**
     * Gets the subtaskId.
     *
     * @return The subtaskId.
     */
    public Object getSubtaskId() {
        logger.info("Getting subtaskId: {}", subtaskId);
        return subtaskId;
    }

    /**
     * Sets the subtaskId.
     *
     * @param subtaskId The subtaskId to be set.
     */
    public void setSubtaskId(Object subtaskId) {
        logger.info("Setting subtaskId: {}", subtaskId);
        this.subtaskId = subtaskId;
    }

    /**
     * Finds a subtask by its taskId and subtaskId.
     *
     * @param taskId    The ID of the task associated with the subtask.
     * @param subtaskId The ID of the subtask to be found.
     * @return The found Subtask object.
     */
    @Override
    public Subtask findSubtask(int taskId, int subtaskId) {
        logger.info("Finding subtask with taskId: {} and subtaskId: {}", taskId, subtaskId);
        TypedQuery<Subtask> query = entityManager.createQuery(
                "SELECT st FROM Subtask st WHERE st.primaryKey.taskId = :taskId AND st.primaryKey.subtaskId = :subtaskId",
                Subtask.class);
        query.setParameter("taskId", taskId);
        query.setParameter("subtaskId", subtaskId);
        Subtask subtask = query.getSingleResult();
        logger.info("Found subtask: {}", subtask);
        return subtask;
    }

    /**
     * Finds all subtasks associated with a given taskId.
     *
     * @param taskId The ID of the task for which to find subtasks.
     * @return A list of Subtask objects associated with the given taskId.
     */
    @Override
    public List<Subtask> findSubtaskByTaskId(int taskId) {
        logger.info("Finding subtasks for taskId: {}", taskId);
        TypedQuery<Subtask> query = entityManager.createQuery(
                "SELECT st FROM Subtask st WHERE st.primaryKey.taskId = :taskId", Subtask.class);
        query.setParameter("taskId", taskId);
        List<Subtask> subtasks = query.getResultList();
        logger.info("Found {} subtasks for taskId: {}", subtasks.size(), taskId);
        return subtasks;
    }

    /**
     * Gets a list of unapproved subtasks for a given userId.
     *
     * @param userId The ID of the user for whom to get unapproved subtasks.
     * @return A list of unapproved Subtask objects associated with the given userId.
     */
    @Override
    public List<Subtask> getUnapprovedSubtasks(int userId) {
        logger.info("Getting unapproved subtasks for userId: {}", userId);
        TypedQuery<Subtask> query = entityManager.createQuery(
                "SELECT st FROM Subtask st WHERE st.apprStatus = 'NA' AND st.primaryKey.taskId IN " +
                        "(SELECT t FROM Task t WHERE t.taskSupervisor.userId = :userId)", Subtask.class);
        query.setParameter("userId", userId);
        List<Subtask> subtasks = query.getResultList();
        logger.info("Found {} unapproved subtasks for userId: {}", subtasks.size(), userId);
        return subtasks;
    }


    /**
     * Updates the approval status of a subtask based on the provided SubtaskInput object.
     *
     * @param subtaskInput The SubtaskInput object containing the new approval status and subtask IDs.
     */
    @Override
    public void updateSubtaskApproval(SubtaskInput subtaskInput) {
        logger.info("Updating approval status for subtask with taskId: {} and subtaskId: {}", subtaskInput.getTaskId(), subtaskInput.getSubtaskId());

        Query query = entityManager.createQuery(
                "update Subtask st set st.apprStatus = :status, st.sbts_status = 'INPR'  where st.primaryKey.taskId = :taskId and st.primaryKey.subtaskId  = :subtaskId");
        query.setParameter("status", subtaskInput.getApprStatus());
        query.setParameter("taskId", subtaskInput.getTaskId());
        query.setParameter("subtaskId", subtaskInput.getSubtaskId());
        int updatedRows = query.executeUpdate();

        logger.info("Updated {} subtask(s) approval status.", updatedRows);
    }

    /**
     * Retrieves all subtasks from the database.
     *
     * @return A list of all Subtask objects.
     */
    @Override
    public List<Subtask> getAllSubtasks() {
        TypedQuery<Subtask> query = entityManager.createQuery("select st from Subtask st", Subtask.class);
        List<Subtask> subtasks = query.getResultList();
        logger.info("Retrieved {} subtasks from the database.", subtasks.size());
        return subtasks;
    }

    /**
     * Retrieves all subtasks associated with a specific user from the database.
     *
     * @param userId The ID of the user to get subtasks for.
     * @return A list of Subtask objects associated with the given user.
     */
    @Override
    public List<Subtask> getAllSubtasksByUserId(int userId) {
        TypedQuery<Subtask> query = entityManager.createQuery("select st from Subtask st where st.primaryKey.taskId in (select t from Task t where t.taskSupervisor.userId = :userId )", Subtask.class);
        query.setParameter("userId", userId);
        List<Subtask> subtasks = query.getResultList();
        logger.info("Retrieved {} subtasks for user with ID: {}", subtasks.size(), userId);
        return subtasks;
    }
	
    
    /**
     * Updates the status of a subtask based on the provided SubtaskInput object.
     *
     * @param subtaskInput The SubtaskInput object containing the new status and subtask IDs.
     */
    @Override
    public void updateSubtaskStatus(SubtaskInput subtaskInput) {
        logger.info("Updating status for subtask with taskId: {} and subtaskId: {}", subtaskInput.getTaskId(), subtaskInput.getSubtaskId());

        Query query = entityManager.createQuery(
                "update Subtask st set st.sbts_status = :status where st.primaryKey.taskId = :taskId and st.primaryKey.subtaskId  = :subtaskId");
        query.setParameter("status", subtaskInput.getSbtsStatus());
        query.setParameter("subtaskId", subtaskInput.getSubtaskId());
        query.setParameter("taskId", subtaskInput.getTaskId());
        int updatedRows = query.executeUpdate();

        logger.info("Updated {} subtask(s) status.", updatedRows);
    }

    /**
     * Retrieves all unapproved subtasks from the database.
     *
     * @return A list of Subtask objects representing unapproved subtasks.
     */
    @Override
    public List<Subtask> getAllUnapprSubtasks() {
        TypedQuery<Subtask> query = entityManager.createQuery(
                "SELECT st FROM Subtask st where st.apprStatus = 'NA'",
                Subtask.class);
        List<Subtask> subtasks = query.getResultList();
        logger.info("Retrieved {} unapproved subtasks from the database.", subtasks.size());
        return subtasks;
    }
    
}