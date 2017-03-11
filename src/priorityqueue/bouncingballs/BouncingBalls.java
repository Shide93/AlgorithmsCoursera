package priorityqueue.bouncingballs;

import edu.princeton.cs.algs4.Particle;
import edu.princeton.cs.algs4.StdDraw;

public class BouncingBalls {
    public static void main(String[] args) {
        int N = 100;
        Particle[] balls = new Particle[N];
        for (int i = 0; i < N; i++) {
            balls[i]=new Particle();
        }
        while (true)
        {
            StdDraw.clear();
            for (int i = 0; i < N; i++) {
                balls[i].move(3);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}
