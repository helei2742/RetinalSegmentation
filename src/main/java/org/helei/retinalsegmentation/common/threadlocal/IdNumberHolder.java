package org.helei.retinalsegmentation.common.threadlocal;



/**
 * ThreadLocal操作工具
 */
public class IdNumberHolder {
    private static final ThreadLocal<String> tl = new ThreadLocal<>();

    public static void saveIdNumber(String id){
        tl.set(id);
    }

    public static String getIdNumber(){
        return tl.get();
    }

    public static void removeIdNumber(){
        tl.remove();
    }
}
