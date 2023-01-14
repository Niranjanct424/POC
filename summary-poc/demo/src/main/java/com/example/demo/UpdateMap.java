package com.example.demo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateMap {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("parent", "oldValue");
        map.put("child", "childValue");
        System.out.println("Original Map: " + map);

        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if (entry.getKey().equals("parent")) {
                Object value = entry.getValue();
                it.remove();
                map.put("newParent", value);
            }
        }
        System.out.println("Updated Map: " + map);
    }
}
