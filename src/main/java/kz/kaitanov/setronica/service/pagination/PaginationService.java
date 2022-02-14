package kz.kaitanov.setronica.service.pagination;

import kz.kaitanov.setronica.model.dto.PageDto;

import java.util.Map;

public interface PaginationService<T> {

    PageDto<T> getPageDtoByParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters);

}