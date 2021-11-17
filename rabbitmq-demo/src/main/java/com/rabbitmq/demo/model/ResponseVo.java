package com.rabbitmq.demo.model;

import java.io.Serializable;

/**
 * @author honglixiang@tiduyun.com
 * @date 2021/6/9
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ResponseVo implements Serializable {

    private String status;

    private String name;

    public ResponseVo(){

    }

    public ResponseVo(String status,String name){
        this.name = name;
        this.status =status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
