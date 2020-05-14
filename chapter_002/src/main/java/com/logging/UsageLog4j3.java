package com.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j3 {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte by = 1;
        short sh = 33;
        int in = 2048;
        long lo = in << 7;
        float fl = 45F;
        double db = fl * 31;
        char ch = 'd';
        boolean bo = true;
        LOG.debug("byte : {}, short : {}, int : {}, long : {}", by, sh, in, lo);
        LOG.debug("float : {}, double : {}", fl, db);
        LOG.debug("char : {}, boolean : {}", ch, bo);
    }
}
