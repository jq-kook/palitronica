package com.palitronica.payment.util;

import org.bson.types.Decimal128;

public interface Constants {

    String MONGO_DUPLICATE_KEY_EXCEPTION_CODE = "E11000";
    String TAX_JAR_API_TOKEN = "fba1083ed9ff76d82a582da6430f5097";

    String FIRST_CUSTOMER_ID = "CUS_001";
    String FIRST_CUSTOMER_NAME = "Jin Kyu";
    String FIRST_CUSTOMER_COUNTRY_CODE = "CA";
    String FIRST_CUSTOMER_CITY = "North York";
    String FIRST_CUSTOMER_ZIPCODE = "M2J0E3";

    String SECOND_CUSTOMER_ID = "CUS_002";
    String SECOND_CUSTOMER_NAME = "Aurora";
    String SECOND_CUSTOMER_COUNTRY_CODE = "CA";
    String SECOND_CUSTOMER_CITY = "Yellowknife";
    String SECOND_CUSTOMER_ZIPCODE = "X1A3T2";

    String THIRD_CUSTOMER_ID = "CUS_003";
    String THIRD_CUSTOMER_NAME = "Luke Skywalker";
    String THIRD_CUSTOMER_COUNTRY_CODE = "US";
    String THIRD_CUSTOMER_CITY = "Los Angeles";
    String THIRD_CUSTOMER_ZIPCODE = "R2D2";

    String FIRST_ITEM_ID = "ITM_001";
    String FIRST_ITEM_NAME = "Froot Loops";
    Decimal128 FIRST_ITEM_PRICE = Decimal128.parse("4.99");
    String FIRST_ITEM_IMAGE_URL = "https://m.media-amazon.com/images/I/91z7161nooL._AC_SY679_.jpg";

    String SECOND_ITEM_ID = "ITM_002";
    String SECOND_ITEM_NAME = "Chex Choco";
    Decimal128 SECOND_ITEM_PRICE = Decimal128.parse("4.49");
    String SECOND_ITEM_IMAGE_URL = "https://m.media-amazon.com/images/I/91YEBE2BgtL._AC_SX679_.jpg";

    String THIRD_ITEM_ID = "ITM_003";
    String THIRD_ITEM_NAME = "Cheerios Honey Nut";
    Decimal128 THIRD_ITEM_PRICE = Decimal128.parse("9.99");
    String THIRD_ITEM_IMAGE_URL = "https://m.media-amazon.com/images/I/81hpHCFCqCL._AC_SL1500_.jpg";

    String FOURTH_ITEM_ID = "ITM_004";
    String FOURTH_ITEM_NAME = "Quaker Harvest Crunch";
    Decimal128 FOURTH_ITEM_PRICE = Decimal128.parse("7.46");
    String FOURTH_ITEM_IMAGE_URL = "https://m.media-amazon.com/images/I/71ALLsKgOKL._AC_SL1200_.jpg";

}
