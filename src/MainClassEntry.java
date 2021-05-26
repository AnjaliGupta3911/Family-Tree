import java.util.Scanner;


public class MainClassEntry {
	
	static FamilyMember firstAncestor=null;
	public static void main(String[] args) throws WrongInput {
		
		System.out.println("Enter name of family members starting from the last traceable person");
		Scanner sc = new Scanner(System.in);
		TreeOperation tree_op = new TreeOperation();
		int i=0;
		String name, spouse, parent, input_more="Y";;
		char gender=0;
		
		
			while("Y".equalsIgnoreCase(input_more))
			{
				System.out.print("Enter family member name: ");
				name = sc.nextLine();
				
				System.out.println("Enter gender (M/F): ");
				char g = sc.nextLine().charAt(0);
				
				if(g=='m' || g== 'M' || g=='f' || g=='F')
					gender= g;
				else
				{
				//there's no such thing as '' character. only workaround is either using Char wrapper class or using 0 ascii code that means null
					gender=0; 
					throw new WrongInput("wrong gender value");					
				}
				
				System.out.println("Enter spouse name (enter NA if not applicable): ");
				spouse = sc.nextLine();
							
				if(i==0)
				{
					FamilyMember fm = new FamilyMember(name,gender,spouse);		
					firstAncestor=fm;
				}
					
				else
				{
					System.out.print("Enter parent name: ");
					parent = sc.nextLine();
					FamilyMember fm = new FamilyMember(name,gender,spouse,parent);		
					//firstAncestor.add(fm);
				}
				

				
				System.out.println("Enter Y to input more else enter N");
				input_more = sc.nextLine();
				i++;
			}
		
		
		System.out.println("Family tree:-");
		TreeOperation.iterate(firstAncestor);	
		TreeOperation.iterate_file(firstAncestor);
	}

}
