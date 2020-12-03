import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.*;


public class MainForm extends JFrame {   // в чем отличие JFrame от Jdialog
    private JPanel MainPanel;
    private JPanel OutputPanel;
    private JTable InputTable;
    private JButton GetFromFileButton;
    private JPanel InputButtonPanel;
    private JButton GetRandomNumbersButton;
    private JButton ExecuteButton;
    private JLabel Output;
    private JPanel ExecutePanel;
    private JScrollPane InputPanel;
    private JPanel SaveButtonPanel;
    private JButton SaveIntoFileButton;
    private JTable OutputTable;

    private JFileChooser fileChooserOpen;  // выбор директории
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;  // выбор меню, но он удален


    public MainForm() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);  // не работает нормально из-за "пружин"
        this.setTitle("MainForm");      // устанавливает название формы
        // this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // устанавливает правила при нажатии на крестик
        this.setContentPane(MainPanel);  // без понятия, что делает, но без этого не работает
        this.pack();  // без понятия, что делает

        /*
        Честно украденные настройки элементов у Соломатина, потому что вручную все это прописывать скучно и долго.
        Я хотел сделать и вынести настройки отдельных элементов в отдельные методы (например метод для таблиц, метод для кнопок),
        которые бы запускались в MainForm, но что-то не получилось не получилось
         */

        JTableUtils.initJTableForArray(InputTable, 100, true, true, false, true);
        JTableUtils.initJTableForArray(OutputTable, 40, true, true, true, true);
        InputTable.setRowHeight(25);  // устанавливает высоту ряда
        OutputTable.setRowHeight(25);

        fileChooserOpen = new JFileChooser();           // не сильно понимаю, что твориться следующие 20 строчек,
        fileChooserSave = new JFileChooser();           // но это вроде работа с файлами
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");


        GetFromFileButton.addActionListener(new ActionListener() {  // прочитать из файла
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFunction fileFunсtion = new FileFunction();
                try {
                    if (fileChooserOpen.showOpenDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(InputTable, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        GetRandomNumbersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] a = ArrayUtils.createRandomIntArray(
                            InputTable.getColumnCount(), 0, 100);
                    JTableUtils.writeArrayToJTable(InputTable, a);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });  // добавить выбор границы случайных чисел

        ExecuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFunction fileFunction = new FileFunction();
                try {
                    Logic logic = new Logic();
                    int[] a = JTableUtils.readIntArrayFromJTable(InputTable);
                    List<Integer> list = fileFunction.arrayToList(a);
                    List<Integer> result = logic.Operation(list);
                    fileFunction.writeListIntoJtable(OutputTable, result);

                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        SaveIntoFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileFunction fileFunсtion = new FileFunction();
                try {
                    int[] a = JTableUtils.readIntArrayFromJTable(InputTable);
                    List<Integer> list = fileFunсtion.arrayToList(a);
                    if (fileChooserSave.showSaveDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        Logic logic = new Logic();
                        fileFunсtion.writeListIntoFile(file, logic.Operation(list));
                       /* FileWriter writer = new FileWriter(file, false);
                        writer.write(Logic.Operation(matrix));
                        writer.close();
                        */
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


// МУСОР
/*





/*  buttonSaveInputInfoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
      */

/*  menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);
        */


       /* menuLookAndFeel = new JMenu();  прикольно, но бесполезно
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        /*   JTableUtils.writeArrayToJTable(tableInput, new int[][]{  можно и без этого
                {0, 1, 2, 3, 4},
                {5, 6, 7, 8, 9}
        });
     */

