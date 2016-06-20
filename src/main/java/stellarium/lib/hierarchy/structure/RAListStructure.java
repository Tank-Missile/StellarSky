package stellarium.lib.hierarchy.structure;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Structure for lists.
 * */
public class RAListStructure implements IHierarchyStructure<List> {

	@Override
	public Iterator iteratorFor(List target) {
		return target.iterator();
	}

}