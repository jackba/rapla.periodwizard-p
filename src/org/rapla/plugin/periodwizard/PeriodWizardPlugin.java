/*--------------------------------------------------------------------------*
 | Copyright (C) 2006 Christopher Kohlhaas                                  |
 |                                                                          |
 | This program is free software; you can redistribute it and/or modify     |
 | it under the terms of the GNU General Public License as published by the |
 | Free Software Foundation. A copy of the license has been included with   |
 | these distribution in the COPYING file, if not go to www.fsf.org         |
 |                                                                          |
 | As a special exception, you are granted the permissions to link this     |
 | program with every library, which license fulfills the Open Source       |
 | Definition as published by the Open Source Initiative (OSI).             |
 *--------------------------------------------------------------------------*/
package org.rapla.plugin.periodwizard;

import org.rapla.client.RaplaClientExtensionPoints;
import org.rapla.components.xmlbundle.I18nBundle;
import org.rapla.components.xmlbundle.impl.I18nBundleImpl;
import org.rapla.framework.Configuration;
import org.rapla.framework.Container;
import org.rapla.framework.PluginDescriptor;
import org.rapla.framework.TypedComponentRole;

public class PeriodWizardPlugin implements PluginDescriptor {
    public static final TypedComponentRole<I18nBundle> RESOURCE_FILE = new TypedComponentRole<I18nBundle>(PeriodWizardPlugin.class.getPackage().getName() + ".WizardResources");
    public static final String PLUGIN_CLASS = PeriodWizardPlugin.class.getName();
    static boolean ENABLE_BY_DEFAULT = false;
    
    public String toString() 
    {
        return "Create Reservations in Periods";
    }
    
    public void provideServices(Container container, Configuration config) {
    	if ( !config.getAttributeAsBoolean("enabled", ENABLE_BY_DEFAULT) )
        	return;

        container.addContainerProvidedComponent( RESOURCE_FILE, I18nBundleImpl.class,I18nBundleImpl.createConfig( RESOURCE_FILE.getId() ) );
        container.addContainerProvidedComponent( RaplaClientExtensionPoints.RESERVATION_WIZARD_EXTENSION, PeriodReservationWizard.class);
    }

    
    
}

