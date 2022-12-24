package org.helei.retinalsegmentation.converter;

import org.helei.retinalsegmentation.utils.UserIDBase64;

public class ConvertException {

    public static String userId2StrId(Long id) {
        return UserIDBase64.encoderUserID(id);
    }
    public static Long strId2UserId(String strId) {
        return UserIDBase64.decoderUserID(strId);
    }
}
