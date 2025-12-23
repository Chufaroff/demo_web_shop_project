package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigProperties;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    protected static final ConfigProperties config =
            ConfigFactory.create(ConfigProperties.class, System.getProperties());

    protected final String userEmail = config.getLogin();
    protected final String password = config.getPassword();

    @BeforeAll
    static void setUp() {

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = config.getTimeout();
        Configuration.pageLoadStrategy = config.getPageLoadStrategy();
        Configuration.holdBrowserOpen = config.getHoldBrowserOpen();


        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)      // Делать скриншоты
                .savePageSource(true)   // Сохранять HTML
                .includeSelenideSteps(true) // true - показывать все шаги Selenide
        );

        // Удаленный браузер (если передан в конфиге)
        String remoteUrl = config.getRemoteUrl();
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "enableLog", true,
                    "sessionTimeout", "5m"
            ));

            Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterEach
    void afterEach() {
        // Attachments проверяют сами, открыт ли браузер
        Attachments.screenshotAs("Final screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.testExecutionInfo(); // новый метод

        try {
            closeWebDriver();
        } catch (Exception e) {
            // Игнорируем для API тестов
        }
    }
}

