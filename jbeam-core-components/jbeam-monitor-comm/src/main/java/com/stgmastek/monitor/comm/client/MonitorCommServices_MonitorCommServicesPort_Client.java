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

package com.stgmastek.monitor.comm.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.1.3
 * Mon Jan 28 17:05:17 IST 2013
 * Generated source version: 2.1.3
 * 
 */

public final class MonitorCommServices_MonitorCommServicesPort_Client {

    private static final QName SERVICE_NAME = new QName("http://services.server.comm.monitor.stgmastek.com/", "MonitorCommServicesService");

    private MonitorCommServices_MonitorCommServicesPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = MonitorCommServicesService.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        MonitorCommServicesService ss = new MonitorCommServicesService(wsdlURL, SERVICE_NAME);
        MonitorCommServices port = ss.getMonitorCommServicesPort();  
        
        {
        System.out.println("Invoking updateSystemInformation...");
        com.stgmastek.monitor.comm.client.MReqSystemInfo _updateSystemInformation_systemInfo = null;
        com.stgmastek.monitor.comm.client.MBaseResponseVO _updateSystemInformation__return = port.updateSystemInformation(_updateSystemInformation_systemInfo);
        System.out.println("updateSystemInformation.result=" + _updateSystemInformation__return);


        }
        {
        System.out.println("Invoking updateBatch...");
        com.stgmastek.monitor.comm.client.MReqBatch _updateBatch_batch = null;
        com.stgmastek.monitor.comm.client.MBaseResponseVO _updateBatch__return = port.updateBatch(_updateBatch_batch);
        System.out.println("updateBatch.result=" + _updateBatch__return);


        }
        {
        System.out.println("Invoking updateInstructionLog...");
        com.stgmastek.monitor.comm.client.MReqInstructionLog _updateInstructionLog_instructionLog = null;
        com.stgmastek.monitor.comm.client.MBaseResponseVO _updateInstructionLog__return = port.updateInstructionLog(_updateInstructionLog_instructionLog);
        System.out.println("updateInstructionLog.result=" + _updateInstructionLog__return);


        }
        {
        System.out.println("Invoking refreshProcessRequestSchedule...");
        com.stgmastek.monitor.comm.client.CReqProcessRequestScheduleVO _refreshProcessRequestSchedule_requestScheduleVO = null;
        com.stgmastek.monitor.comm.client.MBaseResponseVO _refreshProcessRequestSchedule__return = port.refreshProcessRequestSchedule(_refreshProcessRequestSchedule_requestScheduleVO);
        System.out.println("refreshProcessRequestSchedule.result=" + _refreshProcessRequestSchedule__return);


        }
        {
        System.out.println("Invoking addBatch...");
        com.stgmastek.monitor.comm.client.MReqBatch _addBatch_batch = null;
        com.stgmastek.monitor.comm.client.MBaseResponseVO _addBatch__return = port.addBatch(_addBatch_batch);
        System.out.println("addBatch.result=" + _addBatch__return);


        }
        {
        System.out.println("Invoking stopMonitorComm...");
        port.stopMonitorComm();


        }
        {
        System.out.println("Invoking updateBatchProgress...");
        com.stgmastek.monitor.comm.client.MReqBatchProgress _updateBatchProgress_reqBatchProgress = null;
        com.stgmastek.monitor.comm.client.MBaseResponseVO _updateBatchProgress__return = port.updateBatchProgress(_updateBatchProgress_reqBatchProgress);
        System.out.println("updateBatchProgress.result=" + _updateBatchProgress__return);


        }
        {
        System.out.println("Invoking updateBatchLog...");
        com.stgmastek.monitor.comm.client.MReqBatchLog _updateBatchLog_requestBatchLog = null;
        com.stgmastek.monitor.comm.client.MBaseResponseVO _updateBatchLog__return = port.updateBatchLog(_updateBatchLog_requestBatchLog);
        System.out.println("updateBatchLog.result=" + _updateBatchLog__return);


        }

        System.exit(0);
    }

}
