/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Innar Made
 ******************************************************************************/
package org.camunda.bpm.modeler.ui.features.gateway;

import org.camunda.bpm.modeler.core.features.gateway.AbstractCreateGatewayFeature;
import org.camunda.bpm.modeler.core.features.gateway.AddGatewayFeature;
import org.camunda.bpm.modeler.core.utils.GraphicsUtil;
import org.camunda.bpm.modeler.ui.ImageProvider;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class InclusiveGatewayFeatureContainer extends AbstractGatewayFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof InclusiveGateway;
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		
		return new AddGatewayFeature<InclusiveGateway>(fp) {
			
			@Override
			protected void decorate(ContainerShape container) {
				Ellipse ellipse = GraphicsUtil.createGatewayOuterCircle(container);
				ellipse.setLineWidth(3);
			}
		};
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateInclusiveGatewayFeature(fp);
	}

	public static class CreateInclusiveGatewayFeature extends AbstractCreateGatewayFeature<InclusiveGateway> {

		public CreateInclusiveGatewayFeature(IFeatureProvider fp) {
			super(fp, "Inclusive Gateway", "Used for creating alternative button also parallel paths");
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_INCLUSIVE_GATEWAY;
		}

		/* (non-Javadoc)
		 * @see org.camunda.bpm.modeler.features.AbstractCreateFlowElementFeature#getFlowElementClass()
		 */
		@Override
		public EClass getBusinessObjectClass() {
			return Bpmn2Package.eINSTANCE.getInclusiveGateway();
		}
	}
}