package com.satori.model.model;

import java.io.Serializable;

/**
 * @auth YanFuYou
 * @date 03/09/23 下午 10:04
 */
public class BasePageRequest implements Serializable {
    static final long serialVersionUID = 42L;

    private Integer page = 1;

    private Integer size = 10;

    public BasePageRequest() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
