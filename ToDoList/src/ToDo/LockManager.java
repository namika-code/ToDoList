/*
 * @brief 多重起動防止
 * @create 2024/10/07
 * @modified 2025/03/27 更新内容: オブジェクト指向対応
 */

package ToDo;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LockManager {
	private static final String LOCK_FILE = "app.lock";
	private static LockManager instance;
	
	private LockManager() {}

	public static LockManager getInstance() {
		if (instance == null) {
			instance = new LockManager();
		}
		return instance;
	}

	public boolean acquireLock() {
		File lockFile = new File(LOCK_FILE);
		if (lockFile.exists()) {
			JOptionPane.showMessageDialog(null, "アプリはすでに起動しています", "警告", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		try {
			Files.createFile(lockFile.toPath());
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public void releaseLock() {
		File lockFile = new File(LOCK_FILE);
		if (lockFile.exists()) {
			lockFile.delete();
		}
	}
}
