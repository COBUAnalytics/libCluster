package cobu.analytics.clustering.kmeans;


import no.uib.cipr.matrix.DenseVector;
import no.uib.cipr.matrix.Vector;
import org.junit.Test;

import java.util.ArrayList;

public class KMClusterTest {
    @Test(expected = IllegalArgumentException.class)
    public void testInconsistentDimensionBetweenDataAndCentroids() {
        ArrayList<Vector> data = new ArrayList<Vector>();
        ArrayList<Vector> centroids = new ArrayList<Vector>();
        data.add(new DenseVector(new double[]{0.0, 1.0, 2.0}));
        centroids.add(new DenseVector(new double[]{0.0, 0.0}));
        new KMCluster(data, centroids);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInconsistentDimensionInData() {
        ArrayList<Vector> data = new ArrayList<Vector>();
        ArrayList<Vector> centroids = new ArrayList<Vector>();
        data.add(new DenseVector(new double[]{0.0, 0.0, 0.0}));
        data.add(new DenseVector(new double[]{0.0, 0.0}));
        centroids.add(new DenseVector(new double[]{0.0, 0.0, 0.0}));
        new KMCluster(data, centroids);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInconsistentDimensionInCentroids() {
        ArrayList<Vector> data = new ArrayList<Vector>();
        ArrayList<Vector> centroids = new ArrayList<Vector>();
        data.add(new DenseVector(new double[]{0.0, 0.0, 0.0}));
        data.add(new DenseVector(new double[]{0.0, 0.0, 0.0}));
        centroids.add(new DenseVector(new double[]{0.0, 0.0, 0.0}));
        centroids.add(new DenseVector(new double[]{0.0, 0.0}));
        new KMCluster(data, centroids);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroDimension() {
        ArrayList<Vector> data = new ArrayList<Vector>();
        ArrayList<Vector> centroids = new ArrayList<Vector>();
        data.add(new DenseVector(new double[]{}));
        data.add(new DenseVector(new double[]{}));
        centroids.add(new DenseVector(new double[]{}));
        centroids.add(new DenseVector(new double[]{}));
        new KMCluster(data, centroids);
    }

}
