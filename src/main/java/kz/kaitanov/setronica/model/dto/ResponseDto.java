package kz.kaitanov.setronica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {

    private Integer statusCode;
    private String errorMessage;
    private Boolean success;
    private T data;

    public ResponseDto(Integer statusCode, Boolean success) {
        this.statusCode = statusCode;
        this.success = success;
    }

    public ResponseDto(Integer statusCode, Boolean success, T data) {
        this.statusCode = statusCode;
        this.success = success;
        this.data = data;
    }

    public ResponseDto(Integer statusCode, String errorMessage, Boolean success) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public static ResponseDto<Void> ok() {
        return new ResponseDto<>(HttpStatus.OK.value(), Boolean.TRUE);
    }

    public static <T> ResponseDto<T> ok(T data) {
        return new ResponseDto<>(HttpStatus.OK.value(), Boolean.TRUE, data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDto<?> that = (ResponseDto<?>) o;
        return Objects.equals(statusCode, that.statusCode) && Objects.equals(errorMessage, that.errorMessage) && Objects.equals(success, that.success) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, errorMessage, success, data);
    }

}