package org.xvdr.recordings.model;

import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import org.xvdr.robotv.client.model.Event;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class SortedArrayObjectAdapter  extends ArrayObjectAdapter {

    private TreeSet<Object> mSortedItems;

    public SortedArrayObjectAdapter(Comparator comparator, Presenter presenter) {
        super(presenter);
        mSortedItems = new TreeSet<>(comparator);
    }

    @Override
    public void add(Object item) {
        mSortedItems.add(item);
        int index = mSortedItems.headSet(item).size();

        if(index > size()) {
            super.add(item);
            return;
        }

        super.add(index, item);
    }

    public void append(Object item) {
        super.add(item);
    }

    public void addAll(Collection items) {

        for(Object item : items) {
            add(item);
        }
    }

    @Override
    public boolean remove(Object item) {
        mSortedItems.remove(item);
        return super.remove(item);
    }

    @Override
    public void clear() {
        mSortedItems.clear();
        super.clear();
    }
}
