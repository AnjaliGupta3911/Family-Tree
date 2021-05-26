import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WriteToFile {

	void writingToFile() 
	{
		try
		{
			FileWriter my_file = new FileWriter("family_tree.txt");
			my_file.write("yay");
			
			my_file.close();
		}
		catch(Exception e)
		{
			System.out.println("exception thrown in file");
		}
		
		
	}

}
