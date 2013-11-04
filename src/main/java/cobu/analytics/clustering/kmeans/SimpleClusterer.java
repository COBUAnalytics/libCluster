package cobu.analytics.clustering.kmeans;

import no.uib.cipr.matrix.Vector;

import java.util.ArrayList;

public class SimpleClusterer {
    private final int dimension;
    private final ArrayList<Vector> data;
    private final ArrayList<Vector> centroids;
    private double[] distanceToNearestCentroid;
    private int[] nearestCentroid;

    public SimpleClusterer(ArrayList<Vector> data, ArrayList<Vector> centroids){
        this.data = data;
        this.centroids= new ArrayList<Vector>(centroids);
        checkDimensionalConsistency(data,centroids);
        dimension=data.iterator().next().size();
        if(dimension == 0){
            throw new IllegalArgumentException("Dimension must be greater than 0");
        }
        distanceToNearestCentroid = new double[data.size()];
        nearestCentroid = new int[data.size()];
        findNearestCentroid();
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

    private double distance(Vector datum,Vector centroid){
        double sum=0.0;
        for(int i=0;i< datum.size();i++){
            double d=datum.get(i)-centroid.get(i);
            sum+=d*d;
        }
        return Math.sqrt(sum);
    }

    private void findNearestCentroid(){
        for(int d = 0;d<data.size();d++){
            double distance = Double.MAX_VALUE;
            int cluster = -1;
            for(int c=0;c<centroids.size();c++){
                double x=distance(data.get(d),centroids.get(c));
                if(x<distance){
                    cluster=c;
                    distance=x;
                }
            }
            this.distanceToNearestCentroid[d]=distance;
            this.nearestCentroid[d]=cluster;
        }
    }

    public double[] getDistanceToNearestCentroid() {
        return distanceToNearestCentroid;
    }

    public int[] getNearestCentroid() {
        return nearestCentroid;
    }
}
