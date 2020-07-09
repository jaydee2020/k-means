/*
 *
 */
package kmeanscluster;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * 
 * This class is used to load data from a CSV file.<br>
 * 
 * Java code and OOP design by Jim D<br>  
 * K-Means Cluster Algorithm by Jeff Heaton<br>
 * "Artificial Intelligence for Humans
 * Volume 1: Fundamental Algorithms," Heaton Research, Inc., 2013<br>
 * 
 * 
 *@author J. D, Java code and OOP design
 * 
 */
public class DataInOut 
{
    Scanner inputStream = null;
        

    /**
     *Loads the data from the input file, creating a DataVector object 
     *with the observations and the label from each entry in the file. 
     *Each DataVector object is assigned a random Cluster (range based on NumberOfClusters
     *static variable in Cluster class). Each DataVector object is then stored
     *in the DataVector array list.
     * 
     * @param theFileName
     * @throws FileNotFoundException 
     */
    public void loadData(String theFileName) throws FileNotFoundException
    {
        inputStream =  new Scanner(new File(theFileName));
        int observationCount = DataVector.getNumObservations();
        while (inputStream.hasNextLine())
        {
            String aLine = inputStream.nextLine();
            String[] inputData = aLine.split(",");
        

            if(inputData.length != (observationCount + 1 ))
            {
              System.out.println("The number of observations in the CSV file"
                      + " does not match the user-enterd number!");
              System.exit(0);
            }
            
            double[] observations = new double[observationCount];
            
            for(int i = 0; i < observationCount; i++)
                observations[i]= Double.parseDouble(inputData[i]);
            
            int randomCluster = (int)(Cluster.getNumberOfClusters() * Math.random() + 1);
                       
            DataVector inputDataVector = new DataVector
                                (observations, inputData[observationCount], randomCluster);
          
            inputDataVector.setIntoStorage();
        }
        
        inputStream.close();
    }
}
