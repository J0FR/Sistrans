package uniandes.isis2304.parranderos.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.JDODataStoreException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.math.BigDecimal;
import uniandes.isis2304.parranderos.negocio.Alohandes;
import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.Reserva;
import uniandes.isis2304.parranderos.negocio.VOAlojamiento;
import uniandes.isis2304.parranderos.negocio.VOAlojamientoServicio;
import uniandes.isis2304.parranderos.negocio.VOApartamentoAlquiler;
import uniandes.isis2304.parranderos.negocio.VOCliente;
import uniandes.isis2304.parranderos.negocio.VOHabitacionHostal;
import uniandes.isis2304.parranderos.negocio.VOHabitacionHotel;
import uniandes.isis2304.parranderos.negocio.VOHabitacionHuesped;
import uniandes.isis2304.parranderos.negocio.VOHabitacionViviendaUniversitaria;
import uniandes.isis2304.parranderos.negocio.VOHostal;
import uniandes.isis2304.parranderos.negocio.VOHotel;
import uniandes.isis2304.parranderos.negocio.VOOperadorUsuario;
import uniandes.isis2304.parranderos.negocio.VOReserva;
import uniandes.isis2304.parranderos.negocio.VOViviendaTemporal;
import uniandes.isis2304.parranderos.negocio.VOViviendaUniversitaria;

