
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import util.*;

import javax.swing.*;

public class FileFunction {

    public List<Integer> arrayToList(int[] a) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        return list;
    }

    public int[] listToArray(List<Integer> list) {
        int[] a = new int[list.size()];
        for (int i = 0; i < a.length; i++){
            a[i] = list.get(i);
        }
        return a;
    }

    public List<Integer> readListFromFile(String InputFile)  {
        int[] a = ArrayUtils.readIntArrayFromFile(InputFile);
        ArrayList<Integer> list = new ArrayList<>();
        for (int number : a) {
            list.add(number);
        }
        return list;
    }

    public void writeListIntoFile(String InputFile, List<Integer> list) throws FileNotFoundException {
        int[] a = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        util.ArrayUtils.writeArrayToFile(InputFile, a);
    }

    public void writeListIntoJtable(JTable table, List<Integer> list){
        int[] a = listToArray(list);
        JTableUtils.writeArrayToJTable(table,a);
    }

}
