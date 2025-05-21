import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class RungeKutta {
    private double y0;
    private int tn;
    private double r;
    private double[] y;
    private double h;
    private double[] k1, k2, k3, k4;

    public RungeKutta(double y0, double r, int tn) {
        this.y0 = y0;
        this.tn = tn;
        this.r = r;
        this.y = new double[tn+1];
        y[0] = y0;
        h = 1;
        this.k1 = new double[tn];
        this.k2 = new double[tn];
        this.k3 = new double[tn];
        this.k4 = new double[tn];
        solve();

    }

    public double f(double t, double y) {
        return r * y;
    }

    private void solve() {
        double t = 0;
        for (int i = 0; i < y.length - 1; i++) {
            k1[i] = f(t, y[i]);
            k2[i] = f(t + h / 2, y[i] + k1[i] / 2);
            k3[i] = f(t + h / 2, y[i] + k2[i] / 2);
            k4[i] = f(t + h, y[i] + k3[i]);
            y[i + 1] = y[i] + (h / 6) * (k1[i] + 2 * k2[i] + 2 * k3[i] + k4[i]);
            t += h;
        }
    }

    public double[] getY() {
        return y;
    }

    public void draw() {
        double max = -1;
        for (int i = 0; i < y.length; i++) {
            max = Math.max(max, y[i]);
        }
        double scale = tn / max;
        StdDraw.setXscale(-h*0.5 , tn+h);
        StdDraw.setYscale(-h*0.5 , tn+h);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(0, 0, tn, 0);
        StdDraw.line(0, 0, 0, tn);
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 9));
        for (int i = 0; i < y.length; i++) {
            StdDraw.text(i * h, -h*0.1, (i * h) + "");
        }
        StdDraw.setPenColor(Color.RED);
        for(int i=0;i<y.length-1;i++){
            StdDraw.line(i,y[i]*scale,i+1,y[i+1]*scale);
            StdDraw.filledCircle(i, y[i]*scale, 0.05);
            StdDraw.text(i, y[i]*scale + 0.3, String.format("%.2f", y[i]));

        }
        StdDraw.filledCircle(tn, y[tn]*scale, 0.05);
        StdDraw.text(tn, y[tn]*scale + 0.3, String.format("%.2f", y[tn]));
        StdDraw.show();

    }
}
