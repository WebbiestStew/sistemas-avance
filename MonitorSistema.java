import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class MonitorSistema {
    public static void main(String[] args) {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        System.out.println("Uso de CPU: " + osBean.getSystemCpuLoad() * 100 + "%");
        System.out.println("Memoria libre: " + osBean.getFreePhysicalMemorySize() / (1024 * 1024) + " MB");
    }
}