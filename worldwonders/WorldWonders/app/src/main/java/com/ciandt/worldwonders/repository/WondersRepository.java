package com.ciandt.worldwonders.repository;

import android.content.Context;

import com.ciandt.worldwonders.database.Dao;
import com.ciandt.worldwonders.database.WonderBookmarkDao;
import com.ciandt.worldwonders.database.WonderDao;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.model.WonderBookmark;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pmachado on 8/24/15.
 */
public class WondersRepository extends BaseRepository<Wonder> {

    public WondersRepository(Context context) {
        super(context, new WonderDao(context));
    }

    @Override
    protected List<Wonder> getAll() {
        Dao<WonderBookmark> bookmarkDao = new WonderBookmarkDao(context);

        List<Wonder> result = dao.getAll();
        List<WonderBookmark> bookmarks = bookmarkDao.getAll();

        Set<Integer> wonderMarked = new HashSet<>();

        for (WonderBookmark bookmark : bookmarks) {
            wonderMarked.add(bookmark.idWonder);
        }

        for (Wonder wonder : result) {
            wonder.isMarked = wonderMarked.contains(wonder.id);
        }

        bookmarkDao.close();
        dao.close();

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }

        return result;
    }
}
