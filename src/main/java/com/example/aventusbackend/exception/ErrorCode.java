package com.example.aventusbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    //    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    EMPTY_EMAIL(1001, "Email không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1002, "Email không hợp lệ", HttpStatus.BAD_REQUEST),
    EMPTY_PASSWORD(1003, "Password không được để trống", HttpStatus.BAD_REQUEST),
    EMPTY_NAME(1004, "Tên không được để trống", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1005, "Tên đăng nhập đã tồn tại", HttpStatus.BAD_REQUEST),
    EMPTY_COMPANY_NAME(1006, "Tên công ty không được để trống", HttpStatus.BAD_REQUEST),
    EMPTY_ADDRESS(1007, "Địa chỉ không được để trống", HttpStatus.BAD_REQUEST),
    EMPTY_WARD(1008, "Phường, Xã không được để trống", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1009, "Người dùng không tồn tại", HttpStatus.NOT_FOUND),
    WRONG_PASSWORD(1010, "Sai mật khẩu", HttpStatus.BAD_REQUEST),
    INVALID_PROVINCE(1011, "Tỉnh không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_DISTRICT(1012, "Quận, huyện không hợp lệ", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1013, "Email đã tồn tại", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1014, "Ngày sinh không hợp lệ", HttpStatus.BAD_REQUEST),
    EMPTY_OLD_PASSWORD(1015, "Mật khẩu cũ không được để trống", HttpStatus.BAD_REQUEST),
    EMPTY_NEW_PASSWORD(1016, "Mật khẩu mới không được để trống", HttpStatus.BAD_REQUEST),
    EMPTY_JOB_NAME(1017, "Tên công việc không được để trống", HttpStatus.BAD_REQUEST),
    NULL_CAREER(1018, "Ngành nghề không được để trống", HttpStatus.BAD_REQUEST),
    NULL_REQUIRE_DEGREE(1019, "Yêu cầu bằng cấp không được để trống", HttpStatus.BAD_REQUEST),
    NULL_REQUIRE_EXPERIENCE(1020, "Yêu cầu số năm kinh nghiệm không được để trống", HttpStatus.BAD_REQUEST),
    NULL_MIN_OFFER(1001, "Mức lương tối thiểu không được để trống", HttpStatus.BAD_REQUEST),
//    NULL_MAX_OFFER(1001, "Mức lương tối thiểu không được để trống", HttpStatus.BAD_REQUEST),
    EMPTY_JOB_DESCRIPTION(1023, "Mô tả công việc không được để trống", HttpStatus.BAD_REQUEST),
    EMPTY_JOB_REQUIREMENT(1024, "Yêu cầu ứng viên không được để trống", HttpStatus.BAD_REQUEST),
    EMPTY_JOB_BENEFIT(1025, "Quyền lợi không được để trống", HttpStatus.BAD_REQUEST),
    INVALID_DEADLINE(1026, "Hạn nộp hồ sơ không hợp lệ", HttpStatus.BAD_REQUEST),
    JOB_NOT_EXISTED(1027, "Việc làm không tồn tại", HttpStatus.NOT_FOUND),
    NULL_MAJOR(1028, "Chuyên ngành không được để trống", HttpStatus.BAD_REQUEST),
    NULL_DEGREE(1028, "Bằng cấp không được để trống", HttpStatus.BAD_REQUEST),
    NULL_EXPERIENCE(1029, "Kinh nghiệm không được để trống", HttpStatus.BAD_REQUEST),
    NULL_EXPECTED_OFFER(1030, "Mức lương mong muốn không được để trống", HttpStatus.BAD_REQUEST),


    ;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

}
