/*
 * @brief ファイル保存、読込
 * @create 2025/03/27
 * @modified 2025/03/27 更新内容: オブジェクト指向対応
 */

package ToDo;

import javax.swing.*;
import java.io.*;

public class FileManager{
	
	public void loadTasksFromFile(String filename, DefaultListModel<String> model) {
		File file = new File(filename);
		if (!file.exists()) {
			return;
		}
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = reader.readLine()) != null){
				model.addElement(line);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "タスクファイルの読込失敗: " + e.getMessage());
		}
	}
	
	public void saveToFile(String filename, DefaultListModel<String> model) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			for(int i = 0; i < model.size(); i++) {
				writer.write(model.getElementAt(i));
				writer.newLine();
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "保存に失敗: " + e.getMessage());
		}
	}

	public void saveSelectedToFile(String filename, String task) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
			writer.write(task);
			writer.newLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "完了保存に失敗; " + e.getMessage());
		}
	}
}

