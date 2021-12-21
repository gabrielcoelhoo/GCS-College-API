package com.gabriel.gcscollegeAPI.model;


public class Enrolment {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
	
	private String englishCourse;
	private String coursePeriod;
	private int accomodation;
	private boolean transfer;
	private String bookingComments;
	
	public String getEnglishCourse() {
		return englishCourse;
	}
	public void setEnglishCourse(String englishCourse) {
		this.englishCourse = englishCourse;
	}
	public String getCoursePeriod() {
		return coursePeriod;
	}
	public void setCoursePeriod(String coursePeriod) {
		this.coursePeriod = coursePeriod;
	}
	public int getAccomodation() {
		return accomodation;
	}
	public void setAccomodation(int accomodation) {
		this.accomodation = accomodation;
	}
	public boolean isTransfer() {
		return transfer;
	}
	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
	public String getBookingComments() {
		return bookingComments;
	}
	public void setBookingComments(String bookingComments) {
		this.bookingComments = bookingComments;
	}
	@Override
	public String toString() {
		return "Enrolment [englishCourse=" + englishCourse + ", coursePeriod=" + coursePeriod + ", accomodation="
				+ accomodation + ", transfer=" + transfer + ", bookingComments=" + bookingComments + "]";
	}
	
	
	
}
	




//see if this Date type is correct