@SuppressWarnings("serial")
public class InterfazAlohandesApp extends JFrame implements ActionListener {
    	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazAlohandesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ_LOGIN = "./src/main/resources/config/interfaceConfigAppLogin.json"; 
	private static final String CONFIG_INTERFAZ_ADMIN = "./src/main/resources/config/interfaceConfigAppAdmin.json"; 
	private static final String CONFIG_INTERFAZ_CLIENTE = "./src/main/resources/config/interfaceConfigAppCliente.json"; 
	private static final String CONFIG_INTERFAZ_UNIANDINO = "./src/main/resources/config/interfaceConfigAppUniandino.json"; 
	private static final String CONFIG_INTERFAZ_EXTERNO = "./src/main/resources/config/interfaceConfigAppExternos.json"; 
	private static final String CONFIG_INTERFAZ_VIVIENDA_UNIVERSITARIA = "./src/main/resources/config/interfaceConfigAppViviendaUniversitaria.json"; 
	private static final String CONFIG_INTERFAZ_HOSTAL = "./src/main/resources/config/interfaceConfigAppHostal.json"; 
	private static final String CONFIG_INTERFAZ_HOTEL = "./src/main/resources/config/interfaceConfigAppHotel.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private Alohandes alohandes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	/**
     * Identificador con el que se entro a la aplicacion
     */
    private String identificadorLogin = "";

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazAlohandesApp( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_LOGIN);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        alohandes = new Alohandes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }

	public void cambioInterfazAdmin() {
		// Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_ADMIN);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
	}

	public void cambioInterfazHotel() {
		// Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_HOTEL);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
	}

	public void cambioInterfazViviendaUniversitaria() {
		// Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_VIVIENDA_UNIVERSITARIA);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
	}

	public void cambioInterfazHostal() {
		// Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_HOSTAL);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
	}

	public void cambioInterfazCliente() {
		// Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_CLIENTE);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
	}

	public void cambioInterfazUniandino() {
		// Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_UNIANDINO);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
	}

	public void cambioInterfazExterno() {
		// Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_EXTERNO);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
	}

	public void cerrarSesion() {
		// Carga la configuración de la interfaz desde un archivo JSON
		this.identificadorLogin = "";
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_LOGIN);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
	}

	/* ****************************************************************
	 * 			Métodos de para ingresar a la interfaz de interes
	 *****************************************************************/

	public void ingresarInterfazAdmin() {
		JPanel myPanel = new JPanel();
		JTextField usuario = new JTextField(5);
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.add(new JLabel("Usuario:"));
		myPanel.add(usuario);
		JTextField contrasenia = new JTextField(5);
		myPanel.add(new JLabel("Contrasenia:"));
		myPanel.add(contrasenia);
		JOptionPane.showMessageDialog(this, myPanel, "Ingresar como administrador", JOptionPane.DEFAULT_OPTION);
		if (usuario.getText().equals("Admin") && contrasenia.getText().equals("Admin")) {
			this.identificadorLogin = "Admin";
			cambioInterfazAdmin();
		}
		else {
			panelDatos.actualizarInterfaz("El usuario o contrasenia son incorrectos.");
		}
	}

    public void ingresarInterfazCliente() {
		String identificador = JOptionPane.showInputDialog (this, "Identificacion del cliente?", "Buscar cliente por identificacion", JOptionPane.QUESTION_MESSAGE);
		VOCliente cliente = buscarClientePorIdentificacion(identificador);
		if (cliente != null) {
			this.identificadorLogin = identificador;
			cambioInterfazCliente();
		}
		else {
			panelDatos.actualizarInterfaz("El cliente con identificacion " + identificador + " no existe.");
		}
	}

	public void ingresarInterfazOperadorUsuario() {
		String identificador = JOptionPane.showInputDialog (this, "Identificacion del OperadorUsuario?", "Buscar OperadorUsuario por identificacion", JOptionPane.QUESTION_MESSAGE);
		VOOperadorUsuario operadorUsuario = buscarOperadorUsuarioPorIdentificacion(identificador);
		if (operadorUsuario != null) {
			this.identificadorLogin = identificador;
			if (operadorUsuario.getTipoVinculo().equals("externo")) {
				cambioInterfazExterno();
			} else {
				cambioInterfazUniandino();
			}
		}
		else {
			panelDatos.actualizarInterfaz("El OperadorUsuario con identificacion " + identificador + " no existe.");
		}
	}

	public void ingresarInterfazHotel() {
		String regComercio = JOptionPane.showInputDialog (this, "RegComercio del hotel?", "Buscar hotel por regComercio", JOptionPane.QUESTION_MESSAGE);
		VOHotel hotel = buscarHotelPorRegComercio(regComercio);
		if (hotel != null) {
			this.identificadorLogin = regComercio;
			cambioInterfazHotel();
		}
		else {
			panelDatos.actualizarInterfaz("El hotel con regComerico " + regComercio + " no existe.");
		}
	}

	public void ingresarInterfazHostal() {
		String regComercio = JOptionPane.showInputDialog (this, "RegComercio del hostal?", "Buscar hostal por regComercio", JOptionPane.QUESTION_MESSAGE);
		VOHostal hostal = buscarHostalPorRegComercio(regComercio);
		if (hostal != null) {
			this.identificadorLogin = regComercio;
			cambioInterfazHostal();
		}
		else {
			panelDatos.actualizarInterfaz("El hotel con regComerico " + regComercio + " no existe.");
		}
	}

	public void ingresarInterfazViviendaUniversitaria() {
		String regComercio = JOptionPane.showInputDialog (this, "RegComercio de la ViviendaUniversitaria?", "Buscar ViviendaUniversitaria por regComercio", JOptionPane.QUESTION_MESSAGE);
		VOViviendaUniversitaria viviendaUniversitaria = buscarViviendaUniversitariaPorRegComercio(regComercio);
		if (viviendaUniversitaria != null) {
			this.identificadorLogin = regComercio;
			cambioInterfazViviendaUniversitaria();
		}
		else {
			panelDatos.actualizarInterfaz("La ViviendaUniversitaria con regComerico " + regComercio + " no existe.");
		}
	}

	
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
		SwingUtilities.updateComponentTreeUI( menuBar );
    }
    
	/* ****************************************************************
	 * 			CRUD de OperadorUsuario
	 *****************************************************************/
    /**
     * Adiciona un tipo de bebida con la información dada por el usuario
     * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no existía
     */
    public void adicionarOperadorUsuario( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			JTextField nombre = new JTextField(5);
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
			myPanel.add(new JLabel("Nombre:"));
			myPanel.add(nombre);
			JTextField identificacion = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("Identificacion:"));
			myPanel.add(identificacion);
			JTextField correoElectronico = new JTextField(5);
			myPanel.add(new JLabel("Correo Electronico:"));
			myPanel.add(correoElectronico);
			JTextField telefono = new JTextField(5);
			myPanel.add(new JLabel("Telefono:"));
			myPanel.add(telefono);
			myPanel.add(new JLabel("Tipo vinculo:"));
			String[] choicesTipoVinculo = {"profesor", "empleado", "egresado", "estudiante", "padre de estudiante", "externo"};
    		final JComboBox<String> tipoVinculo = new JComboBox<String>(choicesTipoVinculo);
			myPanel.add(tipoVinculo);

    		JOptionPane.showMessageDialog(this, myPanel, "Crear Operador Usuario", JOptionPane.DEFAULT_OPTION);
    		if (nombre.getText() != null && identificacion.getText() != null && correoElectronico.getText() != null && telefono.getText() != null)
    		{
        		VOOperadorUsuario tb = alohandes.adicionarOperadorUsuario(identificacion.getText(), nombre.getText(), String.valueOf(tipoVinculo.getSelectedItem()), correoElectronico.getText(), telefono.getText());
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un tipo de operador con nombre: " + nombre.getText());
        		}
        		String resultado = "En adicionarOperadorUsuario\n\n";
        		resultado += "Operador usuario adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
     * Busca el OperadorUsuario con el identificador indicado por el usuario y lo muestra en el panel de datos
     */
    public VOOperadorUsuario buscarOperadorUsuarioPorIdentificacion(String identificador)
    {
    	try 
    	{
    		VOOperadorUsuario operadorUsuario = alohandes.darOperadorUsuarioPorIdentificacion(identificador);
    		return operadorUsuario;
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
			return null;
		}
    }

	/**
     * Busca el Alojamiento con el identificador indicado por el usuario y lo muestra en el panel de datos
     */
    public VOAlojamiento darAlojamientoPorId(long id)
    {
    	try 
    	{
    		VOAlojamiento alojamiento = alohandes.darAlojamientoPorId(id);
    		return alojamiento;
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
			return null;
		}
    }


	/**
     * Consulta en la base de datos los tipos de bebida existentes y los muestra en el panel de datos de la aplicación
     */
    public void listarDineroRecibido( )
    {
    	try 
    	{
			List <Object []> lista = alohandes.darDineroRecibido();

			String resultado = "En listarTipoBebida: \n";
			resultado +=  "\n" + listarDineroRecibido (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	

	/* ****************************************************************
	 * 			CRUD de Cliente
	 *****************************************************************/

	/**
     * Adiciona un registro de cliente con la información dada por el usuario
     * Se crea una nueva tupla de Cliente en la base de datos
     */
	public void adicionarCliente( )
	{
		try 
    	{
    		String identificacion = JOptionPane.showInputDialog (this, "Ingrese la identificación del usuario a registrar", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
    		String nombre = JOptionPane.showInputDialog (this, "Ingrese el nombre del usuario a registrar", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String tipoVinculo = JOptionPane.showInputDialog (this, "Ingrese el tipo de vinculo del usuario a registrar", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String correoElectronico = JOptionPane.showInputDialog (this, "Ingrese el correo electronico del usuario a registrar", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String telefono = JOptionPane.showInputDialog (this, "Ingrese el telefono del usuario a registrar", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			
			if (nombre!= null && identificacion != null && !identificacion.equals("Admin") && tipoVinculo != null && correoElectronico != null && telefono != null)
    		{
        		VOCliente tb = alohandes.adicionarCliente(identificacion, nombre, tipoVinculo, correoElectronico, telefono, new Timestamp(0));
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo registrar al cliente con identifiación " + identificacion);
        		}
        		String resultado = "En adicionarCliente\n\n";
        		resultado += "Clienteadicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
     * Busca el cliente con el identificador indicado por el usuario y lo muestra en el panel de datos
     */
    public VOCliente buscarClientePorIdentificacion(String identificador)
    {
    	try 
    	{
    		VOCliente cliente = alohandes.darClientePorIdentificacion(identificador);
    		return cliente;
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
			return null;
		}
    }


	/* ****************************************************************
	 * 			CRUD de HOTEL
	 *****************************************************************/
    public void adicionarHotel( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
			JTextField regComercio = new JTextField(this.identificadorLogin, 5 );
			myPanel.add(new JLabel("Registro de Comercio:"));
			myPanel.add(regComercio);
			JTextField nit = new JTextField(5);
			myPanel.add(new JLabel("NIT:"));
			myPanel.add(nit);
			JTextField nombre = new JTextField(5);
			myPanel.add(new JLabel("Nombre:"));
			myPanel.add(nombre);
			myPanel.add(new JLabel("Tiene restaurante:"));
			String[] choicesBool = {"Y", "N"};
    		final JComboBox<String> restaurante = new JComboBox<String>(choicesBool);
			myPanel.add(restaurante);
			myPanel.add(new JLabel("Tiene parqueadero:"));
    		final JComboBox<String> parqueadero = new JComboBox<String>(choicesBool);
			myPanel.add(parqueadero);
			myPanel.add(new JLabel("Tiene piscina:"));
    		final JComboBox<String> piscina = new JComboBox<String>(choicesBool);
			myPanel.add(piscina);

    		JOptionPane.showMessageDialog(this, myPanel, "Crear Hotel", JOptionPane.DEFAULT_OPTION);
    		if (regComercio.getText() != null && nit.getText() != null && nombre.getText() != null)
    		{
        		VOHotel tb = alohandes.adicionarHotel(regComercio.getText(), nit.getText(), nombre.getText(), String.valueOf(restaurante.getSelectedItem()), String.valueOf(parqueadero.getSelectedItem()), String.valueOf(piscina.getSelectedItem()));
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un hotel con nombre: " + nombre.getText());
        		}
        		String resultado = "En adicionarHotel\n\n";
        		resultado += "Hotel adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


	/* ****************************************************************
	 * 			CRUD de HOSTAL
	 *****************************************************************/
    public void adicionarHostal( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
			JTextField regComercio = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("Registro de Comercio:"));
			myPanel.add(regComercio);
			JTextField nit = new JTextField(5);
			myPanel.add(new JLabel("NIT:"));
			myPanel.add(nit);
			JTextField nombre = new JTextField(5);
			myPanel.add(new JLabel("Nombre:"));
			myPanel.add(nombre);
			JTextField horaAperturaRecepcion = new JTextField(5);
			myPanel.add(new JLabel("Hora Apertura Recepcion:"));
			myPanel.add(horaAperturaRecepcion);
			JTextField horaCierreRecepcion = new JTextField(5);
			myPanel.add(new JLabel("Hora Cierre Recepcion:"));
			myPanel.add(horaCierreRecepcion);
			
    		JOptionPane.showMessageDialog(this, myPanel, "Crear Hostal", JOptionPane.DEFAULT_OPTION);
    		if (regComercio.getText() != null && nit.getText() != null && nombre.getText() != null && horaAperturaRecepcion.getText() != null && horaCierreRecepcion.getText() != null)
    		{
        		VOHostal tb = alohandes.adicionarHostal(regComercio.getText(), nit.getText(), nombre.getText(), horaAperturaRecepcion.getText(), horaCierreRecepcion.getText());
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un hostal con nombre: " + nombre.getText());
        		}
        		String resultado = "En adicionarHostal\n\n";
        		resultado += "Hostal adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
     * Busca el Hostal con el regComercio indicado por el usuario 
     */
    public VOHostal buscarHostalPorRegComercio(String regComercio)
    {
    	try 
    	{
    		VOHostal hostal = alohandes.darHostalPorRegComercio(regComercio);
    		return hostal;
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
			return null;
		}
    }


	/* ****************************************************************
	 * 			CRUD de VIVIENDAUNIVERSITARIA
	 *****************************************************************/
    public void adicionarViviendaUniversitaria( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
			JTextField regComercio = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("Registro de Comercio:"));
			myPanel.add(regComercio);
			JTextField nit = new JTextField(5);
			myPanel.add(new JLabel("NIT:"));
			myPanel.add(nit);
			JTextField nombre = new JTextField(5);
			myPanel.add(new JLabel("Nombre:"));
			myPanel.add(nombre);
			JTextField precioSalaEstudio = new JTextField(5);
			myPanel.add(new JLabel("Precio Sala de Estudio:"));
			myPanel.add(precioSalaEstudio);
			JTextField precioSalaEsparcimiento = new JTextField(5);
			myPanel.add(new JLabel("Precio Sala de Esparcimiento:"));
			myPanel.add(precioSalaEsparcimiento);
			JTextField precioGimnasio = new JTextField(5);
			myPanel.add(new JLabel("Precio Gimnasio:"));
			myPanel.add(precioGimnasio);
			myPanel.add(new JLabel("Tiene restaurante:"));
			String[] choicesBool = {"Y", "N"};
    		final JComboBox<String> restaurante = new JComboBox<String>(choicesBool);
			myPanel.add(restaurante);
			
    		JOptionPane.showMessageDialog(this, myPanel, "Crear vivienda universitaria", JOptionPane.DEFAULT_OPTION);
    		if (regComercio.getText() != null && nit.getText() != null && nombre.getText() != null && precioSalaEstudio.getText() != null && precioSalaEsparcimiento.getText() != null && precioGimnasio.getText() != null)
    		{
        		VOViviendaUniversitaria tb = alohandes.adicionarViviendaUniversitaria(regComercio.getText(), nit.getText(), nombre.getText(), Integer.valueOf(precioSalaEstudio.getText()), Integer.valueOf(precioSalaEsparcimiento.getText()), Integer.valueOf(precioGimnasio.getText()), String.valueOf(restaurante.getSelectedItem()));
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un vivienda universitaria con nombre: " + nombre.getText());
        		}
        		String resultado = "En adicionarViviendaUniversitaria\n\n";
        		resultado += "vivienda universitaria adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
     * Busca el ViviendaUniversitaria con el regComercio indicado por el usuario 
     */
    public VOViviendaUniversitaria buscarViviendaUniversitariaPorRegComercio(String regComercio)
    {
    	try 
    	{
    		VOViviendaUniversitaria viviendaUniversitaria = alohandes.darViviendaUniversitariaPorRegComercio(regComercio);
    		return viviendaUniversitaria;
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
			return null;
		}
    }


	/* ****************************************************************
	 * 			CRUD de HabitacionHuesped
	 *****************************************************************/
    public void adicionarHabitacionHuesped( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			JTextField comidas = new JTextField(5);
			myPanel.add(new JLabel("Numero comidas:"));
			myPanel.add(comidas);
			myPanel.add(new JLabel("Tipo baño:"));
			String[] tipoBanioOpciones = {"privado", "compartido"};
    		final JComboBox<String> tipoBanio = new JComboBox<String>(tipoBanioOpciones);
			myPanel.add(tipoBanio);
			myPanel.add(new JLabel("Tipo habitacion:"));
			String[] tipoHabitacionOpciones = {"individual", "compatida"};
    		final JComboBox<String> tipoHabitacion = new JComboBox<String>(tipoHabitacionOpciones);
			myPanel.add(tipoHabitacion);
			JTextField dtoMesExtra = new JTextField(5);
			myPanel.add(new JLabel("Descuento por mes extra:"));
			myPanel.add(dtoMesExtra);
			JTextField identificacionOperadorUsuario = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("Identificacion operador usuario:"));
			myPanel.add(identificacionOperadorUsuario);
			
    		JOptionPane.showMessageDialog(this, myPanel, "Crear habitacion huesped", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && comidas.getText() != null && dtoMesExtra.getText() != null && identificacionOperadorUsuario.getText() != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOHabitacionHuesped tb = alohandes.adicionarHabitacionHuesped(alojamiento.getId(), Integer.valueOf(comidas.getText()), String.valueOf(tipoBanio.getSelectedItem()), String.valueOf(tipoHabitacion.getSelectedItem()), Integer.valueOf(dtoMesExtra.getText()), identificacionOperadorUsuario.getText());
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un habitacion huesped con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarHabitacionHuesped\n\n";
        		resultado += "habitacion huesped adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	public void adicionarHabitacionHuespedUsuario( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			JTextField comidas = new JTextField(5);
			myPanel.add(new JLabel("Numero comidas:"));
			myPanel.add(comidas);
			myPanel.add(new JLabel("Tipo baño:"));
			String[] tipoBanioOpciones = {"privado", "compartido"};
    		final JComboBox<String> tipoBanio = new JComboBox<String>(tipoBanioOpciones);
			myPanel.add(tipoBanio);
			myPanel.add(new JLabel("Tipo habitacion:"));
			String[] tipoHabitacionOpciones = {"individual", "compatida"};
    		final JComboBox<String> tipoHabitacion = new JComboBox<String>(tipoHabitacionOpciones);
			myPanel.add(tipoHabitacion);
			JTextField dtoMesExtra = new JTextField(5);
			myPanel.add(new JLabel("Descuento por mes extra:"));
			myPanel.add(dtoMesExtra);
			String identificacionOperadorUsuario = this.identificadorLogin;
			
    		JOptionPane.showMessageDialog(this, myPanel, "Crear habitacion huesped", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && comidas.getText() != null && dtoMesExtra.getText() != null && identificacionOperadorUsuario != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOHabitacionHuesped tb = alohandes.adicionarHabitacionHuesped(alojamiento.getId(), Integer.valueOf(comidas.getText()), String.valueOf(tipoBanio.getSelectedItem()), String.valueOf(tipoHabitacion.getSelectedItem()), Integer.valueOf(dtoMesExtra.getText()), identificacionOperadorUsuario);
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un habitacion huesped con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarHabitacionHuesped\n\n";
        		resultado += "habitacion huesped adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
     * Borra de la base de datos el habitacion huesped con el identificador dado po el usuario
     * Cuando dicho habitacion huesped no existe, se indica que se borraron 0 registros de la base de datos
     */
    public void eliminarHabitacionHuesped( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del alojamiento?", "Borrar alojamiento por Id", JOptionPane.QUESTION_MESSAGE);
    		
			
			
			if (idTipoStr != null)
			{
				VOHabitacionHuesped habitacionHuesped = alohandes.darHabitacionHuespedPorId(Long.valueOf(idTipoStr));
				Timestamp fechaUltimaReserva = alohandes.darUltimaFechaPorIdAlojamiento(Long.valueOf(idTipoStr));
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

				if(habitacionHuesped != null && (habitacionHuesped.getIdentificacionOperadorUsuario().equals(this.identificadorLogin) || this.identificadorLogin.equals("Admin")) && (fechaUltimaReserva == null || (int) ChronoUnit.DAYS.between(fechaActual.toInstant(), fechaUltimaReserva.toInstant()) <= -1))
				{
				long idAlojamiento = Long.valueOf (idTipoStr);
				long tbEliminados = alohandes.eliminarHabitacionHuesped(idAlojamiento);
				alohandes.eliminarAlojamientoServicioPorIdAlojamiento(idAlojamiento);
				alohandes.eliminarAlojamiento(idAlojamiento);

				String resultado = "En eliminar habitacionHuesped\n\n";
				resultado += tbEliminados + " Alojamientos eliminados\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada, puede que el alojamiento no exista, no sea de su propiedad o no cumpla los requisitos para eliminar");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
			
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


	/* ****************************************************************
	 * 			CRUD de ApartamentoAlquiler
	 *****************************************************************/
    public void adicionarApartamentoAlquiler( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			String[] choicesBool = {"Y", "N"};
			myPanel.add(new JLabel("Tiene servicios publicos:"));
    		final JComboBox<String> servPublico = new JComboBox<String>(choicesBool);
			myPanel.add(servPublico);
			myPanel.add(new JLabel("Tiene administracion:"));
    		final JComboBox<String> administracion = new JComboBox<String>(choicesBool);
			myPanel.add(administracion);
			JTextField identificacionOperadorUsuario = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("Identificacion operador usuario:"));
			myPanel.add(identificacionOperadorUsuario);
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear ApartamentoAlquiler", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && identificacionOperadorUsuario.getText() != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 30, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOApartamentoAlquiler tb = alohandes.adicionarApartamentoAlquiler(alojamiento.getId(), String.valueOf(servPublico.getSelectedItem()), String.valueOf(administracion.getSelectedItem()), identificacionOperadorUsuario.getText());
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un ApartamentoAlquiler con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarApartamentoAlquiler\n\n";
        		resultado += "ApartamentoAlquiler adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	public void adicionarApartamentoAlquilerUsuario( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			String[] choicesBool = {"Y", "N"};
			myPanel.add(new JLabel("Tiene servicios publicos:"));
    		final JComboBox<String> servPublico = new JComboBox<String>(choicesBool);
			myPanel.add(servPublico);
			myPanel.add(new JLabel("Tiene administracion:"));
    		final JComboBox<String> administracion = new JComboBox<String>(choicesBool);
			myPanel.add(administracion);
			String identificacionOperadorUsuario = this.identificadorLogin;
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear ApartamentoAlquiler", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && identificacionOperadorUsuario != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 30, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOApartamentoAlquiler tb = alohandes.adicionarApartamentoAlquiler(alojamiento.getId(), String.valueOf(servPublico.getSelectedItem()), String.valueOf(administracion.getSelectedItem()), identificacionOperadorUsuario);
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un ApartamentoAlquiler con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarApartamentoAlquiler\n\n";
        		resultado += "ApartamentoAlquiler adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
     * Borra de la base de datos el ApartamentoAlquiler con el identificador dado po el usuario
     * Cuando dicho ApartamentoAlquiler no existe, se indica que se borraron 0 registros de la base de datos
     */
    public void eliminarApartamentoAlquiler( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del alojamiento?", "Borrar alojamiento por Id", JOptionPane.QUESTION_MESSAGE);
			if (idTipoStr != null)
    		{

				VOApartamentoAlquiler ApartamentoAlquiler = alohandes.darApartamentoAlquilerPorId(Long.valueOf(idTipoStr));
				Timestamp fechaUltimaReserva = alohandes.darUltimaFechaPorIdAlojamiento(Long.valueOf(idTipoStr));
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

				if(ApartamentoAlquiler != null && (ApartamentoAlquiler.getIdentificacionOperadorUsuario().equals(this.identificadorLogin) || this.identificadorLogin.equals("Admin")) && (fechaUltimaReserva == null || (int) ChronoUnit.DAYS.between(fechaActual.toInstant(), fechaUltimaReserva.toInstant()) <= -1))
				{
					long idAlojamiento = Long.valueOf (idTipoStr);
					long tbEliminados = alohandes.eliminarApartamentoAlquiler(idAlojamiento);
					alohandes.eliminarAlojamientoServicioPorIdAlojamiento(idAlojamiento);
					alohandes.eliminarAlojamiento(idAlojamiento);

					String resultado = "En eliminar ApartamentoAlquiler\n\n";
					resultado += tbEliminados + " ApartamentoAlquiler eliminados\n";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else{
					panelDatos.actualizarInterfaz("Operación cancelada, puede que el alojamiento no exista o no sea de su propiedad");
				}
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


	/* ****************************************************************
	 * 			CRUD de ViviendaTemporal
	 *****************************************************************/
    public void adicionarViviendaTemporal( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			JTextField numHabitaciones = new JTextField(5);
			myPanel.add(new JLabel("Numero habitaciones:"));
			myPanel.add(numHabitaciones);
			JTextField precSegArren = new JTextField(5);
			myPanel.add(new JLabel("Precio seguro arrendamiento:"));
			myPanel.add(precSegArren);
			JTextField caracSeguro = new JTextField(5);
			myPanel.add(new JLabel("Caracteristicas seguro:"));
			myPanel.add(caracSeguro);
			JTextField diasAlquilado = new JTextField(5);
			myPanel.add(new JLabel("Dias alquilado:"));
			myPanel.add(diasAlquilado);
			JTextField identificacionOperadorUsuario = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("Identificacion operador usuario:"));
			myPanel.add(identificacionOperadorUsuario);
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear ViviendaTemporal", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && numHabitaciones.getText() != null && precSegArren.getText() != null && caracSeguro.getText() != null && diasAlquilado.getText() != null && identificacionOperadorUsuario.getText() != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOViviendaTemporal tb = alohandes.adicionarViviendaTemporal(alojamiento.getId(), Integer.valueOf(numHabitaciones.getText()), Integer.valueOf(precSegArren.getText()), caracSeguro.getText(), Integer.valueOf(diasAlquilado.getText()), identificacionOperadorUsuario.getText());
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un ViviendaTemporal con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarViviendaTemporal\n\n";
        		resultado += "ViviendaTemporal adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/* ****************************************************************
	 * 			CRUD de ViviendaTemporal
	 *****************************************************************/
    public void adicionarViviendaTemporalUsuario( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			JTextField numHabitaciones = new JTextField(5);
			myPanel.add(new JLabel("Numero habitaciones:"));
			myPanel.add(numHabitaciones);
			JTextField precSegArren = new JTextField(5);
			myPanel.add(new JLabel("Precio seguro arrendamiento:"));
			myPanel.add(precSegArren);
			JTextField caracSeguro = new JTextField(5);
			myPanel.add(new JLabel("Caracteristicas seguro:"));
			myPanel.add(caracSeguro);
			JTextField diasAlquilado = new JTextField(5);
			myPanel.add(new JLabel("Dias alquilado:"));
			myPanel.add(diasAlquilado);
			String identificacionOperadorUsuario = this.identificadorLogin;
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear ViviendaTemporal", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && numHabitaciones.getText() != null && precSegArren.getText() != null && caracSeguro.getText() != null && diasAlquilado.getText() != null && identificacionOperadorUsuario!= null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOViviendaTemporal tb = alohandes.adicionarViviendaTemporal(alojamiento.getId(), Integer.valueOf(numHabitaciones.getText()), Integer.valueOf(precSegArren.getText()), caracSeguro.getText(), Integer.valueOf(diasAlquilado.getText()), identificacionOperadorUsuario);
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un ViviendaTemporal con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarViviendaTemporal\n\n";
        		resultado += "ViviendaTemporal adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
     * Borra de la base de datos el ViviendaTemporal con el identificador dado po el usuario
     * Cuando dicho ViviendaTemporal no existe, se indica que se borraron 0 registros de la base de datos
     */
    public void eliminarViviendaTemporal( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del alojamiento?", "Borrar ViviendaTemporal por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{

				VOViviendaTemporal viviendaTemporal = alohandes.darViviendaTemporalPorId(Long.valueOf(idTipoStr));
				Timestamp fechaUltimaReserva = alohandes.darUltimaFechaPorIdAlojamiento(Long.valueOf(idTipoStr));
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

				if(viviendaTemporal != null && (viviendaTemporal.getIdentificacionOperadorUsuario().equals(this.identificadorLogin) || this.identificadorLogin.equals("Admin")) && (fechaUltimaReserva == null || (int) ChronoUnit.DAYS.between(fechaActual.toInstant(), fechaUltimaReserva.toInstant()) <= -1))
				{
    			long idAlojamiento = Long.valueOf (idTipoStr);
    			long tbEliminados = alohandes.eliminarViviendaTemporal(idAlojamiento);
				alohandes.eliminarAlojamientoServicioPorIdAlojamiento(idAlojamiento);
    			alohandes.eliminarAlojamiento(idAlojamiento);

    			String resultado = "En eliminar ViviendaTemporal\n\n";
    			resultado += tbEliminados + " ViviendaTemporal eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
				}
				else{
					panelDatos.actualizarInterfaz("Operación cancelada, puede que el alojamiento no exista o no sea de su propiedad");
				}
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }



	/* ****************************************************************
	 * 			CRUD de Reserva
	 *****************************************************************/

	/**
	 * Adiciona un registro de reserva con la información dada por el usuario
	 * Se crea una nueva tupla de Reserva en la base de datos
	 */
	public void adicionarReserva( )
	{
		try 
    	{
    		String FechaInicio = JOptionPane.showInputDialog (this, "Ingrese la fecha de inicio de la reserva (dd/MM/yyyy)", "Adicionar Reserva", JOptionPane.QUESTION_MESSAGE);
    		String FechaFin = JOptionPane.showInputDialog (this, "Ingrese la fecha en que finaliza la reserva (dd/MM/yyyy)", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String identificacionCliente = JOptionPane.showInputDialog (this, "Ingrese su identificacion", "Identificacion Cliente", JOptionPane.QUESTION_MESSAGE);
			String idAlojamiento = JOptionPane.showInputDialog (this, "Ingrese el id del alojamineto que va a reservar", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			Cliente cliente = alohandes.darClientePorIdentificacion(identificacionCliente);
			if (FechaInicio!= null && FechaFin != null && identificacionCliente != null && idAlojamiento != null && cliente != null)
    		{

				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				Timestamp fechaIni = new Timestamp(formatoFecha.parse(FechaInicio).getTime());
				Timestamp fechaFin = new Timestamp(formatoFecha.parse(FechaFin).getTime());

				Instant instant1 = fechaIni.toInstant();
        		Instant instant2 = fechaFin.toInstant();
				int daysBetween = (int) ChronoUnit.DAYS.between(instant1, instant2);
				long idAlojamientolong = Long.valueOf (idAlojamiento);
				VOAlojamiento var = darAlojamientoPorId(idAlojamientolong);
				VOHabitacionViviendaUniversitaria perteneceViviendaUniversitaria = alohandes.darHabitacionViviendaUniversitariaPorId(idAlojamientolong);
				VOReserva tb = null;
				int diasMin = 0;
				if (var != null) {
					diasMin = var.getDuracionMin();
				} 

				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
				Timestamp fechaUltimaReserva = cliente.getUltimaFechaReserva();
				Instant instantOne = fechaActual.toInstant();
        		Instant instantTwo = fechaUltimaReserva.toInstant();
				int daysBetweenUltimaReserva = (int) ChronoUnit.DAYS.between(instantOne, instantTwo);

				
				List<Reserva> reservas = alohandes.darReservasPorIdAlojamiento(idAlojamientolong);
				for (Reserva reserva : reservas) {
					if ((fechaIni.before(reserva.getFechaFin()) && fechaIni.after(reserva.getFechaIni())) || (fechaFin.before(reserva.getFechaFin()) && fechaFin.after(reserva.getFechaIni()))) {
						throw new Exception ("La reserva se solapa con otra reserva!");
					}
				}

				if (diasMin > daysBetween) {
					throw new Exception ("Reserva no cumple con la cantidad minima de dias para la reserva!");
				} else if (daysBetweenUltimaReserva == 0) {
					throw new Exception ("Ya se realizo la maxima cantidad de reservas para este cliente por hoy!");
				} else if (perteneceViviendaUniversitaria != null && (cliente.getTipoVinculo().equals("padre de estudiante") || cliente.getTipoVinculo().equals("egresado"))) {
					throw new Exception ("Egresados ni padres de familia pueden reservar una habitacion en las viviendas universitarias!");
				} else if (daysBetween <= 0 || (int) ChronoUnit.DAYS.between(instantOne, instant1) <= 0 || ChronoUnit.DAYS.between(instantOne, instant2) <= 0) {
					throw new Exception ("Error en las fechas ingresadas!");
				} else {
					tb = alohandes.adicionarReserva(fechaIni, fechaFin, identificacionCliente, idAlojamientolong);
					alohandes.cambiarCiudadBebedor(identificacionCliente, fechaActual);
				}

				if (tb == null)
        		{
        			throw new Exception ("No se pudo registrar la reserva del cliente con id " + identificacionCliente + " para el alojamiento con id " + idAlojamiento);
        		}

        		String resultado = "En adicionarReserva\n\n";
        		resultado += "Reserva adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
     * Busca el Reserva con el id indicado por el usuario 
     */
    public VOReserva buscarReservaPorId(long id)
    {
    	try 
    	{
    		VOReserva reserva = alohandes.darReservaPorId(id);
			return reserva;
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
			return null;
		}
    }

	/**
	 * Adiciona un registro de reserva con la información dada por el usuario
	 * Se crea una nueva tupla de Reserva en la base de datos
	 */
	public void adicionarReservaUsuario( )
	{
		try 
    	{
    		String FechaInicio = JOptionPane.showInputDialog (this, "Ingrese la fecha de inicio de la reserva (dd/MM/yyyy)", "Adicionar Reserva", JOptionPane.QUESTION_MESSAGE);
    		String FechaFin = JOptionPane.showInputDialog (this, "Ingrese la fecha en que finaliza la reserva (dd/MM/yyyy)", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String identificacionCliente = this.identificadorLogin;
			String idAlojamiento = JOptionPane.showInputDialog (this, "Ingrese el id del alojamineto que va a reservar", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			Cliente cliente = alohandes.darClientePorIdentificacion(identificacionCliente);
			if (FechaInicio!= null && FechaFin != null && identificacionCliente != null && idAlojamiento != null && cliente != null)
    		{

				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				Timestamp fechaIni = new Timestamp(formatoFecha.parse(FechaInicio).getTime());
				Timestamp fechaFin = new Timestamp(formatoFecha.parse(FechaFin).getTime());
				
				Instant instant1 = fechaIni.toInstant();
        		Instant instant2 = fechaFin.toInstant();
				int daysBetween = (int) ChronoUnit.DAYS.between(instant1, instant2);
				long idAlojamientolong = Long.valueOf (idAlojamiento);
				VOAlojamiento var = darAlojamientoPorId(idAlojamientolong);
				VOHabitacionViviendaUniversitaria perteneceViviendaUniversitaria = alohandes.darHabitacionViviendaUniversitariaPorId(idAlojamientolong);
				VOReserva tb = null;
				int diasMin = 0;
				if (var != null) {
					diasMin = var.getDuracionMin();;
				} 

				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
				Timestamp fechaUltimaReserva = cliente.getUltimaFechaReserva();
				Instant instantOne = fechaActual.toInstant();
        		Instant instantTwo = fechaUltimaReserva.toInstant();
				int daysBetweenUltimaReserva = (int) ChronoUnit.DAYS.between(instantOne, instantTwo);


				if (diasMin > daysBetween) {
					throw new Exception ("Reserva no cumple con la cantidad minima de dias para la reserva!");
				} else if (daysBetweenUltimaReserva == 0) {
					throw new Exception ("Ya se realizo la maxima cantidad de reservas para este cliente por hoy!");
				} else if (perteneceViviendaUniversitaria != null && (cliente.getTipoVinculo().equals("padre de estudiante") || cliente.getTipoVinculo().equals("egresado"))) {
					throw new Exception ("Egresados ni padres de familia pueden reservar una habitacion en las viviendas universitarias!");
				} else if (daysBetween <= 0 || (int) ChronoUnit.DAYS.between(instantOne, instant1) <= 0 || ChronoUnit.DAYS.between(instantOne, instant2) <= 0) {
					throw new Exception ("Error en las fechas ingresadas!");
				} else {
					tb = alohandes.adicionarReserva(fechaIni, fechaFin, identificacionCliente, idAlojamientolong);
					alohandes.cambiarCiudadBebedor(identificacionCliente, fechaActual);
				}

				if (tb == null)
        		{
        			throw new Exception ("No se pudo registrar la reserva del cliente con id " + identificacionCliente + " para el alojamiento con id " + idAlojamiento);
        		}
        		String resultado = "En adicionarReserva\n\n";
        		resultado += "Reserva adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Borra de la base de datos la reserva con el identificador dado por el usuario
	 * Cuando dicho reserva no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarReservaPorId( )
	{
		try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id de la reserva", "Borrar reserva por Id", JOptionPane.QUESTION_MESSAGE);
			long idTipo = Long.valueOf (idTipoStr);
			VOReserva val = buscarReservaPorId(idTipo);
    		if (idTipoStr != null && val.getIdentificacionCliente().equals(this.identificadorLogin))
    		{
    			long tbEliminados = alohandes.eliminarReservaPorId(idTipo);

    			String resultado = "En eliminar Reserva\n\n";
    			resultado += tbEliminados + " Reserva eliminada\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("ERROR: No se pudo eliminar la reserva con id " + idTipo + " porque no existe o no es de su propiedad");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de HabitacionHotel
	 *****************************************************************/

	/**
	 * Adiciona un registro de habitacionHotel con la información dada por el usuario
	 * Se crea una nueva tupla de HabitacionHotel en la base de datos
	 */
	public void adicionarHabitacionHotel( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			myPanel.add(new JLabel("Tipo de habitación:"));
			String[] tipoHabitacionOpciones = {"estandar", "semisuite", "suite"};
    		final JComboBox<String> tipoHabitacion = new JComboBox<String>(tipoHabitacionOpciones);
			myPanel.add(tipoHabitacion);

			JTextField tamanio = new JTextField(5);
			myPanel.add(new JLabel("Tamaño:"));
			myPanel.add(tamanio);

			JTextField idHotel = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("RegDeComercio del hotel al que corresponde:"));
			myPanel.add(idHotel);
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear habitacion de hotel", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && tamanio.getText() != null && idHotel.getText() != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOHabitacionHotel tb = alohandes.adicionarHabitacionHotel(alojamiento.getId(), String.valueOf(tipoHabitacion.getSelectedItem()), String.valueOf(tamanio.getText()),  String.valueOf(idHotel.getText()));
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un habitacion hotel con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarHabitacionHotel\n\n";
        		resultado += "habitacion hotel adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
	 * Adiciona un registro de habitacionHotel con la información dada por el usuario
	 * Se crea una nueva tupla de HabitacionHotel en la base de datos
	 */
	public void adicionarHabitacionHotelUsuario( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			myPanel.add(new JLabel("Tipo de habitación:"));
			String[] tipoHabitacionOpciones = {"estandar", "semisuite", "suite"};
    		final JComboBox<String> tipoHabitacion = new JComboBox<String>(tipoHabitacionOpciones);
			myPanel.add(tipoHabitacion);

			JTextField tamanio = new JTextField(5);
			myPanel.add(new JLabel("Tamaño:"));
			myPanel.add(tamanio);

			String idHotel = this.identificadorLogin;
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear habitacion de hotel", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && tamanio.getText() != null && idHotel != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOHabitacionHotel tb = alohandes.adicionarHabitacionHotel(alojamiento.getId(), String.valueOf(tipoHabitacion.getSelectedItem()), String.valueOf(tamanio.getText()),  String.valueOf(idHotel));
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un habitacion hotel con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarHabitacionHotel\n\n";
        		resultado += "habitacion hotel adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
	 * Elimina de la base de datos la habitacionHotel con el identificador dado por el usuario
	 * Cuando dicha habitacionHotel no existe, se indica que se borraron 0 registros de la base de datos
	 */
    public void eliminarHabitacionHotel( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del alojamiento?", "Borrar alojamiento por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{

				VOHabitacionHotel habitacionHotel = alohandes.darHabitacionHotelPorId(Long.valueOf(idTipoStr));
				Timestamp fechaUltimaReserva = alohandes.darUltimaFechaPorIdAlojamiento(Long.valueOf(idTipoStr));
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

				if(habitacionHotel != null && (habitacionHotel.getIdHotel().equals(this.identificadorLogin) || this.identificadorLogin.equals("Admin")) && (fechaUltimaReserva == null || (int) ChronoUnit.DAYS.between(fechaActual.toInstant(), fechaUltimaReserva.toInstant()) <= -1))
				{
					long idAlojamiento = Long.valueOf (idTipoStr);
					long tbEliminados = alohandes.eliminarHabitacionHotelPorId(idAlojamiento);
					alohandes.eliminarAlojamientoServicioPorIdAlojamiento(idAlojamiento);
					alohandes.eliminarAlojamiento(idAlojamiento);

					String resultado = "En eliminar habitacionHotel\n\n";
					resultado += tbEliminados + " Alojamientos eliminados\n";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}else{
					panelDatos.actualizarInterfaz("Operación cancelada, puede que el alojamiento no exista o no sea de su propiedad");
				}

    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
     * Busca el Hotel con el regComercio indicado por el usuario y lo muestra en el panel de datos
     */
    public VOHotel buscarHotelPorRegComercio(String regComercio)
    {
    	try 
    	{
    		VOHotel hotel = alohandes.darHotelPorRegComercio(regComercio);
    		return hotel;
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
			return null;
		}
    }



	/* ****************************************************************
	 * 			CRUD de HabitacionHostal
	 *****************************************************************/

	/**
	 * Adiciona un registro de habitacionHostalcon la información dada por el usuario
	 * Se crea una nueva tupla de HabitacionHostal en la base de datos
	 */
	public void adicionarHabitacionHostal( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo); 

			// Datos de interfaz otro

			JTextField aforo = new JTextField(5);
			myPanel.add(new JLabel("Aforo:"));
			myPanel.add(aforo);

			JTextField idHostal = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("RegDeComercio del hostal al que corresponde:"));
			myPanel.add(idHostal);
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear habitacion de hostal", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && aforo.getText() != null && idHostal.getText() != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOHabitacionHostal tb = alohandes.adicionarHabitacionHostal(alojamiento.getId(),  Integer.valueOf(aforo.getText()),  String.valueOf(idHostal.getText()));
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un habitacion hostal con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarHabitacionHostal\n\n";
        		resultado += "habitacion hostal adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
	 * Adiciona un registro de habitacionHostalcon la información dada por el usuario
	 * Se crea una nueva tupla de HabitacionHostal en la base de datos
	 */
	public void adicionarHabitacionHostalUsuario( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo); 

			// Datos de interfaz otro

			JTextField aforo = new JTextField(5);
			myPanel.add(new JLabel("Aforo:"));
			myPanel.add(aforo);

			String idHostal = this.identificadorLogin;
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear habitacion de hostal", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && aforo.getText() != null && idHostal != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOHabitacionHostal tb = alohandes.adicionarHabitacionHostal(alojamiento.getId(),  Integer.valueOf(aforo.getText()),  String.valueOf(idHostal));
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear un habitacion hostal con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarHabitacionHostal\n\n";
        		resultado += "habitacion hostal adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


	/**
	 * Elimina de la base de datos la habitacionHostal con el identificador dado por el usuario
	 * Cuando dicha habitacionHostal no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarHabitacionHostal( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del alojamiento?", "Borrar alojamiento por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
				VOHabitacionHostal habitacionHostal = alohandes.darHabitacionHostalPorId(Long.valueOf(idTipoStr));
				Timestamp fechaUltimaReserva = alohandes.darUltimaFechaPorIdAlojamiento(Long.valueOf(idTipoStr));
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

				if(habitacionHostal != null && (habitacionHostal.getIdHostal().equals(this.identificadorLogin) || this.identificadorLogin.equals("Admin")) && (fechaUltimaReserva == null || (int) ChronoUnit.DAYS.between(fechaActual.toInstant(), fechaUltimaReserva.toInstant()) <= -1))
				{
					long idAlojamiento = Long.valueOf (idTipoStr);
					long tbEliminados = alohandes.eliminarHabitacionHostalPorId(idAlojamiento);
					alohandes.eliminarAlojamientoServicioPorIdAlojamiento(idAlojamiento);
					alohandes.eliminarAlojamiento(idAlojamiento);

					String resultado = "En eliminar habitacionHostal\n\n";
					resultado += tbEliminados + " Alojamientos eliminados\n";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}else{
					panelDatos.actualizarInterfaz("Operación cancelada, puede que el alojamiento no exista o no sea de su propiedad");
				}
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/* ****************************************************************
	 * 			CRUD de AlojamientoServicio
	 *****************************************************************/

	 public void adicionarAlojamientoServicio( )
	 {
		 try 
		 {
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
			JTextField idAlojamiento = new JTextField(5 );
			myPanel.add(new JLabel("Id del alojamiento (regComercio / Id):"));
			myPanel.add(idAlojamiento);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			 // Datos de interfaz otro
			myPanel.add(new JLabel("Tipo Servicio:"));
			String[] tipoServicioOpciones = {"salaEsparcimiento", "tvCable", "gimnasio", "salaEstudio", "wifi", "baniera", "sala", "yacuzzi", "menaje", "luz", "telefono", "cocina", "agua"};
    		final JComboBox<String> tipoServicio = new JComboBox<String>(tipoServicioOpciones);
			myPanel.add(tipoServicio);
 
			 JOptionPane.showMessageDialog(this, myPanel, "Crear AlojamientoServicio", JOptionPane.DEFAULT_OPTION);
			 if (idAlojamiento != null && costo != null && tipoServicio != null)
			 {
				String val = String.valueOf(tipoServicio.getSelectedItem());
				VOAlojamientoServicio tb = null;
				if (val.equals("salaEsparcimiento")){
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 1, Integer.parseInt(costo.getText()));
				} else if (val.equals("tvCable")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 2, Integer.parseInt(costo.getText()));
				} else if (val.equals("gimnasio")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 3, Integer.parseInt(costo.getText()));
				} else if (val.equals("salaEstudio")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 4, Integer.parseInt(costo.getText()));
				} else if (val.equals("wifi")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 5, Integer.parseInt(costo.getText()));
				} else if (val.equals("baniera")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 6, Integer.parseInt(costo.getText()));
				} else if (val.equals("sala")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 7, Integer.parseInt(costo.getText()));
				} else if (val.equals("yacuzzi")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 8, Integer.parseInt(costo.getText()));
				} else if (val.equals("menaje")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 9, Integer.parseInt(costo.getText()));
				} else if (val.equals("luz")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 10, Integer.parseInt(costo.getText()));
				} else if (val.equals("telefono")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 11, Integer.parseInt(costo.getText()));
				} else if (val.equals("cocina")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 12, Integer.parseInt(costo.getText()));
				} else if (val.equals("agua")) {
					tb = alohandes.adicionarAlojamientoServicio(Long.parseLong(idAlojamiento.getText()), 13, Integer.parseInt(costo.getText()));
				}
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un hotel con nombre: " + idAlojamiento.getText());
				}
				String resultado = "En adicionarHotel\n\n";
				resultado += "AlojamientoServicio adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
	
	/* ****************************************************************
	 * 			CRUD de Alojamiento
	 *****************************************************************/

	/**
     * Consulta en la base de datos los top 20 alojamientos ofertados
     */
    public void listarTop20( )
    {
    	try 
    	{
			List<Object[]> lista = alohandes.darTop20Ofertas();

			String resultado = "El top 20 ofertas es:\n";
			resultado +=  "\n" + listarTop20Ofertas(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/* ****************************************************************
	 * 			CRUD de HabitacionViviendaUniversitaria
	 *****************************************************************/

	/**
	 * Adiciona un registro de habitacionViviendaUniversitaria con la información dada por el usuario
	 * Se crea una nueva tupla de habitacionViviendaUniversitaria en la base de datos
	 */
	public void adicionarHabitacionViviendaUniversitaria( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			myPanel.add(new JLabel("Tipo de habitación:"));
			String[] tipoHabitacionOpciones = {"individual", "compartido"};
    		final JComboBox<String> tipoHabitacion = new JComboBox<String>(tipoHabitacionOpciones);
			myPanel.add(tipoHabitacion);

			JTextField capacidad = new JTextField(5);
			myPanel.add(new JLabel("Capacidad:"));
			myPanel.add(capacidad);

			JTextField idViviendaUniversitaria = new JTextField(this.identificadorLogin, 5);
			myPanel.add(new JLabel("RegDeComercio de la vivienda universitaria al que corresponde:"));
			myPanel.add(idViviendaUniversitaria);
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear habitacion de vivienda universitaria", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && capacidad.getText() != null && idViviendaUniversitaria.getText() != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOHabitacionViviendaUniversitaria tb = alohandes.adicionarHabitacionViviendaUniversitaria(alojamiento.getId(), String.valueOf(tipoHabitacion.getSelectedItem()), Integer.valueOf(capacidad.getText()),  String.valueOf(idViviendaUniversitaria.getText()));
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear una habitacion de vivienda universitaria con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarHabitacionViviendaUniversitaria\n\n";
        		resultado += "habitacion vivienda universitaria adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
	 * Adiciona un registro de habitacionViviendaUniversitaria con la información dada por el usuario
	 * Se crea una nueva tupla de habitacionViviendaUniversitaria en la base de datos
	 */
	public void adicionarHabitacionViviendaUniversitariaUsuario( )
    {
    	try 
    	{
			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			// Datos de interfaz alojamiento
			JTextField ubicacion = new JTextField(5);
			myPanel.add(new JLabel("Ubicacion:"));
			myPanel.add(ubicacion);
			JTextField costo = new JTextField(5);
			myPanel.add(new JLabel("Costo:"));
			myPanel.add(costo);

			// Datos de interfaz otro
			myPanel.add(new JLabel("Tipo de habitación:"));
			String[] tipoHabitacionOpciones = {"individual", "compartida"};
    		final JComboBox<String> tipoHabitacion = new JComboBox<String>(tipoHabitacionOpciones);
			myPanel.add(tipoHabitacion);

			JTextField capacidad = new JTextField(5);
			myPanel.add(new JLabel("Capacidad:"));
			myPanel.add(capacidad);

			String idViviendaUniversitaria = this.identificadorLogin;
		
    		JOptionPane.showMessageDialog(this, myPanel, "Crear habitacion de vivienda universitaria", JOptionPane.DEFAULT_OPTION);
    		if (ubicacion.getText() != null && costo.getText() != null && capacidad.getText() != null && idViviendaUniversitaria != null)
    		{
				VOAlojamiento alojamiento = alohandes.adicionarAlojamiento(ubicacion.getText(), 1, Integer.valueOf(costo.getText()));
        		if (alojamiento == null)
        		{
        			throw new Exception ("No se pudo crear un alojamiento con ubicacion: " + ubicacion.getText());
        		}
				VOHabitacionViviendaUniversitaria tb = alohandes.adicionarHabitacionViviendaUniversitaria(alojamiento.getId(), String.valueOf(tipoHabitacion.getSelectedItem()), Integer.valueOf(capacidad.getText()),  String.valueOf(idViviendaUniversitaria));
				if (tb == null)
				{
					alohandes.eliminarAlojamiento(alojamiento.getId());
					throw new Exception ("No se pudo crear una habitacion de vivienda universitaria con id de alojamiento: " + alojamiento.getId());
				}
        		String resultado = "En adicionarHabitacionViviendaUniversitaria\n\n";
        		resultado += "habitacion vivienda universitaria adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/**
	 * Elimina de la base de datos la habitacionViviendaUniversitaria con el identificador dado por el usuario
	 * Cuando dicha habitacionViviendaUniversitaria no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarHabitacionViviendaUniversitaria( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del alojamiento?", "Borrar alojamiento por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
				VOHabitacionViviendaUniversitaria habitacionViviendaUniversitaria = alohandes.darHabitacionViviendaUniversitariaPorId(Long.valueOf(idTipoStr));
				Timestamp fechaUltimaReserva = alohandes.darUltimaFechaPorIdAlojamiento(Long.valueOf(idTipoStr));
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

				if(habitacionViviendaUniversitaria != null && (habitacionViviendaUniversitaria.getIdViviendaUniversitaria().equals(this.identificadorLogin) || this.identificadorLogin.equals("Admin")) && (fechaUltimaReserva == null || (int) ChronoUnit.DAYS.between(fechaActual.toInstant(), fechaUltimaReserva.toInstant()) <= -1))
				{
					long idAlojamiento = Long.valueOf (idTipoStr);
					long tbEliminados = alohandes.eliminarHabitacionViviendaUniversitariaPorId(idAlojamiento);
					alohandes.eliminarAlojamientoServicioPorIdAlojamiento(idAlojamiento);
					alohandes.eliminarAlojamiento(idAlojamiento);

					String resultado = "En eliminar habitacionViviendaUniversitaria\n\n";
					resultado += tbEliminados + " Alojamientos eliminados\n";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}else{
					panelDatos.actualizarInterfaz("Operación cancelada, puede que el alojamiento no exista o no sea de su propiedad");
				}
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
	
	/**
	 * RFC4 - MOSTRAR LOS ALOJAMIENTOS DISPONIBLES EN UN RANGO DE FECHAS, 
	 * QUE CUMPLEN CON UN CONJUNTO DE REQUERIMIENTOS DE DOTACIÓN O SERVICIOS.
	 */

	public void mostrarAlojamientoConCondicion( )
	{
		try 
    	{
			

			JPanel myPanel = new JPanel(new GridBagLayout());
			
			JTextField fechaIni = new JTextField(7);
			JTextField fechaFin = new JTextField(7);
			//Creacion de CheckBox
			JCheckBox salaEsparcimientoCheck = new JCheckBox("Sala de esparcimiento");
			JCheckBox tvCableCheck = new JCheckBox("tvCable");
			JCheckBox gimnasioCheck = new JCheckBox("Gimnasio");
			JCheckBox salaEstudioCheck = new JCheckBox("Sala de estudio");
			JCheckBox wifiCheck = new JCheckBox("Wifi");
			JCheckBox banieraCheck = new JCheckBox("Baniera");
			JCheckBox salaCheck = new JCheckBox("Sala");
			JCheckBox yacuzziCheck = new JCheckBox("Yacuzzi");
			JCheckBox menajeCheck = new JCheckBox("Menaje");
			JCheckBox luzCheck = new JCheckBox("Luz");
			JCheckBox telefonoCheck = new JCheckBox("Telefono");
			JCheckBox cocinaCheck = new JCheckBox("Cocina");
			JCheckBox aguaCheck = new JCheckBox("Agua");

			GridBagConstraints gbc = new GridBagConstraints();

			//Izquierda
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;

			gbc.gridy = 1;
			myPanel.add(salaEsparcimientoCheck, gbc);

			gbc.gridy = 2;
			myPanel.add(tvCableCheck, gbc);

			gbc.gridy = 3;
			myPanel.add(gimnasioCheck, gbc);

			gbc.gridy = 4;
			myPanel.add(salaEstudioCheck, gbc);

			gbc.gridy = 5;
			myPanel.add(wifiCheck, gbc);

			gbc.gridy = 6;
			myPanel.add(banieraCheck, gbc);

			gbc.gridy = 7;
			myPanel.add(salaCheck, gbc);

			gbc.gridy = 8;
			myPanel.add(new JLabel("Fecha de inicio (dd/MM/yyyy) "), gbc);

			gbc.gridy = 9;
			myPanel.add(fechaIni,gbc);


			//Centro
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridx = 1;
			gbc.gridy = 0;
			myPanel.add(new JLabel("Escoja los servicios que desea"), gbc);
			
			//Derecha
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 2;

			gbc.gridy = 1;
			myPanel.add(yacuzziCheck, gbc);

			gbc.gridy = 2;
			myPanel.add(menajeCheck, gbc);

			gbc.gridy = 3;
			myPanel.add(luzCheck, gbc);

			gbc.gridy = 4;
			myPanel.add(telefonoCheck, gbc);

			gbc.gridy = 5;
			myPanel.add(cocinaCheck, gbc);

			gbc.gridy = 6;
			myPanel.add(aguaCheck, gbc);

			gbc.gridy = 8;
			myPanel.add(new JLabel("Fecha de fin (dd/MM/yyyy)"), gbc);

			gbc.gridy = 9;
			myPanel.add(fechaFin, gbc);

			

    		JOptionPane.showMessageDialog(this, myPanel, "Consultar alojamientos con condiciones", JOptionPane.DEFAULT_OPTION);

    		if (fechaIni.getText() != null && fechaFin.getText() != null )
    		{	
				//Selecciones
				List<String> servicios = new ArrayList<String>();
				if(salaEsparcimientoCheck.isSelected())
				{
					servicios.add("salaEsparcimiento");
				}

				if(tvCableCheck.isSelected())
				{
					servicios.add("tvCable");
				}

				if(gimnasioCheck.isSelected())
				{
					servicios.add("gimnasio");
				}

				if(salaEstudioCheck.isSelected())
				{
					servicios.add("salaEstudio");
				}

				if(wifiCheck.isSelected())
				{
					servicios.add("wifi");
				}

				if(banieraCheck.isSelected())
				{
					servicios.add("baniera");
				}

				if(salaCheck.isSelected())
				{
					servicios.add("sala");
				}

				if(yacuzziCheck.isSelected())
				{
					servicios.add("yacuzzi");
				}

				if(menajeCheck.isSelected())
				{
					servicios.add("menaje");
				}

				if(luzCheck.isSelected())
				{
					servicios.add("luz");
				}

				if(telefonoCheck.isSelected())
				{
					servicios.add("telefono");
				}

				if(cocinaCheck.isSelected())
				{
					servicios.add("cocina");
				}

				if(aguaCheck.isSelected())
				{
					servicios.add("agua");
				}
					
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				Timestamp fechaInicio = new Timestamp(formatoFecha.parse(fechaIni.getText()).getTime());
				Timestamp fechaFinal = new Timestamp(formatoFecha.parse(fechaFin.getText()).getTime());


				List<Object[]> alojamientosConCondicion= alohandes.darAlojamientosConCondicion(fechaInicio, fechaFinal, servicios);
        		if ( alojamientosConCondicion == null)
        		{
        			throw new Exception ("No se encontro ningun alojamiento con esas condciciones");
        		}

        		String resultado = "En mostrarAlojamientoConCondicion\n\n";
        		resultado += "Los alojamientos con esas condiciones son:\n";
				
				for (Object[] alojamiento : alojamientosConCondicion) {
					resultado += "id: " + alojamiento[0] + " ubicación: " +alojamiento[1] + " costo: " + alojamiento[2] + "\n";
				}
    			
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	
    }


	/**
	* RFC3 - MOSTRAR EL INDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS 
	*/

	public void mostrarIndiceOcupacion( )
	{	
		try{
			List<Object[]> indicesOcupacion= alohandes.darIndiceOcupacion();
			if ( indicesOcupacion == null)
			{
				throw new Exception ("No se encontraron alojamientos disponibles para consultar el indice de ocupacion");
			}
			String resultado = "En mostrar indice de ocupación\n\n";
        	resultado += "El indice de ocupación para los alojamientos es el siguiente:\n";

			for (Object[] ocupacion : indicesOcupacion) {
				resultado += "id del alojamiento: " + ocupacion[0] + " ubicación: " + ocupacion[1] 
									+ " Cantidad de días reservado: " + ocupacion[2] 
									+ " Indice de ocupación: " + ocupacion[3] + "\n";
			}
			
			panelDatos.actualizarInterfaz(resultado);


		}
		catch(Exception e){
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
 
	}	

	/**
	 * RF9
	 **/

	public void deshabilitarHabitacionHuesped(){
		
		try{

			String idAlojamiento = JOptionPane.showInputDialog (this, "Ingrese el id del alojamiento a deshabilitar", "Deshabilitar alojamiento", JOptionPane.QUESTION_MESSAGE);

			if(idAlojamiento != null)
			{
				String resp = alohandes.deshabilitarAlojamiento(Long.parseLong(idAlojamiento), "A_HABITACIONHUESPED");
				panelDatos.actualizarInterfaz(resp);
			}
			else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}

		}
		catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void deshabilitarApartamentoAlquiler(){

		try{

			String idAlojamiento = JOptionPane.showInputDialog (this, "Ingrese el id del alojamiento a deshabilitar", "Deshabilitar alojamiento", JOptionPane.QUESTION_MESSAGE);

			if(idAlojamiento != null)
			{
				String resp = alohandes.deshabilitarAlojamiento(Long.parseLong(idAlojamiento), "A_APARTAMENTOALQUILER");
				panelDatos.actualizarInterfaz(resp);
			}
			else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}

		}
		catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void deshabilitarViviendaTemporal(){

		try{

			String idAlojamiento = JOptionPane.showInputDialog (this, "Ingrese el id del alojamiento a deshabilitar", "Deshabilitar alojamiento", JOptionPane.QUESTION_MESSAGE);

			if(idAlojamiento != null)
			{
				String resp = alohandes.deshabilitarAlojamiento(Long.parseLong(idAlojamiento), "A_VIVIENDATEMPORAL");
				panelDatos.actualizarInterfaz(resp);
			}
			else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}

		}
		catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void deshabilitarHabitacionHotel(){
		
		try{

			String idAlojamiento = JOptionPane.showInputDialog (this, "Ingrese el id del alojamiento a deshabilitar", "Deshabilitar alojamiento", JOptionPane.QUESTION_MESSAGE);

			if(idAlojamiento != null)
			{
				String resp = alohandes.deshabilitarAlojamiento(Long.parseLong(idAlojamiento), "A_HABITACIONHOTEL");
				panelDatos.actualizarInterfaz(resp);
			}
			else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}

		}
		catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void deshabilitarHabitacionViviendaUniversitaria(){

		try{

			String idAlojamiento = JOptionPane.showInputDialog (this, "Ingrese el id del alojamiento a deshabilitar", "Deshabilitar alojamiento", JOptionPane.QUESTION_MESSAGE);

			if(idAlojamiento != null)
			{
				String resp = alohandes.deshabilitarAlojamiento(Long.parseLong(idAlojamiento), "A_HABITACIONVIVIENDAUNIVERSITARIA");
				panelDatos.actualizarInterfaz(resp);
			}
			else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}

		}
		catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void deshabilitarHabitacionHostal(){
		
		try{

			String idAlojamiento = JOptionPane.showInputDialog (this, "Ingrese el id del alojamiento a deshabilitar", "Deshabilitar alojamiento", JOptionPane.QUESTION_MESSAGE);

			if(idAlojamiento != null)
			{
				String resp = alohandes.deshabilitarAlojamiento(Long.parseLong(idAlojamiento), "A_HABITACIONHOSTAL");
				panelDatos.actualizarInterfaz(resp);
			}
			else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}

		}
		catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	 


	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Alohandes
	 */
	public void mostrarLogAlohandes ()
	{
		mostrarArchivo ("alohandes.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogAlohandes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("alohandes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de alohandes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

    

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
    // /**
    //  * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
    //  * @param lista - La lista con los tipos de bebida
    //  * @return La cadena con una líea para cada tipo de bebida recibido
    //  */
    // private String listarTiposBebida(List<VOTipoBebida> lista) 
    // {
    // 	String resp = "Los tipos de bebida existentes son:\n";
    // 	int i = 1;
    //     for (VOTipoBebida tb : lista)
    //     {
    //     	resp += i++ + ". " + tb.toString() + "\n";
    //     }
    //     return resp;
	// }

    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
     */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazAlohandesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}

	/**
     * Genera una cadena de caracteres con la lista de parejas de objetos recibida: una línea por cada pareja
     * @param lista - La lista con las parejas (ID_PROVEEDOR, DINERO_RECIBIDO_ANIO_ACTUAL, DINERO_RECIBIDO_ANIO_CORRIDO)
     * @return La cadena con una línea para cada pareja recibido
     */
    private String listarDineroRecibido (List<Object[]> lista) 
    {
    	String resp = "El dinero recibido por cada recibido por los proveedores este anio y el anio corrido es:\n";
    	int i = 1;
        for (Object [] tupla : lista)
        {
			String id_proveedor = (String) tupla [0];
			int dinero_recibido_anio_actual = ((BigDecimal) tupla[1]).intValue();
			int dinero_recibido_anio_corrido = ((BigDecimal) tupla[2]).intValue();

	        String resp1 = i++ + ". " + "[";
			resp1 += "id_proveedor:" + id_proveedor + ", ";
			resp1 += "dinero_recibido_anio_actual: " + dinero_recibido_anio_actual + ", ";
			resp1 += "dinero_recibido_anio_corrido: " + dinero_recibido_anio_corrido;
	        resp1 += "]";
	        resp += resp1 + "\n";
        }
        return resp;
	}

	/**
     * Genera una cadena de caracteres con la lista de parejas de objetos recibida: una línea por cada pareja
     * @param lista - La lista con las parejas (SELECT A_ALOJAMIENTO.ID, A_ALOJAMIENTO.UBICACION, A_ALOJAMIENTO.DURACIONMIN, A_ALOJAMIENTO.COSTO)
     * @return La cadena con una línea para cada pareja recibido
     */
    private String listarTop20Ofertas (List<Object []> lista) 
    {
    	String resp = "";
    	int i = 1;
        for (Object [] tupla : lista)
        {
	        String resp1 = i++ + ". " + "[";
			resp1 += "id_alojamiento:" + tupla[0] + ", ";
			resp1 += "ubicacion: " + tupla[1] + ", ";
			resp1 += "duracion_min: " + tupla[2] + ", ";
			resp1 += "costo: " + tupla[3];
	        resp1 += "]";
	        resp += resp1 + "\n";
        }
        return resp;
	}

    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazAlohandesApp interfaz = new InterfazAlohandesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
