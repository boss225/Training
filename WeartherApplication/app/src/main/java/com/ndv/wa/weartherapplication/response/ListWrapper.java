package com.ndv.wa.weartherapplication.response;

import java.util.List;

/**
 * Created by admin on 11/21/2017.
 */

public class ListWrapper<T> {
    List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
