package com.gabriel.gcscollegeAPI.model;

import javax.persistence.Entity;

@Entity
public class Course {
	
	private String courseStart;
	private String courseEnd;
	private String level;
	
	public String getCourseStart() {
		return courseStart;
	}
	public void setCourseStart(String courseStart) {
		this.courseStart = courseStart;
	}
	public String getCourseEnd() {
		return courseEnd;
	}
	public void setCourseEnd(String courseEnd) {
		this.courseEnd = courseEnd;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Course [courseStart=" + courseStart + ", courseEnd=" + courseEnd + ", level=" + level + "]";
	}
	
	

}
