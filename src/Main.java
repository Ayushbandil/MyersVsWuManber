import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static int list1Count = 4000;
    private static int list2Count = 5000;

    static XYSeriesCollection dataset = new XYSeriesCollection();


    public static void main(String[] args) {
        ArrayList<Integer> deletionArray = new ArrayList<>();
        initiateDeletionArray(deletionArray);

        appendValues(deletionArray);

        JFreeChart chart = ChartFactory.createXYLineChart("Comparisons vs Deletions for Myers' and Wu-Manber-Myers-Miller Algorithms ",
                "Number of Deletions", "Number of Comparisons", dataset);

        ChartGenerator newChart = new ChartGenerator();
        newChart.generateChart(dataset, chart, "Graph.jpeg");
    }

    private static void appendValues(ArrayList<Integer> deletionArray) {
        XYSeries myersSeries = new XYSeries("Myers Algorithm");
        XYSeries wuManberSeries = new XYSeries("Wu-Manber Algorithm");
        for (Integer i : deletionArray) {

            ArrayList<Integer> list1 = initializeList1();
            ArrayList<Integer> list2 = initializeList2(list1, i);

            Myers myers = new Myers();
            myers.runMyersAlgorithm(list1, list2);

            WuManber mbr = new WuManber();
            mbr.runWuManberAlgorithm(list1, list2);

            System.out.println("For Myers Algorithm: D = " + i + " Comparisons = " + myers.comparisonCount + " Edit Distance = " + myers.editDistance);
            System.out.println("For Wu-Manber Algorithm: D = " + i + " Comparisons = " + mbr.comparisonCount + " Edit Distance = " + mbr.editDistance);

            myersSeries.add((double) i, (double) myers.comparisonCount);
            wuManberSeries.add((double) i, (double) mbr.comparisonCount);
        }

        //Generate Graphs
        dataset.addSeries(myersSeries);
        dataset.addSeries(wuManberSeries);
    }

    private static ArrayList<Integer> initializeList2(ArrayList<Integer> list1, Integer D) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        toReturn.addAll(list1);
        Random random = new Random();
        int insertions = list2Count - list1Count + D;

        for (int i = 0; i < D; i++) {
            int randomIndex = random.nextInt(toReturn.size() - 1);
            toReturn.remove(randomIndex);
        }

        for (int i = 0; i < insertions; i++) {
            int randomInteger = random.nextInt(insertions);
            int randomIndex = random.nextInt(400);
            toReturn.add(randomIndex, randomInteger);
        }
        return toReturn;
    }

    private static ArrayList<Integer> initializeList1() {
        ArrayList<Integer> toReturn = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < list1Count; i++) {
            int randomInteger = random.nextInt(400);
            toReturn.add(randomInteger);
        }
        return toReturn;
    }

    private static void initiateDeletionArray(ArrayList<Integer> deletionArray) {
        deletionArray.add(10);
        deletionArray.add(50);
        deletionArray.add(100);
        deletionArray.add(200);
        deletionArray.add(400);
        deletionArray.add(600);
        deletionArray.add(800);
        deletionArray.add(1000);
    }
}
