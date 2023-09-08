package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {

    //Adjust the ratedCapacities of the battery as required
    int ratedCapacities = 120;
    CountsBySoH counts = new CountsBySoH();
    int SoH[] = new int[presentCapacities.length];


    for (int i = 0; i < presentCapacities.length; i++) {

      if(ratedCapacities != 0)
      //Calculate SoH%
        SoH[i] = (presentCapacities[i] * 100) / ratedCapacities;
      else
        return counts;

      //Classify the batteries into classes of healthy, exchange and failed
      if(SoH[i] > 80)
        counts.healthy++;
      else if(SoH[i] > 65 && SoH[i] <= 80)
        counts.exchange++;
      else
        counts.failed++;
    }

    return counts;
  }

  static void testBucketingByHealth() {

    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {115, 118, 80, 95, 91, 77};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    
    //Test cases to catch asserion error
    try{
      assert(counts.healthy == 2);
      assert(counts.exchange == 3);
      assert(counts.failed == 1);
      System.out.println("Done counting :)\n");
      
    }catch(AssertionError e){
      System.out.println("Test failed :(");
      return;
    }
    

    //Print the final count
    System.out.println("Number of healthy batteries: " + counts.healthy);
    System.out.println("Number of batteries for exchange: " + counts.exchange);
    System.out.println("Number of failed batteries: " + counts.failed);

  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}