/*
 * @brief エントリーポイント
 * @create 2024/10/07
 * @modified 2025/03/27 更新内容: オブジェクト指向対応
 */
package ToDo;

public class Main{
	public static void main(String[] args) {
        if (!LockManager.getInstance().acquireLock()) {
            return;
        }
		
        // シャットダウンフックを追加：アプリ終了時にロック解除
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LockManager.getInstance().releaseLock();
        }));
        
		ToDoListManager manager = new ToDoListManager();
		ToDoListUI ui = new ToDoListUI(manager);
		ui.show();
	}
}