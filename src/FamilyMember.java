import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


public class FamilyMember {

	String name;
	char gender;
	String spouse;
	int generation;
	FamilyMember parent;
	LinkedList children = new LinkedList<FamilyMember>();
	
	FamilyMember(String member_name, char gend, String spouse_name, String parent_name) throws WrongInput
	{
		name = member_name;
		gender = gend;	
		addParent(parent_name);
		
		if(parent!=null)
		{
			parent.addChild(this);				
		}
		else
		{
			throw new WrongInput("parent name not found");			
		}
		generation = parent.generation + 1;
		
		if(spouse_name.equalsIgnoreCase("na"))
			spouse="";
		else
			spouse = spouse_name;	
	}
	
	FamilyMember(String member_name, char gend, String spouse_name)
	{
		name = member_name;
		gender = gend;
		generation = 1;
		parent = null;
		
		if(spouse_name.equalsIgnoreCase("na"))
			spouse="";
		else
			spouse = spouse_name;			
	}
	
	
	void addChild(FamilyMember child)
	{
		children.add(child);
	}
	
	void addParent(String parent_name)
	{
		//perform breadth 1st search. if node = parent then add to parent node
		FamilyMember parent_node = TreeOperation.search(parent_name);	
		if(parent_node != null)
			this.parent = parent_node;
	}
	
	boolean isParent(FamilyMember child)
	{
		//to check if calling object is parent of the argument object. e.g. A.isParent(B)
		ListIterator<FamilyMember> child_iterator = this.children.listIterator();
		boolean result=false;
		
		while(child_iterator.hasNext())
		{
			if(child_iterator.next().name.equals(child.name))
			{
				result=true;
				break;
			}
		}
		return result;	
	}
	
	void addx(FamilyMember f)
	{
		//adds a new node to the tree
		/*
		 * s1- check if calling node is parent of f
		 * 	if yes then add f as child of parent
		 * 	else perform breadth-first search and compare each node with parent
		 */
		LinkedList list = new LinkedList<FamilyMember>();
		FamilyMember n = this;
		if(n.isParent(f) == true)
		{
			n.addChild(f);
		}
		else
		{
			//perform breadth-1st search	
			list.add(n.children);
			ListIterator<FamilyMember> iterator = list.listIterator();	
			while(iterator.hasNext())
			{
				FamilyMember fm = iterator.next();
				addx(fm);
			}
			
		}	
		
	}
}
