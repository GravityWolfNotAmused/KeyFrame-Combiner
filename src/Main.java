import imageConcat.ui.UIFrame;

public class Main {
    private static UIFrame ui;
    public static void main(String[] args) {
        new Thread(() -> ui = new UIFrame()).run();
    }
}