import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;

/**
 * @see //https://stackoverflow.com/a/21307289/230513
 */
public class wykres2 extends JPanel implements Runnable {

    private final DynamicTimeSeriesCollection dataset;
    private final JFreeChart chart;
    public int data;

    public wykres2(final String title ,int data) {
        this.data = data;
        dataset = new DynamicTimeSeriesCollection(1, 500, new Second());
        dataset.setTimeBase(new Second(0, 0, 0, 23, 1, 2014));
        dataset.addSeries(new float[1], 0, title);
        chart = ChartFactory.createTimeSeriesChart(
                title, "Time", title, dataset, true, true, false);
        final XYPlot plot = chart.getXYPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setFixedAutoRange(1000000);

        axis.setDateFormatOverride(new SimpleDateFormat("ss.SS"));
        final ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);


    }

    public void update(float value) {
        float[] newData = new float[1];
        newData[0] = value;
        dataset.advanceTime();
        dataset.appendData(newData);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("testing");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                final wykres2 chart
                        = new wykres2("Alternating data",plcS71200.readedData);
                frame.add(chart);
                frame.pack();
                Timer timer = new Timer(1000, new ActionListener() {
                    private boolean b;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        chart.update(b ? 1 : 0);
                        b = !b;
                    }
                });
                timer.start();
                frame.setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);
        final wykres2 chart
                = new wykres2("Alternating data",plcS71200.readedData);
        frame.add(chart);
        frame.pack();


        frame.setVisible(true);
        while(true)
        {


chart.update(plcS71200.readedData);

           try {Thread.sleep(200);}catch (InterruptedException e){};


        }


    }
}