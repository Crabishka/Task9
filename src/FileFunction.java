
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.*;

import javax.swing.*;

public class FileFunction {


    public List<Integer> arrayToList(int[] a) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int number : a) {
            list.add(number);
        }
        return list;
    }

    public int[] listToArray(List<Integer> list) {
        int[] a = new int[list.size()];
        int i = 0;
        for (int number : list){
            a[i] = number;
            i++;
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
        int[] a = listToArray(list);
        util.ArrayUtils.writeArrayToFile(InputFile, a);
    }

    public void writeListIntoJtable(JTable table, List<Integer> list){
        int[] a = listToArray(list);
        JTableUtils.writeArrayToJTable(table,a);
    }

}
