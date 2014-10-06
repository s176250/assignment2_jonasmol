package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import no.uio.inf5750.assignment2.model.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class StudentDAOTest
{
	@Autowired
	StudentDAO studentDAO;
	public void setStudentDAO(StudentDAO studentDAO) 
	{
		this.studentDAO = studentDAO;
	}

	@Test
	public void testSaveStudent() 
	{
		Student s = new Student("Test1");
		int id = studentDAO.saveStudent(s);
		assertEquals(id, s.getId());
	}

	@Test
	public void testGetStudent() 
	{
		Student s = new Student("Test2");
		 int id = studentDAO.saveStudent(s);
		 s = studentDAO.getStudent(id);
		 assertEquals(id, s.getId());
	}
	
	@Test
	public void testGetStudentInvalidID() 
	{
		 assertNull(studentDAO.getStudent(-1));
	}
	

	@Test
	public void testGetStudentByName()
	{
		studentDAO.saveStudent(new Student("Test3"));
		Student s = studentDAO.getStudentByName("Test3");
		assertEquals("Test3", s.getName());
	}
	

	@Test
	public void testGetAllStudents() 
	{
		Student s = new Student("Test5");
		Student ss = new Student("Test6");
		studentDAO.saveStudent(s);
		studentDAO.saveStudent(ss);
		assertTrue(studentDAO.getAllStudents().contains(s) && studentDAO.getAllStudents().contains(ss));
	}

	@Test
	public void testDelStudent() 
	{
		Student ss = new Student("Test7");
		int id = studentDAO.saveStudent(ss);
		studentDAO.delStudent(ss);
		assertNull(studentDAO.getStudent(id));
	}
}
