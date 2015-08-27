package com.ciandt.worldwonders.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ciandt.worldwonders.database.Dao;
import com.ciandt.worldwonders.database.WonderBookmarkDao;
import com.ciandt.worldwonders.database.WonderDao;
import com.ciandt.worldwonders.model.Wonder;
import com.ciandt.worldwonders.model.WonderBookmark;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pmachado on 8/27/15.
 */
public class BaseRepository <T> {
    private Context context;
    private List<AsyncTask> tasks;
/*
    public BaseRepository(Context context) {
        this.context = context;
        tasks = new ArrayList<>();
    }

    @NonNull
    public void insert(final OnInsertListener insertListener, T data) {
        AsyncTask<T, Void, Boolean> asyncTask = new AsyncTask<T, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(T... params) {
                T bookmark = params[0];
                Dao<T> dao = new Dao<T>(context);
                boolean result = dao.insert(data);
                dao.close();
                return result;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                insertListener.onInserted(null, result);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);
        asyncTask.execute(data);
    }

    @NonNull
    public void delete(final WonderDeleteListener wonderDeleteListener, WonderBookmark bookmark) {
        AsyncTask<WonderBookmark, Void, Boolean> asyncTask = new AsyncTask<WonderBookmark, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(WonderBookmark... params) {
                WonderBookmark bookmark = params[0];
                Dao<WonderBookmark> bookmarkDao = new WonderBookmarkDao(context);
                boolean result = bookmarkDao.delete(bookmark);
                bookmarkDao.close();
                return result;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                wonderDeleteListener.onBookmarkDeleted(null, result);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);
        asyncTask.execute(bookmark);
    }

    @NonNull
    public void getAll(final WonderAllListener wonderAllListener) {
        AsyncTask<Void, Void, List<Wonder>> asyncTask = new AsyncTask<Void, Void, List<Wonder>>() {
            @Override
            protected List<Wonder> doInBackground(Void... params) {
                Dao<Wonder> wonderDao = new WonderDao(context);
                Dao<WonderBookmark> bookmarkDao = new WonderBookmarkDao(context);

                List<Wonder> result = wonderDao.getAll();
                List<WonderBookmark> bookmarks = bookmarkDao.getAll();

                Set<Integer> wonderMarked = new HashSet<>();

                for (WonderBookmark bookmark : bookmarks) {
                    wonderMarked.add(bookmark.idWonder);
                }

                for (Wonder wonder : result) {
                    wonder.isMarked = wonderMarked.contains(wonder.id);
                }

                wonderDao.close();
                bookmarkDao.close();

                return result;
            }

            @Override
            protected void onPostExecute(List<Wonder> wonders) {
                super.onPostExecute(wonders);
                wonderAllListener.onWonderAll(null, wonders);
                tasks.remove(this);
            }
        };
        tasks.add(asyncTask);
        asyncTask.execute();
    }

    public void cancel() {
        for (AsyncTask asyncTask : tasks) {
            if (!asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }
        }
    }

    public interface OnGetAllListener<T> {
        void onGetAll(Exception e, List<T> list);
    }

    public interface OnInsertListener {
        void onInserted(Exception e, boolean result);
    }

    public interface OnDeleteListener {
        void onDeleted(Exception e, boolean result);
    }
    */
}
