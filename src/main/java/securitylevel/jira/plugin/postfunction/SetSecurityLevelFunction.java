package securitylevel.jira.plugin.postfunction;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.security.IssueSecurityLevel;
import com.atlassian.jira.issue.security.IssueSecurityLevelManager;
import com.atlassian.jira.security.groups.GroupManager;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class SetSecurityLevelFunction implements FunctionProvider {

	private final IssueSecurityLevelManager issueSecurityLevelManager = 
			ComponentAccessor.getIssueSecurityLevelManager();
 
    private final GroupManager groupManager = 
    		ComponentAccessor.getGroupManager();

	public void execute(Map transientVars, Map args, PropertySet ps) {


		MutableIssue issue = (MutableIssue) transientVars.get("issue");
		User reporter = issue.getReporterUser();
        Collection<String> reporterGroups = 
        		groupManager.getGroupNamesForUser(reporter.getName());

		try {
			Collection<IssueSecurityLevel> issueSecurityLevels 
				= issueSecurityLevelManager.getUsersSecurityLevels(issue.getProjectObject(), reporter);
			
			groups:
			for (Iterator<String> iterator = reporterGroups.iterator(); iterator.hasNext();) {

				String groupName = (String) iterator.next();
			
				for (Iterator<IssueSecurityLevel> iterator1 = 
						issueSecurityLevels.iterator(); iterator1.hasNext();) {
				
					IssueSecurityLevel securityLevel = iterator1.next();
					String s = securityLevel.getName();

					if (s.startsWith("#") && s.subSequence(1, s.length()).equals(groupName)) {
						issue.setSecurityLevelId(securityLevel.getId());
						issue.store();
						break groups;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
		
	}

}
