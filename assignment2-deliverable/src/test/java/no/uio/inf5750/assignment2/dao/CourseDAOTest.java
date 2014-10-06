package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import no.uio.inf5750.assignment2.model.Course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class CourseDAOTest
{
	@Autowired
	CourseDAO courseDAO;
	public void setStudentDAO(CourseDAO courseDAO) 
	{
		this.courseDAO = courseDAO;
	}
	
	@Test
	public void testSaveCourse() 
	{
		Course course = new Course("TestCode1", "TestName1");
		int id = courseDAO.saveCourse(course);
		assertEquals(id, course.getId());
	}

	@Test
	public void testGetCourse() 
	{
		Course course = new Course("TestCode2", "TestName2");
		 int id = courseDAO.saveCourse(course);
		 course = courseDAO.getCourse(id);
		 assertEquals(id, course.getId());
	}
	
	@Test
	public void testGetCourseInvalidID() 
	{
		 assertNull(courseDAO.getCourse(-1));
	}
	
	@Test
	public void testGetCourseByCourseCode() 
	{
		courseDAO.saveCourse(new Course("TestCode3", "TestName3"));
		Course course = courseDAO.getCourseByCourseCode("TestCode3");
		assertEquals("TestCode3", course.getCourseCode());
	}

	@Test
	public void testGetCourseByName() 
	{
		courseDAO.saveCourse(new Course("TestCode4", "TestName4"));
		Course course = courseDAO.getCourseByName("TestName4");
		assertEquals("TestName4", course.getName());
	}

	@Test
	public void testGetAllCourses() 
	{
		Course course1 = new Course("TestCode5", "TestName5");
		Course course2 = new Course("TestCode6", "TestName6");
		courseDAO.saveCourse(course1);
		courseDAO.saveCourse(course2);
		assertTrue(courseDAO.getAllCourses().contains(course1) && courseDAO.getAllCourses().contains(course2));
	}

	@Test
	public void testDelCourse() 
	{
		Course degree = new Course("TestCode7", "TestName7");
		int id = courseDAO.saveCourse(degree);
		courseDAO.delCourse(degree);
		assertNull(courseDAO.getCourse(id));
	}

}
