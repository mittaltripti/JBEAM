/**
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
 *
 * $Revision: 6 $
 *
 * $Header: /Utilities/JDBCPool/src/jdbc/pool/CXMLManager.java 6     1/29/09 9:49p Kedarr $
 *
 * $Log: /Utilities/JDBCPool/src/jdbc/pool/CXMLManager.java $
 * 
 * 6     1/29/09 9:49p Kedarr
 * Rectified the code for possible null pointer exception that might be
 * thrown from the code.
 * 
 * 5     3/17/08 2:43p Kedarr
 * Added REVISION number. Implemented Delete method. Added comments in the
 * xml file during save.
 * 
 * 4     3/20/07 10:17a Kedarr
 * Changes made to read and store the new attribute.
 * 
 * 3     5/02/06 4:35p Kedarr
 * Changes made for adding a new attribute for Algorithm.
 * Updated javadoc.
 * 
 * 2     2/15/06 6:15p Kedarr
 * Resolved a bug in getAllPoolAttributes() method. The method used to
 * return the last pools attributes defined in the xml file for all pools.
 * 
 * 1     11/24/05 10:19a Kedarr
 * Extendes the abstract class CPoolAttributeManager and implements the
 * abstract methods. The purpose of this class is to manage XML file.
 * 
 */
package jdbc.pool;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import com.stg.logger.LogLevel;

/**
 * Reads and stores the pool attributes in an XML configuration file.
 * 
 * @version $Revision: 6 $
 * @author kedarr
 * @since 13.01
 * 
 */
public class CXMLManager extends CPoolAttributeManager {

    /**
     * XMLConfiguration object.
     * Comment for <code>xmlPoolConfig</code>.
     */
    private XMLConfiguration xmlPoolConfig;

    /**
     * <code>log4<i>J</i></code> Logger object.
     * Comment for <code>logger_</code>.
     */
    private Logger logger_;

    /**
     * Comment.
     */
    private static final String COMMENT = 
        "If your application is in use then please do not edit this file manually., " +
        "Any changes made to the file while the application is active will not have," +
        " any effect on the Pool's configuration and are likely to be lost. If your," +
        "application is inactive then you may edit this file with an XML editor. If," +
        "you do so then please refer to the JDBC Pools documentation.";
    
