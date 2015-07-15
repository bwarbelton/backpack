package com.backpack.dataAccess.child;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.backpack.dataAccess.MyBatisConnectionFactory;
import com.backpack.model.Child;

public class ChildDataServiceSQL implements ChildDataService {

	@Override
	public Child insert(Child child) {
		Child existingChild = fetchChild(child.getChildId());
		if (existingChild != null) {
			return null;
		}
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory()
				.openSession();
		ChildDataMapper mapper = session.getMapper(ChildDataMapper.class);
		mapper.insertChild(child);
		session.commit();
		session.close();
		existingChild = fetchChild(child.getChildId());
		if (existingChild != null) {
			return existingChild;
		} else {
			return null;
		}
	}

	@Override
	public Child update(Child child) {
		Child existingChild = fetchChild(child.getChildId());
		if (existingChild == null) {
			return null;
		}
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory()
				.openSession();
		ChildDataMapper mapper = session.getMapper(ChildDataMapper.class);
		mapper.updateChild(child);
		session.commit();
		session.close();
		existingChild = fetchChild(child.getChildId());
		if (existingChild != null) {
			return existingChild;
		} else {
			return null;
		}
	}
	
	@Override
	public List<Child> fetchAllChildren() {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory()
				.openSession();
		ChildDataMapper mapper = session.getMapper(ChildDataMapper.class);
		List<Child> childrenList = mapper.fetchAllChildren();
		session.close();
		return childrenList;
	}

	@Override
	public Child fetchChild(int childId) {
		SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory()
				.openSession();
		ChildDataMapper mapper = session.getMapper(ChildDataMapper.class);
		Child child = mapper.fetchChild(childId);
		session.close();
		return child;
	}

}
