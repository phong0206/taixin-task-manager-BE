package io.github.phongnv.restful_spring_boot.infrastruture.common.types;

import lombok.Data;

@Data
public class FindOrFailOptions<T> extends FindOptions<T> {
  private String errorMessage;
}