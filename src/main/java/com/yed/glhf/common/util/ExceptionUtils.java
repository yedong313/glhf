package com.yed.glhf.common.util;

import com.yed.glhf.common.exception.ServiceException;

public class ExceptionUtils {
    public static void throwServiceException(String format, Object... args) {
        throw new ServiceException(String.format(format, args));
    }
}
