
package kmeanscluster;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *This class handles data vectors, which are data entries composed of 
 *one or more numeric observations(stored in an array), a label(string), and
 *cluster tag (integer value). The cluster tag is used to associate the data vector
 *with a particular cluster (a grouping of similar data vectors). The term data 
 *vector should not be confused with the data structure named "vector".<br>
 * 
 * 
 * Each entry in a CSV file used with this program should have the following format:<br>
 * observation1,observation2,...observationN,label<br>
 * <br>
 * 
 * 
 * Java code and OOP design by Jim D<br>  
 * K-Means Cluster Algorithm by Jeff Heaton<br>
 * "Artificial Intelligence for Humans
 * Volume 1: Fundamental Algorithms," Heaton Research, Inc., 2013
 * 
 * @author Jim D, Java code and OOP design
 */

public class DataVector 
{
    //static variables-----------------------
    private static int numberOfObservations = 0;
    private static ArrayList<DataVector> dataVectorStorage = new ArrayList<DataVector>();
    
    //instance variables---------------------
    private double[] observations;
    private String label;
    private int clusterTag;
    
    
   public DataVector()
    {
        if(numberOfObservations > 0)
        observations = new double[numberOfObservations];
        else
        {   Scanner keyboard = new Scanner(System.in);
            while(numberOfObservations <= 0 )
            {
                System.out.println("Number of observations must be greater than zero."
                        + " Enter number of observations: ");
                numberOfObservations = keyboard.nextInt();
            }
            observations = new double[numberOfObservations];
        
        }
            
    }
    
    public DataVector(double[] theObservations, String theLabel, int theClusterTag)
    {
        if(numberOfObservations > 0)
        observations = new double[numberOfObservations];
        else
        {   Scanner keyboard = new Scanner(System.in);
            while(numberOfObservations <= 0 )
            {
                System.out.println("Number of observations must be greater than zero."
                        + " Enter number of observations: ");
                numberOfObservations = keyboard.nextInt();
            }
            
            observations = new double[numberOfObservations];
       
        }
        
        setObservations(theObservations);
        label = theLabel;
        clusterTag = theClusterTag;
            
    }
    
    //--------------Public Static Methods------------------------------------
    
    

    /**
     *setNumObservations method sets the static variable for number of observations according to the 
     *integer value passed as the argument. The DataVector constructor uses this 
     *static variable to set the size of observation array. Observations are the 
     *numeric data stored in each DataVector object. 
     * @param numObservations 
     */
    public static void setNumObservations(int numObservations)
    {
        numberOfObservations = numObservations;
        
        if(numObservations > 0)
        numberOfObservations =  numObservations;
        else
        {   Scanner keyboard = new Scanner(System.in);
            while(numObservations <= 0 )
            {
                System.out.println("Number of observations must be greater than zero."
                        + " Re-enter number of observations: ");
                numObservations = keyboard.nextInt();
            }
            numberOfObservations =  numObservations;
        }
    }
    
       

    /**
     *setClusterTag method sets the variable used to indicate the cluster 
     *associated with a DataVector object currently stored in the array list. 
     *The first parameter is the cluster number, the second parameter is the 
     *array list index of the DataVector object. 
     * @param inputClusterTag
     * @param arrIndex 
     */
    public static void setClusterTag(int inputClusterTag, int arrIndex)
    {
        dataVectorStorage.get(arrIndex).clusterTag = inputClusterTag;
    }
    
    
    
    /**
     *Returns the cluster number associated with a DataVector object that
     *is currently stored in the array list. The parameter is the
     *array list index of the DataVector object.
     * @param arrIndex
     * @return 
     */
    public static int getClusterTag(int arrIndex)
    {
        return dataVectorStorage.get(arrIndex).clusterTag; 
    }
    
    
             
    /** 
     * Returns an array of the observations for a DataVector object that is
     *currently stored in the dataVecctorStorage ArrayList. The parameter is the
     *ArrayList index of the DataVector object.
     * 
     * @param arrIndex
     * @return 
     */
    public static double[] getObservations(int arrIndex)
    {
        return dataVectorStorage.get(arrIndex).observations; 
    }
    
    
    /**
     *Displays the observations, label, and cluster number for every
     *DataVector object in the ArrayList.
     */
    public static void showDataVectorStorage()
    {
        for(int i = 0; i < dataVectorStorage.size(); i++)
        {
            dataVectorStorage.get(i).showDataVector();
        }
    }
    
    
    
    /**
     * Returns the size of the dataVectorStorage ArrayList.
     * @return 
     */
    public static int getDataVectorStorageSize()
    {
        return dataVectorStorage.size();
    }
    
    
             
