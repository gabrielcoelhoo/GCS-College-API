package com.gabriel.gcscollegeAPI.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

//following this tutorial
//https://www.baeldung.com/jpa-many-to-many

@Entity
public class Course {
	
	//what is serializable 
	//public class Country implements Serializable {
    //private static final long serialVersionUID = 1L;
	//search later !1
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private LocalDate courseStart;
	
	@Column(nullable = false)
	@NotNull
	private LocalDate courseEnd;
	
	@Column(nullable = false, length = 10)
	@NotNull
	private String period;
	
	@Column(nullable = false, length = 40)
	@NotNull
	private String level;
	
	@NotNull
	private int vacancies;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getCourseStart() {
		return courseStart;
	}
	public void setCourseStart(LocalDate courseStart) {
		this.courseStart = courseStart;
	}
	public LocalDate getCourseEnd() {
		return courseEnd;
	}
	public void setCourseEnd(LocalDate courseEnd) {
		this.courseEnd = courseEnd;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public int getVacancies() {
		return vacancies;
	}
	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
