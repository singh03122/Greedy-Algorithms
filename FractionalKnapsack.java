import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    public static double getMaxVal(ItemValue[] arr, int capacity) {

        // we have to sort array , after taking ratio of it
        Arrays.sort(arr, new Comparator<ItemValue>() {

            @Override
            public int compare(ItemValue item1, ItemValue item2) {
                double cpr1 = Double.valueOf(item1.profit / item1.weight);
                double cpr2 = Double.valueOf(item2.profit / item2.weight);

                if (cpr1 < cpr2)
                    return 1;
                else
                    return -1;

            }
        }); // sorting after ration in the decending order

        double totalValue = 0;
        for (ItemValue i : arr) {
            int curWeight = (int) i.weight;
            int curProfit = (int) i.profit;

            if (capacity - curWeight >= 0) {
                capacity -= curWeight;
                totalValue += curProfit;
            } else {
                double fraction = ((double) capacity) / ((double) curWeight);
                totalValue += fraction * curProfit;

                capacity = (int) (capacity - (curWeight * fraction));

                break;
            }
        }
        return totalValue;

    }

    static class ItemValue {
        int profit, weight;

        public ItemValue(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
        }

    }

    public static void main(String[] args) {
        ItemValue[] arr = {
                new ItemValue(25, 5),
                new ItemValue(75, 10),
                new ItemValue(100, 12),
                new ItemValue(50, 4),
                new ItemValue(45, 7),
                new ItemValue(90, 9),
                new ItemValue(30, 3)
        };
        int capacity = 37;

        double maxValue = getMaxVal(arr, capacity);
        System.out.println("max profit is :- " + maxValue);
    }
}
