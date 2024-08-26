package com.mindex.challenge.data;

import java.util.List;
import java.time.LocalDate;
import com.mindex.challenge.data.Employee;

public class Compensation {
	private Employee employee;
	private int salary;
	private LocalDate effectiveDate;
	
	public Compensation(Employee employee, int salary, LocalDate effectiveDate) {
		this.employee = employee;
		this.salary = salary;
		this.effectiveDate = effectiveDate;
    	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getEffectiveDate() {
		return effectiveDate;
	}
	
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
