package securitylevel.jira.plugin.postfunction;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.security.IssueSecurityLevel;
import com.atlassian.jira.issue.security.IssueSecurityLevelManager;
import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.event.type.EventDispatchOption;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;

import java.util.Collection;
import java.util.Map;

public class SetSecurityLevelFunction implements FunctionProvider {

	private final IssueSecurityLevelManager issueSecurityLevelManager = ComponentAccessor
			.getIssueSecurityLevelManager();

	private final GroupManager groupManager = ComponentAccessor
			.getGroupManager();

	@SuppressWarnings("rawtypes")
	public void execute(Map transientVars, Map args, PropertySet ps) {

		MutableIssue issue = (MutableIssue) transientVars.get("issue");
		User reporter = issue.getReporterUser();
		Collection<String> reporterGroups = groupManager
				.getGroupNamesForUser(reporter.getName());

		try {
			Collection<IssueSecurityLevel> issueSecurityLevels = issueSecurityLevelManager
					.getUsersSecurityLevels(issue.getProjectObject(), reporter);

			for (String groupName : reporterGroups) {
				
				for (IssueSecurityLevel securityLevel : issueSecurityLevels) {

					String securityLevelName = securityLevel.getName();

					if (securityLevelName.startsWith("#")
							&& securityLevelName.subSequence(1,
									securityLevelName.length()).equals(
									groupName)) {
						issue.setSecurityLevelId(securityLevel.getId());
						saveIssue(issue);
						return;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void saveIssue(MutableIssue issue) {
		
		IssueManager issueManager = ComponentAccessor.getIssueManager();
		User user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();
		boolean sendEmail = false;
		
		issueManager.updateIssue(user, issue, EventDispatchOption.DO_NOT_DISPATCH, sendEmail);
	}
}
