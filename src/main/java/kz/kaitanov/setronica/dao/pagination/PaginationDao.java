package kz.kaitanov.setronica.dao.pagination;

import java.util.List;
import java.util.Map;

public interface PaginationDao<T> {

    List<T> getItemsByParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters);

    Long getCountByParameters(Map<String, Object> parameters);

}