import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
/*******************************************************
 * PROGRAMMERS: Kevin Hernandez & Kimberly Pereyra
 * UNIVERSITY: Inter-american of Puerto Rico, Bayamon
 * CLASS: Advanced Programming
 * PROFESSOR: Jaime Yeckle Sanchez
 * DATE: MAY 8, 2018
 *******************************************************/

public class Update
{    
    static void modifyFile(String oldString, String newString)
    {
        File fileToBeModified = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\Inventory.txt");
        String oldContent = "";         
        BufferedReader reader = null;         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent
             
            String newContent = oldContent.replaceFirst(oldString, newString);
             
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    static void changePass(String oldString, String newString)
    {
        File fileToBeModified = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\Users.txt");
        String oldContent = "";         
        BufferedReader reader = null;         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent
             
            String newContent = oldContent.replaceFirst(oldString, newString);
             
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
}