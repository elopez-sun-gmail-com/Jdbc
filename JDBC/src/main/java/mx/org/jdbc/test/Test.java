package mx.org.jdbc.test;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import mx.org.jdbc.datos.Conexion;
import mx.org.jdbc.datos.PersonaDAO;
import mx.org.jdbc.datos.PersonaDAOJdbc;
import mx.org.jdbc.domain.PersonaDTO;



/**
 *
 * @author elopez
 */
public class Test {
    
     public static void main(String[] args) {

    	
    	 Connection conexion = null;
         try {
             conexion = Conexion.getConnection();
             if (conexion.getAutoCommit()) {
                 conexion.setAutoCommit(false);
             }

             PersonaDAO personaDao = new PersonaDAOJdbc(conexion);
             
             {
                 PersonaDTO cambioPersona = new PersonaDTO();
                 //cambioPersona.setIdPersona(2);
                 cambioPersona.setNombre("Karla Ivonne");
                 cambioPersona.setApellido("Gomez");
                 cambioPersona.setEmail("kgomez@mail.com");
                 cambioPersona.setTelefono("7713445678");
                 personaDao.update(cambioPersona);
                 
                 PersonaDTO nuevaPersona = new PersonaDTO();
                 nuevaPersona.setNombre("Carlos");
                 //nuevaPersona.setApellido("Ramirez11111111111111111111111111111111111111111111");
                 nuevaPersona.setApellido("Ramirez");
                 personaDao.insert(nuevaPersona);
            	 
            	 
             }
             
             List<PersonaDTO> personas = personaDao.select();
             
             for(PersonaDTO persona: personas){
                 System.out.println("Persona DTO:" + persona);
             }
             
             conexion.commit();
             System.out.println("Se ha hecho commit de la transaccion");
         } catch (SQLException ex) {
             ex.printStackTrace(System.out);
             System.out.println("Entramos al rollback");
             try {
                 conexion.rollback();
             } catch (SQLException ex1) {
                 ex1.printStackTrace(System.out);
             }
         }
    	
    }
}
