
package com.pennant.prodmtr.model.view;

public class TotalAnalysis {
	private int totalProjects;
	private int totalModl;
	private int totalSprints;
	private int totalTasks;
	private int totalSubtasks;

	public TotalAnalysis() {
		// TODO Auto-generated constructor stub
	}
	public int getTotalProjects() {
		return totalProjects;
	}
	public void setTotalProjects(int totalProjects) {
		this.totalProjects = totalProjects;
	}
	public int getTotalModl() {
		return totalModl;
	}
	public void setTotalModl(int totalModl) {
		this.totalModl = totalModl;
	}
	public int getTotalSprints() {
		return totalSprints;
	}
	public void setTotalSprints(int totalSprints) {
		this.totalSprints = totalSprints;
	}
	public int getTotalTasks() {
		return totalTasks;
	}
	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
	}
	public int getTotalSubtasks() {
		return totalSubtasks;
	}
	public void setTotalSubtasks(int totalSubtasks) {
		this.totalSubtasks = totalSubtasks;
	}
	public TotalAnalysis(int totalProjects, int totalModl, int totalSprints, int totalTasks, int totalSubtasks) {
		super();
		this.totalProjects = totalProjects;
		this.totalModl = totalModl;
		this.totalSprints = totalSprints;
		this.totalTasks = totalTasks;
		this.totalSubtasks = totalSubtasks;
	}



}
