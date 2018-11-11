import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class machineLearning {

   final static int NUM_VARIAVEIS = 20;
   final static int NUM_TRAININGSETS = 65;

   public static double[] hipothesis(double [][] matrixOfX, double[] deltas){
      double[] hipothesis = new double[NUM_TRAININGSETS];
   
   //calculate an array of hipothesis
      for(int i = 0; i < matrixOfX.length; ++i) {
         for(int j = 0; j < matrixOfX[0].length; ++j) {
            hipothesis[i] = hipothesis[i] + (matrixOfX[i][j] * deltas[j]);
         }
      }
      return hipothesis;
   
   }

   public static double costFunction(double [] hipothesis, double[] deltas, double[] results) {
      double cost;
      double[] difference = new double[NUM_TRAININGSETS];
      double sum = 0;
   
   //calculate an array of the hipothesis minos the actual value
      for(int i = 0; i < hipothesis.length; ++i) {
         difference[i] = hipothesis[i] - results[i];
      }
   
   
   //calculate the square sum
      for(int i = 0; i < difference.length; i++) {
         sum = sum + Math.sqrt(difference[i]);
      }
   
   //calculate the cost
      cost = (1 / 40) * sum;
   
      return cost;
   
   }

   public static double gradientDescent (double[] hipothesis, double[] deltas, double[] results, int Xindex, double [][] matrixOfX){
   
      double newDelta = 0;
      double alpha = 0.00000001;
      double[] difference = new double[NUM_TRAININGSETS];
      double[] finalDifference = new double[NUM_TRAININGSETS];
      double finalDifferenceSum = 0;
   
   //calculate an array of the hipothesis minos the actual value
      for(int i = 0; i < hipothesis.length; ++i) {
         difference[i] = hipothesis[i] - results[i];
      }
   
   //multiply the difference by Xj
      for(int i = 0; i < hipothesis.length; ++i) {
         finalDifference[i] = difference[i] * matrixOfX[i][Xindex];
      }
   
   //sum of the final difference
      for(int i = 0; i < finalDifference.length; ++i) {
         finalDifferenceSum = finalDifferenceSum + finalDifference[i];
      }
   
   //update the delta
      newDelta = deltas[Xindex] - Math.abs(((alpha/NUM_TRAININGSETS) * finalDifferenceSum));
      
      while(Math.abs(newDelta) > NUM_VARIAVEIS) {
         newDelta = newDelta/10;
      }
         
   
      return newDelta;
   }
   
   
   public static void main(String[] args) throws IOException{
   
      FileInputStream fileByteStream = new FileInputStream("matrixofx.txt");
      FileInputStream fileByteStream2 = new FileInputStream("results.txt");
      FileInputStream fileByteStream3 = new FileInputStream("defaultValues.txt");
      FileInputStream fileByteStream4 = new FileInputStream("defaultResults.txt");
      Scanner scn = new Scanner(fileByteStream);
      Scanner scnResults = new Scanner(fileByteStream2);
      Scanner defaultscn = new Scanner(fileByteStream3);
      Scanner scnDefaultResults = new Scanner(fileByteStream4);
     
      Scanner scnUser = new Scanner(System.in);
      
      boolean same = true;
   
      double[][] matrixOfX = new double[NUM_TRAININGSETS][NUM_VARIAVEIS];
      
      double[][] matrixDefault = new double[NUM_TRAININGSETS][NUM_VARIAVEIS];
      
      double[] defaultResults = new double[NUM_TRAININGSETS];
   
      double[] deltas = new double[NUM_VARIAVEIS];
      
      double[] results = new double[NUM_TRAININGSETS];
      
      double[] newDeltas = new double[NUM_VARIAVEIS];
      
      double[] hipothesis = new double[NUM_TRAININGSETS];
      
      double differenceBetweenDeltas = 0;
   
   //Initializing deltas
      for(int i = 0; i < NUM_VARIAVEIS; ++i){
         deltas[i] = 1;
         newDeltas[i] = 0;
      }
   //Initializing x0 to 1
      for(int i = 0; i < NUM_TRAININGSETS; ++i) {
         matrixOfX[i][0] = 0;
         matrixDefault[i][0] = 0;
      }
   
      
   //Building the matrix of X
      for(int i = 0; i < NUM_TRAININGSETS; ++i) {
         for(int j = 1; j < NUM_VARIAVEIS; ++j) {
            matrixOfX[i][j] = scn.nextDouble();
            matrixDefault[i][j] = defaultscn.nextDouble();
         }
      }
        
   //fixing training sets
      for(int i = 0; i < NUM_TRAININGSETS; ++i) {
         matrixOfX[i][11] = matrixOfX[i][11]/10;
         matrixOfX[i][13] = matrixOfX[i][13]/10000;
         matrixOfX[i][15] = matrixOfX[i][15]/10;
         matrixOfX[i][16] = (matrixOfX[i][16]/matrixOfX[i][18]) * 10;
         matrixOfX[i][17] = (matrixOfX[i][17]/matrixOfX[i][18]) * 10;
         matrixOfX[i][18] = 0;
           
         matrixDefault[i][11] = matrixDefault[i][11]/10;
         matrixDefault[i][13] = matrixDefault[i][13]/10000;
         matrixDefault[i][15] = matrixDefault[i][15]/10;
         matrixDefault[i][16] = (matrixDefault[i][16]/matrixDefault[i][18]) * 10;
         matrixDefault[i][17] = (matrixDefault[i][17]/matrixDefault[i][18]) * 10;
         matrixDefault[i][18] = 0;
      }
   
   //Building the arrays of results
      for(int i = 0; i < NUM_TRAININGSETS; ++i) {
         results[i] = scnResults.nextDouble();
         defaultResults[i] = scnDefaultResults.nextDouble();
      }
        
   //finding the best deltas
      int itinerations = 0;
      while (itinerations < 100){
        
         hipothesis = hipothesis(matrixOfX, deltas);
         
         for(int i = 0; i < deltas.length; ++i) {
         
            newDeltas[i] = gradientDescent (hipothesis, deltas, results, i, matrixOfX);
         
         }
         
         for(int i = 0; i < newDeltas.length; ++i) {
            deltas[i] = newDeltas[i];
         }  
         itinerations = itinerations + 1;
          
      }
   
   
   //Begin to interact with the user
   
      double[] userX = new double[NUM_VARIAVEIS];
      String country = "";
      int countryNum = 0;
   
      userX[0] = 1;
   
      System.out.println("************ Welcome to NAME OF THE PROGRAM! **********");
   
      System.out.println("What country do you plan on analyzing?");
      country = scnUser.next();
   
      switch(country) {
         case "Albania":
            countryNum = 0;
            break;
         case "Algeria":
            countryNum = 1;
            break;
         case "Australia":
            countryNum = 2;
            break;
         case "Austria":
            countryNum = 3;
            break;
         case "Belgium":
            countryNum = 4;
            break;
         case "Brazil":
            countryNum = 5;
            break;
         case "China":
            countryNum = 6;
            break;
         case "Bulgaria":
            countryNum = 7;
            break;
         case "Canada":
            countryNum = 8;
            break;
         case "Chile":
            countryNum = 9;
            break;
         case "Colombia":
            countryNum = 10;
            break;
         case "Costa Rica":
            countryNum = 11;
            break;
         case "Croatia":
            countryNum = 12;
            break;
         case "Czech Republic":
         case "Czechia":
            countryNum = 13;
            break;
         case "Denmark":
            countryNum = 14;
            break;
         case "Estonia":
            countryNum = 15;
            break;
         case "Finland":
            countryNum = 16;
            break;
         case "France":
            countryNum = 17;
            break;
         case "Georgia":
            countryNum = 18;
            break;
         case "Germany":
            countryNum = 19;
            break;
         case "Greece":
            countryNum = 20;
            break;
         case "Hungary":
            countryNum = 21;
            break;
         case "Iceland":
            countryNum = 22;
            break;
         case "Indonesia":
            countryNum = 23;
            break;
         case "Ireland":
            countryNum = 24;
            break;
         case "Israel":
            countryNum = 25;
            break;
         case "Italy":
            countryNum = 26;
            break;
         case "Japan":
            countryNum = 27;
            break;
         case "Jordan":
            countryNum = 28;
            break;
         case "Korea":
         case "South Korea":
            countryNum = 29;
            break;
         case "Latvia":
            countryNum = 30;
            break;
         case "Lebanon":
            countryNum = 31;
            break;
         case "Lithuania":
            countryNum = 32;
            break;
         case "Luxemburg":
            countryNum = 33;
            break;
         case "Macau":
         case "Macao":
            countryNum = 34;
            break;
         case "Macedonia":
            countryNum = 35;
            break;
         case "Malta":
            countryNum = 36;
            break;
         case "Mexico":
            countryNum = 37;
            break;
         case "Moldova":
            countryNum = 38;
            break;
         case "Montenegro":
            countryNum = 39;
            break;
         case "Netherlands":
            countryNum = 40;
            break;
         case "New Zealand":
            countryNum = 41;
            break;
         case "Norway":
            countryNum = 42;
            break;
         case "Peru":
            countryNum = 43;
            break;
         case "Poland":
            countryNum = 44;
            break;
         case "Portugal":
            countryNum = 45;
            break;
         case "Puerto Rico":
            countryNum = 46;
            break;
         case "Qatar":
            countryNum = 47;
            break;
         case "Romania":
            countryNum = 48;
            break;
         case "Russia":
         case "Russian Federation":
            countryNum = 49;
            break;
         case "Singapure":
            countryNum = 50;
            break;
         case "Slovak Republic":
            countryNum = 51;
            break;
         case "Slovenia":
            countryNum = 52;
            break;
         case "Spain":
            countryNum = 53;
            break;
         case "Sweden":
            countryNum = 54;
            break;
         case "Switzerland":
            countryNum = 55;
            break;
         case "Thailand":
            countryNum = 56;
            break;
         case "Trintad and Tobago":
            countryNum = 57;
            break;
         case "Tunisia":
            countryNum = 58;
            break;
         case "Turkey":
            countryNum = 59;
            break;
         case "United Arab Emirades":
            countryNum = 60;
            break;
         case "United Kingdom":
         case "UK":
            countryNum = 61;
            break;
         case "United States":
         case "United States of America":
         case "USA":
         case "US":
            countryNum = 62;
            break;
         case "Uruguai":
            countryNum = 63;
            break;
         case "Vietnam":
            countryNum = 64;
            break;
         default:
            countryNum = -1;
            break;
      }
         
       
   
   
      System.out.println("What is the global gender gap index? For default values digit -1");
      userX[1] = scnUser.nextDouble();
      if(userX[1] == -1) {
         userX[1] = matrixDefault[countryNum][1];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the difference in science literacy among genders? For default values digit -1");
      userX[2] = scnUser.nextDouble();
      if(userX[2] == -1) {
         userX[2] = matrixDefault[countryNum][2];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the intra-individual science literacy difference among genders? For default values digit -1");
      userX[3] = scnUser.nextDouble();
      if(userX[3] == -1) {
         userX[3] = matrixDefault[countryNum][3];
      }
      else {
      same = false;
      }
      
      System.out.println("What is the gender difference in self-efficacy? For default values digit -1");
      userX[4] = scnUser.nextDouble();
      if(userX[4] == -1) {
         userX[4] = matrixDefault[countryNum][4];
      }
      else {
      same = false;
      }
      
      System.out.println("What is the gender difference in broad interest in science? For default values digit -1");
      userX[5] = scnUser.nextDouble();
      if(userX[5] == -1) {
         userX[5] = matrixDefault[countryNum][5];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the gender difference in enjoyment of science? For default values digit -1");
      userX[6] = scnUser.nextDouble();
      if(userX[6] == -1) {
         userX[6] = matrixDefault[countryNum][6];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the gender difference in mathematics? For default values digit -1");
      userX[7] = scnUser.nextDouble();
      if(userX[7] == -1) {
         userX[7] = matrixDefault[countryNum][7];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the gender difference in reading? For default values digit -1");
      userX[8] = scnUser.nextDouble();
      if(userX[8] == -1) {
         userX[8] = matrixDefault[countryNum][8];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the gender difference in intra-individual math scores? For default values digit -1");
      userX[9] = scnUser.nextDouble();
      if(userX[9] == -1) {
         userX[9] = matrixDefault[countryNum][9];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the gender difference in intra-individual reading scores? For default values digit -1");
      userX[10] = scnUser.nextDouble();
      if(userX[10] == -1) {
         userX[10] = matrixDefault[countryNum][10];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the unemployment rate? For default values digit -1");
      userX[11] = scnUser.nextDouble();
      if(userX[11] == -1) {
         userX[11] = matrixDefault[countryNum][11];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the fertility rate? For default values digit -1");
      userX[12] = scnUser.nextDouble();
      if(userX[12] == -1) {
         userX[12] = matrixDefault[countryNum][12];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the per capita GPA? For default values digit -1");
      userX[13] = scnUser.nextDouble();
      if(userX[13] == -1) {
         userX[13] = matrixDefault[countryNum][13];
      }
      else {
      same = false;
      }
      
      System.out.println("What percentage of the GPA is invested in eduation? For default values digit -1");
      userX[14] = scnUser.nextDouble();
      if(userX[14] == -1) {
         userX[14] = matrixDefault[countryNum][14];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the GINI index of inequality? For default values digit -1");
      userX[15] = scnUser.nextDouble();
      if(userX[15] == -1) {
         userX[15] = matrixDefault[countryNum][15];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the number of universities divided by the total population? For default values digit -1");
      userX[16] = scnUser.nextDouble();
      if(userX[16] == -1) {
         userX[16] = matrixDefault[countryNum][16];
      }
      else {
      same = false;
      }
   
      System.out.println("What is the number of universities amoung the top 5000 universities divided by the population? For default values digit -1");
      userX[17] = scnUser.nextDouble();
      if(userX[17] == -1) {
         userX[17] = matrixDefault[countryNum][17];
      }
      else {
      same = false;
      }
   
   //Calculare the hipothesis for the user
   
      double userHipothesis = 0;
   
      for(int g = 0; g < NUM_VARIAVEIS; ++g) {
         userHipothesis = userHipothesis + (deltas[g] * userX[g]);
      }
   
      userHipothesis = (-1)*(userHipothesis / 10000);
   
   //Find what the hipothesis means
      double meaningHypothesis = (userHipothesis * 100) / 3238.982;
   
   //Print result
   
      System.out.printf("The calculated percentage of women among STEM graduates is: %.2f \n", userHipothesis);
   
      
      if(same && countryNum != -1) {
      System.out.println("There is no observed change in the percentage of women amoung STEM graduates in your country");
      }
      else if(countryNum != -1) {
      double difference = userHipothesis - defaultResults[countryNum];
      System.out.printf("Your values caused a difference of %.2f percent \n", difference);
      }
      System.out.println();
      System.out.println("************ Thanks for using NAMEEE! **********");
   
   
   }
}