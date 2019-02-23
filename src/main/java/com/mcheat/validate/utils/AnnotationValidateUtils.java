package com.mcheat.validate.utils;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

/**
 * 验证注解信息
 *
 * @Author McHeat
 * @Date 2019/01/31
 * @Version 1.0.0
 */
@Slf4j
public class AnnotationValidateUtils {

    public static <T> void validate(T obj) {
        if (obj == null) {
            throw new RuntimeException("参数为空");
        }

        ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = vFactory.getValidator();
        Set resultSet = validator.validate(obj);
        if (resultSet.size() > 0) {
            for (Iterator<ConstraintViolation> itr = resultSet.iterator(); itr.hasNext(); ) {
                ConstraintViolation cv = itr.next();
                Annotation an = cv.getConstraintDescriptor().getAnnotation();
                if (an instanceof NotNull ||
                        an instanceof NotEmpty ||
                        an instanceof NotBlank) {
                    throw new RuntimeException("参数字段XXX为空");
                } else {
                    throw new RuntimeException("参数异常");
                }
            }
        }
    }

}
