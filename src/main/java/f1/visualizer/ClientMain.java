package f1.visualizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import f1.visualizer.view.MainFrame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientMain {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
