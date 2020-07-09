
package kmeanscluster;
import java.util.ArrayList;

/**
 * This class handles the clusters, which are used to group similar DataVectors.<br>
 * 
 * Java code and OOP design by Jim D<br>  
 * K-Means Cluster Algorithm by Jeff Heaton<br>
 * "Artificial Intelligence for Humans
 * Volume 1: Fundamental Algorithms," Heaton Research, Inc., 2013<br>
 * 
 * @author Jim D, Java code and OOP design
 * 
 * 
 * 
 */
public class Cluster 
{
    //instance variable
    private DataVector centroid; //The centroid (mean) of each cluster is itself
                                    //a DataVector object.
    
   //static variables
    private static int numberOfClusters = 0;
    private static ArrayList<Cluster> clusterStorage = new ArrayList<Cluster>();
    
    
    
    public Cluster()
    {
        centroid = new DataVector();
    }
    
    /**
     * setNumberOfCluster method set the number of clusters used to group similar DataVector objects.
     * Clusters must be greater than zero.
     * @param numClusters representing the user input for number of clusters desired
     */
    public static void setNumberOfCluster(int numClusters)
    {
        if(numClusters > 0)
        numberOfClusters = numClusters;
        else
        {
            System.out.println("Clusters must be greater than 0!");
                   
            System.exit(0);
        }
    }
    
    /**
     * 
     * @return the number of clusters
     */
    public static int getNumberOfClusters()
    {
        return numberOfClusters;
    }
    
    
    /**
     * populateClusters method takes a parameter for the user-desired number of clusters.
     * Clusters must be greater than zero. After setting the <b>numberOfClusters</b> static variable to
     * numClusters, this method creates a corresponding number of new Cluster objects and adds each one
     * to the <b>clusterStorage</b> ArrayList.
     * @param numClusters 
     */
    public static void  populateClusters(int numClusters)
    {
        if(numClusters > 0)
        numberOfClusters = numClusters;
        else
        {
            System.out.println("Clusters must be greater than 0!");
            System.exit(0);
        }
        
        for (int i = 1; i <= numClusters; i++)
        {
            Cluster aCluster = new Cluster();
            aCluster.centroid.setClusterTag(i);
            clusterStorage.add(aCluster);
        }
    }
    
    /**
     *Calculates the centroid (average value of the DataVector objects) of each Cluster 
     *in the clusterStorage ArrayList based on all the DataVector objects currently 
     *associated with each respective cluster. The new centroid values are updated 
     *for the clusters in the ArrayList.
     */
    public static void calculateCentroids()
    {
        int numberOfObservations = DataVector.getNumObservations();
        int numberOfDataVectors = DataVector.getDataVectorStorageSize();
        
        //For each Cluster object in clusterStorage, for each DataVector object in
        //dataVectorStorage, if the DataVector is assigned to the Cluster, add the
        //value of its observations to a running total for each observation.
        //When done, calculate the average of each observation total, then update
        //the Cluster instance variable centroid with these average values.
        for(int i = 0; i < numberOfClusters; i++)
        {
            int clusterNumber = i + 1;
            int vectorsInCluster = 0;
            double[] observationsSums = new double[numberOfObservations];
            //zero out all elements of observationsSum array
            for(int a = 0; a < numberOfObservations; a++)
            {
                observationsSums[a]=0.0;
            }
            
            for(int j = 0; j < numberOfDataVectors; j++)
            {
                if(DataVector.getClusterTag(j) == clusterNumber)
                {
                    double[] copyObservations = DataVector.getObservations(j);
                    for(int k = 0 ; k < numberOfObservations; k++)
                    {
                        observationsSums[k] += copyObservations[k];
                        
                    }
                    vectorsInCluster++;
                }
            }
            
            double[] observationsAverage = new double[numberOfObservations];
            for(int m = 0; m < numberOfObservations; m++)
            {
                observationsAverage[m] = observationsSums[m] / vectorsInCluster;
            }
            
            clusterStorage.get(i).centroid.setObservations(observationsAverage);
            
        }
    }
    
    /**
     * showClusters method prints all the variables of the centroid for all the
     * Cluster in the clusterStorage ArrayList.
     */
    public static void showClusters()
    {
        for(int i = 0; i < numberOfClusters; i++)
        {
            clusterStorage.get(i).centroid.showDataVector();
        }
    }
    
    /**
     * showOneCluster method prints all the variables of the centroid for one
     * Cluster in the clusterStorage ArrayList, corresponding to the index
     * value passed to it. 
     * @param arrIndex 
     */
    public static void showOneCluster(int arrIndex)
    {
        
            clusterStorage.get(arrIndex).centroid.showDataVector();
        
    }
    
    
    /**
     * showObservationForOneCentroid method prints just the observations of the centroid
     * for one Cluster in the clusterStorage ArrayList, corresponding to the index
     * value passed to it.
     * @param arrIndex 
     */
    public static void showObservationsForOneCentroid(int arrIndex)
    {
        clusterStorage.get(arrIndex).centroid.showObservations();
    }
    
    /**
     *getCentroid returns the centroid variable (DataVector object) for a Cluster
     * in the clusterStorage ArrayList, corresponding to the index value passed to it.
     * @param arrIndex
     * @return 
     */
    public static DataVector getCentroid(int arrIndex)
    {
        return clusterStorage.get(arrIndex).centroid;
    }
     
}


