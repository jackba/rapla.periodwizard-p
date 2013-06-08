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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.MenuElement;

import org.rapla.entities.dynamictype.DynamicType;
import org.rapla.facade.CalendarModel;
import org.rapla.framework.RaplaContext;
import org.rapla.framework.RaplaException;
import org.rapla.gui.RaplaGUIComponent;
import org.rapla.gui.toolkit.IdentifiableMenuEntry;
import org.rapla.gui.toolkit.RaplaMenuItem;

/** The period-wizard-plugin eases the creation of reservations that repeat weekly
    in a given period. This is a very common usecase at universities and schools.
 */
public class PeriodReservationWizard extends RaplaGUIComponent implements IdentifiableMenuEntry ,ActionListener {

    public PeriodReservationWizard(RaplaContext sm) {
        super( sm);
        setChildBundleName( PeriodWizardPlugin.RESOURCE_FILE);
    }

	 public String getId() {
			return "200_periodWizard";
		}

		public MenuElement getMenuElement() {
			RaplaMenuItem item = new RaplaMenuItem( getId());
			item.setText(getString("reservation.create_with_default_wizard"));
			item.setIcon( getIcon("icon.new"));
			item.addActionListener( this);
			boolean canCreateReservation = canCreateReservation();
	        item.setEnabled( canAllocate() && canCreateReservation);
			return item;
		}
	    

		public void actionPerformed(ActionEvent e) {
			Component mainComponent = getMainComponent();
			try
			{
				CalendarModel model = getService(CalendarModel.class);
				WizardSequence sequence = new WizardSequence(getContext());
			    DynamicType type = model.guessNewEventType();
				sequence.start(mainComponent,model,type);
			}
			catch (RaplaException ex)
			{
				showException( ex, mainComponent);
			}
	    }
}


