package state;

import java.util.*;

/**
 * код для работы с подсловарями
 */
public class MapTransformer extends AbstractMap<String, String>{

    private final Map<String, String> generalMap;

    public MapTransformer(Map<String, String> initialMap){
        this.generalMap = initialMap;
    }

    /**
     * код формирования подсловаря по общему словарю
     * @param prefix - префикс ключа
     * @return - подсловарь отдельной компоненты без префиксов
     */
    public Map<String, String> getSubMap(String prefix){
        Map<String, String> subMap = new HashMap<>();
        for(Map.Entry<String, String> entry : generalMap.entrySet()){
            String key = entry.getKey();
            if (key.startsWith(prefix)){
                String paramName = key.split("\\.")[1];
                subMap.put(paramName, entry.getValue());
            }
        }
        if(!subMap.isEmpty()){
            return subMap;
        }
        else{
            throw new IllegalArgumentException("Wrong prefix");
        }
    }

    /**
     * код добавления префикса к подсловарю
     * @param prefix - префикс
     * @return - подсловарь с префиксом
     */
    private Map<String, String> addPrefixToSubMap(Map<String, String> subMap, String prefix){
        Map<String, String> subMapWithPrefix = new HashMap<>();
        for(Map.Entry<String, String> entry : subMap.entrySet()){
            subMapWithPrefix.put(prefix + "." + entry.getKey(), entry.getValue());
        }
        return subMapWithPrefix;
    }

    /**
     * код добавления подсловаря в общий словарь по префиксу
     * @param prefix - префикс, добвляемый к подсловарю
     * @param subMap - подсловарь
     */
    public void addSubMapToGeneralMapByPrefix(String prefix, Map<String, String> subMap){
        subMap = addPrefixToSubMap(subMap, prefix);
        generalMap.putAll(subMap);
    }


    @Override
    public Set<Entry<String, String>> entrySet() {
        return generalMap.entrySet();
    }
}
