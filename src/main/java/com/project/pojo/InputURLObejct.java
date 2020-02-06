package com.project.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InputURLObejct {
    public InputURLObejct(){}
    private String url;
    private String expireIn;
    private String unit;
}
