package org.openjfx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {

    @Test
    void testgutschein() {

    }


    @Test
    void getBookingfromFlug() {
        System.out.println(App.db.getBookingfromFlug(835));;
        System.out.println(App.db.getBookingfromFlug(835).size());
    }
}