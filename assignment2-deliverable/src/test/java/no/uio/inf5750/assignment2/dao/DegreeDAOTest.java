package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import no.uio.inf5750.assignment2.model.Degree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class DegreeDAOTest
{

	@Autowired
	DegreeDAO degreeDAO;
	public void setStudentDAO(DegreeDAO degreeDAO) 
	{
		this.degreeDAO = degreeDAO;
	}
	
	@Test
	public void testSaveDegree() 
	{
		Degree degree = new Degree("Test1");
		int id = degreeDAO.saveDegree(degree);
		assertEquals(id, degree.getId());
	}

	@Test
	public void testGetDegree() 
	{
		 Degree degree = new Degree("Test2");
		 int id = degreeDAO.saveDegree(degree);
		 degree = degreeDAO.getDegree(id);
		 assertEquals(id, degree.getId());
	}
	
	@Test
	public void testGetDegreeInvalidID() 
	{
		 assertNull(degreeDAO.getDegree(-1));
	}

	@Test
	public void testGetDegreeByType() 
	{
		degreeDAO.saveDegree(new Degree("Test3"));
		Degree degree = degreeDAO.getDegreeByType("Test3");
		assertEquals("Test3", degree.getType());
	}

	@Test
	public void testGetAllDegrees() 
	{
		Degree degree1 = new Degree("Test4");
		Degree degree2 = new Degree("Test5");
		degreeDAO.saveDegree(degree1);
		degreeDAO.saveDegree(degree2);
		assertTrue(degreeDAO.getAllDegrees().contains(degree1) && degreeDAO.getAllDegrees().contains(degree2));
	}

	@Test
	public void testDelDegree() 
	{
		Degree degree = new Degree("Test6");
		int id = degreeDAO.saveDegree(degree);
		degreeDAO.delDegree(degree);
		assertNull(degreeDAO.getDegree(id));
	}

}
