package org.openmrs.module.facilityreporting.metadata;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.metadatadeploy.bundle.Requires;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.idSet;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.privilege;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.role;

/**
 * Implementation of access control to the app.
 */
@Component
public class FacilityReportingSecurityMetadata extends AbstractMetadataBundle {
	
	public static class _Privilege {
		
		public static final String APP_AIR_ADMIN = "App: facilityReporting.home";
	}
	
	public static final class _Role {
		
		public static final String API_PRIVILEGES_VIEW_AND_EDIT = "API Privileges (View and Edit)";
		
		public static final String APPLICATION_AIR = "AIR Administration";
	}
	
	/**
	 * @see AbstractMetadataBundle#install()
	 */
	@Override
	public void install() {
		
		install(privilege(_Privilege.APP_AIR_ADMIN, "Able to enter additional indicators for AIR"));
		install(role(_Role.APPLICATION_AIR, "Can access AIR app", idSet(_Role.API_PRIVILEGES_VIEW_AND_EDIT),
		    idSet(_Privilege.APP_AIR_ADMIN)));
	}
}
