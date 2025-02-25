package io.github.phongnv.restful_spring_boot.infrastruture.common.types;

import lombok.Data;

@Data
public class FindPaginatedOptions<T> extends FindOptions<T> {
  private Integer limit;
  private Integer page;
  private T filter;
}
