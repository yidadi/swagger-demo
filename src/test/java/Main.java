import org.apache.catalina.LifecycleState;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.util.Scanner;

public class Main {
    static final String contextPath = "/";
    static final String appBase = "src/main/webapp";
    static final String basedir = "/User/yiwenxiang/tomcat/";
    static final int port = 8083;
    static Tomcat tomcat;

    static Scanner scanner = new Scanner(System.in);

    /**
     * tomcat启动程序
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        start();
    }

    /**
     * restart tomcat
     *
     * @throws Exception
     */
    public static void restart() throws Exception {
        try {
            tomcat.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            tomcat.destroy();
        }

        while (tomcat != null && !tomcat.getServer().getState().equals(LifecycleState.DESTROYED)
                && !tomcat.getServer().getState().equals(LifecycleState.STOPPED)) {
            Thread.sleep(1000);
        }
        start();
    }

    static void start() throws Exception {
        tomcat = new Tomcat();
        tomcat.setBaseDir(basedir);

        tomcat.setPort(Integer.valueOf(port));
        tomcat.getHost().setAppBase(new File(appBase).getAbsolutePath());
        tomcat.addWebapp(contextPath, new File(appBase).getAbsolutePath());
        tomcat.start();

        System.out.print("按任意键并回车重启");
        String command = scanner.nextLine();
        restart();

    }
}
