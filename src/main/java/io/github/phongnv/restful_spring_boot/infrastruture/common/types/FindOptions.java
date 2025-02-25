package io.github.phongnv.restful_spring_boot.infrastruture.common.types;

import lombok.Data;
import org.springframework.data.domain.Sort;
import java.util.List;

@Data
public class FindOptions<T> {
  private T where;
  private Sort order;
  private List<String> relations;
  private Boolean loadEagerRelations;
  private Boolean withDeleted;
  private List<String> select;
}
