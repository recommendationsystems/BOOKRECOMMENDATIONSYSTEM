package org.companyname.projectname.recommendapp;


import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.awt.Color;
import java.io.*;
import java.util.*;
 
class App {
 
  private App() {
  }
 
  public static void main(String[] args) throws Exception {
 
    DataModel  model = new FileDataModel(new File("data/dataset.csv"));  //load data from csv file 
    
    UserSimilarity EuclideanDistancesimilarity = new EuclideanDistanceSimilarity(model); //EuclideanDistance Similarity for recommendation . 
    UserSimilarity CityBlocksimilarity = new CityBlockSimilarity(model); //CityBlockdistance similarity(also known as Manhattan distance) for recommendation .
    UserSimilarity loglikesimilarity = new LogLikelihoodSimilarity(model); //log likelihood similarity for recommendation .
    UserSimilarity Tanimotosimilarity = new TanimotoCoefficientSimilarity(model);   //TanimotoCoefficient similarity(Also known as JaccardCoefficient) for recommendation . 
    UserSimilarity UncenteredCosinesimilarity = new UncenteredCosineSimilarity(model); //UncenteredCosine similarity for recommendation .
      
    UserNeighborhood EuclideanDistanceneighborhood =  new NearestNUserNeighborhood(2, EuclideanDistancesimilarity, model);  //
    UserNeighborhood CityBlockneighborhood =  new NearestNUserNeighborhood(2, CityBlocksimilarity, model);  //
    UserNeighborhood loglikeneighborhood =  new NearestNUserNeighborhood(2, loglikesimilarity, model);  //
    UserNeighborhood Tanimotoneighborhood =  new NearestNUserNeighborhood(2, Tanimotosimilarity, model); //
    UserNeighborhood UncenteredCosineneighborhood =  new NearestNUserNeighborhood(2, UncenteredCosinesimilarity, model); //


    Recommender EuclideanDistancerecommender = new GenericUserBasedRecommender(  model, EuclideanDistanceneighborhood, EuclideanDistancesimilarity); // creates a recommendation engine
    Recommender loglikerecommender = new GenericUserBasedRecommender(  model, loglikeneighborhood, loglikesimilarity); // creates a recommendation engine
    Recommender Tanimotorecommender = new GenericUserBasedRecommender(  model, Tanimotoneighborhood, Tanimotosimilarity);
    Recommender UncenteredCosinerecommender = new GenericUserBasedRecommender(  model, UncenteredCosineneighborhood, UncenteredCosinesimilarity);
    Recommender CityBlockrecommender = new GenericUserBasedRecommender(  model, CityBlockneighborhood, CityBlocksimilarity);
    
    
    List<RecommendedItem>recommendationsEuclideanDistance =   EuclideanDistancerecommender.recommend(55,1);
    List<RecommendedItem>recommendationsTanimoto =   Tanimotorecommender.recommend(55,1);
    List<RecommendedItem>recommendationsloglike =   loglikerecommender.recommend(55,1);
    List<RecommendedItem>recommendationsUncenteredCosine =   UncenteredCosinerecommender.recommend(55,1);
    List<RecommendedItem>recommendationsCityBlock =   CityBlockrecommender.recommend(55,1);
    /*one recommendation for user with ID 4 . In Mahout it always take Integer value i.e It will always take userId and number of item to be recommended */
  
    
    
    
    for (RecommendedItem recommendation : recommendationsEuclideanDistance ) {
        
    	System.out.println("EuclideanDistanceSimilarity is => " +recommendation);
      }
    
    for (RecommendedItem recommendation : recommendationsTanimoto ) {
      System.out.println("TanimotoCoefficientSimilarity(JaccardCoefficient) is  =>" +recommendation);
    }
    for (RecommendedItem   recommendation: recommendationsloglike ) {
        System.out.println("LogLikelihoodSimilarity is  => " +recommendation);}
       
    for (RecommendedItem recommendation : recommendationsUncenteredCosine ) {
        System.out.println("UncenteredCosineSimilarity is => " +recommendation);} 
      
  for (RecommendedItem recommendation : recommendationsCityBlock ) {
      System.out.println("CityBlockSimilarity(Manhattan distance) is => " +recommendation );} 
 
 

  { System.out.println("list is "+ ":" +'\n' +"{ "  +'\n'+recommendationsTanimoto +'\n' +recommendationsloglike +'\n' + recommendationsUncenteredCosine +'\n' +recommendationsCityBlock +'\n' +recommendationsEuclideanDistance +'\n'+"}" );} 
  
  
  }
    }
   
