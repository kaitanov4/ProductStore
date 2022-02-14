package kz.kaitanov.setronica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageDto<T> {

    private Long count;
    private List<T> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageDto<?> pageDto = (PageDto<?>) o;
        return Objects.equals(count, pageDto.count) && Objects.equals(items, pageDto.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, items);
    }

}