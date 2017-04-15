/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2016 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.github.wesleyegberto.adoptajsr.jsfchat;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.inject.Singleton;

import com.sun.faces.cdi.ApplicationMapProducer;
import com.sun.faces.cdi.ApplicationProducer;
import com.sun.faces.cdi.ComponentProducer;
import com.sun.faces.cdi.CompositeComponentProducer;
import com.sun.faces.cdi.ExternalContextProducer;
import com.sun.faces.cdi.FacesContextProducer;
import com.sun.faces.cdi.FlashProducer;
import com.sun.faces.cdi.FlowMapProducer;
import com.sun.faces.cdi.HeaderMapProducer;
import com.sun.faces.cdi.HeaderValuesMapProducer;
import com.sun.faces.cdi.InitParameterMapProducer;
import com.sun.faces.cdi.RequestCookieMapProducer;
import com.sun.faces.cdi.RequestMapProducer;
import com.sun.faces.cdi.RequestParameterMapProducer;
import com.sun.faces.cdi.RequestParameterValuesMapProducer;
import com.sun.faces.cdi.RequestProducer;
import com.sun.faces.cdi.ResourceHandlerProducer;
import com.sun.faces.cdi.SessionMapProducer;
import com.sun.faces.cdi.SessionProducer;
import com.sun.faces.cdi.ViewMapProducer;
import com.sun.faces.cdi.ViewProducer;


/**
 * The CDI extension.
 */
@Singleton
public class CdiExtension implements Extension {

    public CdiExtension() {
	System.out.println("[CdiExtension] constructor");
    }
    
    /**
     * After bean discovery.
     *
     * @param afterBeanDiscovery the after bean discovery.
     */
    public void afterBean(final @Observes AfterBeanDiscovery afterBeanDiscovery, BeanManager beanManager) {
	System.out.println("[CdiExtension] Adding custom producers");
        afterBeanDiscovery.addBean(new ApplicationProducer());
        afterBeanDiscovery.addBean(new ApplicationMapProducer());
        afterBeanDiscovery.addBean(new CompositeComponentProducer());
        afterBeanDiscovery.addBean(new ComponentProducer());
        afterBeanDiscovery.addBean(new FlashProducer());
        afterBeanDiscovery.addBean(new FlowMapProducer());
        afterBeanDiscovery.addBean(new HeaderMapProducer());
        afterBeanDiscovery.addBean(new HeaderValuesMapProducer());
        afterBeanDiscovery.addBean(new InitParameterMapProducer());
        afterBeanDiscovery.addBean(new RequestParameterMapProducer());
        afterBeanDiscovery.addBean(new RequestParameterValuesMapProducer());
        afterBeanDiscovery.addBean(new RequestProducer());
        afterBeanDiscovery.addBean(new RequestMapProducer());
        afterBeanDiscovery.addBean(new ResourceHandlerProducer());
        afterBeanDiscovery.addBean(new ExternalContextProducer());
        afterBeanDiscovery.addBean(new FacesContextProducer());
        afterBeanDiscovery.addBean(new RequestCookieMapProducer());
        afterBeanDiscovery.addBean(new SessionProducer());
        afterBeanDiscovery.addBean(new SessionMapProducer());
        afterBeanDiscovery.addBean(new ViewMapProducer());
        afterBeanDiscovery.addBean(new ViewProducer());

    }

}
