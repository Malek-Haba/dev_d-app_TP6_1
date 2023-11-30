package service;

import corbaConversion.IConversionRemotePOA;
import org.omg.PortableServer.Servant;

public class ConversionImpl extends IConversionRemotePOA {
    public double convertirMontant(double mt){
        return mt*3.3;
    }

}