    /**
     * Constructs the Configuration Manager around a supplied xml file.
     * 
     * @param file
     *            XML configuration file.
     * @throws ConfigurationException
     */
    protected CXMLManager(File file) throws ConfigurationException {
        super(file);
        xmlPoolConfig = new XMLConfiguration(file);
        logger_ = Logger.getLogger(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see jdbc.pool.CPoolAttributeManager#save(jdbc.pool.CPoolAttribute)
     */
    protected boolean update(CPoolAttribute pattribute)
            throws ConfigurationException {
        CPoolAttribute attribute = null;
        try {
            attribute = (CPoolAttribute) pattribute.clone();
        } catch (CloneNotSupportedException e) {
            logger_
                    .error("Unable to store the atrributes.");
            // CPoolAttribute supports clone.
        }
        boolean bThrowException = true;
        int iSize = xmlPoolConfig.getMaxIndex("pool[@name]");
        for (int i = 0; i <= iSize; i++) {
            if (xmlPoolConfig.getString("pool(" + i + ")[@name]").equals(
                    attribute.getPoolName())) {
                xmlPoolConfig.setProperty("pool(" + i + ")[@driver]", attribute
                        .getDriver());
                xmlPoolConfig.setProperty("pool(" + i + ")[@vendor]", attribute
                        .getVendor());
                xmlPoolConfig.setProperty("pool(" + i + ")[@url]", attribute
                        .getURL());
                xmlPoolConfig.setProperty("pool(" + i + ")[@user]", attribute
                        .getUser());
                xmlPoolConfig.setProperty("pool(" + i + ")[@password]",
                        attribute.getPassword());
                xmlPoolConfig.setProperty("pool(" + i
                        + ")[@initial-connections]", attribute
                        .getInitialPoolSize()
                        + "");
                xmlPoolConfig.setProperty("pool(" + i
                        + ")[@capacity-increament]", attribute
                        .getCapacityIncreament()
                        + "");
                xmlPoolConfig.setProperty("pool(" + i + ")[@maximum-capacity]",
                        attribute.getMaximumCapacity() + "");
                xmlPoolConfig.setProperty(
                        "pool(" + i + ")[@inactive-time-out]", attribute
                                .getConnectionIdleTimeout()
                                + "");
                xmlPoolConfig.setProperty("pool(" + i
                        + ")[@shrink-pool-interval]", attribute
                        .getShrinkPoolInterval()
                        + "");
                xmlPoolConfig.setProperty("pool(" + i
                        + ")[@critical-operation-time-limit]", attribute
                        .getCriticalOperationTimeLimit()
                        + "");
                xmlPoolConfig.setProperty("pool(" + i + ").in-use-wait-time",
                        attribute.getInUseWaitTime() + "");
                xmlPoolConfig.setProperty("pool(" + i + ").load-on-startup",
                        (attribute.isLoadOnStartup() ? "true" : "false"));
                xmlPoolConfig.setProperty("pool(" + i
                        + ").max-usage-per-jdbc-connection", attribute
                        .getMaxUsagePerJDBCConnection()
                        + "");
                xmlPoolConfig.setProperty("pool(" + i
                        + ").pool-algorithm", attribute
                        .getPoolAlgorithm());
                xmlPoolConfig.setProperty("pool(" + i
                        + ").inmemory-statistics-history-size", attribute
                        .getStatisticalHistoryRecordCount() + "");
                xmlPoolConfig.setProperty("pool(" + i
                		+ ").sql-query", attribute
                		.getSqlQuery() + "");
                bThrowException = false;
                break;
            }
        }
        if (bThrowException) {
            throw new ConfigurationException(
                    "Unable to find such a configuration. Pool Name #"
                            + attribute.getPoolName());
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see jdbc.pool.CPoolAttributeManager#getAllPoolAttributes()
     */
    protected Map<String, CPoolAttribute> getAllPoolAttributes() {
        Map<String, CPoolAttribute> poolAttributeMap = Collections.synchronizedMap(new HashMap<String, CPoolAttribute>());
        int iSize = xmlPoolConfig.getMaxIndex("pool[@name]"); //returns -1 if does not exists
        for (int i = 0; i <= iSize; i++) {
            CPoolAttribute attribute = null;
            try {
                attribute = getPoolAttribute(i);
            } catch (ConfigurationException e) {
                //this should not arise
            }
            if (attribute != null) {
                poolAttributeMap.put(attribute.getPoolName(), attribute);
            }
        }
        return poolAttributeMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see jdbc.pool.CPoolAttributeManager#getPoolAttribute(java.lang.String)
     */
    protected CPoolAttribute getPoolAttribute(String name) throws ConfigurationException {
        int iSize = xmlPoolConfig.getMaxIndex("pool[@name]");
        for (int i = 0; i <= iSize; i++) {
            if (xmlPoolConfig.getString("pool(" + i + ")[@name]").equals(name)) {
                return getPoolAttribute(i);
            }
        }
        throw new ConfigurationException("Unable to locate pool.");
    }
    
    /**
     * Returns the Pool Attributes stored in the XML file against the specified index.
     *
     * @param index
     * @return CPoolAtribute
     * @throws ConfigurationException
     */
    private CPoolAttribute getPoolAttribute(int index) throws ConfigurationException {
        CPoolAttribute attribute;
        attribute = new CPoolAttribute();
        attribute.setPoolName(xmlPoolConfig.getString("pool(" + index
                + ")[@name]"));
        if (logger_.isDebugEnabled()) {
            logger_.debug("Reading attributes for pool #"
                    + attribute.getPoolName());
        }
        attribute.setDriver(xmlPoolConfig.getString("pool(" + index
                + ")[@driver]"));
        attribute.setVendor(xmlPoolConfig.getString("pool(" + index
                + ")[@vendor]"));
        attribute.setURL(xmlPoolConfig.getString("pool(" + index + ")[@url]"));
        attribute
                .setUser(xmlPoolConfig.getString("pool(" + index + ")[@user]"));
        attribute.setPassword(xmlPoolConfig.getString("pool(" + index
                + ")[@password]"));
        attribute.setInitialPoolSize(xmlPoolConfig.getInt("pool(" + index
                + ")[@initial-connections]"));
        attribute.setCapacityIncreament(xmlPoolConfig.getInt("pool(" + index
                + ")[@capacity-increament]"));
        attribute.setMaximumCapacity(xmlPoolConfig.getInt("pool(" + index
                + ")[@maximum-capacity]"));
        attribute.setConnectionIdleTimeout(xmlPoolConfig.getInt("pool(" + index
                + ")[@inactive-time-out]"));
        attribute.setShrinkPoolInterval(xmlPoolConfig.getInt("pool(" + index
                + ")[@shrink-pool-interval]"));
        attribute.setCriticalOperationTimeLimit(xmlPoolConfig.getInt("pool("
                + index + ")[@critical-operation-time-limit]"));
        try {
            attribute.setInUseWaitTime(xmlPoolConfig.getInt("pool(" + index
                    + ").in-use-wait-time"));
        } catch (NoSuchElementException e) {
            attribute.setInUseWaitTime(-1);
        }

        try {
            attribute.setLoadOnStartup(xmlPoolConfig.getBoolean("pool(" + index
                    + ").load-on-startup"));
        } catch (NoSuchElementException nsee) {
            attribute.setLoadOnStartup(false);
        }
        try {
            attribute.setMaxUsagePerJDBCConnection(xmlPoolConfig.getInt("pool("
                    + index + ").max-usage-per-jdbc-connection"));
            if (attribute.getMaxUsagePerJDBCConnection() >= 0
                    && attribute.getMaxUsagePerJDBCConnection() < 2) {
                logger_.log(LogLevel.NOTICE,
                        "Pool attribute max-usage-per-jdbc-connection defaulted to -1 for pool #"
                                + attribute.getPoolName());
                attribute.setMaxUsagePerJDBCConnection(-1);
                attribute.setToBeSaved(true);
            }
        } catch (NoSuchElementException nsee) {
            attribute.setMaxUsagePerJDBCConnection(-1);
            attribute.setToBeSaved(true);
        }
        try {
            attribute.setPoolAlgorithm(xmlPoolConfig.getString("pool(" + index
                    + ").pool-algorithm"));
        } catch (NoSuchElementException nsee) {
            attribute.setPoolAlgorithm(IPool.FIFO_ALGORITHM);
            attribute.setToBeSaved(true);
        }
        try {
            attribute.setStatisticalHistoryRecordCount(xmlPoolConfig.getInt("pool(" + index
                    + ").inmemory-statistics-history-size"));
        } catch (NoSuchElementException nsee) {
            attribute.setStatisticalHistoryRecordCount(1);
            attribute.setToBeSaved(true);
        }
        try {
        	String sqlQuery = xmlPoolConfig.getString("pool(" + index + ").sql-query");
        	if (sqlQuery == null || "1".equals(sqlQuery)) {
        		throw new IllegalArgumentException("sql-query element has to be defined");
        	}
        	attribute.setSqlQuery(sqlQuery);
        } catch (NoSuchElementException nsee) {
        	throw new ConfigurationException("sql-query element has to be defined.");
        }
        if (logger_.isDebugEnabled()) {
            logger_.debug("Pool Attributes read for pool #"
                    + attribute.getPoolName());
        }
        return attribute;
    }
    

    /**
     * Stores the revision number of the source code. This will be available in
     * the .class file and then we can get the revision number of the class.
     * Comment for <code>REVISION</code>.
     */
    public static final String REVISION = "$Revision:: 6         $";

    /*
     * (non-Javadoc)
     * 
     * @see jdbc.pool.CPoolAttributeManager#create(jdbc.pool.CPoolAttribute)
     */
    protected boolean create(CPoolAttribute attribute)
            throws ConfigurationException {
        int iSize = xmlPoolConfig.getMaxIndex("pool[@name]");
        if (logger_.isDebugEnabled()) {
            logger_.debug("Validating Pool Existance");
        }
        for (int i = 0; i <= iSize; i++) {
            if (xmlPoolConfig.getString("pool(" + i + ")[@name]").equals(
                    attribute.getPoolName())) {
                throw new ConfigurationException(
                        "Pool Configuration Already Created. Unable to create a new configuration.");
            }
        }
        if (logger_.isDebugEnabled()) {
            logger_.debug("Pool does not exist.");
            logger_.debug("Creating new pool [in-memory] #" + attribute.getPoolName());
        }
        iSize++;    //Increment the iSize by 1 as we are adding a new element.
        xmlPoolConfig.addProperty("pool(-1)[@name]", attribute.getPoolName());
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@driver]", attribute
                .getDriver());
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@vendor]", attribute
                .getVendor());
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@url]", attribute
                .getURL());
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@user]", attribute
                .getUser());
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@password]", attribute
                .getPassword());
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@initial-connections]",
                attribute.getInitialPoolSize() + "");
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@capacity-increament]",
                attribute.getCapacityIncreament() + "");
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@maximum-capacity]",
                attribute.getMaximumCapacity() + "");
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@inactive-time-out]",
                attribute.getConnectionIdleTimeout() + "");
        xmlPoolConfig.addProperty("pool(" + iSize + ")[@shrink-pool-interval]",
                attribute.getShrinkPoolInterval() + "");
        xmlPoolConfig.addProperty("pool(" + iSize
                + ")[@critical-operation-time-limit]", attribute
                .getCriticalOperationTimeLimit()
                + "");
        xmlPoolConfig.addProperty("pool(" + iSize + ").in-use-wait-time",
                attribute.getInUseWaitTime() + "");
        xmlPoolConfig.addProperty("pool(" + iSize + ").load-on-startup",
                (attribute.isLoadOnStartup() ? "true" : "false"));
        xmlPoolConfig.addProperty("pool(" + iSize
                + ").max-usage-per-jdbc-connection", attribute
                .getMaxUsagePerJDBCConnection()
                + "");
        xmlPoolConfig.addProperty("pool(" + iSize
                + ").pool-algorithm", attribute
                .getPoolAlgorithm()
                );
        xmlPoolConfig.addProperty("pool(" + iSize
                + ").inmemory-statistics-history-size", attribute
                .getStatisticalHistoryRecordCount()
                + "");
        xmlPoolConfig.addProperty("pool(" + iSize
        		+ ").sql-query", attribute
        		.getSqlQuery()
        		+ "");
        if (logger_.isDebugEnabled()) {
            logger_.debug("Pool created [in-memory] #" + attribute.getPoolName());
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see jdbc.pool.CPoolAttributeManager#save()
     */
    protected void save() throws ConfigurationException {
        if (logger_.isDebugEnabled()) {
            logger_.debug("Saving Pool configuration to disk..");
            //add comment.
        }
        int iSize = xmlPoolConfig.getMaxIndex("jar[@product]");
        if (iSize < 0) {
            xmlPoolConfig.addProperty("jar(-1)[@product]", CConnectionPoolManager.VERSION[0]);
            xmlPoolConfig.setProperty("jar(0)[@version]", CConnectionPoolManager.VERSION[1]);
            xmlPoolConfig.setProperty("jar(0)[@releasedate]", CConnectionPoolManager.VERSION[2]);
            xmlPoolConfig.setProperty("jar(0).configsaveddate", new Date());
            xmlPoolConfig.setProperty("jar(0).note", COMMENT);
        } else {
            xmlPoolConfig.setProperty("jar(0)[@product]", CConnectionPoolManager.VERSION[0]);
            xmlPoolConfig.setProperty("jar(0)[@version]", CConnectionPoolManager.VERSION[1]);
            xmlPoolConfig.setProperty("jar(0)[@releasedate]", CConnectionPoolManager.VERSION[2]);
            xmlPoolConfig.setProperty("jar(0).configsaveddate", new Date());
            xmlPoolConfig.setProperty("jar(0).note", COMMENT);
        }
        xmlPoolConfig.save();
        if (logger_.isDebugEnabled()) {
            logger_.debug("Pool configuration saved.");
        }
    }
    
    /* (non-Javadoc)
     * @see jdbc.pool.CPoolAttributeManager#delete(java.lang.String)
     */
    public synchronized boolean delete(String strPoolName) throws ConfigurationException {
        if (strPoolName == null) {
            throw new NullPointerException("Pool Name not supplied.");
        }
        boolean bThrowException = true;
        int iSize = xmlPoolConfig.getMaxIndex("pool[@name]");
        for (int i = 0; i <= iSize; i++) {
            if (xmlPoolConfig.getString("pool(" + i + ")[@name]").equals(
                    strPoolName)) {
                xmlPoolConfig.clearProperty("pool(" + i + ")[@name]");
                xmlPoolConfig.clearProperty("pool(" + i + ")[@driver]");
                xmlPoolConfig.clearProperty("pool(" + i + ")[@vendor]");
                xmlPoolConfig.clearProperty("pool(" + i + ")[@url]");
                xmlPoolConfig.clearProperty("pool(" + i + ")[@user]");
                xmlPoolConfig.clearProperty("pool(" + i + ")[@password]");
                xmlPoolConfig.clearProperty("pool(" + i
                        + ")[@initial-connections]");
                xmlPoolConfig.clearProperty("pool(" + i
                        + ")[@capacity-increament]");
                xmlPoolConfig.clearProperty("pool(" + i + ")[@maximum-capacity]");
                xmlPoolConfig.clearProperty(
                        "pool(" + i + ")[@inactive-time-out]");
                xmlPoolConfig.clearProperty("pool(" + i
                        + ")[@shrink-pool-interval]");
                xmlPoolConfig.clearProperty("pool(" + i
                        + ")[@critical-operation-time-limit]");
                xmlPoolConfig.clearProperty("pool(" + i + ").in-use-wait-time");
                xmlPoolConfig.clearProperty("pool(" + i + ").load-on-startup");
                xmlPoolConfig.clearProperty("pool(" + i
                        + ").max-usage-per-jdbc-connection");
                xmlPoolConfig.clearProperty("pool(" + i
                        + ").pool-algorithm");
                xmlPoolConfig.clearProperty("pool(" + i
                        + ").inmemory-statistics-history-size");
                xmlPoolConfig.clearProperty("pool(" + i
                		+ ").sql-query");
                bThrowException = false;
                save();
                return true;
            }
        }
        if (bThrowException) {
            throw new ConfigurationException(
                    "Unable to find such a configuration. Pool Name #"
                            + strPoolName);
        }
        return false;
        
    }
    
}
