import java.util.ArrayList;
import java.util.List;

public class Logic {

    public List<Integer> Operation(List<Integer> list) {

        int min = list.size() - 2;  // минимальное количество "плохих" чисел
        int indexOfMain = 0; // индекс числа, на котором зафиксировано минимальное количество "плохих" чисел (назовем индекс минимума)
        int mainDifference = 0; // разность чисел, на которой зафиксировано минимальное количество "плохих" чисел
        int difference = 0; // просто разность, которая меняется в цикле

        for (int i = 0; i < list.size() - 1; i++)  // проходимся по всем (N^2-N)/2 парам

            for (int j = i + 1; j < list.size(); j++) {
                difference = list.get(j) - list.get(i);     // считаем текущую разность
                if (difference % (j - i) != 0) continue;    // проверяем разность на адекватность
                difference = difference / (j - i);          // если подходит, считаем ее

                if (amountOfWrongNumbers(list, i, difference) <= min) {  // если количество плохих чисел, при это разности
                    min = amountOfWrongNumbers(list, i, difference);    // получилось минимальное, то записываем в min
                    indexOfMain = i;                                    // сохраняем индекс и разность числа, при котором
                    mainDifference = difference;                        // получился минимум "плохих" чисел
                }

            }

        List<Integer> result = new ArrayList<>();
        int mainNumber = list.get(indexOfMain);
        for (int i = 0; i < list.size(); i++) {
            result.add(mainNumber - (indexOfMain - i) * mainDifference);
        }
        /*
        for (int i = 0; i < indexOfMain; i++) {                                    // теперь создаем новый список
            result.add(list.get(indexOfMain) - (indexOfMain - i) * mainDifference);// который основан на числе, при
        }                                                                          // котором получился минимум разности
                                                                                   // и "плохих" чисел
        result.add(list.get(indexOfMain));
                                                                                    // список создается как бы справа
        for (int i = indexOfMain + 1; i < list.size(); i++) {                       // и слева от индекса минимума
            result.add(i, list.get(indexOfMain) + (i - indexOfMain) * mainDifference);
        }

         */
        return result;
    }

    public int amountOfWrongNumbers(List<Integer> list, int n, int difference) {
        int mainNumber = list.get(n);   // берется опорное число, от которого будем проверять
        int count = 0;
        int i = 0;
        // здесь просто находятся количество "плохих" чисел по формуле ариф прогрессии a[n]=a[i]+k*(n-i)
        for (int number : list) {
            if (mainNumber - (n - i) * difference != number) count++;
            i++;
        }
        /*
        for (int i = 0; i < n - 1; i++) {
            if (mainNumber - (n - i) * difference != list.get(i)) count++;
        }
        for (int i = n + 1; i < list.size(); i++) {
            if (mainNumber + (i - n) * difference != list.get(i)) count++;
        }
        */

        return count;
    }
}
