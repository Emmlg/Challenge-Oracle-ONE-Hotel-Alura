package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.jdbc.Controller.HuespedController;
import com.alura.jdbc.Controller.ReservaContoller;
import com.alura.jdbc.Controller.ValidarString;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	
	//--------------
	private ValidarString validarString = new  ValidarString() ;
	private ReservaContoller reservaContoller = new ReservaContoller();;
	private HuespedController huespedController = new HuespedController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {

       
        
        
        //cargar datos de la tabla huesped
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				limpiarTablahusped();
			    cargarTablaHuesped();
				limpiarTablaReserva();
				cargarTablaReservas();
				
			}
		});
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 328, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		 cargarTablaReservas();
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		 cargarTablaHuesped();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					boolean validar = validarString.isNumeric(txtBuscar.getText()) ||	
							validarString.isLetter(txtBuscar.getText());
				//buscar
				if(!validar) {
					JOptionPane.showMessageDialog(null, "Verifique sus datos", "Aviso!",
							JOptionPane.WARNING_MESSAGE);
					
					return;
				}
					
				if(validarString.isNumeric(txtBuscar.getText())) {
					System.out.println("soy numero "+Integer.valueOf(txtBuscar.getText()));
					limpiarTablaReserva();
					cargarTablaReservas(Integer.valueOf(txtBuscar.getText()));
				}
				
				if(validarString.isLetter(txtBuscar.getText())) {
					System.out.println("soy ape "+txtBuscar.getText());
					limpiarTablahusped();
					cargarTablaHuesped(txtBuscar.getText());			
				}
				
				
			}
				
			
		});
		

		
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				// to - do notificar si guardo los registros
				
			if(!tieneFilaElegida(tbHuespedes)) {
				modificarHuesped();	
				limpiarTablahusped();
				cargarTablaHuesped();
			}
			if(!tieneFilaElegida(tbReservas)) {
				modificarReseras();
				limpiarTablaReserva();
				cargarTablaReservas();
				
			}
					

				
					
					
					
				
				
			}
			
			
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// to- do notificar si se elimina algun registro
				
				if(!tieneFilaElegida(tbHuespedes)) {
					// eliminarHuesped();
					eliminarHuesped();
					limpiarTablahusped();
					cargarTablaHuesped();
					// actualizar tabla seundaria
					limpiarTablaReserva();
					cargarTablaReservas();
				}
				if(!tieneFilaElegida(tbReservas)) {
					//eliminarReserva();
					eliminarReserva();
					limpiarTablaReserva();
					cargarTablaReservas();
					// actualizar tabla seundaria
					limpiarTablahusped();
					cargarTablaHuesped();
					
				}
			}
		});
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	   private boolean tieneFilaElegida(JTable tabla) {
	        return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	    }

	    private void modificarReseras() {
	        if (tieneFilaElegida(tbReservas)) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije la celda a modificar");
	            return;
	        }

	        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString() ) ;
	                    Date FechEntrada = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
	                    Date FechSalida = (Date) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
	                    Integer valor = Integer.valueOf( modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
	                    String formpago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
	                    int filasModificadas;
	                    try {
	                    	filasModificadas = this.reservaContoller.modificar(id, FechEntrada, FechSalida,valor,formpago);
						} catch (SQLException e) {
				
							throw new RuntimeException(e);						
						}
	                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	                }, () -> JOptionPane.showMessageDialog(this,"Por favor, elije una reserva"));
	    }

	    private void modificarHuesped() {
	        if (tieneFilaElegida(tbHuespedes)) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije la celda a modificar");
	            return;
	        }

	        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id_reservas = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString() ) ;
	                    String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
	                    String ape = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
	                    Date fechNaci = (Date) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
	                    String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
	                    String telegono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
	                    Integer id =Integer.valueOf( modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
	                    
	                    int filasModificadas;
	                    try {
	                    	filasModificadas = this.huespedController.modificar(id_reservas, nombre,ape,fechNaci,nacionalidad,telegono,id);
						} catch (SQLException e) {
				
							throw new RuntimeException(e);						
						}
	                    JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
	                }, () -> JOptionPane.showMessageDialog(this,"Por favor, elije un huesped"));
	    }
	
	    private void eliminarReserva() {
	    	
	        if (tieneFilaElegida(tbReservas)) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }

	        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf( modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

	                    int cantidadEliminada;
	                    try {
							cantidadEliminada = this.reservaContoller.eliminar(id);
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}

	                    modelo.removeRow(tbReservas.getSelectedRow());

	                    JOptionPane.showMessageDialog(this,cantidadEliminada + " reservas eliminadas con éxito!");
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	    }

	    private void eliminarHuesped() {
	    	
	        if (tieneFilaElegida(tbHuespedes)) {
	            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
	            return;
	        }

	        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	                .ifPresentOrElse(fila -> {
	                    Integer id = Integer.valueOf( modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

	                    int cantidadEliminada;
	                    try {
							cantidadEliminada = this.huespedController.eliminar(id);
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}

	                    modelo.removeRow(tbHuespedes.getSelectedRow());

	                    JOptionPane.showMessageDialog(this,cantidadEliminada + " huesped eliminado con éxito!");
	                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	    }

	
private void cargarTablaHuesped() {
	 var reserva = huespedController.listar();
     
     reserva.forEach(reghuesped -> modeloHuesped.addRow(new Object[] {
    		 reghuesped.getId_huesped(),
    		 reghuesped.getNombre(),
    		 reghuesped.getApellido(),
    		 reghuesped.getFechNaci(),
    		 reghuesped.getNacionalidad(),
    		 reghuesped.getTelefono(),
    		 reghuesped.getId()
  		}));
		
	}

private void cargarTablaReservas() {
	
		 var reserva = reservaContoller.listar();
	           
            reserva.forEach(regreserva -> modelo.addRow(new Object[] {
           		regreserva.getId_reservas(),
           		regreserva.getFechEntrada(),
           		regreserva.getFechSalidad(),
           		regreserva.getValor(),
           		regreserva.getFormpago()
         		}));
		
	}

	private void limpiarTablaReserva() {
		modelo.getDataVector().clear();
		
	}
	private void limpiarTablahusped() {
		modeloHuesped.getDataVector().clear();
		
	}

private void cargarTablaHuesped(String ape) {
	 var reserva = huespedController.listar(ape);
    
    reserva.forEach(reghuesped -> modeloHuesped.addRow(new Object[] {
   		 reghuesped.getId_huesped(),
   		 reghuesped.getNombre(),
   		 reghuesped.getApellido(),
   		 reghuesped.getFechNaci(),
   		 reghuesped.getNacionalidad(),
   		 reghuesped.getTelefono(),
   		 reghuesped.getId()
 		}));
		
	}

private void cargarTablaReservas(Integer id_reserva) {
	
		 var reserva = reservaContoller.listar(id_reserva);
	           
           reserva.forEach(regreserva -> modelo.addRow(new Object[] {
          		regreserva.getId_reservas(),
          		regreserva.getFechEntrada(),
          		regreserva.getFechSalidad(),
          		regreserva.getValor(),
          		regreserva.getFormpago()
        		}));
		
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
