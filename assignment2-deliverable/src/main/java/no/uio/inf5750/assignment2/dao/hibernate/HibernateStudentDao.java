package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Student;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateStudentDao implements StudentDAO
{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public int saveStudent(Student student)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(student);
		sessionFactory.getCurrentSession().flush();
		return student.getId();
	}

	@Override
	public Student getStudent(int id)
	{
		return  (Student) sessionFactory.getCurrentSession().get( Student.class, id );
	}

	@Override
	public Student getStudentByName(String name)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria( Student.class );
		criteria.add(Restrictions.eq("name", name ));
		return (Student) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Student> getAllStudents()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria( Student.class );
		return criteria.list();
	}

	@Override
	public void delStudent(Student student)
	{
		sessionFactory.getCurrentSession().delete(student);
		sessionFactory.getCurrentSession().flush();
	}

}
