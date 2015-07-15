package com.backpack.dataAccess.child;

import java.util.List;

import com.backpack.model.Child;

public interface ChildDataService {
	
	public Child insert(Child child);
	
	public Child update(Child child);
	
	public List<Child> fetchAllChildren();

	public Child fetchChild(int childId);
	
}
