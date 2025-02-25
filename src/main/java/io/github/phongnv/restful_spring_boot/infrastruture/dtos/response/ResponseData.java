package io.github.phongnv.restful_spring_boot.infrastruture.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> implements Serializable {
    private int status;
    private T data;
    private String path;
    private String method;
    private Date timestamp = new Date();
}
