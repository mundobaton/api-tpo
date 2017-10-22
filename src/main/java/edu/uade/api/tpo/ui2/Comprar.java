package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;

public class Comprar implements ActionListener{

	private JFrame frmComprarApi;
	JPanel panelDatosDelPago;
	private JTextField txtNumeroDeTarjeta;
	private JTextField txtCodigoDeSeguridad;
	private JTextField txtFechaDeVencimiento;
	private JTextField txtNombreDelTitular;
	private JTextField txtNumeroDeCuenta;
	private JTextField txtCuil;
	private String opcion;
	private JTextField txtOferta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comprar window = new Comprar();
					window.frmComprarApi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Comprar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmComprarApi = new JFrame();
		frmComprarApi.setTitle("Comprar | API");
		frmComprarApi.setBounds(100, 100, 500, 470);
		frmComprarApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmComprarApi.setJMenuBar(menuBar);
		
		JMenu mnMiCuenta = new JMenu("Mi Cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta Corriente");
		mnMiCuenta.add(mntmCuentaCorriente);
		
		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		
		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		
		JMenu mnPublicaciones = new JMenu("Publicaciones");
		menuBar.add(mnPublicaciones);
		
		JMenuItem mntmNuevaPublicacion = new JMenuItem("Nueva Publicación");
		mnPublicaciones.add(mntmNuevaPublicacion);
		
		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmComprarApi.getContentPane().setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Publicación > Comprar > Medio de Pago");
		lblBreadcrumb.setBounds(10, 20, 480, 16);
		frmComprarApi.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 500, 12);
		frmComprarApi.getContentPane().add(separator);
		
		JPanel panelSeleccionarMP = new JPanel();
	    panelSeleccionarMP.setBounds(10, 60, 480, 120);
	    frmComprarApi.getContentPane().add(panelSeleccionarMP);
	    panelSeleccionarMP.setLayout(null);
	    
	    JLabel lblSeleccionarMP = new JLabel("Seleccione el medio de pago:");
	    lblSeleccionarMP.setBounds(0, 0, 480, 16);
	    panelSeleccionarMP.add(lblSeleccionarMP);
	    
	    ButtonGroup group = new ButtonGroup();
	    /**
	     * Mostrar los radio button que se correspondan con los medios
	     * de pago disponibles para esta publicacion
	     * 
	     * IMPORTANTE: no olvida que al menos un radio button debe estar siempre
	     * seleccionado. Seleccionar siempre el primero de la lista, para que
	     * sea mas sencillo.
	     * 
	     * NO OLVIDAR: panelDatosDelPago.show(panelDatosDelPago, "nombre_del_primer_medio_disponible"); => efectivo, tarjeta_credito, transferencia_bancaria.
	     */
	    JRadioButton rdbtnEfectivo = new JRadioButton("Efectivo");
	    rdbtnEfectivo.setSelected(true);
	    rdbtnEfectivo.setBounds(0, 20, 480, 25);
	    rdbtnEfectivo.setName("efectivo");
	    panelSeleccionarMP.add(rdbtnEfectivo);
	    group.add(rdbtnEfectivo);
	    rdbtnEfectivo.addActionListener(this);
	    
	    JRadioButton rdbtnTarjetaDeCredito = new JRadioButton("Tarjeta de Crédito (MercadoPago)");
	    rdbtnTarjetaDeCredito.setBounds(0, 50, 480, 25);
	    rdbtnTarjetaDeCredito.setName("tarjeta_credito");
	    panelSeleccionarMP.add(rdbtnTarjetaDeCredito);
	    group.add(rdbtnTarjetaDeCredito);
	    rdbtnTarjetaDeCredito.addActionListener(this);
	    
	    JRadioButton rdbtnTransferenciaBancaria = new JRadioButton("Transferencia Bancaria");
	    rdbtnTransferenciaBancaria.setBounds(0, 80, 480, 25);
	    rdbtnTransferenciaBancaria.setName("transferencia_bancaria");
	    panelSeleccionarMP.add(rdbtnTransferenciaBancaria);
	    group.add(rdbtnTransferenciaBancaria);
	    rdbtnTransferenciaBancaria.addActionListener(this);
		
		panelDatosDelPago = new JPanel();
		panelDatosDelPago.setBounds(10, 180, 480, 200);
		frmComprarApi.getContentPane().add(panelDatosDelPago);
		panelDatosDelPago.setLayout(new CardLayout(0, 0));
		
		JPanel panelEfectivo = new JPanel();
		panelDatosDelPago.add(panelEfectivo, "efectivo");
		panelEfectivo.setLayout(null);
		/**
		 * IMPORTANTE: el panelEfectivo solo debe ser visible si la publicacion
		 * es una SUBASTA. De lo contrario hay que ocultarlo.
		 * 
		 * panelEfectivo.setVisible(publicacion es subasta);
		 */
		
		JLabel lblPrecioInicial = new JLabel("Precio inicial:");
		lblPrecioInicial.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblPrecioInicial.setBounds(0, 0, 100, 22);
		panelEfectivo.add(lblPrecioInicial);
		
		/**
		 * Indicar el precio inicial de la publicacion
		 */
		JLabel lblPrecioInicialPublicacion = new JLabel("$1000");
		lblPrecioInicialPublicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblPrecioInicialPublicacion.setBounds(100, 0, 380, 22);
		panelEfectivo.add(lblPrecioInicialPublicacion);
		
		JLabel lblOferta = new JLabel("Oferta:");
		lblOferta.setBounds(0, 30, 60, 30);
		panelEfectivo.add(lblOferta);
		
		txtOferta = new JTextField();
		txtOferta.setBounds(55, 30, 300, 30);
		panelEfectivo.add(txtOferta);
		txtOferta.setColumns(10);
		
		JLabel lblPesosArgentinos = new JLabel("pesos argentinos");
		lblPesosArgentinos.setBounds(359, 30, 115, 30);
		panelEfectivo.add(lblPesosArgentinos);
		
		JPanel panelTarjetaCredito = new JPanel();
		panelDatosDelPago.add(panelTarjetaCredito, "tarjeta_credito");
		panelTarjetaCredito.setLayout(null);
		
		JLabel lblDatosDelPago = new JLabel("Datos del Pago:");
		lblDatosDelPago.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblDatosDelPago.setBounds(0, 0, 480, 22);
		panelTarjetaCredito.add(lblDatosDelPago);
		
		JLabel lblNumeroDeTarjeta = new JLabel("Número de tarjeta");
		lblNumeroDeTarjeta.setBounds(0, 30, 480, 16);
		panelTarjetaCredito.add(lblNumeroDeTarjeta);
		
		txtNumeroDeTarjeta = new JTextField();
		txtNumeroDeTarjeta.setBounds(0, 50, 480, 30);
		panelTarjetaCredito.add(txtNumeroDeTarjeta);
		txtNumeroDeTarjeta.setColumns(10);
		
		JLabel lblCodigoDeSeguridad = new JLabel("Código de Seguridad");
		lblCodigoDeSeguridad.setBounds(0, 90, 235, 16);
		panelTarjetaCredito.add(lblCodigoDeSeguridad);
		
		txtCodigoDeSeguridad = new JTextField();
		txtCodigoDeSeguridad.setBounds(0, 110, 235, 30);
		panelTarjetaCredito.add(txtCodigoDeSeguridad);
		txtCodigoDeSeguridad.setColumns(10);
		
		JLabel lblFechaDeVencimiento = new JLabel("Fecha de Vencimiento");
		lblFechaDeVencimiento.setBounds(245, 90, 235, 16);
		panelTarjetaCredito.add(lblFechaDeVencimiento);
		
		txtFechaDeVencimiento = new JTextField();
		txtFechaDeVencimiento.setBounds(245, 110, 235, 30);
		panelTarjetaCredito.add(txtFechaDeVencimiento);
		txtFechaDeVencimiento.setColumns(10);
		
		JLabel lblNombreDelTitular = new JLabel("Nombre del titular (como figura en la tarjeta)");
		lblNombreDelTitular.setBounds(0, 150, 480, 16);
		panelTarjetaCredito.add(lblNombreDelTitular);
		
		txtNombreDelTitular = new JTextField();
		txtNombreDelTitular.setBounds(0, 170, 480, 30);
		panelTarjetaCredito.add(txtNombreDelTitular);
		txtNombreDelTitular.setColumns(10);
		
		JPanel panelTransferenciaBancaria = new JPanel();
		panelDatosDelPago.add(panelTransferenciaBancaria, "transferencia_bancaria");
		panelTransferenciaBancaria.setLayout(null);
		
		JLabel lblDatosDelPago_1 = new JLabel("Datos del Pago:");
		lblDatosDelPago_1.setBounds(0, 0, 480, 22);
		lblDatosDelPago_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		panelTransferenciaBancaria.add(lblDatosDelPago_1);
		
		JLabel lblNumeroDeCuenta = new JLabel("Número de cuenta (CBU)");
		lblNumeroDeCuenta.setBounds(0, 30, 480, 16);
		panelTransferenciaBancaria.add(lblNumeroDeCuenta);
		
		txtNumeroDeCuenta = new JTextField();
		txtNumeroDeCuenta.setBounds(0, 50, 480, 30);
		panelTransferenciaBancaria.add(txtNumeroDeCuenta);
		txtNumeroDeCuenta.setColumns(10);
		
		JLabel lblCuil = new JLabel("CUIL/CUIT");
		lblCuil.setBounds(0, 90, 480, 16);
		panelTransferenciaBancaria.add(lblCuil);
		
		txtCuil = new JTextField();
		txtCuil.setBounds(0, 110, 480, 30);
		panelTransferenciaBancaria.add(txtCuil);
		txtCuil.setColumns(10);

		JButton btnConfirmar = new JButton("Confirmar");
	    btnConfirmar.setBounds(370, 390, 120, 30);
	    frmComprarApi.getContentPane().add(btnConfirmar);
	    
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBounds(250, 390, 120, 30);
	    frmComprarApi.getContentPane().add(btnCancelar);
	    

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JRadioButton){
            JRadioButton btn = (JRadioButton) e.getSource();
            System.out.println(btn.getName());

            CardLayout cl = (CardLayout)(panelDatosDelPago.getLayout());
            cl.show(panelDatosDelPago, btn.getName());
        }		
	}
	

}
