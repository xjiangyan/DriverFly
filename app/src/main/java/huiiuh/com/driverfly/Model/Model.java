package huiiuh.com.driverfly.Model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Model {
    private static Model model = new Model();
    private ExecutorService mExecutorService = Executors.newCachedThreadPool();

    public static Model getInstance() {
        return model;
    }

    public ExecutorService getGlobalThreadPool() {
        return mExecutorService;
    }
}
