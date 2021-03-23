/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.apache.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ico
 */
public class MainGeocode {

    /**
     * demo for address resolver
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AddressResolver resolver =new AddressResolver( new TqsBasicHttpClient());
            //36.3072373,-112.7623243 GRAND CANYON
            //57.485068,-4.0766803 SURPRISE!!
            //Address result = resolver.findAddressForLocation( 40.6406609,-8.6566883);
            // Address result = resolver.findAddressForLocation( 57.485068,-4.0766803);
            Address result = resolver.findAddressForLocation( 112,224);

            System.out.println( "Result: ".concat( result.toString() ));
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainGeocode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainGeocode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainGeocode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(MainGeocode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
