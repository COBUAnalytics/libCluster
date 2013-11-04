package cobu.analytics.clustering.kmeans;

import no.uib.cipr.matrix.Vector;

import java.util.ArrayList;

public class KMCluster {
    private final int dimension;
    private final ArrayList<Vector> data;
    private final ArrayList<Vector> centroids;
    private ArrayList<Double> distanceSquaredToNearestCluster;
    private ArrayList<Integer> closestCluster;

    public KMCluster(ArrayList<Vector> data,ArrayList<Vector> centroids){
        this.data = data;
        this.centroids= new ArrayList<Vector>(centroids);
        checkDimensionalConsistency(data,centroids);
        dimension=data.iterator().next().size();
        if(dimension == 0){
            throw new IllegalArgumentException("Dimension must be greater than 0");
        }
    }

    private void checkDimensionalConsistency(ArrayList<Vector> data, ArrayList<Vector> centroids) {
        checkForConstantDimension(data);
        checkForConstantDimension(centroids);
        if(data.iterator().next().size()!=centroids.iterator().next().size()){
            throw new IllegalArgumentException("Data and centroid dimensions must be equal.");
        }
    }

    private void checkForConstantDimension(ArrayList<Vector> data) {
        int dimension = data.iterator().next().size();
        for(Vector v: data){
            if(dimension==0){
                throw new IllegalArgumentException("Cannot accept 0-dimensional data.");
            }else{
                if(dimension!=v.size()){
                    throw new IllegalArgumentException("Dimension must be constant in data set.");
                }
            }
        }
    }
}
