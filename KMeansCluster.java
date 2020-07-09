
package kmeanscluster;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *DRIVER FILE<br>
 * Java code and OOP design by Jim D<br>
 *  
 * K-Means Cluster Algorithm by Jeff Heaton<br>
 * "Artificial Intelligence for Humans
 * Volume 1: Fundamental Algorithms," Heaton Research, Inc., 2013<br>
 * <br>
 * 
 *Implementation Terminology: 
 *A data vector is one data entry composed of 
 *one or more numeric observations(stored in an array), a label(string), and
 *cluster tag (integer value). The cluster tag is used to associate the data vector
 *with a particular cluster (grouping of similar data vectors). The term data 
 *vector should not be confused with the data structure named "vector".<br>
 * <br>
 *
 * To use a CSV file used with this program, each data entry should have the following format:<br>
 * <br>
 * observation1,observation2,...observationN,label<br>
 * <br>
 * 
 * The CSV file should NOT include a header.
 * 
 *@author Jim D, Java code and OOP design
 */

public class KMeansCluster {

    public static void main(String[] args) 
    {
        
       Scanner keyboard = new Scanner(System.in);
       
       System.out.print("Enter the file name: ");
       String fileName = keyboard.nextLine();
       
       System.out.print("Enter the number of observations in an individual data vector: ");
       int observations = keyboard.nextInt();
       DataVector.setNumObservations(observations);
       
       
       System.out.print("Enter the number of clusters desired: ");
       int clusters = keyboard.nextInt();
       Cluster.populateClusters(clusters);
        
       DataInOut dataIn = new DataInOut();
        
        try
        {
        dataIn.loadData(fileName);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("error opening file");
            System.exit(0);
        }

      
        System.out.println("\nUNSORTED DATA PLACED INTO RANDOM CLUSTERS:\n");
        DataVector.showDataVectorStorage();
        
        
        System.out.println("\n\n-----------------------------------------");
        
        Cluster.calculateCentroids();
       
        boolean moved = true;
        
       
//         DataVectors begin with a randomly assigned cluster. This loop refines
//         the cluster assignments based on a DataVector's proximity to the 
//         centroid (mean) of its current cluster. For each data vector in the
//         array list, the loop reassigns the data vector
//         to the cluster with nearest centroid, then recalculates all the centroids.
//         The loop continues until all data vectors are in clusters with nearest 
//         centroids and no further reassignments are made.
         
        while(moved)
         
        {
            moved = false;
            moved = DataVector.assignDataVectors();
            if(moved)
                Cluster.calculateCentroids();
        }
      

         System.out.println("-----------------------------------------\n");
         
         System.out.println("CLUSTERED DATA:\n");
         DataVector.showDataVectorStorageByCluster();
               
        
    }
    
}
