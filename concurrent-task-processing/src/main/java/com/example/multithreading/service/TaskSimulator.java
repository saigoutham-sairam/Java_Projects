import com.example.multhithreading.service
  
public class TaskSimulator {
    public static void simulateTask() {
        try {
            // Simulate blocking operation (e.g., API call or database query)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
