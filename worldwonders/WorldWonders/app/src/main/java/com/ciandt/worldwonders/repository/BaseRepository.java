package com.ciandt.worldwonders.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ciandt.worldwonders.database.Dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmachado on 8/27/15.
 */
public class BaseRepository <T> {
    protected Context context;
    protected Dao<T> dao;
    protected List<AsyncTask> tasks;

    public BaseRepository(Context context, Dao<T> dao) {
        this.context = context;
        tasks = new ArrayList<>();
        this.dao = dao;
    }

    @NonNull
    public void insert(final OnInsertListener insertListener, T data) {
        AsyncTask<T, Void, Boolean> asyncTask = new AsyncTask<T, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(T... params) {
                return insert(params[0]);
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

    protected Boolean insert(T data) {
        Boolean result = dao.insert(data);
        return result;
    }

    @NonNull
    public void delete(final OnDeleteListener deleteListener, T data) {
        AsyncTask<T, Void, Boolean> asyncTask = new AsyncTask<T, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(T... params) {
                return delete(params[0]);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                deleteListener.onDeleted(null, result);
                tasks.remove(this);
            }
        };

        tasks.add(asyncTask);
        asyncTask.execute(data);
    }

    protected Boolean delete(T data) {
        Boolean result = dao.delete(data);
        dao.close();
        return result;
    }

    @NonNull
    public void getAll(final OnGetAllListener getAllListener) {
        AsyncTask<Void, Void, List<T>> asyncTask = new AsyncTask<Void, Void, List<T>>() {
            @Override
            protected List<T> doInBackground(Void... params) {
                return getAll();
            }

            @Override
            protected void onPostExecute(List<T> data) {
                super.onPostExecute(data);
                getAllListener.onGetAll(null, data);
                tasks.remove(this);
            }
        };
        tasks.add(asyncTask);
        asyncTask.execute();
    }

    protected List<T> getAll() {
        List<T> result = dao.getAll();
        dao.close();
        return result;
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
}
