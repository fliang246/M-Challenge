package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Compensation;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
	ReportingStructure getNumberOfReports(String id);
	Compensation createCompensation(Employee employee);
	Compensation readCompensation(String id);
}
