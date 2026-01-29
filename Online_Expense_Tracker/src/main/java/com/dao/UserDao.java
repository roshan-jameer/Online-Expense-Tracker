package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.entity.User;

public class UserDao {
	private SessionFactory factory = null;
	private Session session = null;
	private Transaction tran = null;
	public UserDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	public boolean saveuser(User user) {
		boolean f = false;
		try {
			session = factory.openSession();
			tran = session.beginTransaction();
			session.save(user);
			tran.commit();
			f = true;
		}catch(Exception e) {
			if(tran != null) {
				f = false;
				e.printStackTrace();
			}
		}
		return f;
	}
	
	// Method for Login Page
	public User login(String email, String password)
	{
		User u = null;
		session = factory.openSession();
	Query q = session.createQuery("from User where email=:em and password=:ps");
		q.setParameter("em", email);
		q.setParameter("ps", password);
		u = (User) q.uniqueResult();
		return u;
	}
	}
