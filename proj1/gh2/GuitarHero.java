package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final double FREQUENCY = 440.0;
    public static final int NUM = 37;

    public static double cal(int index) {
        return FREQUENCY * Math.pow(2, (index - 24) / 12);
    }

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] guitarStrings = new GuitarString[NUM];
        for (int i = 0; i < NUM; i += 1) {
            guitarStrings[i] = new GuitarString(cal(i));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    guitarStrings[index].pluck();
                }
            }

            double sample = 0;
            for (int i = 0; i < NUM; i += 1) {
                sample += guitarStrings[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < NUM; i += 1) {
                guitarStrings[i].tic();
            }
        }
    }
}
