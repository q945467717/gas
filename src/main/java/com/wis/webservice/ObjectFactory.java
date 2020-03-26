
package com.wis.webservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wis.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wis.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StationDTO }
     * 
     */
    public StationDTO createStationDTO() {
        return new StationDTO();
    }

    /**
     * Create an instance of {@link PValueDTO }
     * 
     */
    public PValueDTO createPValueDTO() {
        return new PValueDTO();
    }

    /**
     * Create an instance of {@link Timestamp }
     * 
     */
    public Timestamp createTimestamp() {
        return new Timestamp();
    }

    /**
     * Create an instance of {@link StationParameterDTO }
     * 
     */
    public StationParameterDTO createStationParameterDTO() {
        return new StationParameterDTO();
    }

    /**
     * Create an instance of {@link StationDTOArray }
     * 
     */
    public StationDTOArray createStationDTOArray() {
        return new StationDTOArray();
    }

}
