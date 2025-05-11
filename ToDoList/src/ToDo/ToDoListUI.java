/*
 * @brief GUI
 * @create 2025/03/27
 * @modified 2025/03/27 更新内容: オブジェクト指向対応
 */

package ToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoListUI{
	private ToDoListManager manager;
	private JFrame frame;
	private JTextField taskInput;
	private JList<String> taskList;
	private boolean isClosing = false;	// ウィンドウ閉じるフラグ
	
	public ToDoListUI(ToDoListManager manager) {
		this.manager = manager;
		InitializeUI();
	}
	
	public void InitializeUI() {
		frame = new JFrame("ToDoリスト");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(600, 600);
		
		taskList = new JList<>(manager.getListModel());
		JScrollPane scrollPane = new JScrollPane(taskList);
		
		taskInput = new JTextField();
		JButton addButton = new JButton("追加");
		JButton removeButton = new JButton("削除");
		JButton restoreButton = new JButton("戻す");
		JButton saveButton = new JButton("保存");
		JButton completeButton = new JButton("完了");
		JButton upButton = new JButton("↑");
		JButton downButton = new JButton("↓");
		
        // カラーなど
        Color begonia = new Color(243, 113, 103);		// 赤
        Color lightskyblue = new Color(135, 206, 250);	// 青
        Color Lavender = new Color(220, 190, 240);		// 紫
        Color seagreen = new Color(138, 199, 90);		// 海緑色
        
        addButton.setBackground(begonia);
        removeButton.setBackground(lightskyblue);
        restoreButton.setBackground(Lavender);
        saveButton.setBackground(seagreen);
        completeButton.setBackground(Color.orange);
		
		addButton.addActionListener(e -> addTask());
		removeButton.addActionListener(e -> removeTask());
		restoreButton.addActionListener(e -> restoreTask());
		completeButton.addActionListener(e -> completeTask());
		saveButton.addActionListener(e -> saveTask());
		upButton.addActionListener(e -> upTask());
		downButton.addActionListener(e -> downTask());
		
		JPanel inputPanel = new JPanel(new BorderLayout());
		inputPanel.add(taskInput, BorderLayout.CENTER);
		inputPanel.add(addButton, BorderLayout.EAST);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(removeButton);
		buttonPanel.add(restoreButton);
		buttonPanel.add(completeButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(upButton);
		buttonPanel.add(downButton);
		
		frame.setLayout(new BorderLayout());
		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		// ウィンドウ閉じる前に確認ダイアログ
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "保存しますか？", "終了確認", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					isClosing = true;
					manager.saveTask();
					System.exit(0);
				} else if (result == JOptionPane.NO_OPTION) {
					System.exit(0);
				}
			}
		});
		
		// ダブルクリックでタスク編集
		taskList.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = taskList.locationToIndex(e.getPoint());
					
					if (index != -1) {
						String oldTask = manager.getListModel().getElementAt(index);
						String newTask = JOptionPane.showInputDialog("タスクを編集", oldTask);
						
						if (newTask != null && !newTask.isEmpty()) {
							manager.getListModel().set(index, newTask);
						}
					}
				}
			}
		});
		
		// 入力のとこでEnter押したら勝手に追加
		taskInput.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            addTask();
		        }
		    }
		});
	}
	
	// 追加
	private void addTask() {
		String task = taskInput.getText();
		if(task.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "タスクを入力してください。", "エラー", JOptionPane.ERROR_MESSAGE);
		} else {
			manager.addTask(task);
			taskInput.setText("");
		}
	}
	
	// 削除
	private void removeTask() {
		int index = taskList.getSelectedIndex();
		if(index == -1) {
			JOptionPane.showMessageDialog(frame, "削除するタスクを選択してください。", "エラー", JOptionPane.ERROR_MESSAGE);
		} else {
			manager.removeTask(index);
		}
	}
	
	// 戻す
	private void restoreTask() {
		manager.restoreTask();
	}
	
	// 完了
	private void completeTask() {
		int index = taskList.getSelectedIndex();
		if(index == -1) {
			JOptionPane.showMessageDialog(frame, "完了するタスクを選択してください。", "エラー", JOptionPane.ERROR_MESSAGE);
		} else {
			manager.completeTask(index);
		}
	}
	
	// 保存
	private void saveTask() {
		if(manager.getListModel().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "保存するタスクがありません。", "エラー", JOptionPane.ERROR_MESSAGE);
		} else {
			manager.saveTask();
			
			if(!isClosing) {
				JOptionPane.showMessageDialog(frame, "保存されました。");
			}
		}
	}
	
	// 上へ
	private void upTask() {
		int selectedIndex = taskList.getSelectedIndex();
		if (selectedIndex > 0) {
			String task = manager.getListModel().getElementAt(selectedIndex);
			manager.getListModel().remove(selectedIndex);
			manager.getListModel().add(selectedIndex - 1, task);
			taskList.setSelectedIndex(selectedIndex - 1);
		}
	}
	
	// 下へ
	private void downTask() {
		int selectedIndex = taskList.getSelectedIndex();
		int listSize = manager.getListModel().size();
		
		if (selectedIndex == -1 || listSize <= 1) {
			return;
		}
		
		if (selectedIndex < listSize - 1) {
			String task = manager.getListModel().getElementAt(selectedIndex);
			manager.getListModel().remove(selectedIndex);
			manager.getListModel().add(selectedIndex + 1, task);
			taskList.setSelectedIndex(selectedIndex + 1);
		}
	}
	
	public void show() {
		frame.setVisible(true);
	}	
}
