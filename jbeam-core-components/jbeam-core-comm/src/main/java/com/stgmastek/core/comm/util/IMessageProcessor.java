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
package com.stgmastek.core.comm.util;

/**
 * Interface to identify message processors in the system
 * 
 * Contains the declaration of method that would actually process the message.
 * The classes that implement this class are pooled for performance.
 * 
 *  @author grahesh.shanbhag
 */
public interface IMessageProcessor {
	
	/**
	 * This method processes the supplied message
	 * 
	 * @param message
	 * 		  the message under consideration 
	 * @return true, if the message is successfully processed 
	 * @throws Throwable
	 * 		   Any exception thrown during processing of the message
	 */
	public Boolean processMessage(CoreMessage message) throws Throwable; 
		
}

/*
* Revision Log
* -------------------------------
* $Log:: /Product_Base/Projects/Batch/Code/Java/CoreComm/src/com/stgmastek/core/comm/util/IMessageProcessor.java                                                                     $
 * 
 * 3     12/18/09 3:57p Grahesh
 * Updated the comments
 * 
 * 2     12/17/09 11:56a Grahesh
 * Initial Version
*
*
*/