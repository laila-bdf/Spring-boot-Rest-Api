package com.valueit.springbootapp.exception;


import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults( level = AccessLevel.PRIVATE)
@Builder
public class ErrorMessage {

    String Message;
    Date time;
    Integer code;
}
