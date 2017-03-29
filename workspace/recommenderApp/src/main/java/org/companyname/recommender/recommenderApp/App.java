package org.companyname.recommender.recommenderApp;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import java.io.*;
import java.io.File;

 class App 
{
    private App(){}
	 public static void main( String[] args )throws Exception 
    {
        DataModel model = new FileDataModel(new File("data/data.csv"));  //load data from file 
        UserSimilarity EuclideanDistancesimilarity = new EuclideanDistanceSimilarity(model); //EuclideanDistance similarity for recommendation .
        UserSimilarity loglikesimilarity = new LogLikelihoodSimilarity(model); //loglikelihood similarity for recommendation .
        UserSimilarity Tanimosimilarity = new TanimotoCoefficientSimilarity(model);   //Also known as JaccardCoefficientSimilarity for recommendation . 
        UserSimilarity PearsonCorrelationsimilarity = new PearsonCorrelationSimilarity(model); //PearsonCorrelation similarity for recommendation .

     
     UserNeighborhood EuclideanDistanceneighborhood =  new NearestNUserNeighborhood(2, EuclideanDistancesimilarity, model);  //Define a group of user most similar to a given user . 2 define a group of 2 user having most similar preference
     UserNeighborhood loglikeneighborhood =  new NearestNUserNeighborhood(2, loglikesimilarity, model);  //Define a group of user most similar to a given user . 2 define a group of 2 user having most similar preference
     UserNeighborhood Tanimoneighborhood =  new NearestNUserNeighborhood(2, Tanimosimilarity, model);
     UserNeighborhood PearsonCorrelationneighborhood =  new NearestNUserNeighborhood(2, PearsonCorrelationsimilarity, model);

    
     Recommender loglikerecommender = new GenericUserBasedRecommender(  model, loglikeneighborhood, loglikesimilarity); // creates a recommendation engine
     Recommender Tanimorecommender = new GenericUserBasedRecommender(  model, Tanimoneighborhood, Tanimosimilarity);
     Recommender PearsonCorrelationrecommender = new GenericUserBasedRecommender(  model, PearsonCorrelationneighborhood, PearsonCorrelationsimilarity);
     Recommender EuclideanDistancerecommender = new GenericUserBasedRecommender(  model, EuclideanDistanceneighborhood, EuclideanDistancesimilarity);
     
     
     List<RecommendedItem>recommendationsTanimo =   Tanimorecommender.recommend(87, 1);
     List<RecommendedItem>recommendationsloglike =   loglikerecommender.recommend(87, 1);
     List<RecommendedItem>recommendationsPearsonCorrelation =   PearsonCorrelationrecommender.recommend(87, 1);
     List<RecommendedItem>recommendationsEuclideanDistance =   EuclideanDistancerecommender.recommend(87, 1);
     /*one recommendation for user with ID 4 . In Mahout it always take Integer value i.e It will always take userId and number of item to be recommended */
   
     
     for (RecommendedItem recommendation : recommendationsTanimo ) {
       System.out.println("TanimotoCoefficientSimilarity is " +recommendation);
     }
     for (RecommendedItem recommendation : recommendationsloglike ) {
         System.out.println("LogLikelihoodSimilarity is " +recommendation);}
        
     for (RecommendedItem recommendation : recommendationsPearsonCorrelation ) {
         System.out.println("PearsonCorrelationSimilarity is " +recommendation);} 
       
   for (RecommendedItem recommendation : recommendationsEuclideanDistance ) {
       System.out.println("EuclideanDistanceSimilarity is " +recommendation);} 
     }
         }
  

 }
	
    	
    	
    }
}
