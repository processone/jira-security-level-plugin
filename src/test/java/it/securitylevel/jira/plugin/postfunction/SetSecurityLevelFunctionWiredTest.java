package it.securitylevel.jira.plugin.postfunction;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import securitylevel.jira.plugin.postfunction.SetSecurityLevelFunction;

import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.atlassian.sal.api.ApplicationProperties;

@RunWith(AtlassianPluginsTestRunner.class)
public class SetSecurityLevelFunctionWiredTest {
    private final ApplicationProperties applicationProperties;
    private final SetSecurityLevelFunction function;

    public SetSecurityLevelFunctionWiredTest(ApplicationProperties applicationProperties,SetSecurityLevelFunction function)
    {
        this.applicationProperties = applicationProperties;
        this.function = function;
    }

    @Test
    @Ignore
    public void testExecute() {
        // TODO
    }

}
