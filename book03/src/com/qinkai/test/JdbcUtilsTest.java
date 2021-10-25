package com.qinkai.test;

import com.qinkai.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils() {

        for (int i = 0; i < 100; i++) {
            Connection con = JdbcUtils.getConnection();
            System.out.println(con);
            JdbcUtils.close(con);
        }

    }
}
