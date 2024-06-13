package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = "html:target/reports/cucumberReport.html",
        features = "src/test/resources/InvoiceCreate.feature",
        glue = "steps",
        tags = "invoicePart",
        dryRun = true

)

public class CucumberRunnerCreateInvoice extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
