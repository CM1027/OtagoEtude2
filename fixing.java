import java.io.*;
import java.util.*;
import java.util.Comparator;

public class fixing{
  public static void main(String[] args) throws FileNotFoundException{
    ArrayList<int[][]> input = new ArrayList<int[][]>(); //input array
    ArrayList<int[][]> results = new ArrayList<int[][]>();
    ArrayList<Integer> index = new ArrayList<Integer>();
    ArrayList<String> scanner = new ArrayList<String>();
    ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
    
    
    //scanning starts    
    //File filename = new File(args[0]);
    String s = "";
    int lineCount = 0;
    
    Scanner scan = new Scanner(System.in);
    while(scan.hasNextLine()){
      s =(scan.nextLine());
      scanner.add(s);
    }
    
    String[] ints = s.split("\\s");
    scan.close();
    
    //storing our values as a 2d array
    int arr[][] = new int[scanner.size()+1][ints.length];
    ArrayList<Integer> num = new ArrayList<Integer>();
    for(int f = 0; f < scanner.size(); f++){
      String[] n = scanner.get(f).split("\\s");
      if(n.length != ints.length){
        System.out.println("Bad format");
        System.exit(1);
      }else{
      for(int q = 0; q < n.length; q++){
        try{
          if(Integer.parseInt(n[q]) > 0 && Integer.parseInt(n[q]) <= scanner.size()){
        num.add(Integer.parseInt(n[q]));
        }else{
          System.out.println("Bad values");
          System.exit(1);
        }
        }catch(NumberFormatException e){
          System.out.println("Bad format");
          System.exit(1);
        }
      }
    }
    }
    int na = 0;
    for(int i = 0; i < arr.length-1; i++){
      for(int j = 0; j < arr[i].length; j++){
        if(na < num.size()){
          arr[i][j] = num.get(na);
          na++;
        }
      }
    }
    //end storage
    //end scanning
    
    
    //checking the data
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[i].length; j++){
          if(arr[i].length != scanner.size()+1){
            System.out.println("Bad format");
            System.exit(1);
          }
        }
      }
    
    //store original in list
    input.add(arr);
    //contains the currently active arrays
    ArrayList<int[][]> currentGeneration = new ArrayList<int[][]>(); //contains the currently active arrays
    currentGeneration.add(input.get(0));
    //test print (is it taking in inputs correctly)
//     for(int i = 0; i < input.size(); i++){
//     System.out.println(Arrays.deepToString(input.get(i)));
//     }
    
    //main loop starts
    for(int row = 0; row < scanner.size(); row++){
      ArrayList<int[][]> nextGeneration = new ArrayList<int[][]>();
      for(int[][] source : currentGeneration){ //for every array in current generation
        nextGeneration.addAll(valids(source, row));
      }
      currentGeneration = nextGeneration; //set active generation to the next generation
    }
    
    
    //if no duplicate values in final row add to result array
    for(int i = 0; i < currentGeneration.size(); i++){
      if((isUnique((currentGeneration.get(i)[currentGeneration.get(i).length-1]))) == true){
        results.add(currentGeneration.get(i));
      }
    }
    
    if(results.size() == 0){
      System.out.println("Inconsistent results");
      System.exit(1);
    }
    
    HashSet<ArrayList<String>> set = new HashSet<ArrayList<String>>();
    for(int nu = 0; nu < results.size(); nu++){
      ArrayList<String> list = new ArrayList<String>();
      for(int r = 0; r < results.get(nu).length; r++){
        for(int c = 0; c < results.get(nu)[r].length; c++){
          if(results.get(nu)[r][c] == 0){
            System.out.print("_ ");
          }else{
            System.out.print(results.get(nu)[r][c] + " ");
          } 
        }
        list.add(Arrays.toString(results.get(nu)[r]));
        Collections.sort(list);
        System.out.println();
      }
      set.add(list);
      System.out.println();
    }
    
    
    System.out.println("Different results: " +set.size());
    
  }
  
  public static ArrayList<int[][]> valids(int[][] source, int row){
    //returns all valid pushes 
    ArrayList<int[][]> result = new ArrayList<>();
    int dup = Duplicates(source[row]);
    if(dup < 0){
      //row is invalid return empty result
      return result;
    }
    //find where dup occurs and add all pushes to result
    for(int i = 0; i < source[row].length; i++){
      if(source[row][i] == dup){
        result.add(push(source, row, i));
      }
    }
    return result;
  }
  public static int Duplicates(int[]row){
    HashSet<Integer> values = new HashSet<>();
    int result = -1; 
    for(int value: row){
      if(value <= 0){
        return -1;
      }
      if(values.contains(value)){
        if(result > 0){
          return -1; //second duplicate so not valid
        }
        result = value;
      }else{
        values.add(value);
      }
    }
    return result;
  }
  public static int[][] push(int[][] source, int r, int c){
    //creates brand new array to mess with
    int[][] result = new int[source.length][source[0].length];
    for(int row = 0; row < source.length; row++){
      for(int col = 0; col < source[row].length; col++){
        result[row][col] = source[row][col];
      }
    }
    //push result
    if(result[source.length-1][c] == 0){
      //this can be pushed
      for(int p = source.length-1; p > r; p--){
        int now = result[p-1][c];
        result[p][c] = now;
      }    
      result[r][c] = 0;
    }
    return result;
  }
  
  //https://stackoverflow.com/questions/19593570/checking-for-duplicates-in-an-int-array
  public static boolean isUnique(int[] nums){
    Set<Integer> set = new HashSet<>();
    for(int i = 0; i < nums.length; i++){
    }
    for (int a : nums) {
      if (!set.add(a))
        return false;
    }
    
    return true;
  }
}
