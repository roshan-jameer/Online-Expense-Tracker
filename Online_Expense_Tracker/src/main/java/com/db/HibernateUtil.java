package com.db;

import java.util.Properties;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.entity.Expense;
import com.entity.User;


public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null) {
			Configuration config = new Configuration();
			Properties prop = new Properties();
			
			prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			prop.put(Environment.URL, "jdbc:mysql://localhost:3306/expense_tracker_db");
			prop.put(Environment.USER, "root");
			prop.put(Environment.PASS, "Rosjam@254");
			prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			prop.put(Environment.HBM2DDL_AUTO, "update");
			prop.put(Environment.SHOW_SQL, true);
			
			config.setProperties(prop);
			config.addAnnotatedClass(User.class);
			config.addAnnotatedClass(Expense.class);
			
			StandardServiceRegistry s = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(s);
					}
		return sessionFactory;
	}
}
