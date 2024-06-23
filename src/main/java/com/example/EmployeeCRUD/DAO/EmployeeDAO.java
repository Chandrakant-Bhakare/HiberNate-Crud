package com.example.EmployeeCRUD.DAO;

import java.util.List;

import com.example.EmployeeCRUD.model.Employee;

public interface EmployeeDAO {
	Integer save(Employee emp);
	List<Employee> getAll();
	Employee getByID(int id);
	int delete(int id);
	int update(int id, String name, String phone, float salary);
//	Integer save(Employee employee);
//	List<Employee> getAll();
//	Employee getById(int id);
//	int remove(int id);
//	int update(int id, String name, String phone, float salary);
	


}
