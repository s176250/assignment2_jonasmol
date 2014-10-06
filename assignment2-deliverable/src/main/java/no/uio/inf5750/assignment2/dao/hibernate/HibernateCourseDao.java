package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.model.Course;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateCourseDao implements CourseDAO
{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }
    
	
	@Override
	public int saveCourse(Course course)
	{
		sessionFactory.getCurrentSession().save( course );	
		sessionFactory.getCurrentSession().flush();
		return course.getId();
	}

	@Override
	public Course getCourse(int id)
	{
		return  (Course) sessionFactory.getCurrentSession().get( Course.class, id );
	}

	@Override
	public Course getCourseByCourseCode(String courseCode)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria( Course.class );
		criteria.add(Restrictions.eq("courseCode", courseCode ));
		return (Course) criteria.uniqueResult();
	}

	@Override
	public Course getCourseByName(String name)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria( Course.class );
		criteria.add(Restrictions.eq("name", name ));
		return (Course) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Course> getAllCourses()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria( Course.class );
		return criteria.list();
	}

	@Override
	public void delCourse(Course course)
	{
		sessionFactory.getCurrentSession().delete(course);
		sessionFactory.getCurrentSession().flush();
	}

}
