package com.example.EmployeeCRUD.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.EmployeeCRUD.DAO.EmployeeDAO;
import com.example.EmployeeCRUD.model.Employee;

public class Employeedaoimpl implements EmployeeDAO {
	private Configuration configuration;
	private SessionFactory sessiofactory;
	private Session session;
	public Employeedaoimpl() {
		 configuration = new Configuration();
		 configuration.configure().addAnnotatedClass(Employee.class);
		 sessiofactory =  configuration.buildSessionFactory();
	}

	@Override
	public Integer save(Employee emp) {
		session = sessiofactory.openSession();
		Transaction transaction = null;
		Integer id = null;
		try {
			transaction= session.beginTransaction();
			id = (Integer) session.save(emp);
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		
		
		return id;
	}

	@Override
	public List<Employee> getAll() {
		session=sessiofactory.openSession();
		Transaction transaction = null;
		List<Employee> emp = new ArrayList<Employee>();
		
		try {
			transaction=session.beginTransaction();
			emp = session.createQuery("FROM Employee").list();
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			
		}
		finally {
			session.close();
		}
		return emp;
	}

	@Override
	public Employee getByID(int id) {
		session=sessiofactory.openSession();
		Transaction transaction = null;
		Employee emp = null;
		try {
			transaction=session.beginTransaction();
			emp = session.get(Employee.class, id);
			transaction.commit();
		} catch (Exception e) {
			
		}
		return emp;
	}

	@Override
	public int delete(int id) {
		session=sessiofactory.openSession();
		Transaction transaction = null;
		int result =-1;
		try {
			transaction=session.beginTransaction();
			Employee emp = session.get(Employee.class, id);
			if(emp!=null){
				session.delete(emp);
				transaction.commit();
				result =1;
				
			}			
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();

			
		}
		finally {
			session.close();
		}
		return result;
	}

	@Override
	public int update(int id,String name, String phone, float salary) {
		session = sessiofactory.openSession();
		Transaction transaction = null;
		int result = -1;
		try {
			transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class,id);
			if(employee!=null) {
				employee.setName(name);
				employee.setPhone(phone);
				employee.setSalary(salary);
				session.update(employee);
				transaction.commit();
				result = 1;
			}
			
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return result;
	}

}
