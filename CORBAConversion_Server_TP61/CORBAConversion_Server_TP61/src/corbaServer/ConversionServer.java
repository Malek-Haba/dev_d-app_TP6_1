package corbaServer;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import service.ConversionImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
public class ConversionServer {
    public static void main(String[] args){

        try{
            // Initialisation du middleware CORBA
            ORB orb = ORB.init(args, null);

            // Déclaration d'un objet de type PAO
            // "RootPOA" :  le nom de PAO Manager principale par défaut
            // "POAHelper.narrow" pour faire le sous casting en PAO car CORBA est indépédent du langage
            // Puis activation de ce Manager.
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();

            // Instancier le servant, ConversionImpl dans notre cas.
            ConversionImpl conversionImpl = new ConversionImpl();

            // Publication de la référence de l’OD en utilisant JNDI qui nécessite un InitialContext.
            Context ctx = new InitialContext();

            // Conversion de l’objet CORBA en une référence en utilisant la méthode servant_to_reference
            ctx.rebind("BK", poa.servant_to_reference(conversionImpl));

            // Démarrage du serveur
            orb.run();
        }catch(InvalidName | AdapterInactive | WrongPolicy | NamingException | ServantNotActive e){
            System.out.println("Erreur " + e);
        }
    }
}
