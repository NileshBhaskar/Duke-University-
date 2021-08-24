import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of BabyName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyName {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            System.out.println("Name "+rec.get(0)+
            " Gender "+rec.get(1) +
            " Num Born "+rec.get(2));
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
                totalBoys += numBorn;
            }
            else{
                totalGirls += numBorn;
            }
        }
        System.out.println("Total births = "+totalBirths);
        System.out.println("Total girls = "+totalGirls);
        System.out.println("Total boys = "+totalBoys);
    }
    
    public void testTotalBirhs(){
        //FileResource fr = new FileResource();
        //totalBirths(fr);
        //whatIsNameInYear("Isabella",2012,2014,"F");
        //System.out.println(yearOfHighestRank("Mason","M"));
        //System.out.print("%0.2f"+getAverageRank("Jacob","M"));
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
        //System.out.println(getRank(1971,"Frank","M"));
    }
    
    public int getRank(int year,String name,String gender){
        FileResource fr = new FileResource();
        int rank = 0;
        boolean flag = false;
        
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender))
            {
                rank++;
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year,int rank,String gender){
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            rank--;
            if(rank==0){
                return rec.get(0);
            }
        }
        return "Not Found";
    }
    
    
    
    public void whatIsNameInYear(String name,int year,int newYear,String gender){
            int rank = 0; 
          rank = getRank(year,name,gender);
          FileResource fr = new FileResource();
          for(CSVRecord rec : fr.getCSVParser(false)){
               if(rec.get(1).equals(gender)){
                   rank--;
                   if(rank==0){
                       System.out.println(name+" born in "+year+" would be "+rec.get(0)+" if she was born in "+newYear);
                   }
               }
          }
    }
    
    public int yearOfHighestRank(String name,String gender){
        int finalRank = Integer.MAX_VALUE;
        int year = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int rank = 0;
            FileResource fr = new FileResource(f); 
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(1).equals(gender))
                {
                    rank++;
                    if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                        break;
                    }
                }
            }
            if(finalRank>rank){
                finalRank = rank;
                String str = String.valueOf(f);
                String temp = "";
                for(int i=0;i<str.length();i++){
                   if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
                       temp += String.valueOf(str.charAt(i));
                   }
                }
                year = Integer.parseInt(temp);
            }
        }
        return year;
    }
    
    public double getAverageRank(String name, String gender){
        double sum = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            count++;
            int rank = 0;
            FileResource fr = new FileResource(f); 
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(1).equals(gender)){
                    rank++;
                    if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                        break;
                    }
                }
            }
            sum += rank;
        }
        return sum/count;
    }
    
    public int rank(FileResource fr,String name,String gender){
            int rank = 0;
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(1).equals(gender)){
                    rank++;
                    if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                        return rank;
                    }
                }
            }
            return rank;
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        FileResource fr = new FileResource();
        int r = rank(fr,name,gender);
        int count = 0;
        System.out.println(r);
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(!rec.get(0).equals(name)&&rec.get(1).equals(gender) &&  rank(fr,rec.get(0),rec.get(1))<=r){
                count+=Integer.parseInt(rec.get(2));
            }
        }
        
        return count;
    }
    
}
