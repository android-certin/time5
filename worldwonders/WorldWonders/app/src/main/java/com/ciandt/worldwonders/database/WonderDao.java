package com.ciandt.worldwonders.database;

import com.ciandt.worldwonders.model.Wonder;

import android.content.Context;

import java.util.List;

/**
 * Created by pmachado on 8/24/15.
 * Isso é só um workaround para corrigir o erro de implementação no GenericDao
 * A solução correta é usar diretamente o Wonder.
 * Como estou com sono agora, amanhã eu corrijo.
 */
public class WonderDao  {
    GenericDao dao;

    WonderDao(Context context) {
        dao = new GenericDao(context);
    }

    public List<Wonder> getAll() {
        return  Wonder.fromListHashMap(dao.getAll());
    }

    public Wonder getById(int id) {
        return Wonder.fromHashMap(dao.getById(id));
    }

    public List<Wonder> search(String word) {
        return Wonder.fromListHashMap(dao.search(word));
    }

    public boolean delete(Wonder args) {
        return dao.delete(args.toHashMap());
    }

    public boolean update(Wonder values, int id) {
        return dao.update(values.toHashMap(), id);
    }

    boolean insert(Wonder value) {
        return dao.update(value.toHashMap(), value.id);
    }

    void close() {
        dao.close();
    }

}
