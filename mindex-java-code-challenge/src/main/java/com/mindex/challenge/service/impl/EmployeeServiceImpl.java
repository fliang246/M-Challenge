package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.CompensationRepository
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
	private CompensationRepository compensationRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
	
	//Retrieves number of reports by employeeId string
	@Override
	public ReportingStructure getNumberOfReports(String id) {
		LOG.debug("Computing number of reports for id [{}]", id);

		Employee emp = employeeRepository.findByEmployeeId(id);
		
		return this.countReports(emp);
	}
	
	//Creates Compensation object before inserting into database
	@Override
	public Compensation createCompensation(Employee employee) {
		LOG.debug("Creating compensation [{}]", employee);
		
		Compensation comp = new Compensation(employee, salary, effectDate);
		//Without an IDE to work in, there was no access to the DB
		compensationRepository.insert(comp);
		
		return comp;
	}
	
	//Retrieves Compensation object by employeeId string
	@Override
	public Compensation readCompensation(String id) {
		LOG.debug("Creating compensation for employee with id [{}]", id)
		
		//Without an IDE to work in, there was no access to the DB
		Employee emp = employeeRepository.findByEmployeeId(id);
		Compensation comp = compensationRepository.findByEmployee(emp);
		
		if (comp == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
		
		return comp;
	}
	
	//Recursion algorithm to count nodes in the tree, given Employee obj
	private int countReports(Employee employee) {
		int count = 0;
		if (employee.directReports.isEmpty()) {
			return count;
		}
		for (Employee directReport : employee.directReports) {
			/*
			Database JSON indicates directReports is an array of employeeId Strings
			Employee class type indicates directReports is an array of Employee objects
			The following is in assumption of the latter.
			If former, this algorithm would have to account for findbyEmployeeId(), an additional line
			*/			
			count += this.countReports(directReport);
		}
		return employee.directReports.size() + count;
	}
}
