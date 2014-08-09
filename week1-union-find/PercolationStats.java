public class PercolationStats {
  int[][] grid;

  public PercolationStats(int n, int t){
    if(n <= 0 || t <= 0){
      throw new java.lang.IllegalArgumentException();
    }

    grid = new int[n][t];
  }
  public double mean() {
  }
  public double stddev(){
  }
  public double confidenceLo(){
  }
  public double confidenceHi(){
  }
  public static void main(String[] args){
  }
}

