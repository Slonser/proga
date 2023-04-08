package org.lab5.CSV;

import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvException;

public class CsvParseExceptionHandler implements CsvExceptionHandler {
    @Override
    public CsvException handleException(CsvException exception) {
        System.out.println("Коллекция повреждена и не была загружена");
        return null;
    }
}

