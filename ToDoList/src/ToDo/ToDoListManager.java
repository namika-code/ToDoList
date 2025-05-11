/*
 * @brief データ管理、操作
 * @create 2025/03/27
 * @modified 2025/03/27 更新内容: オブジェクト指向対応
 */

package ToDo;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Stack;

public class ToDoListManager{
	private DefaultListModel<String> listModel;
	private Stack<Pair<String, Integer>> undoStack;
	private Stack<Pair<String, Integer>> completedStack;
	private FileManager fileManager;
	private static final String TASKS_FILE = "bin/Created List/task.txt";
	private static final String COMPLETED_TASKS_FILE = "bin/Created List/completed.txt";
	
	public ToDoListManager() {
		listModel = new DefaultListModel<>();
		undoStack = new Stack<>();
		completedStack = new Stack<>();
		fileManager = new FileManager();
		createDirectories();
		fileManager.loadTasksFromFile(TASKS_FILE, listModel);
	}
	
	private void createDirectories() {
		File taskFile = new File(TASKS_FILE);
		File completedFile = new File(COMPLETED_TASKS_FILE);
		
		if (!taskFile.getParentFile().exists()) {
			taskFile.getParentFile().mkdirs();
		}
		if (!completedFile.getParentFile().exists()) {
			completedFile.getParentFile().mkdirs();
		}
	}
	
	public DefaultListModel<String> getListModel(){
		return listModel;
	}
	
	// 追加
	public void addTask(String task) {
		if (!task.isEmpty()) {
			listModel.addElement(task);
		}
	}
	
	// 削除
	public void removeTask(int index) {
		if (index >= 0 && index < listModel.size()) {
			String removedTask = listModel.getElementAt(index);
			undoStack.push(new Pair<>(removedTask, index));
			listModel.remove(index);
		}
	}
	
	// 戻す
	public void restoreTask() {
		if (!undoStack.isEmpty()) {
			Pair<String, Integer> taskInfo = undoStack.pop();
			String restoredTask = taskInfo.getKey();
			int originalIndex = taskInfo.getValue();
			listModel.add(originalIndex, restoredTask);
		} else if (!completedStack.isEmpty()) {
			Pair<String, Integer> completedTask = completedStack.pop();
			listModel.add(completedTask.getValue(), completedTask.getKey());
			removeTaskFromCompletedFile(completedTask.getKey());
		} else {
			JOptionPane.showMessageDialog(null, "戻せるタスクがありません");
//			JOptionPane.showMessageDialog(frame, "戻せるタスクがありません。", "エラー", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// 完了
	public void completeTask(int index) {
		if (index >= 0 && index < listModel.size()) {
			String completedTask = listModel.getElementAt(index);
			completedStack.push(new Pair<>(completedTask, index));
			fileManager.saveSelectedToFile(COMPLETED_TASKS_FILE, completedTask);
			listModel.remove(index);
		}
	}
	
	// 保存
	public void saveTask() {
		fileManager.saveToFile(TASKS_FILE, listModel);
	}
	
	// 完了→戻すしたとき、完了タスクを.txtから削除
	public void removeTaskFromCompletedFile(String task) {
		try {
			File completedFile = new File(COMPLETED_TASKS_FILE);
			if(completedFile.exists()) {
				List<String> tasks = Files.readAllLines(completedFile.toPath());
				tasks.remove(task);
				
				Files.write(completedFile.toPath(), tasks);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "完了タスクファイルに問題あり: " + e.getMessage());
		}
	}
	
	// Pairｸﾗｽ(タスクとそのｲﾝﾃﾞｯｸｽを保存するための簡易クラス)
	class Pair<T, U>{
		private T key;
		private U value;
		
		public Pair(T key, U value) {
			this.key = key;
			this.value = value;
		}
		
		public T getKey() {
			return key;
		}
		
		public U getValue() {
			return value;
		}
	}
}