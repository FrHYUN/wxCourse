package com.example.demo.utill;

import java.io.Serializable;
import java.util.List;

public class pageResult  extends Result implements Serializable{
    //总计数目
    private long total;

    //分页的数据
    private List rows;

    public pageResult(long total, List list) {
        this.setCode(0);
        this.setMsg("分页查询成功");
        this.total = total;
        this.rows = list;
    }
}
