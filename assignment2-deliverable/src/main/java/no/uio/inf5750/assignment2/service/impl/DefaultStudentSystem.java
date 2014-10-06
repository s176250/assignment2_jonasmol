package no.uio.inf5750.assignment2.service.impl;

import java.util.Collection;
import java.util.Set;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("defaultStudentSystem")
public class DefaultStudentSystem implements StudentSystem
{
	
	@Autowired
	CourseDAO courseDAO;
	
	public void setCourseDAO(CourseDAO courseDAO) 
	{
		this.courseDAO = courseDAO;
	}	
	
	@Autowired
	DegreeDAO degreeDAO;
	public void setDegreeDAO(DegreeDAO degreeDAO) 
	{
		this.degreeDAO = degreeDAO;
	}
	
	@Autowired
	StudentDAO studentDAO;
	public void setStudentDAO(StudentDAO studentDAO) 
	{
		this.studentDAO = studentDAO;
	}

	@Override
	public int addCourse(String courseCode, String name)
	{
		return courseDAO.saveCourse(new Course(courseCode, name));
	}

	@Override
	public void updateCourse(int courseId, String courseCode, String name)
	{
		Course c = courseDAO.getCourse(courseId);
		c.setCourseCode(courseCode);
		c.setName(name);
		courseDAO.saveCourse(c);
	}

	@Override
	public Course getCourse(int courseId)
	{
		return courseDAO.getCourse(courseId);
	}

	@Override
	public Course getCourseByCourseCode(String courseCode)
	{
		return courseDAO.getCourseByCourseCode(courseCode);
	}

	@Override
	public Course getCourseByName(String name)
	{
		return courseDAO.getCourseByName(name);
	}

	@Override
	public Collection<Course> getAllCourses()
	{
		return courseDAO.getAllCourses();
	}

	@Override
	public void delCourse(int courseId)
	{
		Course c = courseDAO.getCourse(courseId);
		
		Collection<Student> ss = studentDAO.getAllStudents();
		for (Student s : ss) 
		{
			if(s.getCourses().remove(c))
				studentDAO.saveStudent(s);
		}
		
		Collection<Degree> ds = degreeDAO.getAllDegrees();
		for (Degree d : ds) 
		{
			if(d.getRequiredCourses().remove(c))
				degreeDAO.saveDegree(d);
		}		
		courseDAO.delCourse(c);
	}

	@Override
	public void addAttendantToCourse(int courseId, int studentId)
	{
		Student s = studentDAO.getStudent(studentId);
		Course c = courseDAO.getCourse(courseId);
		c.getAttendants().add(s);
		s.getCourses().add(c);
		
		studentDAO.saveStudent(s);
		courseDAO.saveCourse(c);
	}

	@Override
	public void removeAttendantFromCourse(int courseId, int studentId)
	{
		Student s = studentDAO.getStudent(studentId);
		Course c = courseDAO.getCourse(courseId);
		c.getAttendants().remove(s);
		s.getCourses().remove(c);
		
		studentDAO.saveStudent(s);
		courseDAO.saveCourse(c);
	}

	@Override
	public int addDegree(String type)
	{
		return degreeDAO.saveDegree(new Degree(type));
	}

	@Override
	public void updateDegree(int degreeId, String type)
	{
		Degree d = degreeDAO.getDegree(degreeId);
		d.setType(type);
		degreeDAO.saveDegree(d);
	}

	@Override
	public Degree getDegree(int degreeId)
	{
		return degreeDAO.getDegree(degreeId);
	}

	@Override
	public Degree getDegreeByType(String type)
	{
		return degreeDAO.getDegreeByType(type);
	}

	@Override
	public Collection<Degree> getAllDegrees()
	{
		return degreeDAO.getAllDegrees();
	}

	@Override
	public void delDegree(int degreeId)
	{
		Degree d = degreeDAO.getDegree(degreeId);
		
		Collection<Student> ss = studentDAO.getAllStudents();
		for (Student s : ss) 
		{
			if(s.getDegrees().remove(d))
				studentDAO.saveStudent(s);
		}
		degreeDAO.delDegree(d);
	}

	@Override
	public void addRequiredCourseToDegree(int degreeId, int courseId)
	{
		Degree d = degreeDAO.getDegree(degreeId);
		Course c = courseDAO.getCourse(courseId);
		d.getRequiredCourses().add(c);
		
		degreeDAO.saveDegree(d);
	}

	@Override
	public void removeRequiredCourseFromDegree(int degreeId, int courseId)
	{
		Degree d = degreeDAO.getDegree(degreeId);
		Course c = courseDAO.getCourse(courseId);
		d.getRequiredCourses().remove(c);
		
		degreeDAO.saveDegree(d);
	}

	@Override
	public int addStudent(String name)
	{
		return studentDAO.saveStudent(new Student(name));
	}

	@Override
	public void updateStudent(int studentId, String name)
	{
		Student s = studentDAO.getStudent(studentId);
		s.setName(name);
		
		studentDAO.saveStudent(s);
	}

	@Override
	public Student getStudent(int studentId)
	{
		return studentDAO.getStudent(studentId);
	}

	@Override
	public Student getStudentByName(String name)
	{
		return studentDAO.getStudentByName(name);
	}

	@Override
	public Collection<Student> getAllStudents()
	{
		return studentDAO.getAllStudents();
	}

	@Override
	public void delStudent(int studentId)
	{
		Student s = studentDAO.getStudent(studentId);
		
		Collection<Course> cs = courseDAO.getAllCourses();
		for (Course c : cs) 
		{
			if(c.getAttendants().remove(s))
				courseDAO.saveCourse(c);
		}
		studentDAO.delStudent(s);
	}

	@Override
	public void addDegreeToStudent(int studentId, int degreeId)
	{
		Degree d = degreeDAO.getDegree(degreeId);
		Student s = studentDAO.getStudent(studentId);
		s.getDegrees().add(d);
		
		studentDAO.saveStudent(s);
	}

	@Override
	public void removeDegreeFromStudent(int studentId, int degreeId)
	{
		Degree d = degreeDAO.getDegree(degreeId);
		Student s = studentDAO.getStudent(studentId);
		s.getDegrees().remove(d);
		
		studentDAO.saveStudent(s);
	}

	@Override
	public boolean studentFulfillsDegreeRequirements(int studentId, int degreeId)
	{
		Degree d = degreeDAO.getDegree(degreeId);
		Student s = studentDAO.getStudent(studentId);
		
		Set<Course> studentsCourses = s.getCourses();
		Set<Course> degreesCourses = d.getRequiredCourses();
		
		return studentsCourses.containsAll(degreesCourses);
	}

}
