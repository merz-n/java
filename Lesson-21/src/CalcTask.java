import java.util.Arrays;

public class CalcTask implements Runnable{
    private float[] part;
    private int offset;

    public CalcTask(float[] part, int offset) {
        this.part = part;
        this.offset = offset;
    }

    @Override
    public void run() {
        for (int i = 0; i < part.length; i++){
            int realindex = i + offset;
            part[i] = (float)(part[i] * Math.sin(0.2f + realindex / 5.0f)
                    * Math.cos(0.2f + realindex / 5.0f)
                    * Math.cos(0.4f + realindex / 2.0f));
        }

    }
}
