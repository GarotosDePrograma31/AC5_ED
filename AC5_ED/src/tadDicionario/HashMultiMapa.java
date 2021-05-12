package tadDicionario;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HashMultiMapa<K, V> implements MultiMapa<K, V> {
	Map<K, LinkedList<Map.Entry<K, V>>> m; 
	int nTamanho; 
	
	public HashMultiMapa() {
		m = new HashMap<K, LinkedList<Map.Entry<K, V>>>(); // mapa default
		nTamanho = 0;
	}

	public int size() { return nTamanho; }
	
	public boolean isEmpty() { return nTamanho == 0; }
	
	public Map.Entry<K, V> put(K key, V value) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (key == null) throw new IllegalArgumentException();
		if ((ll = m.get(key)) == null) {
			ll = new LinkedList<Map.Entry<K, V>>();
			m.put(key, ll);
	}
	Map.Entry<K, V> e = new AbstractMap.SimpleEntry<K, V>(key, value);
	ll.add(e); 
	nTamanho++;
	return e;
	}
	
	public Map.Entry<K, V> get(K key) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (key == null) throw new IllegalArgumentException();
		if ((ll = m.get(key)) == null) return null; 
		return ll.peekFirst(); 
	}
	
	public Iterable<Map.Entry<K, V>> getAll(K key) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (key == null) throw new IllegalArgumentException();
		if ((ll = m.get(key)) == null) return null; 
		return ll;
	}
	
	
	public Map.Entry<K, V> remove(Map.Entry<K, V> e) throws IllegalArgumentException {
		LinkedList<Map.Entry<K, V>> ll;
		if (e == null) throw new IllegalArgumentException();
		K key = e.getKey();
		ll = m.get(key);
		if (ll == null) throw new IllegalArgumentException(); 
		if (ll.remove(e)) {
			nTamanho--;
			if (ll.isEmpty()) m.remove(key); 
			return e; 
		} else throw new IllegalArgumentException(); 
	}
	
	public Iterable<Map.Entry<K, V>> entrySet() {
		LinkedList<Map.Entry<K, V>> ll = new LinkedList<Map.Entry<K, V>>();
		
		for (LinkedList<Map.Entry<K, V>> sub : m.values()) ll.addAll(sub);
		return ll;
	}
}
