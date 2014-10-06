package no.uio.inf5750.assignment2.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.impl.DefaultStudentSystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class StudentSystemTest
{	
	@Autowired
	DefaultStudentSystem dss;
	
	public void setDefauktStudentSystem(DefaultStudentSystem dss) 
	{
		this.dss = dss;
	}	

	@Test
	public void testAddCourse() 
	{
		int id = dss.addCourse("TestCodeDSS1", "TestNameDSS1");
		
		assertEquals(id, dss.getCourse(id).getId());
	}

	@Test
	public void testUpdateCourse() 
	{
		int id = dss.addCourse("TestCodeDSS2", "TestNameDSS2");
		dss.updateCourse(id, "TestCodeDSS3", "TestNameDSS3");
		Course course = dss.getCourse(id);
		
		assertEquals(course.getCourseCode(), "TestCodeDSS3");
		assertEquals(course.getName(), "TestNameDSS3");				
	}
	
	@Test(expected=NullPointerException.class)
	public void testUpdateCourseInvalidId() 
	{
		dss.addCourse("TestCodeDSS4", "TestNameDSS4");
		dss.updateCourse(-1, "TestCodeDSS5", "TestNameDSS5");		
	}

	@Test
	public void testGetCourse() 
	{
		int id = dss.addCourse("TestCodeDSS6", "TestNameDSS6");
		Course course = dss.getCourse(id);
		assertEquals(course.getId(), id);
	}
	
	@Test
	public void testGetCourseInvalidInput() 
	{
		assertNull(dss.getCourse(-1));
	}

	@Test
	public void testGetCourseByCourseCode() 
	{
		int id = dss.addCourse("TestCodeDSS7", "TestNameDSS7");
		Course course = dss.getCourseByCourseCode("TestCodeDSS7");
		
		assertEquals(course.getId(), id);
	}

	@Test
	public void testGetCourseByName() 
	{
		int id = dss.addCourse("TestCodeDSS8", "TestNameDSS8");
		Course course = dss.getCourseByName("TestNameDSS8");
		
		assertEquals(course.getId(), id);
	}

	@Test
	public void testGetAllCourses() 
	{
		int id = dss.addCourse("TestCodeDSS9", "TestNameDSS9");
		int id2 = dss.addCourse("TestCodeDSS10", "TestNameDSS10");
		
		Collection<Course> courses = dss.getAllCourses();
		
		assertTrue(courses.contains(dss.getCourse(id)) && courses.contains(dss.getCourse(id2)));
	}

	@Test
	public void testDelCourse() 
	{
		int courseId = dss.addCourse("TestCodeDSS10", "TestNameDSS10");
		int studId = dss.addStudent("TestStudentName1");
		int degreeId = dss.addDegree("TestDegreeType1");
		dss.addRequiredCourseToDegree(degreeId, courseId);
		dss.addAttendantToCourse(courseId, studId);
		
		dss.delCourse(courseId);
		
		assertTrue(!(dss.getStudent(studId).getCourses().contains(dss.getCourse(courseId))));
		assertTrue(!(dss.getDegree(degreeId).getRequiredCourses().contains(dss.getCourse(courseId))));
		
		assertNull(dss.getCourse(courseId));
	}

	@Test
	public void testAddAttendantToCourse() 
	{
		int courseId = dss.addCourse("TestCodeDSS11", "TestNameDSS11");
		int studId = dss.addStudent("TestStudentName2");
		dss.addAttendantToCourse(courseId, studId);
		
		assertTrue(dss.getCourse(courseId).getAttendants().contains(dss.getStudent(studId)));
	}

	@Test
	public void testRemoveAttendantFromCourse() 
	{
		int courseId = dss.addCourse("TestCodeDSS12", "TestNameDSS12");
		int studId = dss.addStudent("TestStudentName3");
		dss.addAttendantToCourse(courseId, studId);
		dss.removeAttendantFromCourse(courseId, studId);
		
		assertTrue(!(dss.getCourse(courseId).getAttendants().contains(dss.getStudent(studId))));
	}

	@Test
	public void testAddDegree() 
	{
		int id = dss.addDegree("TestTypeDSS1");
		
		assertEquals(id, dss.getDegree(id).getId());
	}

	@Test
	public void testUpdateDegree() 
	{
		int id = dss.addDegree("TestTypeDSS2");
		dss.updateDegree(id, "TestTypeDSS3");
		Degree degree = dss.getDegree(id);
		
		assertEquals(degree.getType(), "TestTypeDSS3");
	}

	@Test
	public void testGetDegree() 
	{
		int id = dss.addDegree("TestTypeDSS4");
		Degree degree = dss.getDegree(id);
		assertEquals(degree.getId(), id);
	}
	
	@Test
	public void testGetDegreeInvalidInput() 
	{
		assertNull(dss.getDegree(-1));
	}

	@Test
	public void testGetDegreeByType() 
	{
		int id = dss.addDegree("TestTypeDSS5");
		Degree degree = dss.getDegreeByType("TestTypeDSS5");
		
		assertEquals(degree.getId(), id);
	}

	@Test
	public void testGetAllDegrees() 
	{
		int id = dss.addDegree("TestTypeDSS6");
		int id2 = dss.addDegree("TestTypeDSS7");
		
		Collection<Degree> degrees = dss.getAllDegrees();
		
		assertTrue(degrees.contains(dss.getDegree(id)) && degrees.contains(dss.getDegree(id2)));
	}

	@Test
	public void testDelDegree() 
	{
		int degreeId = dss.addDegree("TestDegreeType8");
		int studId = dss.addStudent("TestStudentName4");
		dss.addDegreeToStudent(studId, degreeId);
		
		dss.delDegree(degreeId);
		
		assertTrue(!(dss.getStudent(studId).getDegrees().contains(dss.getDegree(degreeId))));
		
		assertNull(dss.getDegree(degreeId));
	}

	@Test
	public void testAddRequiredCourseToDegree() 
	{
		int courseId = dss.addCourse("TestCodeDSS13", "TestNameDSS13");
		int degreeId = dss.addDegree("TestDegreeType9");
		dss.addRequiredCourseToDegree(degreeId, courseId);
		
		assertTrue(dss.getDegree(degreeId).getRequiredCourses().contains(dss.getCourse(courseId)));
	}

	@Test
	public void testRemoveRequiredCourseFromDegree() 
	{
		int courseId = dss.addCourse("TestCodeDSS14", "TestNameDSS14");
		int degreeId = dss.addDegree("TestDegreeType10");
		dss.addRequiredCourseToDegree(degreeId, courseId);
		dss.removeRequiredCourseFromDegree(degreeId, courseId);
		
		assertTrue(!(dss.getDegree(degreeId).getRequiredCourses().contains(dss.getCourse(courseId))));
	}

	@Test
	public void testAddStudent() 
	{
		int id = dss.addStudent("TestStudentName5");
		
		assertEquals(id, dss.getStudent(id).getId());
	}

	@Test
	public void testUpdateStudent() 
	{
		int id = dss.addStudent("TestStudentName6");
		dss.updateStudent(id, "TestStudentName7");
		Student student = dss.getStudent(id);
		
		assertEquals(student.getName(), "TestStudentName7");
	}

	@Test
	public void testGetStudent() 
	{
		int id = dss.addStudent("TestStudentName8");
		Student student = dss.getStudent(id);
		assertEquals(student.getId(), id);
	}
	
	@Test
	public void testGetStudentInvalidInput() 
	{
		assertNull(dss.getStudent(-1));
	}

	@Test
	public void testGetStudentByName() 
	{
		int id = dss.addStudent("TestStudentName9");
		Student student = dss.getStudentByName("TestStudentName9");
		
		assertEquals(student.getId(), id);
	}

	@Test
	public void testGetAllStudents() 
	{
		int id = dss.addStudent("TestStudentName10");
		int id2 = dss.addStudent("TestStudentName11");
		
		Collection<Student> student = dss.getAllStudents();
		
		assertTrue(student.contains(dss.getStudent(id)) && student.contains(dss.getStudent(id2)));
	}

	@Test
	public void testDelStudent() 
	{
		int courseId = dss.addCourse("TestCodeDSS15", "TestNameDSS15");
		int studId = dss.addStudent("TestStudentName12");
		dss.addAttendantToCourse(courseId, studId);
		
		dss.delStudent(studId);
		
		assertTrue(!(dss.getCourse(courseId).getAttendants().contains(dss.getStudent(studId))));
		
		assertNull(dss.getStudent(studId));
	}

	@Test
	public void testAddDegreeToStudent() 
	{
		int studId = dss.addStudent("TestStudentName13");
		int degreeId = dss.addDegree("TestDegreeType11");
		dss.addDegreeToStudent(studId, degreeId);
		
		assertTrue(dss.getStudent(studId).getDegrees().contains(dss.getDegree(degreeId)));
	}

	@Test
	public void testRemoveDegreeFromStudent() 
	{
		int studId = dss.addStudent("TestStudentName14");
		int degreeId = dss.addDegree("TestDegreeType12");
		dss.addDegreeToStudent(studId, degreeId);
		dss.removeDegreeFromStudent(studId, degreeId);
		
		assertTrue(!(dss.getStudent(studId).getDegrees().contains(dss.getDegree(degreeId))));
	}

	@Test
	public void testStudentFulfillsDegreeRequirements() 
	{
		int studId = dss.addStudent("TestStudentName15");
		int courseId = dss.addCourse("TestCodeDSS16", "TestNameDSS16");
		int degreeId = dss.addDegree("TestDegreeType13");
		
		
		dss.addAttendantToCourse(courseId, studId);
		dss.addRequiredCourseToDegree(degreeId, courseId);
		
		assertTrue(dss.studentFulfillsDegreeRequirements(studId, degreeId));
	}
	
	@Test
	public void testStudentDoesNotFulfillDegreeRequirements() 
	{
		int studId = dss.addStudent("TestStudentName16");
		int courseId = dss.addCourse("TestCodeDSS17", "TestNameDSS17");
		int courseId2 = dss.addCourse("TestCodeDSS18", "TestNameDSS18");
		int degreeId = dss.addDegree("TestDegreeType14");
		
		dss.addAttendantToCourse(courseId, studId);
		dss.addRequiredCourseToDegree(degreeId, courseId);
		dss.addRequiredCourseToDegree(degreeId, courseId2);
		
		assertTrue(!(dss.studentFulfillsDegreeRequirements(studId, degreeId)));
	}

}
