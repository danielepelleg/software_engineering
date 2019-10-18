package it.unipr.fava_pellegrini;

import java.io.IOException;

/**
 * Ascii progress meter. On completion this will reset itself,
 * so it can be reused
 * <br /><br />
 * 100% ============================================= |
 */
public class ProgressBar {
    private StringBuilder progress;

    /**
     * initialize progress bar properties.
     */
    public ProgressBar() {
        init();
    }

    /**
     * called whenever the progress bar needs to be updated.
     * that is whenever progress was made.
     *
     * @param done  an int representing the work done so far
     * @param total an int representing the total work
     */
    public void update(int done, int total) {
        char[] workchars = {'|', '/', '-', '\\'};
        String format = "\r%3d%% %s %s %c";
        String initial = "|";

        int percent = (++done * 100) / total;
        int extrachars = (percent / 2) - this.progress.length();

        while (extrachars-- > 0) {
            progress.append('=');
        }

        System.out.printf(format, percent, initial, progress,
                workchars[done % workchars.length]);

        if (done == total) {
            System.out.flush();
            System.out.println();
            init();
        }
    }

    private void init() {
        this.progress = new StringBuilder(60);
    }

    public void progress() throws InterruptedException {
        ProgressBar.this.update(0, 100);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(8);
            ProgressBar.this.update(i, 1000);
        }
    }

    public void loading() throws InterruptedException, IOException {
        String anim = "|/-\\";
        for (int x = 0; x <= 100; x++) {
            String data = "\r" + anim.charAt(x % anim.length()) + " " + x;
            System.out.write(data.getBytes());
            Thread.sleep(30);
        }
    }
}