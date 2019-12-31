package com.travel.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateConverter
 * @Description TODO
 * @Author TOdyZHu
 * @Date 2019-07-29 12:57
 **/
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
