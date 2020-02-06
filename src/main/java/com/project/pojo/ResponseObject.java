package com.project.pojo;

import com.dsp.pojo.Trimmer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseObject {
    Trimmer trimmer;
    Exception expetion = null;
}
