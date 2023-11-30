package corbaClient;

import corbaConversion.IConversionRemote;
import corbaConversion.IConversionRemoteHelper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConversionClient {
    public static void main(String[] args) throws NamingException {
        Context ctx = new InitialContext();
        Object ref = ctx.lookup("BK");
        IConversionRemote stub = IConversionRemoteHelper.narrow((org.omg.CORBA.Object) ref);
        System.out.println(stub.convertirMontant(15));
    }
}
