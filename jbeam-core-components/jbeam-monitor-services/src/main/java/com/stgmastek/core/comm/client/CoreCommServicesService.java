/*
 * Copyright (c) 2014 Mastek Ltd. All rights reserved.
 * 
 * This file is part of JBEAM. JBEAM is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation.
 *
 * JBEAM is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General
 * Public License for the specific language governing permissions and 
 * limitations.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with JBEAM. If not, see <http://www.gnu.org/licenses/>.
 */

package com.stgmastek.core.comm.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.1.3
 * Mon Jan 28 16:40:36 IST 2013
 * Generated source version: 2.1.3
 * 
 */


@WebServiceClient(name = "CoreCommServicesService", 
                  wsdlLocation = "http://172.16.246.108:11001/CoreCommServices?wsdl",
                  targetNamespace = "http://services.server.comm.core.stgmastek.com/") 
public class CoreCommServicesService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://services.server.comm.core.stgmastek.com/", "CoreCommServicesService");
    public final static QName CoreCommServicesPort = new QName("http://services.server.comm.core.stgmastek.com/", "CoreCommServicesPort");
    static {
        URL url = null;
        try {
            url = new URL("http://172.16.246.108:11001/CoreCommServices?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://172.16.246.108:11001/CoreCommServices?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public CoreCommServicesService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CoreCommServicesService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CoreCommServicesService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns CoreCommServices
     */
    @WebEndpoint(name = "CoreCommServicesPort")
    public CoreCommServices getCoreCommServicesPort() {
        return super.getPort(CoreCommServicesPort, CoreCommServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CoreCommServices
     */
    @WebEndpoint(name = "CoreCommServicesPort")
    public CoreCommServices getCoreCommServicesPort(WebServiceFeature... features) {
        return super.getPort(CoreCommServicesPort, CoreCommServices.class, features);
    }

}