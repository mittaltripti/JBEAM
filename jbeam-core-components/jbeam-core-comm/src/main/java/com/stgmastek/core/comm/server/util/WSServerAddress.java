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
package com.stgmastek.core.comm.server.util;

import com.stgmastek.core.comm.util.CommConstants;

/**
 * 
 * The server address information class
 * 
 * @author grahesh.shanbhag
 *
 */
public class WSServerAddress {

	/** The host where the server is to be hosted (or where the server is hosted) */
	private String host;
	
	/** The port where the server is to be launched (or where the server is launched) */
	private Integer port;
	
	/** The end point domain where the server is to be accessed (or domain from where to access the service) */
	private String domain;
	
	/**
	 * Default Constructor
	 */
	public WSServerAddress(){}
	
	/**
	 * Overloaded constructor to take only the end point domain and use the default host and 
	 * port as set in the constants file. 
	 * @see CommConstants
	 * 
	 * @param domain
	 * 		  The end point domain to set
	 */
	public WSServerAddress(String domain){
		setHost(CommConstants.DEFAULT_HOST);
		setPort(CommConstants.DEFAULT_PORT);
		setDomain(domain);
	}
	
	/**
	 * Returns the host 
	 * 
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * Sets the host
	 * 
	 * @param host
	 * 		  The host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Returns the port 
	 * 
	 * @return the port
	 */	
	public Integer getPort() {
		return port;
	}
	
	/**
	 * Sets the port 
	 * 
	 * @param port
	 * 		  The port to set
	 */	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	/**
	 * Returns the host 
	 * 
	 * @return the host
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Sets the domain 
	 * 
	 * @param domain
	 * 		  The domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	/**
	 * Helper method to construct the URL based on the supplied information
	 * 
	 * @return the URL as string
	 */
	public String getAddressURL(){
		return "https://" + getHost() + ":" + getPort()+ "/" + getDomain();
	}
	
	
}

/*
* Revision Log
* -------------------------------
* $Log:: /Product_Base/Projects/Batch/Code/Java/CoreComm/src/com/stgmastek/core/comm/server/util/WSServerAddress.java                                                                $
 * 
 * 4     6/22/10 5:31p Mandar.vaidya
 * SSL Implementation
 * 
 * 3     12/30/09 12:15p Mandar.vaidya
 * Removed warnings
 * 
 * 2     12/17/09 11:55a Grahesh
 * Initial Version
*
*
*/