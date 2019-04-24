package com.ocean.rabbitmq;

import lombok.Data;

import java.io.Serializable;

@Data
public class MailEntity implements Serializable {

    private static final long serialVersionUID = -2164058270260403154L;

    private String id;
    private String name;
    private String title;
    private String content;

}
