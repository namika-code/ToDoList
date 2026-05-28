# ToDoList Java Application

## Overview
A simple task management application built with Java Swing.  
- Add, delete, edit, and complete tasks  
- Color-coded display based on due dates  
- Save and restore completed tasks to/from file  
- Undo feature (for deleted or completed tasks)  
- Prevents multiple app instances using a lock mechanism (`LockManager`)  

---

## Development Environment
- Java 8 or higher (Recommended: Java 11)  
- Swing GUI  
- File-based local data storage  

---

## File Structure
```
├── ToDo/
│   ├── ToDoListManager.java      // Task management logic
│   ├── ToDoListUI.java           // UI logic (based on Swing)
│   ├── Task.java                 // Task model (title, dates, etc.)
│   ├── FileManager.java          // Handles file I/O
│   ├── RoundedBorder.java        // UI styling (rounded buttons)
│   ├── SimpleDatePicker.java     // Utility for date picker panels
│   ├── Main.java                 // Entry point with lock mechanism to prevent multiple launches
│   └── LockManager.java          // Manages single instance using `app.lock` file
├── bin/
│   └── CreatedList/
│       ├── task.txt              // Uncompleted task list (saved)
│       └── completed.txt         // Completed task list
```

---

## Features

| Feature               | Description  
|-----------------------|------------
| ✅ Add Task           | Add tasks with title, start date, and due date using the "Add" button  
| 🗑️ Delete Task         | Select a task from the list and delete it using the "Delete" button (can be undone)  
| 🕘 Complete Task       | Mark tasks as completed using the "Complete" button (saved in a separate file)  
| ↩️ Undo                | Restore the most recently deleted or completed task  
| 💾 Save               | Save the current task list to `bin/CreatedList/task.txt`  
| 📅 Date Management     | Tasks can have start and due dates; color and icons change depending on urgency  
| ⬆️⬇️ Reorder Tasks     | Move tasks up/down in the list  
| ✏️ Edit by Double-Click | Double-click a task to edit its title and dates  

---

## How to Run

1. Make sure you have Java (JRE or JDK) version 8 or higher installed.
2. Please download the .jar file from the Releases page and run it.

If double-click does not work, try launching it manually:

```bash
java -jar ToDoListApp.jar
```
---

## File Storage
- Task file: `bin/CreatedList/task.txt`  
- Completed task file: `bin/CreatedList/completed.txt`  
These files and directories are automatically created when the app is launched.

---

## License
This project is licensed under the MIT License.

---

### 💡 Additional Notes

- You cannot add a task with an empty title.  
- Undo is only available immediately after a delete or complete action.  
- Tasks with past due dates show **❗ red warning**, and within 3 days show **⏰**.  
- After saving, closing the window will skip the confirmation dialog. 



# ToDoList Java アプリケーション

## 概要
シンプルなJava Swing製のToDoリスト管理アプリです。  
- タスクの追加・削除・編集・完了管理  
- 締切日による色分け表示  
- 完了タスクのファイル保存と復元機能  
- Undo機能（削除や完了の戻し）  
- アプリの多重起動防止（LockManagerによるロック制御）  

---

## 開発環境
・Java 8以上（推奨：Java 11）
・Swing GUI
・ローカルファイルへの保存機能


## ファイル構成
```
├── ToDo/
│   ├── ToDoListManager.java      // タスクの管理ロジック
│   ├── ToDoListUI.java           // UIロジック（Swingベース）
│   ├── Task.java                 // タスク情報（タイトル・日付など）
│   ├── FileManager.java          // ファイル読み書き処理
│   ├── RoundedBorder.java        // ボタンの外観調整(角丸ボタン用の拡張)
│   ├── SimpleDatePicker.java     // 日付選択パネル作成ユーティリティ
│   ├── Main.java              // アプリ起動クラス。多重起動防止ロックをかけてUI表示  
│   └── LockManager.java       // アプリの多重起動を防ぐロック管理クラス（app.lockファイルを利用）  
├── bin/
│   └── CreatedList/
│       ├── task.txt              // 保存された未完了タスク一覧
│       └── completed.txt         // 完了済みタスク一覧
```
---

## 主な機能
| 機能             	| 説明 
|------------------	|------
| ✅ タスク追加     	| 「追加」ボタンからタイトル・開始日・締切日を入力して追加できます
| 🗑️ タスク削除     	| リストから選択して「削除」ボタンで削除します（元に戻す可能）
| 🕘 タスク完了     	| 完了したタスクは「完了」ボタンで別ファイルに記録され、非表示になります
| ↩️ 戻す            	| 「削除」または「完了」したタスクを元に戻すことができます
| 💾 保存           	| 現在のタスクリストを `bin/CreatedList/task.txt` に保存します
| 📅 日付管理       	| タスクには「開始日」「締切日」が指定でき、日付によって表示色や警告アイコンが変わります
| ⬆️⬇️ 並び替え		| タスクを上下に移動して順番を調整できます
| ✏️ ダブルクリック編集 | タスクをダブルクリックしてタイトルや日付を編集可能
---

## 実行方法

1. Java（JRE または JDK）バージョン 8 以上がインストールされていることを確認してください。
2. リリースページから `.jar` ファイルをダウンロードして起動してください。

ダブルクリックしても起動しない場合は、手動で起動してみてください。

```bash
java -jar ToDoListApp.jar

```

## ファイル保存場所
タスクファイル: bin/CreatedList/task.txt
完了タスクファイル: bin/CreatedList/completed.txt
これらのファイルとディレクトリはプログラム起動時に自動生成されます。


## ライセンス
本プロジェクトはMITライセンスで公開しています。


### 💡 その他メモ

- タスク名が空白だと追加できません。
- Undoは削除・完了直後のみ可能です。
- 締切日が過ぎると **❗ 赤警告**、残り3日以内なら **⏰** が表示されます。
- 保存後にウィンドウを閉じると再確認が出ないよう工夫されています。
