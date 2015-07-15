package com.backpack.dataAccess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * A factory for creating MyBatisConnection objects.
 */
public class MyBatisConnectionFactory {
	private static final String configXml = "mybatisConfig.xml";
	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			Reader configReader = Resources.getResourceAsReader(configXml);

			if (sqlSessionFactory == null) {
				SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
				sqlSessionFactory = sqlSessionFactoryBuilder.build(configReader);
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("can not find Config XML- " + configXml);
			fileNotFoundException.printStackTrace();
		} catch (IOException iOException) {
			System.err.println("I/O error on Config XML- " + configXml);
			iOException.printStackTrace();
		}
	}
	
	private MyBatisConnectionFactory() {
	}

	/**
	 * Gets the sql session factory.
	 *
	 * @return the sql session factory
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
