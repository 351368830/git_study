package com.zl.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zl.common.MarkerInterface;

import com.zl.service.EasyExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CommonListener<T> extends AnalysisEventListener<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonListener.class);

    List<T> list = new ArrayList<>();
    private final int BATCH_COUNT;
    private EasyExcelService service;

    public CommonListener(int batchCount, EasyExcelService service) {
        this.BATCH_COUNT = batchCount;
        this.service = service;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        list.add(t);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    private void saveData() {
        service.batchInsert(list);
    }
}
