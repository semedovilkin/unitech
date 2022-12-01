package com.example.userms.constant;

import java.util.Arrays;
import java.util.List;

public class Constants {
    private Constants() {}

    public static final String VALID_PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?!.*[&%$]).{6,}$";
    public static final String VALID_PASSWORD_MESSAGE = "Your password include at " +
            "least one upper, one lower and one numeric character.";

    public static final String BEAN_FINN_HUB_NAME = "finnhub_trading_service";
    public static final String BEAN_REALTIME_FINN_HUB_PROXY_NAME = "realtime_finnhub_trading_proxy_service";

    public static final String BEAN_REALTIME_FINN_HUB_NAME_WEB_SOCKET = "realtime_finnhub_trading_service_ws";
    public static final String BEAN_REALTIME_FINN_HUB_NAME_HTTP_LONG_POLLING = "realtime_finnhub_trading_service_http";

    public static final String REAL_TIME_FINN_HUB_NAME_WEB_SOCKET = "finnhub_trading_live_ws";
    public static final String REAL_TIME_FINN_HUB_NAME_HTTP_LONG_POLLING = "finnhub_trading_live_http";

    public static final Integer TRANSACTION_BUY_VALUE = 1;
    public static final Integer TRANSACTION_SELL_VALUE = -1;
    public static final Integer TRANSACTION_UNKNOWN_VALUE = 0;

    public static final List<String> SAMPLE_STOCKS = Arrays.asList(
            "AAPL", "GOOG", "EFX", "LH", "IRM", "ZBRA"
    );

    public static final String HEADER_KEY_AUTHORIZATION = "Authorization";
    public static final String HEADER_VALUE_AUTHORIZATION_PREFIX = "Bearer ";
}