    /**
     *Returns the value of the static variable used to govern the size of
     *the array holding the observations for each DataVector object.
     * @return 
     */
    public static int getNumObservations()
    {
        return numberOfObservations;
    }
   

    /**
     *Progresses through each DataVector object stored in the array list.
     *The distance is calculated between each DataVector and the centroid of
     *each cluster stored in the cluster array list. The DataVector cluster tag 
     *is updated to the cluster with the shortest distance, or the tag is not 
     *changed if the current cluster is the nearest. If at least one cluster 
     *tag has been changed for the entire array list, the value true is returned. 
     *If no cluster tags have been changed (meaning all DataVectors are already 
     *grouped in the cluster with the respectively nearest centroid), false is 
     *returned.
     * @return 
     */
    public static boolean assignDataVectors()
    {
        boolean moved = false;
        
        for(int i = 0; i < dataVectorStorage.size(); i++)
        {
            int currentCentroid = dataVectorStorage.get(i).clusterTag;
            
            int nearestCentroid = 1;            
            double nearestDistance = dataVectorStorage.get(i).calculateDistance(Cluster.getCentroid(0));
            
            for(int j = 1; j < Cluster.getNumberOfClusters(); j++)
            {
                double distance = dataVectorStorage.get(i).calculateDistance(Cluster.getCentroid(j));
                if (distance < nearestDistance)
                {
                    nearestDistance = distance;
                    nearestCentroid = j + 1;
                }
            }
            
            if (currentCentroid != nearestCentroid)
            {
                dataVectorStorage.get(i).clusterTag = nearestCentroid;
                moved = true;
            }
            
        }
               
        return moved;
    }

    
    /**
     *Displays observations, label, and cluster number for each DataVector 
     *object stored in the ArrayList. The display is grouped according to 
     *the cluster tags for each object.
     */    
     public static void showDataVectorStorageByCluster()
    {
         for (int i = 1; i <= Cluster.getNumberOfClusters(); i++)
         {
            System.out.println("CLUSTER " + i + "\nCentroid: ");
            Cluster.showObservationsForOneCentroid(i-1);
            System.out.println();
            
               for(int j = 0; j < dataVectorStorage.size(); j++)
               {
                   if(dataVectorStorage.get(j).clusterTag == i)
                   {
                       dataVectorStorage.get(j).showDataVector();
                   }
               }
               
             System.out.println("\n");

         }
    
    }
    
    
    
    //---------------Public Methods-----------------------------------
        
         
    /**
     *Sets the label for a DataVector object.
     * @param inputLabel 
     */
    public void setLabel(String inputLabel)
    {
        label= inputLabel;
    }
     
    
    /**
     *Sets the variable used to indicate the cluster associated with 
     *a DataVector object.
     * @param inputClusterTag 
     */
    public void setClusterTag(int inputClusterTag)
    {
        clusterTag = inputClusterTag;
    }

    
    /**
     * Returns the cluster number associated with a DataVector object.
     * @return 
     */
    public int getClusterTag()
    {
        return clusterTag;
    }
    

     
    /**
     *Sets the observations for a DataVector object to the values 
     *in the array passed as an argument.
     * @param inputObservations 
     */
    public void setObservations(double[] inputObservations)
    {
        for(int i = 0 ; i < numberOfObservations ; i++)
        {
            observations[i] = inputObservations[i];
        }
        
    }
    
      
    /**
     *Adds the DataVector object to the dataVectorStorage ArrayList.
     */
    public void setIntoStorage()
    {
        dataVectorStorage.add(this);
    }
    
    
    /**
     *Displays just the observations for a DataVector object. 
     */
    public void showObservations()
    {
        for(int i = 0; i <numberOfObservations; i++)
        {
            System.out.printf("%.4f",observations[i]);
            if(i!=numberOfObservations - 1)
                System.out.print(", ");
        }
        
        System.out.println();
    }
    
    /**
     * Displays the observations, label, and cluster number for a DataVector object.
     */
    public void showDataVector()
    {
        for(int i = 0; i <numberOfObservations; i++)
        {
            System.out.print(observations[i]);
            System.out.print(", ");
        }
        
        System.out.print(label + ", Cluster " + clusterTag);
        System.out.println();
    }
        

    /**
     * Returns the Euclidian distance between two DataVector objects.
     * @param vector2
     * @return 
     */
    public double calculateDistance(DataVector vector2)
    {
        double sum = 0.0;
        
        for(int i = 0; i < numberOfObservations; i++)
            sum += Math.pow((observations[i] - vector2.observations[i]), 2);
        
        return Math.sqrt(sum);
    }
    
   
   
    
}
