package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.model.Degree;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateDegreeDao implements DegreeDAO
{
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public int saveDegree(Degree degree)
	{
		sessionFactory.getCurrentSession().saveOrUpdate( degree );
		sessionFactory.getCurrentSession().flush();
		return degree.getId();
	}

	@Override
	public Degree getDegree(int id)
	{
		return  (Degree) sessionFactory.getCurrentSession().get( Degree.class, id );
	}

	@Override
	public Degree getDegreeByType(String type)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria( Degree.class );
		criteria.add(Restrictions.eq("type", type ));
		return (Degree) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Degree> getAllDegrees()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria( Degree.class );
		return criteria.list();
	}

	@Override
	public void delDegree(Degree degree)
	{
		sessionFactory.getCurrentSession().delete(degree);
		sessionFactory.getCurrentSession().flush();
	}

}
