package state;

import java.util.*;

/**
 * код для работы с подсловарями
 */
public class MapTransformer{

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
        generalMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .forEach(entry -> subMap.put(entry.getKey().split("\\.")[1], entry.getValue()));
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
        subMap.forEach((key, value) -> subMapWithPrefix.put(prefix + "." + key, value));
        return subMapWithPrefix;
    }

    /**
     * код добавления подсловаря в общий словарь по префиксу
     * @param prefix - префикс, добвляемый к подсловарю
     * @param subMap - подсловарь
     */
    public Map<String, String> addSubMapToGeneralMapByPrefix(String prefix, Map<String, String> subMap){
        Map<String, String> result = new HashMap<>(generalMap);
        subMap = addPrefixToSubMap(subMap, prefix);
        result.putAll(subMap);
        return result;
    }
}
