package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/app.properties"
})
public interface ConfigProperties extends Config {

    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();

    @Key("baseUrl")
    String getBaseUrl();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("timeout")
    @DefaultValue("5000")
    Long getTimeout();

    @Key("pageLoadStrategy")
    @DefaultValue("eager")
    String getPageLoadStrategy();

    @Key("holdBrowserOpen")
    @DefaultValue("false")
    Boolean getHoldBrowserOpen();
}
