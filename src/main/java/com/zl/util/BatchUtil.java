package com.zl.util;

import java.util.List;
import java.util.function.Function;

public class BatchUtil {

    public static <T> void batchExecute(
            List<T> list, Function<List<T>, Integer> function, Integer batchNum) {
        int startNum = 0;
        if (list.size() <= batchNum) {
            function.apply(list);
        } else {
            while (startNum < list.size()) {
                List<T> subList = null;
                if (startNum + batchNum < list.size()) {
                    subList = list.subList(startNum, batchNum);
                    startNum += batchNum;
                } else {
                    subList = list.subList(startNum, list.size());
                }
                function.apply(subList);
            }
        }
    }
}
