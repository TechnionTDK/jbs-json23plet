package json23plet.modules;

/**
 * Created by yon_b on 14/01/17.
 */
public class ProgressBar {
    StringBuilder bar = new StringBuilder("[");
    String icon = "#";
    double iter;
    double totalWork;
    String[] sign = {"\\", "|", "/", "-"};

    public ProgressBar(int totalWork) {
        this.totalWork = totalWork;
        this.iter = 0;
    }

    public void update() {
        int prog = (int) (((++this.iter)/this.totalWork) * 100);
        if (this.iter == this.totalWork) {
            workEnd();
        }
        else {
            for (int i = 0 ; i < 50 ; i++) {
                if (i <= prog/2) {
                    this.bar.append(this.icon);
                } else {
                    this.bar.append(" ");
                }
            }
            this.bar.append("]").append(String.valueOf(prog)).append("%")
                    .append(" " + this.sign[((int)(this.iter) % 4)]);
        }
        System.out.print("\r" + this.bar.toString());
        this.bar = new StringBuilder("[");
    }
    private void workEnd() {
        for (int i = 0 ; i < 50  ; i++) {
            this.bar.append(this.icon);
        }
        this.bar.append("]").append("100%").append(" -\n");
    }
}
