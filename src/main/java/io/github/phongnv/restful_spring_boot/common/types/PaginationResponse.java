package io.github.phongnv.restful_spring_boot.common.types;

import lombok.Data;
import java.util.List;

@Data
public class PaginationResponse<T> {
  private List<T> data;
  private Pagination pagination;

  @Data
  public static class Pagination {
    private Integer limit;
    private Integer page;
    private Integer total;
  }
}
