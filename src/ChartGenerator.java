import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;

/**
 * Created by Ayush Bandil on 3/12/2019.
 */
public class ChartGenerator {
    private String parentPath = "D:\\Projects\\MyersVsWuManber\\Graph\\";

    void generateChart(XYSeriesCollection dataset, JFreeChart chart, String fileName) {
        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesPaint(1, Color.GREEN);

        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));

        plot.setOutlinePaint(Color.GRAY);
        plot.setOutlineStroke(new BasicStroke(1.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        int width = 940;    /* Width of the image */
        int height = 780;   /* Height of the image */

        try {
            File lineChart = new File(parentPath + fileName);
            ChartUtilities.saveChartAsJPEG(lineChart, chart, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
