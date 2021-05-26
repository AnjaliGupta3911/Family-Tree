import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;


public class TreeOperation {

	static FamilyMember search(String n)
	{
		//breadth 1st search
		//to check if f node's name is same as the name passed as argument
		FamilyMember result=null, current;
		
		Queue<FamilyMember> list = new LinkedList<FamilyMember>();
		list.add(MainClassEntry.firstAncestor);
		
		while(!list.isEmpty())
		{		
			current = list.poll();
			if(current.name.equalsIgnoreCase(n))
			{
				result=current;
				break;
			}
			else
				list.addAll(current.children);
		}
		return result;
	}
	
	static void iterate(FamilyMember f)
	{
		Queue<FamilyMember> list = new LinkedList<FamilyMember>();
		list.add(f);

		while(!list.isEmpty())
		{
			FamilyMember n = list.poll();
			
			if(n.parent != null)
				System.out.println(n.name+ " < "+n.parent.name+". Generation: "+n.generation);
			else 
				System.out.println(n.name+": First ancestor");
			
			if(n.children.isEmpty()==false)
				list.addAll(n.children);
		}
		
	}
	
	static void iterate_file(FamilyMember f)
	{
		try
		{
			FileWriter my_file = new FileWriter("family_tree2.html");
			//BufferedWriter bw = new BufferedWriter(my_file);		
			my_file.write("<html><h3 style=\"text-align:center\"><b> My Family Tree <b></h3><body>");
			
			Queue<FamilyMember> list = new LinkedList<FamilyMember>();
			list.add(f);
			
			String button_main="<button style=\"border:none;color:white;font-size:16px;margin:4px 2px;background-color:";
			String button_blue="<button style=\"border:none;color:white;font-size:16px;margin:4px 2px;background-color:#008CBA;\">";
			String button_end="</button>";
			
			String pink="#FFB6C1";
			String blue="#008CBA";
			String button_1,button_2;
			int gen=0;

			while(!list.isEmpty())
			{
				FamilyMember n = list.poll();
				
				{
				if(n.generation > gen)
					my_file.write("<br><br>");
				else
					my_file.write("&ensp; ");
				gen=n.generation;
				}
				
				if(n.gender=='f'||n.gender=='F')
				{
					button_1=button_main+pink+";\">";
					button_2=button_main+blue+";\">";
				}
				else
				{
					button_1=button_main+blue+";\">";
					button_2=button_main+pink+";\">";
				}
								
				if(gen>1)
				{
					String s= n.name+ " < "+n.parent.name;
					
					my_file.write(button_1+ s +button_end);
					if(n.spouse != "")
					{
						my_file.write("--");
						my_file.write(button_2+ n.spouse +button_end);
					}

				}
				else 
				{
					my_file.write(button_1 + n.name + button_end);
					if(n.spouse != "")
					{
						my_file.write("--");
						my_file.write(button_2+ n.spouse +button_end);
					}
				}
				
				
				if(n.children.isEmpty()==false)
					list.addAll(n.children);
			}
			
			my_file.write("</body></html>");
			my_file.close();
			
		//check if we can do this: Desktop.getDesktop().browse(f.toURI());
		}
		catch(Exception e)
		{
			System.out.println("exception thrown in file");
		}
		
	}
}
