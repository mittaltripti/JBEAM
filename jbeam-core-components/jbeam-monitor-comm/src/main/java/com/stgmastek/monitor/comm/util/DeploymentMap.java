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
package com.stgmastek.monitor.comm.util;

import com.stgmastek.monitor.comm.server.base.ServiceBase;

/**
 * Class to hold the deployment of the services related information 
 * 
 * @author grahesh.shanbhag
 *
 */
public class DeploymentMap{
	
	/** The service name as used in the system USER_SERVICES */	
	private String serviceName;
	
	/** The service interface class */
	private Class<? extends ServiceBase> serviceInterface;
	
	/** The service implementation class */
	private Class<?> serviceImpl;
	
	/** IP Address */	
	private String address;
	
	/** Port */	
	private String port;

	/**
	 * Returns the serviceName
	 * 
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Sets the serviceName
	 * 
	 * @param serviceName 
	 *        The serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Returns the serviceInterface
	 * 
	 * @return the serviceInterface
	 */
	public Class<? extends ServiceBase> getServiceInterface() {
		return serviceInterface;
	}

	/**
	 * Sets the serviceInterface
	 * 
	 * @param serviceInterface 
	 *        The serviceInterface to set
	 */
	public void setServiceInterface(Class<? extends ServiceBase> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	/**
	 * Returns the serviceImpl
	 * 
	 * @return the serviceImpl
	 */
	public Class<?> getServiceImpl() {
		return serviceImpl;
	}

	/**
	 * Sets the serviceImpl
	 * 
	 * @param serviceImpl 
	 *        The serviceImpl to set
	 */
	public void setServiceImpl(Class<?> serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	/**
	 * Returns the address
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address
	 * 
	 * @param address 
	 *        The address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the port
	 * 
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Sets the port
	 * 
	 * @param port 
	 *        The port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	
	
}

/*
* Revision Log
* -------------------------------
* $Log:: /Product_Base/Projects/Batch/Code/Java/MonitorComm/src/com/stgmastek/monitor/comm/util/DeploymentMap.java                                                                   $
 * 
 * 3     12/21/09 10:20a Grahesh
 * Updated the comments
 * 
 * 2     12/17/09 11:59a Grahesh
 * Initial Version
*
*
*/