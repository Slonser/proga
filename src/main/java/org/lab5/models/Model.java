package org.lab5.models;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Model {
    @Override
    public abstract String toString();
    private Map<String, Consumer<Object>> setterMap = new HashMap<>();

    public Model() {
        initializeSetterMap();
    }

    protected abstract void initializeSetterMap();

    public void setFields(Map<String, Object> fields) {
        for (String fieldName : fields.keySet()) {
            if (setterMap.containsKey(fieldName)) {
                setterMap.get(fieldName).accept(fields.get(fieldName));
            }
        }
    }


    protected void registerSetter(String fieldName, Consumer<Object> setter) {
        setterMap.put(fieldName, setter);
    }
    public void setField(String fieldName, Object value){
        if (setterMap.containsKey(fieldName)) {
            setterMap.get(fieldName).accept(value);
        }
    }
}
