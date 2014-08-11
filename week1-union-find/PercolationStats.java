public class PercolationStats {
  private double[] results;
  private int totalRuns;
  private int size;

  public PercolationStats(int n, int t){
    if(n <= 0 || t <= 0){
      throw new java.lang.IllegalArgumentException();
    }

    size = n;
    totalRuns = t;
    results = new double[t];

    run();
  }

  public double mean() {
    return StdStats.mean(results);
  }
  public double stddev(){
    return StdStats.stddev(results);
  }
  public double confidenceLo(){
  }
  public double confidenceHi(){
  }

  private void run(){
    for(int run = 0; run < totalRuns; run++){
      Percolation perc = new Percolation(size * size);
      int count = 0;

      while(!perc.percolates()){
        int x = StdRandom.uniform(1,size);
        int y = StdRandom.uniform(1,size);

        perc.open(x, y);
        count++;
      }

      results[run] = count;
    }
  }

  public static void main(String[] args){
    int n = args[1];
    int t = args[2];

    PercolationStats stats = new PercolationStats(n, t);

    StdOut.println("mean: " + stats.mean());
    StdOut.println("stddev: " + stats.stddev());
    StdOut.println("95% confidence interval: " + stats.confidenceLo(), + ", " + stats.confidenceHi());
  }
}

