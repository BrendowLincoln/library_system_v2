package br.org.femass.utils.services;

import java.util.HashMap;

public class DataProvider {
    private static HashMap<String, Object> datas = new HashMap<>();

    public static Object getDataByKey(String data) {
        return datas.get(data);
    }

    public static void setData(String key, Object value) {
        if(datas.containsKey(key)) {
            datas.replace(key, value);
        } else {
            datas.put(key, value);
        }
    }
}
