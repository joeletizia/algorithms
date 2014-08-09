public class Percolation {
  int grid[];
  private static final int BLOCKED = 0;
  private static final int FULL_OPEN = 1;
  private static final int EMPTY_OPEN = 2;
  private static int ROW_LENGTH;

  private WeightedQuickUnionUF quickUnion;

  public Percolation(int n){
    ROW_LENGTH = n;
    grid = new int[ROW_LENGTH*ROW_LENGTH];

    for(int i = 0; i < ROW_LENGTH*ROW_LENGTH; i++) {
      grid[i] = BLOCKED;
    }

    quickUnion = new WeightedQuickUnionUF(n);
  }

  /*
   * To open a cell basically means to connect it to its adjacent cells open cells.
   */
  public void open(int i, int j){
    grid[location(i,j)] = EMPTY_OPEN;

    connectToAdjacentOpen(i,j);
  }
  public boolean isOpen(int i, int j){
    boundsCheck(i, j);

    return grid[location(i, j)] != BLOCKED;
  }

  public boolean isFull(int i, int j){
    boundsCheck(i, j);

    return grid[location(i,j)] == FULL_OPEN;
  }

  /*
   * true if there is a full site in the bottom row
   */
  public boolean percolates(){
    for(int i = 0; i < ROW_LENGTH ; i++){
      for(int j = ROW_LENGTH; j > 0; j--){
        if(quickUnion.connected(grid[i], grid[j)]){
          return true;
        }
      }
    }

    return false;
  }

  private void connectToAdjacentOpen(int i, int j){
    if(isOpenAndValid(i+1, j)){
      quickUnion.union(location(i,j), location(i+1, j));
    }
    if(isOpenAndValid(i-1, j)){
      quickUnion.union(location(i,j), location(i-1, j));
    }
    if(isOpenAndValid(i, j+1)){
      quickUnion.union(location(i,j), location(i, j+1));
    }
    if(isOpenAndValid(i, j-1)){
      quickUnion.union(location(i,j), location(i, j-1));
    }
  }

  private boolean isOpenAndValid(int i, int j){
    try{
      if(isOpen(i,j)){
        return true;
      }
      return false;
    }
    catch(IndexOutOfBoundsException e) {
      return false;
    }
  }

  private int location(int i, int j){
    return ((i-1) * ROW_LENGTH) + (j-1);
  }

  private void boundsCheck(int i, int j){
    if(i > ROW_LENGTH || j > ROW_LENGTH || i < 0 || j < 0){
      throw new IndexOutOfBoundsException();
    }
  }

  public static void main(String[] args) {
    Percolation perc = new Percolation(5);

    assert perc.location(1,2) == 1;
    assert perc.location(5,5) == 24;

    assert perc.isOpen(1,2) == false;
    perc.open(1,2);
    assert perc.isOpen(1,2) == true;
    assert perc.percolates() == false;

    StdOut.println("Tests Passed!");
  }
}
