import java.util.Optional;
import java.util.Map;

class KeyableMap<V extends Keyable> implements Keyable {
    private final String key;
    private final ImmutableMap<String, V> iMap;

    KeyableMap(String key) {
        this.key = key;
        this.iMap = new ImmutableMap<String, V>();
    }

    KeyableMap(String key, ImmutableMap<String, V> iMap) {
        this.key = key;
        this.iMap = iMap;
    }

    KeyableMap(KeyableMap<V> kMap) {
        this.key = kMap.key;
        this.iMap = kMap.iMap;
    }

    Optional<V> get(String key) {
        return this.iMap.get(key);
    }

    KeyableMap<V> put(V item) {
        return new KeyableMap<V>(this.key, this.iMap.put(item.getKey(), item));
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        //return String.format("%s: %s", this.key, this.iMap.values().toString())
        //    .replace('[', '{')
        //    .replace(']', '}');
        // this should be allowed smh
        String out = "";
        for (Map.Entry<String, V> e : this.iMap) {
            out = out + e.getValue().toString() + ", ";
        }
        return String.format("%s: {%s}", this.key, out.length() > 0 ?
                out.substring(0, out.length() - 2) : out);
    }
}
