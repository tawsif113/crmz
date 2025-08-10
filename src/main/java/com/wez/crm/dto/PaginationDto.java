package com.wez.crm.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PaginationDto {

    private int pageNumber = 0;
    private int pageSize = 10;
    private String sortField = "id";
    private Sort.Direction direction = Sort.Direction.DESC;

    public Pageable toPageable() {
        return PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField));
    }
}
