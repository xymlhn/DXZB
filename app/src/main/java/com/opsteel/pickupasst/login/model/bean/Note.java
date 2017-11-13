package com.opsteel.pickupasst.login.model.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * 文 件 名: Note
 * 创 建 人: Cartman
 * 创建日期: 2017/11/2 16:28
 * 邮   箱: javaxieyongming@gmail.com
 * 公   司: 欧浦智网股份有限公司
 * 描   述：
 */

@Entity
public class Note {
    @Id(assignable = true)
    private long id;
    private String text;

    public Note(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
