package utils

import org.openqa.selenium.Capabilities
import org.openqa.selenium.Platform
import org.openqa.selenium.firefox.FirefoxBinary
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

class TestUtils {

    fun getCurrentWorkingDirectory(): Path {
        return Paths.get(".").toAbsolutePath().normalize()
    }

    fun configureFirefoxWebDriver(driversFolder: Path): FirefoxDriver {
        val pathToBinary = File("/usr/bin/firefox")
        val firefoxBinary = FirefoxBinary(pathToBinary)
        var driverPath = driversFolder
        val profile = FirefoxProfile()

        val OS = System.getProperty("os.name").toLowerCase()
        if (OS.indexOf("win") >= 0) {
            driverPath = driverPath.resolve("src/test/resources/libs/geckodriver.exe")
        } else driverPath = driverPath.resolve("src/test/resources/libs/geckodriverLin")

        System.setProperty("webdriver.gecko.driver", driverPath.toAbsolutePath().toString())
        val cap = DesiredCapabilities()
        cap.setCapability("marionette", true)
        val firefoxOptions = FirefoxOptions(cap)
        firefoxOptions.binary = firefoxBinary

        //------- Пока не надо ---------//
//        profile.setPreference("browser.download.folderList", 2)
//        profile.setPreference("browser.download.dir", "C:\\Test\\")
//        profile.setPreference("browser.download.manager.alertOnEXEOpen", false)
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/x-icon")
//        profile.setPreference("browser.download.manager.showWhenStarting", false)
//        profile.setPreference("browser.download.manager.focusWhenStarting", false)
//        profile.setPreference("browser.download.useDownloadDir", true)
//        profile.setPreference("browser.helperApps.alwaysAsk.force", false)
//        profile.setPreference("browser.download.manager.alertOnEXEOpen", false)
//        profile.setPreference("browser.download.manager.closeWhenDone", true)
//        profile.setPreference("browser.download.manager.showAlertOnComplete", false)
//        profile.setPreference("browser.download.manager.useWindow", false)
//        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false)

        return FirefoxDriver(firefoxOptions)
    }
}