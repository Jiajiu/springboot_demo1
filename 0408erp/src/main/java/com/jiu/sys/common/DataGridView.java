package com.jiu.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DataGridView
 * @Author Jiu
 * @Create 2020/4/9 11:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Integer code=0;
    private String msg="";
    private Long count=0L;
    private Object data;

    public DataGridView(Long count, Object data) {
//        super();
        this.count = count;
        this.data = data;
    }

    public DataGridView(Object data) {
//        super();
        this.data = data;
    }
}
