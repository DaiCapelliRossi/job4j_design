package question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {

        Info info = new Info(0, 0, 0);

        if (previous.equals(current)) {
            return info;
        }

        Map<Integer, String> map = new HashMap<>();
        Map<Integer, String> mapPrevious = new HashMap<>();
        Map<Integer, String> mapCurrent = new HashMap<>();

        previous.forEach(t -> mapPrevious.put(t.getId(), t.getName()));
        current.forEach(t -> mapCurrent.put(t.getId(), t.getName()));

        map.putAll(mapPrevious);
        map.putAll(mapCurrent);

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();

            if (mapPrevious.containsKey(key) && !mapCurrent.containsKey(key)) {
                info.setDeleted(info.getDeleted() + 1);

            } else if (!mapPrevious.containsKey(key) && mapCurrent.containsKey(key)) {
                info.setAdded(info.getAdded() + 1);

            } else if (!(mapPrevious.get(key).equals(mapCurrent.get(key)))) {
                info.setChanged(info.getChanged() + 1);
            }
        }
        return info;
    }
}
