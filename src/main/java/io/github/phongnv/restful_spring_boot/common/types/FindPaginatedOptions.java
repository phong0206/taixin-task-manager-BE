package io.github.phongnv.restful_spring_boot.common.types;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class FindPaginatedOptions<T> extends FindOptions<T> {
  private Integer limit;
  private Integer page;
  private T filter;
}
