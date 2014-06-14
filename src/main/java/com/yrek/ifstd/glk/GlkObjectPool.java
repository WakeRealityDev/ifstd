package com.yrek.ifstd.glk;

import java.util.ArrayList;

public class GlkObjectPool<T extends GlkObject> {
    private final ArrayList<T> pool = new ArrayList<T>();

    public int add(T obj) {
        if (obj == null || obj.isDestroyed()) {
            return 0;
        }
        if (obj.getPointer() != 0) {
            return obj.getPointer();
        }
        for (int i = 0; i < pool.size(); i++) {
            T elt = pool.get(i);
            if (elt == null || elt.isDestroyed()) {
                obj.setPointer(i+1);
                pool.set(i, obj);
                return i+1;
            }
        }
        obj.setPointer(pool.size());
        pool.add(obj);
        return pool.size();
    }

    public T iterate(T obj) {
        int pointer = iterate(obj == null || obj.isDestroyed() ? 0 : obj.getPointer());
        return get(pointer);
    }

    public int iterate(int start) {
        for (int i = start; i < pool.size(); i++) {
            T elt = pool.get(i);
            if (elt != null) {
                if (!elt.isDestroyed()) {
                    return i + 1;
                } else {
                    pool.set(i, null);
                }
            }
        }
        return 0;
    }

    public T get(int pointer) {
        return pointer == 0 ? null : pool.get(pointer-1);
    }

    public void destroy(int pointer) {
        T obj = pool.get(pointer-1);
        obj.destroy();
        pool.set(pointer-1, null);
    }

    public int getPointer(T obj) {
        return add(obj);
    }
}